package homework3.pracricalTasks.task1;

import java.time.LocalDate;
import java.util.List;

class Main {
    public static void main(String[] args) {
        print();
    }

    public static void print() {
        Initializer initializer = new Initializer("2018-12-31", 20);


        List<LocalDate> list = initializer.getDates();
        if (!list.isEmpty()) {
            list.forEach(System.out::println);
        }
    }
}
