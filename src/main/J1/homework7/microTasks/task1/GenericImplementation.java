package j1.homework7.microTasks.task1;

public class GenericImplementation <T> implements SomeInterface<T>{
    protected T data;
    @Override
    public T getData() {
        return data;
    }

    @Override
    public boolean validate(T data) {
        return false;
    }
}
