package com.quizapp;

public class Validator {
    
    public static boolean isValidUsername(String username) {
        return username != null && username.length() >= 3 && username.length() <= 50;
    }
    
    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }
    
    public static boolean isValidQuizTitle(String title) {
        return title != null && title.trim().length() >= 3 && title.length() <= 255;
    }
    
    public static boolean isValidQuestion(String question) {
        return question != null && question.trim().length() >= 10;
    }
    
    public static boolean isValidOption(String option) {
        return option != null && option.trim().length() >= 1 && option.length() <= 255;
    }
    
    public static boolean isValidOption(char option) {
        return option == 'A' || option == 'B' || option == 'C' || option == 'D';
    }
}