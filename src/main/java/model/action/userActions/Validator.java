package model.action.userActions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private final String LOGIN_VALIDATOR = "^[A-Za-z]{1,18}([._-]{1,2})?[A-Za-z0-9]{1,18}$";
    private final String EMAIL_VALIDATOR = "[\\w\\d-_.$]+@\\w+\\.\\w+";
    private final String GOSNO_VALIDATOR = "^([A-Z]{1}\\s\\d{3}\\s[A-Z]{3})|(\\d{3}[A-Z]{3}\\d{2})|(\\d{3}\\s[A-Z]{3}\\s\\d{2})$";

    private boolean flag;

    boolean validateLogin(String login){
        if(!login.isEmpty()){
            Pattern pattern = Pattern.compile(LOGIN_VALIDATOR);
            Matcher matcher = pattern.matcher(login);
            if(matcher.find()){
                flag = true;
            }
            else {
                flag = false;
            }
        }
        else{
            flag = false;
        }
        return flag;
    }

    boolean validatePassword(String password){
        if(!password.isEmpty() && password.length() > 8){
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    boolean validateEmail(String email){
        if(!email.isEmpty()){
            Pattern pattern = Pattern.compile(EMAIL_VALIDATOR);
            Matcher matcher = pattern.matcher(email);
            if(matcher.find()){
                flag = true;
            }
            else {
                flag = false;
            }
        }
        else{
            flag = false;
        }
        return flag;
    }

    public boolean validateGosNo(String gosNo){
        if(!gosNo.isEmpty()){
            Pattern pattern = Pattern.compile(GOSNO_VALIDATOR);
            Matcher matcher = pattern.matcher(gosNo);
            if(matcher.find()){
                flag = true;
            }
            else {
                flag = false;
            }
        }
        else{
            flag = false;
        }
        return flag;
    }
}
