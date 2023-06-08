/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cineboletos_proyecto;

import models.User;

/**
 *
 * @author jackiesanchez
 */
public class Context {
   
        private static User user = null;
    private static boolean isLogIn;

    /**
     * @return the user
     */
    public static User getUser() {
        return user;
    }

    /**
     * @return the isLogIN
     */
    public static boolean isIsLogIn() {
        return isLogIn;
    }

    /**
     * @param aIsLogIN the isLogIN to set
     */
    public static void setIsLogIN(boolean aIsLogIN) {
        isLogIn = aIsLogIN;
    }    
    
    public static void LogIn(User model){
        user = model;
        setIsLogIN(true);
    }
    
    public static void LogOut(){
        user = null;
        setIsLogIN(false);
        App.setRoot("Auth");
    }
    
}
