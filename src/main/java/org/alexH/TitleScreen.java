package org.alexH;

import org.alexH.exceptions.QuestionNotFoundException;
import org.alexH.game.Game;
import org.alexH.game.GameClassic;
import org.alexH.game.GameEndless;
import org.alexH.game.GameShowdown;

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

        Game game = printGameModes(scanner);

        printOptions();

        byte selected = 0;

        println("Choose a Category, by entering it's number: ");
        selected = scanner.nextByte();

        game.init(Categories.getCategoryByIndex(selected));


    }

    private static void printOptions() throws InterruptedException
    {
        Categories[] categories = Categories.values();
        for (int i = 0; i < categories.length - 1; i++)
        {
            println((i + 1) + ". " + categories[i]);
            Thread.sleep(300);
        }
        println("\u001B[3m" + "8. " + categories[7] + "\u001B[0m");
    }

    private static Game printGameModes(Scanner scanner) throws InterruptedException
    {
        println("1. Classic");
        Thread.sleep(300);
        println("2. Showdown");
        Thread.sleep(300);
        println("3. Endless");
        Thread.sleep(300);
        println("Choose a game mode: ");

        String option = scanner.nextLine();

        return switch (option)
        {
            case "1", "1.", "1. ", "1. Classic", "Classic" -> new GameClassic();
            case "2", "2.", "2. ", "2. Showdown", "Showdown" -> new GameShowdown();
            case "3", "3.", "3. ", "3. Endless", "Endless" -> new GameEndless();
            default -> throw new IllegalStateException("Unexpected value: " + option);
        };
    }
}
