/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author jackiesanchez
 * @param <T>
 */
  
public class Params<T> {
    private T data;
    
    /**
     * get a Params
     * 
     * @return Params of Type
     */
    public T getDato() {
        return data;
    }

    /**
     * set a Params
     * @param dato Params of Type
     */
    public void setDato(T dato) {
        this.data = dato;
    } 
}