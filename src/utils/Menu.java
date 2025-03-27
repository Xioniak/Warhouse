package utils;

import items.Utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private final String Title;
    private final Map<String, String> Options = new LinkedHashMap<>();
    private final Map<String, Runnable> Actions = new HashMap<>();

    public Menu(String Title) {
        this.Title = Title;
    }

    public void addOption(String key, String name, Runnable action) {
        Options.put(key, name);
        Actions.put(key, action);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Utils.print("--------| " + Title + " |--------");
            Utils.print("Select an option:");
            Options.forEach((key, name) -> Utils.print("[" + key + "] " + name));
            Utils.print("----------------------------");

            String choice = scanner.nextLine();
            if (choice.equals("0")) {
                System.exit(0);
            }

            Actions.getOrDefault(choice, () -> Utils.print("Invalid option")).run();
        }
    }
}