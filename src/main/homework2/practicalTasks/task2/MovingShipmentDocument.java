package homework2.practicalTasks.task2;

import java.util.Date;
import java.util.List;

// документ отгрузки со склада (перемещение на другой склад)
class MovingShipmentDocument extends ShipmentDocument {
    private String movingStorage; // название склада получения (только для перемещения)
    private String movingStorageOwner; // владелец склада получения (только для перемещения)

    public MovingShipmentDocument(String documentId, Date documentDate, String storageTitle, String storageOwner,
                                  String movingStorage, String movingStorageOwner, List<Item> items) {
        setDocumentType("moving");

        setDocumentId(documentId);
        setDocumentDate(documentDate);
        setStorageTitle(storageTitle);
        setStorageOwner(storageOwner);

        this.movingStorage = movingStorage;
        this.movingStorageOwner = movingStorageOwner;

        setItems(items);
    }

    // Является ли перемещение внутренним (между складами одного владельца)
    public boolean isInternalMovement() {
        return getStorageOwner().equals(movingStorageOwner);
    }
}
