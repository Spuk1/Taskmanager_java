/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import taskmanager.PrimaryController;

/**
 *
 * @author leon
 */
public class UserDataBox extends FlowPane {
    Label first_name = new Label();
    Label last_name = new Label();
    Label birthdate = new Label();
    Label role = new Label();
    EventHandler<ActionEvent> handleRemove = new EventHandler<ActionEvent>() {
    public void handle(ActionEvent e){
        PrimaryController.getInstance().removeTeamMember(id);
    }
    };
    int id;
    public Button remove = new Button();

    public UserDataBox(String first_name, String last_name, String birthdate, String role, int id) {
        this.id = id;
        setHeight(40);
        this.setBorder(new Border(new BorderStroke(Color.BLACK, 
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.first_name.setText(first_name);
        this.first_name.setMinWidth(100);
        this.last_name.setText(last_name);
        this.last_name.setMinWidth(100);
        this.birthdate.setText(birthdate);
        this.birthdate.setMinWidth(100);
        this.role.setText(role);
        this.role.setMinWidth(70);
        this.remove.setText("-");
        getChildren().add(this.first_name);
        getChildren().add(this.last_name);
        getChildren().add(this.birthdate);
        getChildren().add(this.role);
        getChildren().add(this.remove);
        this.remove.setOnAction(handleRemove);
    }
    
    public void hideRemoveButton(){
        remove.setVisible(false);
    }
    
}
