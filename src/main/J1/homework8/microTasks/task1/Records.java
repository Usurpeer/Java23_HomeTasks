package j1.homework8.microTasks.task1;

import j1.homework8.microTasks.task1.exceptions.AlreadyExistsException;

import java.util.List;

public class Records {
    public static void addRecord(List<String> records, String record) {
        int index = records.indexOf(record);

        if (index != -1) {
            throw new AlreadyExistsException(record, index);
        } else {
            records.add(record);
        }
    }
}
