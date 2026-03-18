package ru.nsu.naboka;

import ru.nsu.naboka.exceptions.CalculatorException;
import ru.nsu.naboka.exceptions.ReaderException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    private BufferedReader bufReader;
    private final String[] args;
    private List<String> parts;
    private boolean endOfFile;

    Reader(String[] args){
        this.args = args;
        createBufReader();
    }

    void createBufReader(){
        if(args.length == 1){
            String FilePath = args[0];
            try{
                bufReader = new BufferedReader(new FileReader(FilePath));
            }
            catch (IOException e){
                System.out.println("File not found" + FilePath);
                throw new CalculatorException(e.getMessage());
            }
        } else if (args.length == 0) {
            //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            bufReader = new BufferedReader(new InputStreamReader(System.in));
        } else {
            throw new CalculatorException("Uncorrect number of parameters");
        }
    }

    boolean readNextLine() {
            try{
                String line = bufReader.readLine();
                if(line == null){
                    endOfFile = true;
                    return false;
                }
                line = line.trim();
                if(line.isEmpty()){
                    parts = new ArrayList<>();
                }
                else{
                    parts = List.of(line.split("\\s+"));
                }
                return true;
            }
            catch(IOException e){
                System.out.println("couldn't read the line");
                endOfFile = true;
                return false;
            }
    }

    public boolean isEndOfFile() {
        return endOfFile;
    }

    BufferedReader getBufReader(){
        return bufReader;
    }

    List<String> getParts(){
        return parts != null ? parts : new ArrayList<>();
    }

    public void close() {
        if (bufReader != null) {
            try {
                bufReader.close();
            } catch (IOException e) {
                System.out.println("Error closing reader: " + e.getMessage());
            }
        }
    }

}
