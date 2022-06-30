package com.github.gitvoytenko.bmtesttask;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args){

        String line;
        File inputFile = new File("src/main/resources/input.txt");
        File outputFile = new File("src/main/resources/output.txt");

        ArrayList<String> queries = new ArrayList<>();

        try{
            BufferedReader br = new BufferedReader(
                    new FileReader(inputFile)
            );
            BufferedWriter wr = new BufferedWriter(
                    new FileWriter(outputFile)
            );

            while (br.ready()){
                line = br.readLine();
               queries.add(line);
            }

            br.close();

        String[] infoInTheCup;
        ArrayList<String[]> bid = new ArrayList<>();
        ArrayList<String[]> ask = new ArrayList<>();

        for(String str : queries){
            infoInTheCup = str.split(",");
            if(infoInTheCup[0].equals("u")) {
                if(infoInTheCup[3].equals("bid")){
                    bid.add(infoInTheCup);
                    bid.sort((o1, o2) -> Integer.parseInt(o2[1]) - Integer.parseInt(o1[1]));
                }
                if(infoInTheCup[3].equals("ask")){
                    ask.add(infoInTheCup);
                    ask.sort(Comparator.comparingInt(o -> Integer.parseInt(o[1])));
                }
            }
            if(infoInTheCup[0].equals("q")){
                if(infoInTheCup[1].equals("best_bid")){
                    wr.write(bid.get(0)[1] + ", " + bid.get(0)[2]);
                    wr.newLine();
                }
                if(infoInTheCup[1].equals("best_ask")){
                    wr.write(ask.get(0)[1] + ", " + ask.get(0)[2]);
                    wr.newLine();
                }
            }
            if(infoInTheCup[0].equals("o")){
                wr.write(infoInTheCup[2]);
                wr.newLine();
            }
        }

        wr.close();

        } catch (FileNotFoundException e){
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("Input/Output exception " + e);
        }

    }
}
