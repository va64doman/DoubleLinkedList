/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doublelinkedlistapp;

// This application required user input for entering neighbour's detail
// or required to edit, deleting details or see all neighbours
import java.util.*;

/**
 *
 * @author Van Do
 */

// This class will be using modular programming approach
// This way to make the code cleaner
public class Modular {
    // All methods must include scanner to allow user to input data
    private Scanner scan;
    // Initialise this object to access the methods from DoubleLinkedList class
    private DoubleLinkedList list;
    // Initialise choice to enable to choose to keep running application
    private int choice;
    // This is for handling integer input error
    private int input;
    // Display option to edit linked list
    public void selectOption(){
        // Initialise new Scanner and DoubleLinkedList object
        scan = new Scanner(System.in);
        list = new DoubleLinkedList();
        // Do this function while the user wants to keep using this app
        do{
            // Print out all options
            System.out.println(displayOption());
            // User choosing an option
            int option = handleInt();
            // Using switch case to decide multiple options
            switch(option){
                // If 1, insert neighbour at the beginning
                case 1:
                    System.out.println("Enter your name for owning the beginning neighbour.");
                    String name = scan.next();
                    list.addNeighbourAtStart(name);
                    break;
                // If 2, insert neighbour at the end
                case 2:
                    System.out.println("Enter your name for owning the end neighbour.");
                    name = scan.next();
                    list.addNeighbourAtEnd(name);
                    break;
                // If 3, insert neighbour at any position
                case 3:
                    System.out.println("Choose a position for this neighbour to place at.");
                    int position = handleInt();
                    System.out.println("Enter your name for owning this position.");
                    name = scan.next();
                    list.addNeighbourInBetween(name, position);
                    break;
                // If 4, delete neighbour at any position
                case 4:
                    System.out.println("Enter the position to delete this neighbour.");
                    position = handleInt();
                    list.deleteNeighbourAtPosition(position);
                    break;
                // If 5, display neighbour at any position
                case 5:
                    System.out.println("Enter the position to see this neighbour's details.");
                    position = handleInt();
                    list.displayNeighbour(position);
                    break;
                // If 6, display all neighbours' details
                case 6:
                    list.displayAll();
                    break;
                // If 7, change neighbour's details at any position
                case 7:
                    System.out.println("Enter the position to change any of this neighbour's details.");
                    position = handleInt();
                    list.changeNeighbourDetail(position);
                    break;
                // If 8, get the size of neighbour
                case 8:
                    System.out.println("Total number of neighbours: " + list.getSize());
                    break;
                // Else, display invalid input message
                default:
                    System.out.println("Invalid input.");
                    break;
            }
            // Display this message if wanted to continue this app
            System.out.println("Do you want to continue? Yes (1) or No (Any number)");
            choice = handleInt();
        }while(choice == 1);
    }
    // Display all options
    public String displayOption(){
        // Build up the option easily without typing string in lengthy line
        StringBuilder option = new StringBuilder();
        // Append all string into one
        option.append("Select options.").append("\n");
        option.append("1. Insert Neighbour at Beginning.").append("\n");
        option.append("2. Insert Neighbour at End.").append("\n");
        option.append("3. Insert Neighbour at Position.").append("\n");
        option.append("4. Delete Neighbour at Position.").append("\n");
        option.append("5. Display Neighbour at Position.").append("\n");
        option.append("6. Display All Neighbours.").append("\n");
        option.append("7. Change Neighbour's Details at Position.").append("\n");
        option.append("8. Get size in the street.");
        // Display options
        return option.toString();
    }
    // Handle integer inputs
    public int handleInt(){
        // Assuming this continue in a loop until the user has entered the integer
        boolean loop = true;
        // Continue this loop until the user has entered the input correctly
        while(loop){
            // Try and catch error if the user has not entered the integer
            try{
                input = scan.nextInt();
                loop = false;
            }catch(InputMismatchException e){
                System.out.println("Try again. Wrong input.");
                scan.nextLine();
            }
        }
        return input;
    }
}
