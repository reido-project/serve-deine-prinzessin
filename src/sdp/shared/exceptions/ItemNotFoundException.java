package sdp.shared.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException() {
        super("Unable to execute item action: You don't have this item");
    }
}
