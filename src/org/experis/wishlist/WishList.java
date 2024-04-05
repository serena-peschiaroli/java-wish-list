package org.experis.wishlist;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WishList {
    //aggiungo il file path
    private final static String FILE_PATH = "./resources/list.txt";
    public static void main(String[] args) {
        //apre lo scanner
        Scanner scanner = new Scanner(System.in);
        //dichiarazione arraylist
        ArrayList<Present> presents = new ArrayList<>();
        //variabili per l'inserimento del regalo
        String name;
        double price;
        //flag da inserire nel loop while
        boolean exit = false;
        //loop dove chiedere input finchè l'utente non esce


        while (!exit) {
            System.out.println("Would you like to insert a present in the list? Y/N");
            String choice = scanner.nextLine().trim().toUpperCase();
            switch (choice){
                case "Y":

                    try{
                        System.out.println("Insert the present's name: ");
                        name= scanner.nextLine();
                        System.out.println("Insert the present's price: ");
                        price = Double.parseDouble(scanner.nextLine());
                        //aggiungere il regalo inserito nella lista
                        presents.add(new Present(name, price));
                        //stampare il recap della lista
                        System.out.println("The list contains " + presents.size() + " presents");
                    }catch(NumberFormatException e) {
                        System.out.println("Error: The price must be a number. Please try again.");
                    }catch (IllegalArgumentException e){
                        System.out.println("Error: " + e.getMessage() + " Please try again.");
                    }

                    break;
                case "N":
                    //se "N", esce dal programma
                    exit = true;
                    break;
                default:
                    //scelta non valida
                    System.out.println("Invalid choice, please try again");
                    break;


            }
        }
        //ordina la lista
        Collections.sort(presents);
        //stampa la lista ordinata
        System.out.println("Ordered presents list: ");
        for(Present present : presents){
            System.out.println(present);
        }

    }

    //metodo per scrivere sul file
    private static boolean writeToFile(ArrayList<Present> presents){

        File presentFile = new File (FILE_PATH);
        boolean written = false;
        try(FileWriter writer = new FileWriter(presentFile, false)){
            for (Present present : presents){
                writer.write(present.getName() + "," + present.getPrice() + "\n");
            }
            written = true;
        }catch (IOException e){
            System.out.println("Unable to write to file " + e.getMessage());
        }
        return written;
    }
}
