package j1.homework6.pracricalTasks.task1;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class CommentServiceTest {
    List<Comment> list = new ArrayList<>();

    {
        Random random = new Random(2);

        list.add(new Comment("author1", LocalDate.now().minusDays(random.nextInt(0, 5)),
                true, "Сообщение 1"));
        list.add(new Comment("author2", LocalDate.now().minusDays(random.nextInt(0, 5)),
                false, "Сообщение 2"));
        list.add(new Comment("author1", LocalDate.now().minusDays(random.nextInt(0, 5)),
                false, "Сообщение 3"));
        list.add(new Comment("author4", LocalDate.now().minusDays(random.nextInt(0, 5)),
                true, "Сообщение 4"));
        list.add(new Comment("author1", LocalDate.now().minusDays(random.nextInt(0, 5)),
                false, "Сообщение 5"));
    }

    @Test
    public void ctorWrongValue() {
        NullPointerException ex = assertThrows(NullPointerException.class, () ->
                new CommentService(null)
        );
        assertEquals("Нулевой список комментариев.", ex.getMessage());

        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () ->
                new CommentService(new ArrayList<>())
        );
        assertEquals("Пустой список комментариев.", ex2.getMessage());
    }

    @Test
    public void testWrongParams() {
        wrongTestGetSucModernizationComment();
        wrongTestGetCommentByAuthor();
        wrongTestGetAuthorDate();
    }

    @Test
    public void wrongTestGetSucModernizationComment() {
        CommentService commentService = new CommentService(list);

        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () ->
                commentService.getSucModerationComments(-1, 1)
        );
        assertEquals("Недопустимое значение номера страницы: " + -1, ex2.getMessage());

        ex2 = assertThrows(IllegalArgumentException.class, () ->
                commentService.getSucModerationComments(1, 0)
        );
        assertEquals("Недопустимое значение количества сообщений на странице: " + 0, ex2.getMessage());

    }

    @Test
    public void wrongTestGetCommentByAuthor() {
        CommentService commentService = new CommentService(list);

        NullPointerException ex1 = assertThrows(NullPointerException.class, () ->
                commentService.getCommentByAuthor(null, 1, 1)
        );
        assertEquals("Нулевое значение автора.", ex1.getMessage());

        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () ->
                commentService.getCommentByAuthor("", 1, 1)
        );
        assertEquals("Пустое значение автора.", ex2.getMessage());

        ex2 = assertThrows(IllegalArgumentException.class, () ->
                commentService.getCommentByAuthor("a", -1, 1)
        );
        assertEquals("Недопустимое значение номера страницы: " + -1, ex2.getMessage());
    }

    @Test
    public void wrongTestGetAuthorDate() {
        CommentService commentService = new CommentService(list);

        NullPointerException ex1 = assertThrows(NullPointerException.class, () ->
                commentService.getAuthorAfterDate(null, 1, 1)
        );
        assertEquals("Нулевое значение даты.", ex1.getMessage());


        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () ->
                commentService.getAuthorAfterDate(LocalDate.parse("2017-12-12"), -1, 1)
        );
        assertEquals("Недопустимое значение номера страницы: " + -1, ex2.getMessage());

        ex2 = assertThrows(IllegalArgumentException.class, () ->
                commentService.getAuthorAfterDate(LocalDate.parse("2017-12-12"), 1, 0)
        );
        assertEquals("Недопустимое значение количества сообщений на странице: " + 0, ex2.getMessage());
    }

    @Test
    public void getSucModerationCommentsTest() {
        CommentService commentService = new CommentService(list);

        List<Comment> commentList2 = commentService.getSucModerationComments();

        // что у всех статус принят
        assertTrue(commentList2.get(0).getCompletedModeration());
        assertTrue(commentList2.get(commentList2.size() - 1).getCompletedModeration());

        // что дата позже
        assertTrue(commentList2.get(0).getDateCreation().isAfter(commentList2.get(commentList2.size() - 1).getDateCreation()));

        List<Comment> commentList3 = commentService.getSucModerationComments(0, 5);

        // что начала у них одинаковые, хотя бы так
        assertEquals(commentList2.get(0), commentList3.get(0));

    }

    @Test
    public void getCommentByAuthorTest() {
        CommentService commentService = new CommentService(list);

        List<Comment> commentList2 = commentService.getCommentByAuthor("author1");

        // что сначала не принят, потом принят
        assertFalse(commentList2.get(0).getCompletedModeration());
        assertTrue(commentList2.get(commentList2.size() - 1).getCompletedModeration());

        // что автор совпадает
        assertEquals("author1", commentList2.get(0).getTitleAuthor());
        assertEquals("author1", commentList2.get(commentList2.size() - 1).getTitleAuthor());


        List<Comment> commentList3 = commentService.getCommentByAuthor("author1", 0, 3);

        // что сначала не принят, потом принят
        assertFalse(commentList3.get(0).getCompletedModeration());
        assertTrue(commentList3.get(commentList3.size() - 1).getCompletedModeration());

        // что автор совпадает
        assertEquals("author1", commentList3.get(0).getTitleAuthor());
        assertEquals("author1", commentList3.get(commentList3.size() - 1).getTitleAuthor());
    }

    @Test
    public void getAuthorAfterDateTest() {
        CommentService commentService = new CommentService(list);

        List<Comment> commentList2 = commentService.getAuthorAfterDate(LocalDate.now().minusDays(2));

        String auth = commentList2.get(0).getTitleAuthor();
        // авторы не повторяются
        for(int i = 1; i < commentList2.size(); i++){
            assertNotEquals(auth, commentList2.get(i).getTitleAuthor());
        }

        List<Comment> commentList3 = commentService.getAuthorAfterDate(LocalDate.now().minusDays(2),0,3);

        String auth1 = commentList3.get(0).getTitleAuthor();
        // авторы не повторяются
        for(int i = 1; i < commentList3.size(); i++){
            assertNotEquals(auth1, commentList3.get(i).getTitleAuthor());
        }

    }
}