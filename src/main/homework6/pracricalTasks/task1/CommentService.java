package homework6.pracricalTasks.task1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CommentService { //implements Comparator<Comment>{
    private List<Comment> comments = new ArrayList<>();

    public CommentService(List<Comment> comments) {
        if (comments == null) {
            throw new NullPointerException("Нулевой список комментариев.");
        }
        if (comments.isEmpty()) {
            throw new IllegalArgumentException("Пустой список комментариев.");
        }
        this.comments = comments;
    }

    // комментарии, прошедшие модерацию, с сортировкой от новых к старым
    public List<Comment> getSucModerationComments(int page, int pageSize) {
        if (page < 0) {
            throw new IllegalArgumentException("Недопустимое значение номера страницы: " + page);
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Недопустимое значение количества сообщений на странице: " + pageSize);
        }
        return getSucModerationComments().stream().skip((long) (page) * pageSize).limit(pageSize).toList();
    }

    public List<Comment> getSucModerationComments() {
        return comments.stream()
                .filter(Comment::getCompletedModeration)
                .sorted(Comparator.comparing(Comment::getDateCreation).reversed()).toList();
    }

    // комментарии от переданного в параметре автора, сначала не прошедшие, а потом прошедшие модерацию
    public List<Comment> getCommentByAuthor(String author, int page, int pageSize) {
        if (page < 0) {
            throw new IllegalArgumentException("Недопустимое значение номера страницы: " + page);
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Недопустимое значение количества сообщений на странице: " + pageSize);
        }
        return getCommentByAuthor(author).stream().skip((long) (page) * pageSize).limit(pageSize).toList();
    }

    public List<Comment> getCommentByAuthor(String author) {
        if (author == null) {
            throw new NullPointerException("Нулевое значение автора.");
        }
        if (author.isEmpty()) {
            throw new IllegalArgumentException("Пустое значение автора.");
        }
        return comments.stream()
                .filter(Comment -> Comment.getTitleAuthor().equals(author))
                .sorted(Comparator.comparing(Comment::getCompletedModeration)).toList();
    }

    // вывод авторов сообщений, созданных после переданной даты, без повторов
    public List<Comment> getAuthorAfterDate(LocalDate localDate, int page, int pageSize) {
        if (page < 0) {
            throw new IllegalArgumentException("Недопустимое значение номера страницы: " + page);
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Недопустимое значение количества сообщений на странице: " + pageSize);
        }
        return getAuthorAfterDate(localDate).stream().skip((long) (page) * pageSize).limit(pageSize).toList();
    }

    public List<Comment> getAuthorAfterDate(LocalDate localDate) {
        if (localDate == null) {
            throw new NullPointerException("Нулевое значение даты.");
        }
        return comments.stream()
                .filter(Comment -> Comment.getDateCreation().isAfter(localDate))
                .distinct().toList();
    }
}
