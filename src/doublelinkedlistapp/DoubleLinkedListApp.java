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

// This main class contains modular programming approach view for storing neighbours
// The reference is used from the colleagues and internet, however it is not reliable
// It does not give clear indication of the coding standard and function.
// Therefore, I would modify this code for the better and ergonomic approach
public class DoubleLinkedListApp 
{
    public static void main(String[] args) 
    {
        // Display the application from Modular class
        System.out.println("Neighbour Double Linked List");
        Modular module = new Modular();
        module.selectOption();
    }
}
