package com.games.menu;


import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Scanner;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;

public class Menu<T> {

    private String title;
    private String promptMessage;
    private List<T> items;
    private BiPredicate<Integer, List<T>> LIES_IN_MENU_RANGE = (choice, menuItems) -> choice > 0 && choice <= menuItems.size();
    private Predicate<String> IS_NUMERIC = StringUtils::isNumeric;

    public Menu(String title, String promptMessage, T... items) {
        this.title = title;
        this.promptMessage = promptMessage;
        this.items = Stream.of(items).collect(toList());
    }

    public T readUserChoice() {
        printMenu();
        return getUserChoice();
    }

    private T getUserChoice() {
        Scanner scanner = new Scanner(System.in, "UTF-8");

        String line = scanner.nextLine();
        if (IS_NUMERIC.test(line)) {
            int choice = parseInt(line);
            if (LIES_IN_MENU_RANGE.test(choice, items)) {
                return items.get(choice-1);
            }
        }
        System.out.println("Incorrect choice! Please enter a correct choice");
        return readUserChoice();
    }

    private void printMenu() {
        System.out.println(title.toUpperCase());
        IntStream.rangeClosed(1, items.size())
                .mapToObj(this::formatToItem)
                .forEach(this::print);
        System.out.println(promptMessage);
    }

    private String formatToItem(int i) {
        return String.format("%2s. %s", i, items.get(i - 1));
    }

    private void print(String message) {
        System.out.println(message);
    }
}