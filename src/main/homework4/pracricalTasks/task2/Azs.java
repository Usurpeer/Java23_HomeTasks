package homework4.pracricalTasks.task2;

//класс пользовательского типа данных АЗС - Название - объем поставок
public class Azs {
    private final String title;
    private final int value; // объем поставок
    public Azs(String title, int value){
        this.title = title;
        this.value = value;
    }

    // объем поставок
    public int getValue() {
        return value;
    }

    public String getTitle() {
        return new String(title);
    }
    public Azs getAzs(){
        return new Azs(new String(title), value);
    }
}
