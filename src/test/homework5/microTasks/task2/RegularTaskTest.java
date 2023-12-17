package homework5.microTasks.task2;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegularTaskTest {
    @Test
    public void isPhoneNumberTest(){
        assertEquals("телефон", RegularTask.checkStr("+7-(123)-456-78-90"));
        assertEquals("телефон", RegularTask.checkStr("+7(123)456-78-90"));
        assertEquals("телефон", RegularTask.checkStr("+7-123-456-78-90"));
        assertEquals("телефон", RegularTask.checkStr("+71234567890"));

        assertEquals("none", RegularTask.checkStr("71234567890"));
        assertEquals("none", RegularTask.checkStr("7777-8888-9999"));
    }

    @Test
    public void isInnTest(){
        assertEquals("ИНН", RegularTask.checkStr("1234567890"));
        assertEquals("ИНН", RegularTask.checkStr("000000000000"));
    }

    @Test
    public void isUsernameTest(){
        assertEquals("username", RegularTask.checkStr("A2a.$aa_aaaaaa"));
        assertEquals("username", RegularTask.checkStr(
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.$1234_"));
        assertEquals("username", RegularTask.checkStr("aaaa1111"));


        assertEquals("none", RegularTask.checkStr("a"));
        assertEquals("none", RegularTask.checkStr("4aaaaaaaaaa"));
    }

    @Test
    public void isEmailTest(){
        assertEquals("email", RegularTask.checkStr("user.name@example.com"));
        assertEquals("email", RegularTask.checkStr("user_name1@some.example.com"));


        assertEquals("none", RegularTask.checkStr("@example.com"));
        assertEquals("none", RegularTask.checkStr("user.name@example"));

        assertEquals("none", RegularTask.checkStr(""));
        assertEquals("none", RegularTask.checkStr("$asdfghjk"));
        assertEquals("none", RegularTask.checkStr("qwerty 456"));
    }
}