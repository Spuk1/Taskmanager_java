/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author leon
 */
public class Project {
    private int id;
    private String name;
    private String category;
    private String description;
    private String start_date;
    private String deadline;
    private ObservableList<User> users = FXCollections.observableArrayList();;

    public void setId(int id) {
        this.id = id;
    }

    public Project(int id, String name, String category, String description, String start_date, String deadline) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.start_date = start_date;
        this.deadline = deadline;
    }
    
     @Override
    public String toString() {
        return name;
    }
    

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setUsers(ObservableList<User> users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getDeadline() {
        return deadline;
    }

    public ObservableList<User> getUsers() {
        return users;
    }
}
