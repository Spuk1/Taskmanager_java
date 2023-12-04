/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author leon
 */
public class User {
    private int id;
    private String last_name;
    private String first_name;
    private String birthdate;
    private String role;
    
    public User(int id, String last_name, String first_name, String birthdate, String role){
        this.id = id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.birthdate = birthdate;
        this.role = role;
    }

    public String getRole() {
        return role;
    }
    public User(int id, String last_name, String first_name, String birthdate){
        this.id = id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.birthdate = birthdate;
        this.role = "";
    }

    public int getId() {
        return id;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return last_name + " " + first_name;
    }
    
    public void setRole(String str){
        role = str;
    }
}

