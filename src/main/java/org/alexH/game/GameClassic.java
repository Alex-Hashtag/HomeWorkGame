package org.alexH.game;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.alexH.Categories;
import org.alexH.exceptions.QuestionNotFoundException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static org.alexH.globalFunctions.GlobalFunctions.println;

public class GameClassic extends Game
{
    @Override
    public void start(Categories category) throws IOException, QuestionNotFoundException, InterruptedException
    {
        JsonArray jsonArray = getJsonArray("questions" + File.separator + category.label);
        ArrayList<String> questions = initQuestions(15, jsonArray);
        ArrayList<String> answers;

        boolean correct = true;
        for (String item: questions)
        {
            answers = getQuestionAnswers(item, jsonArray);
            correct = displayQuestion(item, answers);

            if (correct)
                yourAnswerWasCorrect();
            else
            {
                yourAnswerWasWrong(answers.getLast());
                println("Better luck next time");
                break;
            }
        }
    }

    @Override
    public ArrayList<String> initQuestions(int numberOfQuestions, JsonArray jsonArray)
    {
        Random random = new Random(4153);
        ArrayList<String> questions = new ArrayList<>();
        int totalQuestions = jsonArray.size();

        for (int i = 0; i<numberOfQuestions; i++)
        {
            int randomIndex = random.nextInt(totalQuestions);
            JsonObject questionObject = jsonArray.get(randomIndex).getAsJsonObject();
            questions.add(questionObject.get("question").getAsString());
        }

        return questions;
    }
}
