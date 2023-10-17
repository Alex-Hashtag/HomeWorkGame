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
import java.util.Scanner;

import static org.alexH.globalFunctions.GlobalFunctions.println;
import static org.alexH.globalFunctions.GlobalFunctions.scrollDown;


abstract public class Game
{
    private static final String redConsole = "\u001B[31m";
    private static final String greenConsole = "\u001B[32m";
    private static final String defaultConsole = "\u001B[0m";

    abstract public void start(Categories category) throws IOException, QuestionNotFoundException, InterruptedException;

    abstract public ArrayList<String> initQuestions(int numberOfQuestions, JsonArray jsonArray);

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
        ///new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
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

        if (answered.equals(answers.getFirst())) return true;
        return false;
    }
}
