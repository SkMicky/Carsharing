package model.action;

import java.sql.SQLOutput;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private final String LOGIN_VALIDATOR = "^[A-Za-z]{1,18}([._-]{1,2})?[A-Za-z0-9]{1,18}$";
    private final String PASSWORD_VALIDATOR = "^(?=.*[a-z])(?=\\s+$).{8,}$";
    private final String EMAIL_VALIDATOR = "([.[^@\\s]]+)@([.[^@\\s]]+)\\.([a-z]+)";
    private final String PHONE_VALIDATOR = "^^\\+?[78][-\\(]?\\d{3}\\)?-?\\d{3}-?\\d{2}-?\\d{2}$";

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
        if(!password.isEmpty()){
            Pattern pattern = Pattern.compile(PASSWORD_VALIDATOR);
            Matcher matcher = pattern.matcher(password);
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

    boolean validateFirstName(String firstName){
        if(!firstName.isEmpty() && firstName.length() < 20){
            flag = true;
            System.out.println("Success");
        } else {
            flag = false;
            System.out.println("Fail");
        }
        return flag;
    }

    boolean validateLastName(String lastName){
        if(!lastName.isEmpty() && lastName.length() < 20){
            flag = true;
            System.out.println("Success");
        } else {
            flag = false;
            System.out.println("Fail");
        }
        return flag;
    }

    boolean validatePhoneNumber(String phoneNumber){
        if(!phoneNumber.isEmpty()){
            Pattern pattern = Pattern.compile(PHONE_VALIDATOR);
            Matcher matcher = pattern.matcher(phoneNumber);
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
