package org.experis.wishlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WishList {
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
                    System.out.println("Insert the present's name: ");
                    name= scanner.nextLine();
                    System.out.println("Insert the present's price: ");
                    price = Double.parseDouble(scanner.nextLine());
                    //aggiungere il regalo inserito nella lista
                    presents.add(new Present(name, price));
                    //stampare il recap della lista
                    System.out.println("The list contains " + presents.size() + " presents");
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
}
