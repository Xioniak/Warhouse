import items.Instalator;
import items.Item;
import items.WeaponType;
import items.pistol;
import items.Utils;
import utils.Menu;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Item> items = Instalator.items;
    public static String globalLogin;



    /**
     * Metoda główna programu.
     * @param args argumenty wiersza poleceń
     */
    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);
        try {
            rnlScreen(input);
        } catch (Exception e) {
            rnlScreen(input);
        }
    }

    /**
     * Wyświetla pierwszy ekran menu.
     * @param input obiekt Scanner do odczytu danych wejściowych
     */
    public static void rnlScreen(Scanner input) {
        Menu rnlMenu = new Menu("WARHOUSE");
        rnlMenu.addOption("1", "Log In", () -> loginScreen(input));
        rnlMenu.addOption("2", "Register", () -> registerScreen(input));
        rnlMenu.addOption("0", "Exit", () -> System.exit(0));
        rnlMenu.start();
    }

    public static void registerScreen(Scanner input) {
        Utils.titleDivider("REGISTER");
        System.out.println("Login: ");
        String login = input.nextLine();
        System.out.println("Password: ");
        String password_org = input.nextLine();
        System.out.println("Repeat password: ");
        String password_rep = input.nextLine();
        if (password_org.equals(password_rep)) {
            if (Utils.registerUser(login, password_org)) {
                System.out.println("Welcome " + login);
                globalLogin = login;
                mainScreen(input);
            } else {
                System.out.println("User already exists!");
            }
        } else {
            System.out.println("Passwords do not match");
        }
    }

    public static void loginScreen(Scanner input) {
        Utils.titleDivider("LOG IN");
        System.out.println("Login: ");
        String login = input.nextLine();
        System.out.println("Password: ");
        String password = input.nextLine();
        if (Utils.loginUser(login, password)) {
            System.out.println("Welcome " + login);
            globalLogin = login;
            mainScreen(input);
        } else {
            System.out.println("Invalid login or password");
        }
    }

    public static void mainScreen(Scanner input) {
        // Tworzenie i konfiguracja klasy Menu.
        // Menu.addOption - dodanie opcji do menu, zawiera trzy parametry, pierwszy to klucz, drugi to nazwa opcji, trzeci to akcja do wykonania jeśli opcja zostanie wybrana
        // Menu.start - uruchomienie (wyświetlenie) menu
        // Opcja 0 zawsze zamyka program, jest ona opcją "schowaną" czyli nie widać jej na liście opcji

        Menu mainMenu = new Menu("WARHOUSE");

        mainMenu.addOption("1", "Shop", () -> shopScreen(input));
        mainMenu.addOption("2", "Gunsmith", () -> System.out.println("When can you bring your weapon: 09.02.2025"));
        mainMenu.addOption("3", "My Transactions", () -> transactionsScreen(input));
        mainMenu.addOption("0", "Exit", () -> System.exit(0));

        mainMenu.start();
    }

    public static void transactionsScreen(Scanner input) {
        Menu transactionsMenu = new Menu("TRANSACTIONS");
        transactionsMenu.addOption("1", "List transactions", () -> Utils.displayUserTransactions(globalLogin));
        transactionsMenu.addOption("2", "Back", () -> mainScreen(input));
        transactionsMenu.start();
    }

    /**
     * Wyświetla ekran menu sklepu.
     * @param input obiekt Scanner do odczytu danych wejściowych
     */
    public static void shopScreen(Scanner input) {
        Menu shopMenu = new Menu("SHOP");
        shopMenu.addOption("1", "Weapons", () -> System.out.println(Utils.getWeapon()));
        shopMenu.addOption("2", "Attachments", () -> System.out.println(Utils.getProducts()));
        shopMenu.addOption("3", "Back", () -> mainScreen(input));
        shopMenu.start();
    }
}