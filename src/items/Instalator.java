package items;

import java.util.ArrayList;

public class Instalator {
    public static ArrayList<Item> items = new ArrayList<>();
    public Instalator(){
        //pistols
        new Item( WeaponType.pistol, Brand.Glock, "18", "9×19mm PARA", 900);
        new Item( WeaponType.pistol, Brand.Glock, "17", "9×19mm PARA" ,750);
        new Item( WeaponType.pistol, Brand.HK, "USP", "9×19mm PARA" ,800);
        new Item( WeaponType.pistol, Brand.FN, "Five-seveN", "5,7×28 mm" ,1400);
        new Item( WeaponType.pistol, Brand.SAW, "500", ".500 MAGNUM" ,1950);

        //PM's
        new Item( WeaponType.PM, Brand.HK, "MP5", "9×19mm PARA", 1700);
        new Item( WeaponType.PM, Brand.HK, "MP7A1", "4,6mm" ,1500);
        new Item( WeaponType.PM, Brand.KRISS, "Vector GEN.2", "9×19 PARA // .45 ACP" ,1250);
        new Item( WeaponType.PM, Brand.HK, "UMP45 // 9", "9×19mm PARA // .45 APC" ,1500);
        new Item( WeaponType.PM, Brand.FN, "P90", "5,7×28mm" ,1500);

        //Assault Rifle
        new Item( WeaponType.Assault_Rifle, Brand.Daniel_Defense, "MK18", "5,56×45mm NATO", 3200);
        new Item( WeaponType.Assault_Rifle, Brand.AR, "M4", "5,56×45mm NATO", 1);
        new Item( WeaponType.Assault_Rifle, Brand.AR, "M4", "5,56×45mm NATO", 1);
        new Item( WeaponType.Assault_Rifle, Brand.AR, "M4", "5,56×45mm NATO", 1);
        new Item( WeaponType.Assault_Rifle, Brand.AR, "M4", "5,56×45mm NATO", 1);

        //Sniper Rifle
        new Item( WeaponType.Sniper_Rifle, Brand.HK, "...", "...", 1);
        new Item( WeaponType.Sniper_Rifle, Brand.HK, "...", "...", 1);
        new Item( WeaponType.Sniper_Rifle, Brand.HK, "...", "...", 1);
        new Item( WeaponType.Sniper_Rifle, Brand.HK, "...", "...", 1);
        new Item( WeaponType.Sniper_Rifle, Brand.HK, "...", "...", 1);

        //DMR's
        new Item( WeaponType.DMR, Brand.Knights_Armament, "M110", "7,62×51mm", 5800);
        new Item( WeaponType.DMR, Brand.HK, "G28", "5,56×45mm", 1);
        new Item( WeaponType.DMR, Brand.HK, "G28", "5,56×45mm", 1);
        new Item( WeaponType.DMR, Brand.HK, "G28", "5,56×45mm", 1);
        new Item( WeaponType.DMR, Brand.HK, "G28", "5,56×45mm", 1);
    }


}
