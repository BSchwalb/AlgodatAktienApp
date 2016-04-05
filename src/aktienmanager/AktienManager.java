/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aktienmanager;

import java.util.Scanner;

/**
 *
 * @author Super
 */
public class AktienManager {

    /**
     * @param args the command line arguments
     */
    
    static Scanner scanner;
    static MyHashMap stockHashMap;
    
    public static void printHeader() {
        System.out.println("     ...~~~°°° A K T I E N M A N A G E R °°°~~~...");
        System.out.println();
    }
    
    public static void mainMenu() {
        
        printHeader();
        
        System.out.println("  1 - List all stocks");
        System.out.println("  2 - Display stock");
        System.out.println("  3 - Add stock");
        System.out.println("  4 - Delete stock");
        System.out.println("  5 - Export stocks");
        
        System.out.println();
        System.out.print(" > ");
        
        char input = scanner.next().charAt(0);
        
        switch(input) {
            case '1':
                break;
            case '2': 
                break;
            case '3':
                stockHashMap.put(new Stock("MSFT", "Microsoft"));
                break;
            case '4': 
                break;
            case '5':
                break;
            default:
                break;
        }
        
    }
    
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        stockHashMap = new MyHashMap();
        
        mainMenu();
    }
    
}
