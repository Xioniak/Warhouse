package items;

public class Gun {
    private final String name;
    private final String brand;
    private final String caliber;
    private final int magCap;
    private final double price;

    /**
     * Konstruktor klasy Gun.
     * @param name nazwa broni
     * @param brand marka broni
     * @param caliber kaliber broni
     * @param magCap pojemność magazynka
     * @param price cena broni
     */
    public Gun(String name, String brand, String caliber, int magCap, double price) {
        this.name = name;
        this.brand = brand;
        this.caliber = caliber;
        this.magCap = magCap;
        this.price = price;
    }

    public String getName() { return name; }
    public String getBrand() { return brand; }
    public String getCaliber() { return caliber; }
    public int getMagCap() { return magCap; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return "Gun{name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", caliber=" + caliber +
                ", magazineCapacity=" + magCap +
                ", price=" + price + '}';
    }
}