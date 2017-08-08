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

// This class will be the node of the double linked-list
// It holds neighbour's details
public class NeighbourNode {
    // Name of owner of resident
    private String name;
    // Number of weeks stayed at home
    // The reason is not year or month because people moved to different houses by different time
    private int week;
    // Previous neighbour and next neighbour
    private NeighbourNode previous;
    private NeighbourNode next;
    // Null Constructor to initialise the neighbour node
    public NeighbourNode()
    {
        // Initialise previous, next and name to null
        previous = null;
        next = null;
        name = null;
        // Initialise week to 0
        week = 0;
    }
    // Parameterized Constructor for storing new data by those values
    public NeighbourNode(String owner, int span, NeighbourNode before, NeighbourNode after)
    {
        name = owner;
        week = span;
        previous = before;
        next = after;
    }
    // Getters
    // Return name of the resident
    public String getName()
    {
        return name;
    }
    // Return the length of being resident of home by weeks
    public int getWeek()
    {
        return week;
    }
    // Return next neighbour
    public NeighbourNode getNextNeighbour()
    {
        return next;
    }
    // Return previous neighbour
    public NeighbourNode getPreviousNeighbour()
    {
        return previous;
    }
    // Setters
    // Change this resident's name if being replaced
    public void setName(String owner)
    {
        name = owner;
    }
    // Change the length of being resident of this home by extending weeks
    public void setWeek(int span)
    {
        week = span;
    }
    // Change the next neighbour if added between this home
    // and existing neighbour after this home
    public void setNext(NeighbourNode after)
    {
        next = after;
    }
    // Change the previous neighbour if added between this home
    // and existing neighbour before this home
    public void setPrevious(NeighbourNode before)
    {
        previous = before;
    }
    // Return all neighbour's details
    public String printNeighbour()
    {
        // Build up the info easily without typing string in lengthy line
        StringBuilder info = new StringBuilder();
        // Build up details
        info.append("Name of owner: ").append(name).append("\n");
        info.append("Number of weeks of staying: ").append(week).append("\n");
        // Display details
        return info.toString();
    }
}
