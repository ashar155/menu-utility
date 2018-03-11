package com.games.menu;

/**
 * Created by SHIELD on 3/11/2018.
 */
public class App {
    public static void main(String[] args) {
        Menu menu = new Menu("Test menu", "Please enter your selection: ", "Item1", "Item2", "Item3");
        System.out.println(menu.readUserChoice());
    }
}
