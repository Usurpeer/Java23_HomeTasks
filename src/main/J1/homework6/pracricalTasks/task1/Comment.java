package j1.homework6.pracricalTasks.task1;

import java.time.LocalDate;

public class Comment {
    private final String titleAuthor; // имя автора
    private final LocalDate dateCreation; // дата создания
    private boolean completedModeration; // пройдена ли модерация
    private String message; // текст сообщения
    public Comment(String titleAuthor, LocalDate dateCreation, boolean completedModeration, String message){
        if(titleAuthor == null){
            throw new NullPointerException("Нулевое имя автора.");
        }
        if(message == null){
            throw new NullPointerException("Нулевое сообщение.");
        }
        if(dateCreation == null){
            throw new NullPointerException("Дата создания не задана.");
        }
        if(titleAuthor.isEmpty()){
            throw new IllegalArgumentException("Имя автора не задано.");
        }
        if(message.isEmpty()){
            throw new IllegalArgumentException("Сообщение не задано.");
        }

        this.titleAuthor = titleAuthor;
        this.dateCreation = dateCreation;
        this.completedModeration = completedModeration;
        this.message = new String(message);
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + titleAuthor.hashCode();
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj != null){
            if(obj instanceof Comment c){
                return (titleAuthor.equals(c.getTitleAuthor()));
            }
        }
        return false;
    }
    public String getTitleAuthor() {
        return titleAuthor;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public boolean getCompletedModeration() {
        return completedModeration;
    }

    public void setCompletedModeration(boolean completedModeration) {
        this.completedModeration = completedModeration;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
