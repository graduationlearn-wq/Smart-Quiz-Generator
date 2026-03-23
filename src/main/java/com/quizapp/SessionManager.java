package com.quizapp;

public class SessionManager {
    private static SessionManager instance;
    private int userId;
    private String username;
    private String role;
    
    private SessionManager() {}
    
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }
    
    public void login(int userId, String username, String role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }
    
    public void logout() {
        this.userId = 0;
        this.username = null;
        this.role = null;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getRole() {
        return role;
    }
    
    public boolean isLoggedIn() {
        return userId > 0;
    }
}