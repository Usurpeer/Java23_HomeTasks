package homework7.microTasks.task1;


public class DateImplementation<LocalDate> implements SomeInterface<LocalDate> {

    private LocalDate currentDate;

    @Override
    public LocalDate getData() {
        return currentDate;
    }

    @Override
    public boolean validate(LocalDate data) {
        return false;
    }

    public boolean validate(String data) {
        return false;
    }

}
