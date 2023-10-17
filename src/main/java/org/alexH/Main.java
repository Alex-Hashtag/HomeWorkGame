package org.alexH;

import org.alexH.exceptions.QuestionNotFoundException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.keymap.BindingReader;

import java.awt.event.KeyEvent;
import java.io.Console;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.*;
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