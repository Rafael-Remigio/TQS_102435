package pt.ua.tqs.CarService.DAO;

public class IdProvider {

    private static IdProvider instance = null;

    public static IdProvider getInstance() {
        if (instance == null) {
            instance = new IdProvider();
        }

        return instance;
    }

    private long nextID = 0;

    public long getUniqueId() {
        if (nextID < 0) {
            throw new IllegalStateException("Out of IDs!");
        }

        long uniqueId = nextID;
        nextID++;

        return uniqueId;
    }
}