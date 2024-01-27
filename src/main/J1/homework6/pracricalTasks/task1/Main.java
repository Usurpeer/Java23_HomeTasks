package j1.homework6.pracricalTasks.task1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<Comment> commentList = getComments();

        CommentService commentService = new CommentService(commentList);
        List<Comment> commentList2 = commentService.getSucModerationComments();
        System.out.println("\nКомментарии, прошедшие модерацию, с сортировкой от новых к старым: ");
        printList(commentList2);

        System.out.println("\nКомментарии от переданного в параметре автора, сначала не прошедшие, а потом прошедшие" +
                " модерацию: ");
        List<Comment> commentList3 = commentService.getCommentByAuthor("author1");
        printList(commentList3);

        System.out.println("\nАвторы сообщений, созданных после переданной даты, без повторов: ");
        List<Comment> commentList4 = commentService.getAuthorAfterDate(LocalDate.now().minusDays(2));
        printList(commentList4);
    }

    public static void printList(List<Comment> commentList) {
        System.out.println("Размер: " + commentList.size());

        for (Comment com : commentList) {
            System.out.println("Автор: " + com.getTitleAuthor());
            System.out.println("Дата: " + com.getDateCreation());
            System.out.println("Статус модерации: " + com.getCompletedModeration());
            System.out.println("Сообщение комментария: " + com.getMessage() + "\n");
        }
        System.out.println("--------------------------------\n");
    }

    public static List<Comment> getComments() {
        List<Comment> list = new ArrayList<>();
        Random random = new Random(2);

        list.add(new Comment("author1", LocalDate.now().minusDays(random.nextInt(0, 5)), true, "Сообщение 1"));
        list.add(new Comment("author2", LocalDate.now().minusDays(random.nextInt(0, 5)), false, "Сообщение 2"));
        list.add(new Comment("author1", LocalDate.now().minusDays(random.nextInt(0, 5)), false, "Сообщение 3"));
        list.add(new Comment("author4", LocalDate.now().minusDays(random.nextInt(0, 5)), true, "Сообщение 4"));
        list.add(new Comment("author1", LocalDate.now().minusDays(random.nextInt(0, 5)), false, "Сообщение 5"));
        list.add(new Comment("author6", LocalDate.now().minusDays(random.nextInt(0, 5)), true, "Сообщение 6"));
        list.add(new Comment("author7", LocalDate.now().minusDays(random.nextInt(0, 5)), true, "Сообщение 7"));
        list.add(new Comment("author8", LocalDate.now().minusDays(random.nextInt(0, 5)), false, "Сообщение 8"));
        list.add(new Comment("author9", LocalDate.now().minusDays(random.nextInt(0, 5)), true, "Сообщение 9"));
        list.add(new Comment("author11", LocalDate.now().minusDays(random.nextInt(0, 5)), true, "Сообщение 10"));
        list.add(new Comment("author1", LocalDate.now().minusDays(random.nextInt(0, 5)), true, "Сообщение 11"));
        list.add(new Comment("author1", LocalDate.now().minusDays(random.nextInt(0, 5)), false, "Сообщение 12"));
        list.add(new Comment("author2", LocalDate.now().minusDays(random.nextInt(0, 5)), false, "Сообщение 13"));
        list.add(new Comment("author1", LocalDate.now().minusDays(random.nextInt(0, 5)), false, "Сообщение 14"));
        list.add(new Comment("author4", LocalDate.now().minusDays(random.nextInt(0, 5)), true, "Сообщение 15"));
        list.add(new Comment("author1", LocalDate.now().minusDays(random.nextInt(0, 5)), false, "Сообщение 16"));
        list.add(new Comment("author6", LocalDate.now().minusDays(random.nextInt(0, 5)), true, "Сообщение 17"));
        list.add(new Comment("author7", LocalDate.now().minusDays(random.nextInt(0, 5)), true, "Сообщение 18"));
        list.add(new Comment("author8", LocalDate.now().minusDays(random.nextInt(0, 5)), false, "Сообщение 19"));
        list.add(new Comment("author11", LocalDate.now().minusDays(random.nextInt(0, 5)), true, "Сообщение 20"));
        list.add(new Comment("author1", LocalDate.now().minusDays(random.nextInt(0, 5)), true, "Сообщение 21"));
        list.add(new Comment("author1", LocalDate.now().minusDays(random.nextInt(0, 5)), false, "Сообщение 22"));
        list.add(new Comment("author2", LocalDate.now().minusDays(random.nextInt(0, 5)), false, "Сообщение 23"));
        list.add(new Comment("author1", LocalDate.now().minusDays(random.nextInt(0, 5)), false, "Сообщение 24"));
        list.add(new Comment("author4", LocalDate.now().minusDays(random.nextInt(0, 5)), true, "Сообщение 25"));

        return list;
    }
}


