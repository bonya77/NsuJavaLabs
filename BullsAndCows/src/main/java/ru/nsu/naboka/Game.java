package ru.nsu.naboka;
import java.beans.BeanDescriptor;
import java.util.*;
import java.io.*;

public class Game implements GameInterface{
    private String hidden_number;
    private String suggested_number;
    private int bulls;
    private int cows;
    private int attempts;

    Game(){
        bulls = 0;
        cows = 0;
        attempts = 10;
    }

    private void generateNumber(){

        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        int counter = 0;

        while(counter < 4){
            int num = random.nextInt(10);

            if(sb.indexOf(String.valueOf(num)) == -1){
                sb.append(num);
                counter++;
            }
            else {
                continue;
            }

        }
        this.hidden_number = sb.toString();
        System.out.println(hidden_number);
    }

    private void askNumber(){
        System.out.printf("print suggested number: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            suggested_number = reader.readLine();
        }

        catch(IOException e){
            System.out.println(e);
            suggested_number = "";
            return;
        }
        if(suggested_number == "q"){
            System.out.printf("end of game");
            return;
        }


        else if(suggested_number.length() != 4){
            System.out.println("Number should be a four-digit");
            return;
        }

        System.out.println(suggested_number);
    }

    private void findBullsAndCows() {
        bulls = 0;
        cows = 0;
        for (int i = 0; i < hidden_number.length(); i++) {
            for (int j = 0; j < suggested_number.length(); j++) {
                if(hidden_number.charAt(i) == suggested_number.charAt(j) && (i == j)){
                    bulls++;
                }
                else if (hidden_number.charAt(i) == suggested_number.charAt(j)) {
                    cows++;
                }

            }
        }
        System.out.println("number of cows: " + cows);
        System.out.println("nmber of bulls: " + bulls);
    }


    public void playingGame(){
        generateNumber();

        while(attempts != 0){
            askNumber();
            findBullsAndCows();
            if(bulls == 4){
                System.out.println("You guessed right!");
                return;
            }
            attempts--;
            System.out.println("attemptes left: " + attempts);
        }
    }

}
