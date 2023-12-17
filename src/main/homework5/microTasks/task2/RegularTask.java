package homework5.microTasks.task2;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegularTask {
    private RegularTask(){

    }
    public static String checkStr(String str){
        if(isEmail(str)){
            return "email";
        }
        if(isPhoneNumber(str)){
            return "телефон";
        }
        if(isInn(str)){
            return "ИНН";
        }
        if(isUsername(str)){
            return "username";
        }
        return "none";
    }
    private static boolean isEmail(String str){
        Pattern pt = Pattern.compile("\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*\\.\\w{2,4}");
        Matcher mt = pt.matcher(str);
        return mt.matches();
    }
    private static boolean isPhoneNumber(String str){
        Pattern pt = Pattern.compile("\\+7-?(\\d\\d\\d|\\(\\d\\d\\d\\))-?\\d\\d\\d-?\\d\\d-?\\d\\d");
        Matcher mt = pt.matcher(str);
        return mt.matches();
    }
    private static boolean isInn(String str){
        Pattern pt = Pattern.compile("(\\d{10}|\\d{12})");
        Matcher mt = pt.matcher(str);
        return mt.matches();
    }
    private static boolean isUsername(String str){
        Pattern pt = Pattern.compile("[a-zA-Z]+[0-9a-zA-Z0-9._$]{7,}");
        Matcher mt = pt.matcher(str);
        return mt.matches();
    }
}
