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
        
        // Increment size by 1
        size++;
    }
    
    // Insert new neighbour if live between existing neighbours at this position
    public void addNeighbourInBetween(String name, int pos)
    {
        // Previous neighbour and next neighbour are null to initialise
        NeighbourNode neighbour = new NeighbourNode(name, 0, null, null);
        
        // If user selected the first position, set this neighbour
        // at the start of the list
        if(pos == 1)
        {
            addNeighbourAtStart(name);
        }
        
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
                }
                
                // Move on to the next neighbour
                pointer = pointer.getNextNeighbour();
            }
            
            // Increment size by 1
            size++;
        }
    }
    
    // Delete the neighbour at this position due to destruction or empty house
    public void deleteNeighbourAtPosition(int pos)
    {
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
                    }

                    // Set pointer to the next neighbour
                    pointer = pointer.getNextNeighbour();
                }
            }
        }
        
        else
        {
            System.out.println("The street is empty.");
        }
    }
    
    // Display only to this neighbour by position
    public void displayNeighbour(int pos)
    {
        int found = 0;
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
                    System.out.println("This neighbour has been found.");
                    pointer.printNeighbour();
                    found++;
                }

                // Set pointer to the next neighbour
                pointer = pointer.getNextNeighbour();
            }
            
            if(found == 0)
            {
                System.out.println("This neighbour has not existed or found.");
            }
        }
        
        else
        {
            System.out.println("The street is empty.");
        }
    }
    
    // Display all neighbours
    public void displayAll()
    {
        // If list is not empty, do this function
        if(!isEmpty())
        {
            // Move to the next neighbour if the selection is not at the start
            NeighbourNode pointer = start;
            // Print the start neighbour's details
            pointer.printNeighbour();
            // Set pointer to start's next neighbour
            pointer = start.getNextNeighbour();

            // Continue this loop until the next neighbour is not null
            while(pointer.getNextNeighbour() != null)
            {
                // Print the next neighbour's details
                pointer.printNeighbour();
                // Set pointer as this pointer's next neighbour
                pointer = pointer.getNextNeighbour();
            }
        }
        
        else
        {
            System.out.println("The street is empty.");
        }
    }
    
    // Change neighbour's detail by name, duration of living or all
    // Select the neighbour's position to confirm change
    public void changeNeighbourDetail(int pos)
    {
        Scanner choice = new Scanner(System.in);
        // Initialise choose as integer
        int choose;
        
        // Do this function while choose is not selected in the option
        do{
            // Choosing the options to change details by name, weeks or both.
            // Otherwise, cancel change
            System.out.println("Choice of changing details.");
            System.out.println("(0) Cancel Change");
            System.out.println("(1) Name of Owner");
            System.out.println("(2) Number of Weeks on Staying");
            System.out.println("(3) Both");

            // Set choose as Scanner's object as integer
            choose = choice.nextInt();
        } 
        while(choose != 0 || choose != 1 || choose != 2 || choose != 3);
        
        
        
    }
}
