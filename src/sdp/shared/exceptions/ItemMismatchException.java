package sdp.shared.exceptions;

public class ItemMismatchException extends RuntimeException{
    public ItemMismatchException() {
        super("Item Mismatch: Provided consumable data is assigned to a non-consumable item");
    }
}
