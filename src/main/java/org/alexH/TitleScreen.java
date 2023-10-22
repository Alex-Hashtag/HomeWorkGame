package org.alexH;

import org.alexH.exceptions.QuestionNotFoundException;
import org.alexH.game.Game;
import org.alexH.game.GameClassic;

import java.io.IOException;
import java.util.Scanner;

import static org.alexH.globalFunctions.GlobalFunctions.println;


public class TitleScreen
{
    private static final String redConsole = "\u001B[31m";
    private static final String greenConsole = "\u001B[32m";
    private static final String defaultConsole = "\u001B[0m";
    public void printTitleScreen() throws InterruptedException, IOException, QuestionNotFoundException
    {

        System.setProperty("org.jline.terminal.dumb", "true");
        Scanner scanner = new Scanner(System.in);

        printOptions();

        byte selected = 0;

        println("Choose a Category, by entering it's number: ");
        selected = scanner.nextByte();

        Game game = new GameClassic();

        game.init(Categories.getCategoryByIndex(selected));


    }

    private static void printOptions() throws InterruptedException
    {
        Categories[] categories = Categories.values();
        for (int i = 0; i < categories.length; i++)
        {
            println((i + 1) + ". " + categories[i]);
            Thread.sleep(300);
        }
    }
}
