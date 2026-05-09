package ru.nsu.naboka;

import ru.nsu.naboka.swingView.SwingStarter;
import ru.nsu.naboka.terminalView.TerminalStarter;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import static java.lang.System.in;

public class Main {
    static void main() {
        InputStream inputStream = new BufferedInputStream(in);
        System.out.println("Choose view: \n 1 for swing \n 2 for terminal\n");
        Scanner scanner = new Scanner(new InputStreamReader(inputStream));
        int viewChecker = scanner.nextInt();

        if(viewChecker == 1){
            SwingStarter swingStarter = new SwingStarter();
            swingStarter.start();
        }
        if(viewChecker == 2){
            TerminalStarter terminalStarter = new TerminalStarter();
            terminalStarter.start();
        }

    }
}
