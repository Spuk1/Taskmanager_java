/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author leon
 */
public class Task {
    int id;
    String name;
    String description;
    String created;
    String deadline;
    String state_id;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCreated() {
        return created;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getState_id() {
        return state_id;
    }

    public Task(int id, String name, String description, String created, String deadline, String state) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.deadline = deadline;
        this.state_id = state;
    }
}
