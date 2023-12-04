/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import util.DBConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author leon
 */
public class TaskmanagerDAO {
        public ObservableList<User> getUsers(){
        ObservableList<User> persons = FXCollections.observableArrayList();
        Connection con;
        String sql;
        String row;
        
        try {
            con = DBConnector.connect();
            sql = "Select * from Users;";
            ResultSet rs = con.createStatement().executeQuery(sql);
            
            while(rs.next()){
                persons.add(new User(rs.getInt("user_id"),
                        rs.getString("last_name"),
                        rs.getString("first_name"),
                        rs.getString("birthdate")));
            }
        }
        catch(SQLException err) {
            System.err.println(err.getMessage());
        }
        return persons;
    }
       
        
       public void updatePerson(User person){
           Connection con;
        String sql;
        String row;
        
        try {
            con = DBConnector.connect();
            sql = "Update persons set last_name='"+ person.getLast_name() + "', first_name='" + person.getFirst_name() +
                    "', birthdate='"+ person.getBirthdate() + "' where id=" + person.getId();
            ResultSet rs = con.createStatement().executeQuery(sql);
        }
        catch(SQLException err) {
            System.err.println(err.getMessage());
        }
       }
       
       
       public void addUser2Project(int userID, int projectID){
        Connection con;
        String sql;
        String row;
        
        try {
            con = DBConnector.connect();
            sql = "insert into Project_Users (project_id, user_id, role_id) Values ("+ projectID + ", " + userID + ", 2);";
            ResultSet rs = con.createStatement().executeQuery(sql);
        }
        catch(SQLException err) {
            System.err.println(err.getMessage());
        }
       }
       
        public void removeUserFromProject(int id){
        Connection con;
        String sql;
        String row;
        
        try {
            con = DBConnector.connect();
            sql = "Delete from Project_Users where user_id = "+id;
            ResultSet rs = con.createStatement().executeQuery(sql);
        }
        catch(SQLException err) {
            System.err.println(err.getMessage());
        }
       }
       
       
       public ObservableList<Project> getProjects(int id){
           ObservableList<Project> projects = FXCollections.observableArrayList();
           Connection con;
        String sql;
        String row;
        
        try {
            con = DBConnector.connect();
            sql = "SELECT p.project_id, p.name, p.category, p.description, p.start_date, p.deadline FROM Project_Users pu"
                    + " Join Users u using (user_id) JOIN Projects p using (project_ID) JOIN Roles r using (role_id)"
                    + " where user_id=" + id;
            ResultSet rs = con.createStatement().executeQuery(sql);
            
            while(rs.next()){
                projects.add(new Project(rs.getInt("project_id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getString("start_date"),
                        rs.getString("deadline")
                ));
            }
        }
        catch(SQLException err) {
            System.err.println(err.getMessage());
        }
           return projects;
       }
       
       public ObservableList<User> getProjectUsers(int id){
        ObservableList<User> users= FXCollections.observableArrayList();
        Connection con;
        String sql;
        String row;
        
        try {
            con = DBConnector.connect();
            sql = "select u.user_ID, u.first_name, u.last_name, u.birthdate, r.name as 'role_name' from Project_Users pu"
                    + " Join Users u using (user_id) Join Roles r using(role_id) where pu.project_id ="+id;
            ResultSet rs = con.createStatement().executeQuery(sql);
            
            while(rs.next()){
                users.add(new User(rs.getInt("user_id"),
                        rs.getString("last_name"),
                        rs.getString("first_name"),
                        rs.getString("birthdate"),
                        rs.getString("role_name")));
            }
        }
        catch(SQLException err) {
            System.err.println(err.getMessage());
        }
        return users;
       }
       
       
       public ObservableList<User> getNoneProjectUsers(int id){
        ObservableList<User> users= FXCollections.observableArrayList();
        Connection con;
        String sql;
        String row;
        
        try {
            con = DBConnector.connect();
            sql = "select * from Users where user_id not in (Select user_id from Project_Users where project_id ="+id + ")";
            ResultSet rs = con.createStatement().executeQuery(sql);
            
            while(rs.next()){
                users.add(new User(rs.getInt("user_id"),
                        rs.getString("last_name"),
                        rs.getString("first_name"),
                        rs.getString("birthdate")));
            }
        }
        catch(SQLException err) {
            System.err.println(err.getMessage());
        }
        return users;
       }
       
       public User getUser(int id){
        User user = null;
        Connection con;
        String sql;
        String row;
        
        try {
            con = DBConnector.connect();
            sql = "Select * from Users where user_id=" + id;
            ResultSet rs = con.createStatement().executeQuery(sql);
            
            while(rs.next()){
                user = new User(rs.getInt("user_id"),
                        rs.getString("last_name"),
                        rs.getString("first_name"),
                        rs.getString("birthdate"));
            }
        }
        catch(SQLException err) {
            System.err.println(err.getMessage());
        }
        return user;
       }
       
       public String getUserRole(int userId, int projectId){
        String role = null;
        Connection con;
        String sql;
        String row;
        
        try {
            con = DBConnector.connect();
            sql = "Select r.name from Project_Users pu "
                    + "Join Roles r using (role_id) "
                    + " where user_id=" + userId + " and "
                    + "project_id="+ projectId;
            ResultSet rs = con.createStatement().executeQuery(sql);
            
            while(rs.next()){
                role = rs.getString("name");
        }
        }
        catch(SQLException err) {
            System.err.println(err.getMessage());
        }
        return role;
       }
       
       
       public Project getProject(int id){
        Project project = null;
        Connection con;
        String sql;
        String row;
        
        try {
            con = DBConnector.connect();
            sql = "Select * from Projects where project_id=" + id;
            ResultSet rs = con.createStatement().executeQuery(sql);
            
            while(rs.next()){
                project = new Project(rs.getInt("project_id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getString("start_date"),
                        rs.getString("deadline")
                );
            }
        }
        catch(SQLException err) {
            System.err.println(err.getMessage());
        }
        return project;
       }
       
        public ObservableList<Task> getTasks(int id){
           ObservableList<Task> tasks = FXCollections.observableArrayList();
           Connection con;
        String sql;
        String row;
        
        try {
            con = DBConnector.connect();
            sql = "select t.task_id, t.created, t.deadline, t.description, t.name, s.name as 'state' from Tasks t"
                    + " Join States s using (state_id) where project_id = " + id + " order by task_id ASC";
            ResultSet rs = con.createStatement().executeQuery(sql);
            
            while(rs.next()){
                tasks.add(new Task(
                        rs.getInt("task_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("created"),
                        rs.getString("deadline"),
                        rs.getString("state")
                ));
            }
        }
        catch(SQLException err) {
            System.err.println(err.getMessage());
        }
           return tasks;
       }

    public void addTask(String name, String description, LocalDate deadline, String state, int project_id) {
        Connection con;
        String sql;
        String row;
        
        try {
            con = DBConnector.connect();
            sql = "insert into Tasks (name, description, created,  deadline, state_id, project_id) Values"
                    + "( '" + name + "', '" + description + "', '" + LocalDate.now() + "', '" + deadline.toString() + "', (Select state_id from States where name = '" + state + "')," + project_id + ")";
            System.out.println(sql);
            ResultSet rs = con.createStatement().executeQuery(sql);
        }
        catch(SQLException err) {
            System.err.println(err.getMessage());
        }
    }
    
     public void editTask(String name, String description, LocalDate deadline, String state, int taskId) {
        Connection con;
        String sql;
        String row;
        
        try {
            con = DBConnector.connect();
            sql = "update Tasks set name = '"+name+"', description = '"+description+"', deadline = '"+deadline+"', state_id=(Select state_id from States where name = '"+state+"') where task_id = "+taskId;
            System.out.println(sql);
            ResultSet rs = con.createStatement().executeQuery(sql);
        }
        catch(SQLException err) {
            System.err.println(err.getMessage());
        }
    }

    public void deleteTask(int taskID) {
        Connection con;
        String sql;
        String row;
        
        try {
            con = DBConnector.connect();
            sql = "delete from Tasks where task_id="+taskID;
            ResultSet rs = con.createStatement().executeQuery(sql);
        }
        catch(SQLException err) {
            System.err.println(err.getMessage());
        }
    }

    public void addUser(String first_name, String last_name, String birthdate) {
        Connection con;
        String sql;
        String row;
        
        try {
            con = DBConnector.connect();
            sql = "Insert into Users (first_name, last_name, birthdate) Values ('"
                    + first_name + "', '" + last_name + "', '" + birthdate + "');";
            ResultSet rs = con.createStatement().executeQuery(sql);
        }
        catch(SQLException err) {
            System.err.println(err.getMessage());
        }
    }

    public void deleteUser(int id) {
        Connection con;
        String sql;
        String row;
        
        try {
            con = DBConnector.connect();
            sql = "Delete from Project_Users where user_id = "+ id;
            ResultSet rs = con.createStatement().executeQuery(sql);
        }
        catch(SQLException err) {
            System.err.println(err.getMessage());
        }
        
        try {
            con = DBConnector.connect();
            sql = "Delete from Users where user_id = "+ id;
            ResultSet rs = con.createStatement().executeQuery(sql);
        }
        catch(SQLException err) {
            System.err.println(err.getMessage());
        }
    }

    public void addProject(String name, String category, String description, String created, String deadline, int user_id) {
         Connection con;
        String sql;
        String row;
        
        try {
            con = DBConnector.connect();
            sql = "Insert into Projects (name, category, description, start_date, deadline) Values ('"
                    + name + "', '" + category + "', '" + description + "', '" + created + "', '" + deadline + "');";
            ResultSet rs = con.createStatement().executeQuery(sql);
        }
        catch(SQLException err) {
            System.err.println(err.getMessage());
        }
        
        try {
            con = DBConnector.connect();
            sql = "Insert into Project_Users (project_id, user_id, role_id) Values ("
                    + "(Select project_id from Projects Order by project_id DESC Limit 1), '" + user_id + "', 1 " + ");";
            ResultSet rs = con.createStatement().executeQuery(sql);
        }
        catch(SQLException err) {
            System.err.println(err.getMessage());
        }
    }

    public void editProject(String name, String category, String description, String created, String deadline, int user_id, int project_id) {
         Connection con;
        String sql;
        String row;
        
        try {
            con = DBConnector.connect();
            sql = "update Projects set name='"
                    + name + "', category='" + category + "', description='" + description + "', deadline='" + deadline + "' where project_id = " + project_id;
            ResultSet rs = con.createStatement().executeQuery(sql);
        }
        catch(SQLException err) {
            System.err.println(err.getMessage());
        }
    }

    public void deleteProject(int projectId) {
        Connection con;
        String sql;
        String row;
        
        try {
            con = DBConnector.connect();
            sql = "Delete from Project_Users where project_id = "+ projectId;
            ResultSet rs = con.createStatement().executeQuery(sql);
        }
        catch(SQLException err) {
            System.err.println(err.getMessage());
        }
        
        try {
            con = DBConnector.connect();
            sql = "Delete from Tasks where project_id = "+ projectId;
            ResultSet rs = con.createStatement().executeQuery(sql);
        }
        catch(SQLException err) {
            System.err.println(err.getMessage());
        }
        
        try {
            con = DBConnector.connect();
            sql = "Delete from Projects where project_id = "+ projectId;
            ResultSet rs = con.createStatement().executeQuery(sql);
        }
        catch(SQLException err) {
            System.err.println(err.getMessage());
        }
    }

    public void editUser(String firstName, String lastName, String birthdate, int id) {
        Connection con;
        String sql;
        String row;
        
        try {
            con = DBConnector.connect();
            sql = "update Users set first_name='"
                    + firstName + "', last_name='" + lastName + "', birthdate='" + birthdate + "' where user_id = " + id;
            ResultSet rs = con.createStatement().executeQuery(sql);
        }
        catch(SQLException err) {
            System.err.println(err.getMessage());
        }
    }
}
