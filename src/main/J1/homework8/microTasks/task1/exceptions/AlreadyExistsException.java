package j1.homework8.microTasks.task1.exceptions;

public class AlreadyExistsException extends RuntimeException {
    private final String value;
    private final int position;

    public AlreadyExistsException(String value, int position) {
        super("\nТекст:\n\t" + value + "\nУже был введен под номером: " + position + "\n");

        this.value = value;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public String getValue() {
        return value;
    }
}
