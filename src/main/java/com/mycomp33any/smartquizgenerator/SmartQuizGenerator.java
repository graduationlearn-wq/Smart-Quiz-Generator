package com.mycomp33any.smartquizgenerator;

public class SmartQuizGenerator {
    public static void main(String[] args) {
        // This line makes the GUI visible
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }
}