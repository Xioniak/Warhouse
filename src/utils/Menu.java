package utils;

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
            System.out.println("--------| " + Title + " |--------");
            System.out.println("Select an option:");
            Options.forEach((key, name) -> System.out.println("[" + key + "] " + name));
            System.out.println("-----------------------------");

            String choice = scanner.nextLine();
            if (choice.equals("0")) {
                System.exit(0);
            }

            Actions.getOrDefault(choice, () -> System.out.println("Invalid option")).run();
        }
    }
}