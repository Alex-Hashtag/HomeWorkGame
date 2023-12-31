package org.alexH.game;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.alexH.Categories;
import org.alexH.exceptions.QuestionNotFoundException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import static org.alexH.globalFunctions.GlobalFunctions.println;
import static org.alexH.globalFunctions.GlobalFunctions.scrollDown;


abstract public class Game
{
    private static final String redConsole = "\u001B[31m";
    private static final String greenConsole = "\u001B[32m";
    private static final String yellowConsole = "\u001B[33m";
    private static final String defaultConsole = "\u001B[0m";

    abstract public void init(Categories category) throws IOException, QuestionNotFoundException, InterruptedException;

    public ArrayList<String> initQuestions(int numberOfQuestions, JsonArray jsonArray)
    {
        Random random = new Random();
        ArrayList<String> questions = new ArrayList<>();
        int totalQuestions = jsonArray.size();

        for (int i = 0; i < numberOfQuestions; i++)
        {
            int randomIndex = random.nextInt(totalQuestions);
            JsonObject questionObject = jsonArray.get(randomIndex).getAsJsonObject();
            String questionString = questionObject.get("question").getAsString();
            if (!questions.contains(questionString))
                questions.add(questionString);
            else
                i--;

        }

        return questions;
    }

    public JsonArray getJsonArray(String path) throws FileNotFoundException
    {
        Reader reader = new FileReader(path);
        return new Gson().fromJson(reader, JsonObject.class).getAsJsonArray("questions");
    }

    public ArrayList<String> getQuestionAnswers(String question, JsonArray questions) throws QuestionNotFoundException
    {
        for (int i = 0; i < questions.size(); i++)
        {
            JsonObject questionObj = questions.get(i).getAsJsonObject();
            if (questionObj.get("question").getAsString().equals(question))
            {
                JsonArray answers = questionObj.get("incorrect_answers").getAsJsonArray();
                answers.add(questionObj.get("correct_answer"));

                ArrayList<String> answerArrayList = new ArrayList<>();
                for (int j = 0; j < answers.size(); j++)
                {
                    answerArrayList.add(answers.get(j).getAsString());
                }


                return answerArrayList;
            }
        }

        throw new QuestionNotFoundException("Question not found: " + question);
    }

    public boolean displayQuestion(String question, ArrayList<String> answers) throws IOException, InterruptedException
    {

        scrollDown();

        ArrayList<String> randomizedAnswers = new ArrayList<>();
        randomizedAnswers.add(answers.get(0));
        randomizedAnswers.add(answers.get(1));
        randomizedAnswers.add(answers.get(2));
        randomizedAnswers.add(answers.get(3));

        Collections.shuffle(randomizedAnswers);

        println(greenConsole + question + defaultConsole);
        println("a) " + randomizedAnswers.get(0));
        println("b) " + randomizedAnswers.get(1));
        println("c) " + randomizedAnswers.get(2));
        println("d) " + randomizedAnswers.get(3));

        println();
        println("What do you think is the correct answer?");

        Scanner scanner = new Scanner(System.in);
        String answered = scanner.nextLine();

        switch (answered)
        {
            case "a", "a)" -> answered = randomizedAnswers.get(0);
            case "b", "b)" -> answered = randomizedAnswers.get(1);
            case "c", "c)" -> answered = randomizedAnswers.get(2);
            case "d", "d)" -> answered = randomizedAnswers.get(3);
        }

        return answered.equals(answers.getLast());
    }

    public void yourAnswerWasCorrect()
    {
        println(greenConsole + "Congratulations! You answer was correct, you can continue to the next question" + defaultConsole);
    }

    public void yourAnswerWasWrong(String correctAnswer)
    {
        println(redConsole + "I'm sorry :( You answer was wrong, the correct answer was \"" + correctAnswer + "\" you can no longer play the game" + defaultConsole);
    }

    public void youWon()
    {
        println(greenConsole + "Congratulations, you won! Your prize is that you can now play again!" + defaultConsole);
    }

    public void printScore(int score)
    {
        println(yellowConsole + "You ended with a score of " + score + " points" + defaultConsole);
    }

}
