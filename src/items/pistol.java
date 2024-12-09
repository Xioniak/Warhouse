package items;

public class pistol extends Gun {
    private boolean hasSilencer;

    public pistol(String name, String brand, String caliber, int magCap, double price, boolean hasSilencer) {
        super(name, brand, caliber, magCap, price);
        this.hasSilencer = hasSilencer;
    }

    public boolean hasSilencer() {
        return hasSilencer;
    }

    @Override
    public String toString() {
        return super.toString() + ", hasSilencer=" + hasSilencer;
    }
}
