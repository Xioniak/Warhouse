package items;




public class Item {
    int price;
    String model, kaliber;
    items.WeaponType weaponType;
    Brand brand;
    public Item(items.WeaponType weaponType, Brand brand, String model, String kaliber, int price) {
        this.weaponType = weaponType;
        this.price = price;
        this.brand = brand;
        this.model = model;
        this.kaliber = kaliber;
        items.Instalator.items.add(this);
    }


    public int getPrice() {
        return price;
    }

    public Brand getBrand() {
        return brand;
    }

    public items.WeaponType getWeaponType() {
        return weaponType;
    }

    public String getModel() {
        return model;
    }

    public String getKaliber() {
        return kaliber;
    }

}
