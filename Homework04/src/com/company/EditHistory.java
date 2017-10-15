package com.company;

import java.util.EmptyStackException;
import java.util.Scanner;

public class EditHistory {

    private static SizedStack<String> editHistoryStack = new SizedStack<>(10);
    private static SizedStack<String> tempStorageStack = new SizedStack<>(10);
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public EditHistory() {
        editHistoryStack = new SizedStack<>(10);
        tempStorageStack = new SizedStack<>(10);
    }

    public static void startEditHistory(Scanner scanner) {
        System.out.println("Thank you for using Edit History.");
        System.out.println("Use keyword " + ANSI_GREEN + "copy " + ANSI_RESET + "to copy your last entry. Use keyword " + ANSI_GREEN + "delete " + ANSI_RESET + "to delete your last entry.");
        System.out.println("Use keyword " +  ANSI_GREEN + "undo " + ANSI_RESET + "to undo your last deletion.");
        System.out.println("Please start typing.");
        System.out.println();
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            boolean quit = false;

            if (input.equals("quit")) {
                System.out.println("Your edit history " + editHistoryStack + " is saved.");
                quit = true;
            } else if (input.equals("copy")) {
                EditHistory.copyInput(input);
                continue;
            } else if (input.equals("delete")) {
                EditHistory.deleteInput(input);
                continue;
            } else if (input.equals("undo")) {
                EditHistory.undoInput(input);
                continue;
            } else if (!input.equals("copy") && !input.equals("delete") && !input.equals("undo")) {
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
        if (copy.equals("copy")) {
            editHistoryStack.push(editHistoryStack.peek());
            System.out.println("Your edit history list: " + editHistoryStack);
        }
    }

    public static void deleteInput(String input) {
        String delete = input.toLowerCase();
        if (delete.equals("delete")) {
            try {
                System.out.println("Deleted: " + tempStorageStack.push(editHistoryStack.pop()));
            } catch (EmptyStackException e) {
                System.out.println("Nothing to delete from your Edit History.");
            }
        }
    }

    public static void undoInput(String input) {
        String undo = input.toLowerCase();
        if (undo.equals("undo")) {
            try {
                System.out.println("Undone: " + editHistoryStack.push(tempStorageStack.pop()));
            } catch (EmptyStackException e) {
                System.out.println("Nothing to undo.");
            }
        }
    }
}
