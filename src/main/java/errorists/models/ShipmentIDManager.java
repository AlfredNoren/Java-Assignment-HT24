package errorists.models;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ShipmentIDManager {
    private static final Random random = new Random();
    private static final int ID_LENGTH = 8;
    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    // Shared set to guarantee uniqueness across generated shipment IDs.
    private static Set<String> existingIDs = new HashSet<>();

    // Constructor allows startup code to preload already assigned IDs.
    public ShipmentIDManager(Set<String> existingIDs) {
        ShipmentIDManager.existingIDs = existingIDs;
    }

    // Keeps generating until an unused ID is found, then reserves it.
    public static String generateUniqueID() {
        String newID;
        do {
            newID = generateID();
        } while (existingIDs.contains(newID));
        existingIDs.add(newID);
        return newID;
    }

    // Generates a random 8-character ID using uppercase letters and digits.
    public static String generateID() {
        StringBuilder id = new StringBuilder(ID_LENGTH);
        for (int i = 0; i < ID_LENGTH; i++) {
            id.append(CHAR_POOL.charAt(random.nextInt(CHAR_POOL.length())));
        }
        return id.toString();
    }

    // Registers an externally created ID to avoid future collisions.
    public void addExistingID(String id) {
        existingIDs.add(id);
    }

    // Removes an ID when a shipment is deleted or otherwise invalidated.
    public void removeExistingID(String id) {
        existingIDs.remove(id);
    }
}
