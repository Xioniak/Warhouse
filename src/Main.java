import items.Brand;
import items.Instalator;
import items.Item;
import items.WeaponType;
import items.pistol;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static ArrayList<Item> items = Instalator.items;
    public static void main(String[] args) {
        new Instalator();
        Scanner input = new Scanner(System.in);
        try {
            ekran1(input);
        } catch (Exception e) {
            ekran1(input);
        }
    }

    public static void ekran1(Scanner input) {
        pistol glock = new pistol("17", "Glock", "9", 17, 3500.50, false);

        System.out.println("|WEAPON SHOP|");
        System.out.println("Select option:");
        System.out.println("[1] Shop");
        System.out.println("[2] Gunsmith");
        System.out.println("[0] Exit");
        System.out.println(glock.toString());

        int a = input.nextInt();
        input.nextLine();

        switch (a) {
            case 1:
                ekran2(input);
                break;
            case 2:
                System.out.println("When can you bring your weapon: 09.02.2024 ");
                ekran1(input);
                break;
            case 0:
                System.out.println("Thank you for coming, see you later");
                System.exit(0);
        }
    }
    public static void ekran2(Scanner input) {
        System.out.println("[1] Weapons");
        System.out.println("[2] Attachments");
        System.out.println("[0] Back");
        int a2 = input.nextInt();
        switch (a2) {
            case 1:
                int num = 1;
                int num2 = num;
                for (WeaponType type : WeaponType.values()) {
                    System.out.println("["+num+"] "+ type);
                    num++;
                }
                System.out.println("[0] Back");
                int a3 = input.nextInt();
                if (a3 == 0) {
                    ekran2(input);
                }
                WeaponType type2 = WeaponType.values()[a3-num2];
                int num3 = 1;
                int num4 = num3;
                ArrayList<Item> temp = new ArrayList<>();
                for (Item i : items) {
                    if (i.getWeaponType().equals(type2)) {
                        System.out.println("["+num3+"] Marka: "+i.getBrand() +" | Model: "+ i.getModel() +" | kaliber: "+i.getCaliber()+" | Price: " + i.getPrice() + " $" );
                        temp.add(i);
                        num3++;
                    }
                }
                System.out.println("[0] Back");
                int a4 = input.nextInt();
                if (a4 == 0) {
                    ekran2(input);
                } else {
                    Item item = temp.get(a4-num4);
                    System.out.println(item);
                    ekran2(input);
                }
            case 0:
                ekran1(input);
        }

    }

}