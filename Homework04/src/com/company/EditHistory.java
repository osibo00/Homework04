package com.company;

import java.util.EmptyStackException;
import java.util.Scanner;

public class EditHistory {

    private static SizedStack<String> editHistoryStack = new SizedStack<>(10);

    public EditHistory() {
        editHistoryStack = new SizedStack<>(10);
    }

    public static void startEditHistory(Scanner scanner) {
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            boolean quit = false;

            if (input.equals("quit")) {
                System.out.println(editHistoryStack.peek());
                quit = true;
            } else if (input.equals("c")) {
                EditHistory.copyInput(input);
                continue;
            } else if (input.equals("d")) {
                EditHistory.deleteInput(input);
                continue;
            } else if (input.equals("u")) {
                EditHistory.undoInput(input);
                continue;
            } else if (!input.equals("c") && !input.equals("d") && !input.equals("u")) {
                EditHistory.saveInput(input);
                continue;
            }

            if (quit) {
                break;
            }
        }
    }

    public static void saveInput(String input) {
        editHistoryStack.push(input);
        System.out.println("Saved: " + editHistoryStack);
    }

    public static void copyInput(String input) {
        String copy = input.toLowerCase();
        if (copy.equals("c")) {
            editHistoryStack.push(editHistoryStack.peek());
            System.out.println("Your edit history list: " + editHistoryStack);
        }
    }

    public static void deleteInput(String input) {
        String delete = input.toLowerCase();
        if (delete.equals("d")) {
            try {
                editHistoryStack.pop();
            } catch (EmptyStackException e) {
                System.out.println("Nothing to delete from your Edit History.");
            }
        }
    }
    // in order to use undo, the .poped value has to be pushed to another stack or list first in deleteInput
    public static void undoInput(String input) {
        String undo = input.toLowerCase();
        if (undo.equals("u")) {
            editHistoryStack.push(editHistoryStack.pop());
        }
    }
}
