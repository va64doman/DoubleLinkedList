/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doublelinkedlistapp;

/**
 *
 * @author Van Do
 */

// Using user's input to select options to change neighbour's detail
import java.util.*;
// This class will contain implemented double linked list
// This allows to hold the structure of the neighbours
public class DoubleLinkedList 
{
    // Start is a variable that contains the first neighbour on the list
    private NeighbourNode start;
    // End is a variable that contains the last neighbour on the list
    private NeighbourNode end;
    // The size of the linked list
    private int size;
    // The choice for selecting the option to change neighbour's details
    // It is allowing to get easy access without causing infinite loop
    private int choose;
    // This integer is used when the neighbour was found or not
    private int found;
    // Initialise scanner to ensure most methods have scanner to be enable
    // to input data in the variable
    Scanner scan = new Scanner(System.in);
    // Null constructor to initialise the list
    public DoubleLinkedList()
    {
        // Initialise start and end to null
        start = null;
        end = null;
        // Initialise size to 0
        size = 0;
    }
    // Check if the list is empty by checking the start is null
    public boolean isEmpty()
    {
        return start == null;
    }
    // Return the size of the list
    public int getSize()
    {
        return size;
    }
    // Add neighbour at the beginning of the list
    public void addNeighbourAtStart(String name)
    {
        // Previous neighbour and next neighbour are null to initialise
        NeighbourNode neighbour = new NeighbourNode(name, 0, null, null);
        // Check if the beginning is null, then make this neighbour the beginning and end
        if(start == null)
        {
            start = neighbour;
            end = start;
        }
        // Otherwise, make the beginning neighbour second and the new neighbour first
        else
        {
            start.setPrevious(neighbour);
            neighbour.setNext(start);
            // Change new front neighbour
            start = neighbour;
        }
        // Display all neighbours after added
        displayAll();
        // Increment size by 1
        size++;
    }
    // Add neighbour at the end of the list
    public void addNeighbourAtEnd(String name)
    {
        // Previous neighbour and next neighbour are null to initialise
        NeighbourNode neighbour = new NeighbourNode(name, 0, null, null);
        // Check if the beginning is null, then make this neighbour the beginning and end
        if(start == null)
        {
            start = neighbour;
            end = start;
        }
        // Otherwise, make the end neighbour second-last and the new neighbour last
        else
        {
            neighbour.setPrevious(end);
            end.setNext(neighbour);
            // Change new end neighbour
            end = neighbour;
        }
        // Display all neighbours after added
        displayAll();
        // Increment size by 1
        size++;
    }
    // Insert new neighbour if live between existing neighbours at this position
    public void addNeighbourInBetween(String name, int pos)
    {
        found = 0;
        // Previous neighbour and next neighbour are null to initialise
        NeighbourNode neighbour = new NeighbourNode(name, 0, null, null);
        // If user selected the first position, set this neighbour
        // at the start of the list
        if(pos == 1)
        {
            addNeighbourAtStart(name);
        }
        // If position not 1, do this function
        else
        {
            // Initialise pointer as start to search through the list
            // Until the position has been reached
            NeighbourNode pointer = start;
            // Keep going until count reached to size, count initialises to 2
            // because the condition will be selected if the user has not
            // selected the first neighbour
            for(int count = 2; count <= size; count++)
            {
                // If count is equal to position, set this position
                // and make the neighbour from this position as previous
                // neighbour from new neighbour and the neighbour after the
                // previous neighbour as next neighbour from new neighbour
                // for example, 1 and 3 are current neighbours
                // if 2 is added between them, the list will be 1 2 3
                if(count == pos)
                {
                    // Initialise tempting using the next neighbour from pointer
                    NeighbourNode tempting = pointer.getNextNeighbour();
                    // Set the pointer's next neighbour as this new neighbour
                    pointer.setNext(neighbour);
                    // Set new neighbour's previous neighbour as the pointer
                    neighbour.setPrevious(pointer);
                    // Set new neighbour's next neighbour as tempting neighbour
                    // Since the new neighbour is between the pointer and 
                    // tempting neighbour
                    neighbour.setNext(tempting);
                    // Set tempting's previous neighbour as new neighbour
                    tempting.setPrevious(neighbour);
                    found++;
                    // Increment size by 1
                    size++;
                }
                // Move on to the next neighbour
                pointer = pointer.getNextNeighbour();
            }
            // If there are no position found, display this message
            if(found == 0)
            {
                System.out.println("Unsuccessfully added. Please check size of neighbour.");
            }
            // Display all neighbours after added
            displayAll();
        }
    }
    // Delete the neighbour at this position due to destruction or empty house
    public void deleteNeighbourAtPosition(int pos)
    {
        found = 0;
        // If list is not empty, do this function
        if(!isEmpty())
        {
            // If user selected the first neighbour
            if(pos == 1)
            {
                // If there is only one neighbour, make start and end to null and
                // size to 0
                if(size == 1)
                {
                    start = null;
                    end = null;
                    size = 0;
                }
                // If there are more than 1, do this function
                else
                {
                    // Get the next neighbour after starting neighbour
                    start = start.getNextNeighbour();
                    // Set the next neighbour's previous neighbour to null
                    start.setPrevious(null);
                    // Decrement size by 1
                    size--;
                }
            }
            // If user selected the end of the neighbour
            else if(pos == size)
            {
                // Set end neighbour by the old end neighbour's previous neighbour
                end = end.getPreviousNeighbour();
                // Set new end neighbour's next neighbour to null
                end.setNext(null);
                // Decrement size by 1
                size--;
            }
            // If position is in between the start and end
            else
            {
                // Move to the next neighbour if the selection is not at the start
                NeighbourNode pointer = start.getNextNeighbour();

                // Keep going until count reaches size
                for(int count = 2; count <= size; count++)
                {
                    // If count is equal to position
                    if(count == pos)
                    {
                        // Initialise previous by pointer's previous neighbour
                        NeighbourNode previous = pointer.getPreviousNeighbour();
                        // Initialise next by pointer's next neighbour
                        NeighbourNode next = pointer.getNextNeighbour();
                        // Set previous neighbour's next neighbour by the deleted
                        // neighbour's next neighbour
                        previous.setNext(next);
                        // Set next neighbour's previous neighbour by the deleted
                        // neighbour's previous neighbour
                        next.setPrevious(previous);
                        // Decrement size by 1
                        size--;
                        found++;
                        System.out.println("Delete neighbour successfully.");
                    }
                    // To avoid null exception, check if count is not equal to size
                    // The next neighbour at the end is null
                    // This condition is only used in deletion of node
                    if(count != size)
                    {
                        // Set pointer to the next neighbour
                        pointer = pointer.getNextNeighbour();
                    }
                }
                // If there are no position found, display this message
                if(found == 0)
                {
                    System.out.println("This neighbour has not existed or found.");
                }
            }
        }
        // If so, display this message
        else
        {
            System.out.println("The street is empty.");
        }
        // Display all neighbours after deleted
        displayAll();
    }
    // Display only to this neighbour by position
    public void displayNeighbour(int pos)
    {
        found = 0;
        // If list is not empty, do this function
        if(!isEmpty())
        {
            // Initialise pointer at start of list
            NeighbourNode pointer = start;
            // Keep going until count reaches size
            for(int count = 1; count <= size; count++)
            {
                // If count is equal to position
                if(count == pos)
                {
                    System.out.println(pointer.printNeighbour());
                    found++;
                }
                // Set pointer to the next neighbour
                pointer = pointer.getNextNeighbour();
            }
            // If there are no position found, display this message
            if(found == 0)
            {
                System.out.println("This neighbour has not existed or found.");
            }
        }
        // If size of list is 0, display this message
        else
        {
            System.out.println("The street is empty.");
        }
    }
    // Display all neighbours
    public void displayAll()
    {
        // Display titles
        System.out.println();
        System.out.println("Displaying all neighbours");
        // If list is not empty, do this function
        if(!isEmpty())
        {
            // Move to the next neighbour if the selection is not at the start
            NeighbourNode pointer = start;
            // Print the start neighbour's details
             System.out.println(pointer.printNeighbour());
            // Set pointer to start's next neighbour
            pointer = start.getNextNeighbour();
            // Continue this loop until the next neighbour is not null
            while(pointer != null)
            {
                // Print the next neighbour's details
                System.out.println(pointer.printNeighbour());
                // Set pointer as this pointer's next neighbour
                pointer = pointer.getNextNeighbour();
            }
        }
        // If so, display this message
        else
        {
            System.out.println("The street is empty.");
        }
    }
    // Change neighbour's detail by name, duration of living or all
    // Select the neighbour's position to confirm change
    public void changeNeighbourDetail(int pos)
    {
        // Use this boolean if the user has not selected one of the options
        boolean selected = false;
        if(!isEmpty())
        {
            // Do this function while choose is not selected in the option
            do
            {
                // Display option for changing data
                System.out.println(displayOption());
                // Set choose as Scanner's object as integer
                choose = handleInt();
                // Choose a selection for editing neighbour's details
                switch(choose)
                {
                    // If choose is 0, exit this method
                    case 0:
                        System.out.println("Cancelling change.");
                        selected = true;
                        break;
                    // If choose is 1, change this neighbour's name
                    case 1:
                        // Initialise found to 0
                        found = 0;
                        // Initialise the pointer as the start of the list
                        NeighbourNode pointer = start;
                        // Keep going until count reaches to size
                        for(int count = 1; count <= size; count++)
                        {
                            // If count is equal to position, display this neighbour's old name
                            if(count == pos)
                            {
                                System.out.println("This neighbour's old name: " + pointer.getName());
                                // Increment found by 1
                                found++;
                            }
                            // Set pointer as pointer's next neighbour
                            pointer = pointer.getNextNeighbour();
                        }
                        // If position has been found, proceed to this step
                        if(found > 0)
                        {
                            // Re-initialise pointer as starting neighbour
                            pointer = start;
                            // User inputs new name for this neighbour
                            System.out.print("Enter new name of owner: ");
                            // Initialise owner as user's input
                            String owner = scan.next();
                            // As it already been found, set the name for this neighbour
                            // as its new name
                            for(int count = 1; count <= size; count++)
                            {
                                if(count == pos)
                                {
                                    pointer.setName(owner);
                                    displayNeighbour(pos);
                                }
                                pointer = pointer.getNextNeighbour();
                            }
                        }
                        // If neighbour is not found, display this message
                        else
                        {
                            System.out.println("This neighbour has not been existed or found.");
                        }
                        selected = true;
                        break;
                    // If choose is 2, change this neighbour's duration of staying by weeks
                    case 2:
                        // Initialise found to 0
                        found = 0;
                        // Initialise the pointer as the start of the list
                        pointer = start;
                        // Keep going until count reaches to size
                        for(int count = 1; count <= size; count++)
                        {
                            // If count is equal to position, display this neighbour's old name
                            if(count == pos)
                            {
                                System.out.println("This neighbour's old duration of stay in weeks: " + pointer.getWeek());
                                // Increment found by 1
                                found++;
                            }
                            // Set pointer as pointer's next neighbour
                            pointer = pointer.getNextNeighbour();
                        }
                        // If position has been found, proceed to this step
                        if(found > 0)
                        {
                            // Re-initialise pointer as starting neighbour
                            pointer = start;
                            // User inputs new weeks for this neighbour
                            System.out.print("Enter current duration of stay in weeks: ");
                            // Initialise week as user's input
                            int week = handleInt();
                            // As it already been found, set the week for this neighbour
                            // as its new week
                            for(int count = 1; count <= size; count++)
                            {
                                if(count == pos)
                                {
                                    pointer.setWeek(week);
                                    displayNeighbour(pos);
                                }
                                pointer = pointer.getNextNeighbour();
                            }
                        }
                        // If neighbour is not found, display this message
                        else
                        {
                            System.out.println("This neighbour has not been existed or found.");
                        }
                        selected = true;
                        break;
                    // If choose is 3, change this neighbour's name and weeks
                    case 3:
                        // Initialise found to 0
                        found = 0;
                        // Initialise the pointer as the start of the list
                        pointer = start;
                        // Keep going until count reaches to size
                        for(int count = 1; count <= size; count++)
                        {
                            // If count is equal to position, display this neighbour's old name
                            if(count == pos)
                            {
                                // Display this neighbour's old record
                                System.out.println("Old Record for This Neighbour");
                                System.out.println(pointer.printNeighbour());
                                // Increment found by 1
                                found++;
                            }
                            // Set pointer as pointer's next neighbour
                            pointer = pointer.getNextNeighbour();
                        }
                        // If position has been found, proceed to this step
                        if(found > 0)
                        {
                            // Re-initialise pointer as starting neighbour
                            pointer = start;
                            // Neighbour's name
                            // User inputs new name for this neighbour
                            System.out.print("Enter new name of owner: ");
                            // Initialise owner as user's input
                            String owner = scan.next();
                            // Neighbour's duration of stay
                            // User inputs new weeks for this neighbour
                            System.out.print("Enter current duration of stay in weeks: ");
                            // Initialise week as user's input
                            int week = handleInt();
                            // As it already been found, set the week for this neighbour
                            // as its new week
                            for(int count = 1; count <= size; count++)
                            {
                                // Change both details and display new details
                                if(count == pos)
                                {
                                    pointer.setName(owner);
                                    pointer.setWeek(week);
                                    displayNeighbour(pos);
                                }
                                pointer = pointer.getNextNeighbour();
                            }
                        }
                        // If neighbour is not found, display this message
                        else
                        {
                            System.out.println("This neighbour has not been existed or found.");
                        }
                        selected = true;
                        break;
                    // Else, display invalid input message
                    default:
                        System.out.println("Invalid input. Try again.");
                        break; 
                }
            } 
            while(!selected);
        }
        // If empty, display message
        else
        {
            System.out.println("The street is empty.");
        }
    }
    // Display all options
    public String displayOption()
    {
        // Build up the option easily without typing string in lengthy line
        StringBuilder option = new StringBuilder();
        // Choosing the options to change details by name, weeks or both.
        // Otherwise, cancel change
        option.append("Choice of changing details.").append("\n");
        option.append("(0) Cancel Change").append("\n");
        option.append("(1) Name of Owner").append("\n");
        option.append("(2) Number of Weeks on Staying").append("\n");
        option.append("(3) Both");
        // Return the stringbuilder as string
        return option.toString();
    }
    // Handle integer inputs
    public int handleInt()
    {
        int input = 0;
        // Assuming this continue in a loop until the user has entered the integer
        boolean loop = true;
        // Continue this loop until the user has entered the input correctly
        while(loop)
        {
            // Try and catch error if the user has not entered the integer
            try
            {
                input = scan.nextInt();
                loop = false;
            }
            catch(InputMismatchException e)
            {
                System.out.println("Try again. Wrong input.");
                scan.nextLine();
            }
        }
        return input;
    }
}