package org.experis.wishlist;

import java.io.File;
import java.io.FileNotFoundException;
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
        //dichiarazione arraylist e caricamento del file all'apertura
        ArrayList<Present> presents = readFile();
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
        scanner.close();
        //ordina la lista
        Collections.sort(presents);
        //stampa la lista ordinata
        System.out.println("Ordered presents list: ");
        for(Present present : presents){
            System.out.println(present);
        }


        //controllare se il salvataggio va a buon fine
        if(!writeToFile(presents)){
            System.out.println("Error writing to file");
        }

    }

    //metodo per scrivere sul file
    private static boolean writeToFile(ArrayList<Present> presents){

        File presentFile = new File (FILE_PATH);
        boolean written = false;
        try(FileWriter writer = new FileWriter(presentFile, false)){
            for (Present present : presents){
                //punto e virgola come delitatore invece di virgola semplice
                writer.write(present.getName() + ";" + present.getPrice() + "\n");
            }
            written = true;
        }catch (IOException e){
            System.out.println("Unable to write to file " + e.getMessage());
        }
        return written;
    }

    //metodo per leggere il file
    private static ArrayList<Present> readFile(){
        //inizializza una lista vuota
        ArrayList<Present> presents = new ArrayList<>();
        //crea un oggetto file
        File presentFile = new File(FILE_PATH);
        //apre il file per la lettura
        try(Scanner scanner = new Scanner(presentFile)){
            //legge il file linea per linea
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                //divide la linea in parti (CSV)
                String[] parts = line.split(";");
                //controlla se la linea è formattata correttamente
                if(parts.length == 2){
                    //estrae ed analizza i dati
                    String name = parts[0];
                    double price = Double.parseDouble(parts[1]);
                    // crea un nuovo Present obj e lo aggiunge alla lista
                    presents.add(new Present(name, price));
                }
            }
            //handling delle eccezioni
        }catch (FileNotFoundException e){
            System.out.println("Unable to read file : " + e.getMessage());
        }
        //ritorna la lista
        return presents;
    }
}
