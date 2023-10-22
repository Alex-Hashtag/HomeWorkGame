package org.alexH.game;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.alexH.Categories;
import org.alexH.exceptions.QuestionNotFoundException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static org.alexH.globalFunctions.GlobalFunctions.println;


public class GameEndless extends Game
{
    @Override
    public void init(Categories category) throws IOException, QuestionNotFoundException, InterruptedException
    {
        JsonArray jsonArray = getJsonArray("questions" + File.separator + category.label);
        ArrayList<String> questions = initQuestions(1, jsonArray);
        ArrayList<String> answers;
        Scanner scanner = new Scanner(System.in);

        boolean correct = true;
        int i = 0;
        while (true)
        {
            answers = getQuestionAnswers(questions.get(i), jsonArray);
            correct = displayQuestion(questions.get(i), answers);

            if (correct)
            {
                yourAnswerWasCorrect();
                println("Press enter to continue....");
                scanner.nextLine();
            }
            else
            {
                yourAnswerWasWrong(answers.getLast());
                println("Better luck next time");
                break;
            }
            addQuestion(questions, jsonArray);
            i++;
        }

        printScore(i + 1);

    }

    public void addQuestion(ArrayList<String> questions, JsonArray jsonArray)
    {
        Random random = new Random();
        int totalQuestions = jsonArray.size();

        while (true)
        {
            int randomIndex = random.nextInt(totalQuestions);
            JsonObject questionObject = jsonArray.get(randomIndex).getAsJsonObject();
            String questionString = questionObject.get("question").getAsString();
            if (!questions.contains(questionString))
            {
                questions.add(questionString);
                break;
            }

        }
    }


}
