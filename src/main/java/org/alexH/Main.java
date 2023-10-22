package org.alexH;

import org.alexH.exceptions.QuestionNotFoundException;

import java.io.IOException;
import java.util.Scanner;

import static org.alexH.globalFunctions.GlobalFunctions.println;


public class Main
{
    public static void main(String[] args) throws InterruptedException, IOException, QuestionNotFoundException
    {

        System.setProperty("org.jline.terminal.dumb", "true");

        Scanner scanner = new Scanner(System.in);

        byte selected = 0;

        println("Current Working Directory: " + System.getProperty("user.dir"));


        TitleScreen titleScreen = new TitleScreen();

        titleScreen.printTitleScreen();
    }
}