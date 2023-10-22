package org.alexH.game;

import com.google.gson.JsonArray;
import org.alexH.Categories;
import org.alexH.exceptions.QuestionNotFoundException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.alexH.globalFunctions.GlobalFunctions.println;


public class GameClassic extends Game
{
    @Override
    public void init(Categories category) throws IOException, QuestionNotFoundException, InterruptedException
    {
        JsonArray jsonArray = getJsonArray("questions" + File.separator + category.label);
        ArrayList<String> questions = initQuestions(15, jsonArray);
        ArrayList<String> answers;
        Scanner scanner = new Scanner(System.in);

        boolean correct = true;
        for (String item : questions)
        {
            answers = getQuestionAnswers(item, jsonArray);
            correct = displayQuestion(item, answers);

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
                System.exit(0);
            }
        }

        youWon();

    }
}
