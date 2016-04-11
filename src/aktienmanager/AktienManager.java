/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aktienmanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Super
 */
public class AktienManager {

    /**
     * @param args the command line arguments
     */
    private static Scanner scanner;
    private static StockHashMap stockHashMap;

    public static void printHeader() {
        System.out.println("     ...~~~°°° A K T I E N M A N A G E R °°°~~~...");
        System.out.println();
    }

    public static void printHelp() {
        System.out.println("Invalid command!");
        System.out.println("Commands: ADD, DEL, IMPORT, SEARCH, PLOT, SAVE, LOAD, QUIT");
        /*
        System.out.println("####################################################");
        System.out.println("#                                                  #");
        System.out.println("#                    COMMANDS                      #");
        System.out.println("#                                                  #");
        System.out.println("####### ADD [ABBREVIATION] [FULLNAME] [SIN] ########");
        System.out.println("#  Adds a new share with the specified             #");
        System.out.println("#  abbreviation, the full name of the stock and    #");
        System.out.println("#  the security identification number              #");
        System.out.println("#                                                  #");
        System.out.println("################ DEL [ABBREVIATION] ################");
        System.out.println("#  Deletes the specified share                     #");
        System.out.println("#                                                  #");
        System.out.println("####################################################");
        System.out.println("#                                                  #");
        System.out.println("#                                                  #");
        System.out.println("#                                                  #");
        System.out.println("#                                                  #");
        System.out.println("#                                                  #");
        System.out.println("####################################################");
         */
    }

    public static boolean printMenu() {
        System.out.println("");
        System.out.println("Enter a command:");
        System.out.print(" > ");

        String input[] = scanner.nextLine().split(" ", 2);
        String command = input[0];
        String args[] = {};
        if (input.length > 1)
            args = input[1].split(" ");
        switch (command.toUpperCase()) {
            case "ADD":
                stockHashMap.put(new Stock(args[0], args[1], args[2]));
                break;
            case "DEL":
                stockHashMap.remove(args[0]);
                break;
            case "IMPORT":
                stockHashMap.get(args[0]).importHistory(args[1]);
                break;
            case "SEARCH":
                System.out.println(stockHashMap.get(args[0]).marketHistory[0]);
                break;
            case "PLOT":
                System.out.println(stockHashMap.get(args[0]).showMeYourMoves());
                break;
            case "SAVE":
                try (PrintWriter writer = new PrintWriter(new File(args[0]))) {
                    writer.print(stockHashMap);
                    writer.flush();
                } catch (IOException ex) {
                    Logger.getLogger(AktienManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "LOAD":
                try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
                    String lines[] = Files.readAllLines(Paths.get(args[0]), Charset.defaultCharset()).toArray(new String[0]);
                    stockHashMap = new StockHashMap(lines);
                } catch (IOException ex) {
                    Logger.getLogger(AktienManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "QUIT":
                System.out.println("Goodbye!");
                return false;
            case "DEBUG":
                stockHashMap.put(new Stock("msft", "MICROSOFT", "232152"));
                stockHashMap.get("msft").importHistory("msft.csv");
                break;
            case "SHOW":
                System.out.println(stockHashMap);
                break;
            case "DISPLAY":
                Stock stock = stockHashMap.get(args[0]);
                for (int i = 0; i < stock.marketHistory.length; i++) {
                    System.out.println(stock.marketHistory[i]);
                }
                break;
            default:
                printHelp();
        }
        return true;
    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        stockHashMap = new StockHashMap();

        printHeader();
        while (printMenu());
    }

}
