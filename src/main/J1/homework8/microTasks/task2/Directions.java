package j1.homework8.microTasks.task2;

import java.util.Arrays;

public enum Directions {
    // стрелки
    UP("UP", "37", new int[]{0, 1}),
    DOWN("DOWN", "38", new int[]{0, -1}),
    LEFT("LEFT", "39", new int[]{-1, 0}),
    RIGHT("RIGHT", "40", new int[]{1, 0});

    // их поля
    private final String title;
    private final String code;
    private final int[] vector;

    Directions(String title, String code, int[] vector) {
        this.title = title;
        this.code = code;
        this.vector = Arrays.copyOf(vector, vector.length);
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public int[] getVector() {
        return vector;
    }

    @Override
    public String toString() {
        return "Клавиша: " + getTitle() + "\n\tКод Клавиши: " + getCode() + "\n\tЕё массив: " +
                Arrays.toString(getVector());
    }
}
