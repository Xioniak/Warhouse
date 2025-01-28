package items;

/**
 * Klasa reprezentująca pistolet.
 */
public class pistol extends Gun {
    private boolean hasSilencer;

    /**
     * Konstruktor klasy pistol.
     * @param name nazwa pistoletu
     * @param brand marka pistoletu
     * @param caliber kaliber pistoletu
     * @param magCap pojemność magazynka
     * @param price cena pistoletu
     * @param hasSilencer czy pistolet ma tłumik
     */
    public pistol(String name, String brand, String caliber, int magCap, double price, boolean hasSilencer) {
        super(name, brand, caliber, magCap, price);
        this.hasSilencer = hasSilencer;
    }

    /**
     * Sprawdza, czy pistolet ma tłumik.
     * @return true, jeśli pistolet ma tłumik
     */
    public boolean hasSilencer() {
        return hasSilencer;
    }

    /**
     * Zwraca reprezentację obiektu w postaci łańcucha znaków.
     * @return łańcuch znaków reprezentujący obiekt
     */
    @Override
    public String toString() {
        return super.toString() + ", hasSilencer=" + hasSilencer;
    }
}