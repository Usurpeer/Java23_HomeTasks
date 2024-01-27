package j1.homework3.microTasks.task3;

class Main {
    public static void main(String[] args) {
        printValues();
    }

    public static void printValues() {
        System.out.println(CalculateWeekends.getQuantityWeekends("2023-01-01", "2023-12-31"));
    }
}
