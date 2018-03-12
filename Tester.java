/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import java.util.Scanner;
import model.*;
/**
 *
 * @author atkins4440j
 */
public class Tester {
    public static void main(String[] args){
        Deck deck = new Deck();
        deck.shuffle();
        welcomeScreen();
    }
    public static void welcomeScreen(){
        System.out.println("-----WELCOME TO 21 GAME-----");
        System.out.println("---MAKE YOUR CHOICE BELOW---");
        System.out.println("----------------------------");
        System.out.println("---1-START A NEW GAME-------");
        System.out.println("---2-CREDITS BABEY----------");
        System.out.println("---3-QUIT THE GAME----------");
        System.out.println("----------------------------");
        System.out.println("-----------v1.0.0-----------");
        System.out.println("----------------------------");
        Scanner choice = new Scanner(System.in);
        int pick = choice.nextInt();
        switch(pick){
            case 1:
            case 2:
                System.out.println("MADE BY JACKSON ATKINS");
                System.out.println("PERIOD 1 AP COMPUTER SCIENCE A");
                System.out.println("LET IT BE KNOWN I AM NASA CODER");
                System.out.println();
                welcomeScreen();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Incorrect input!");
                break;
        }
    }
}
