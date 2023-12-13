package homework3.microTasks.task2;

class Main {
    public static void main(String[] args) {
        printMeth();
    }

    public static void printMeth() {
        System.out.println(DateDifference.getDifference("2010-12-31T20:15:00Z", "Europe/Moscow", "Asia/Vladivostok"));
    }
}
