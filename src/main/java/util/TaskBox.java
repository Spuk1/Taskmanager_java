/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import taskmanager.PrimaryController;

/**
 *
 * @author leon
 */
public class TaskBox extends VBox{
    Label id = new Label();
    Label name = new Label();
    Label description = new Label();
    Label created = new Label();
    Label deadline = new Label();
    Label state = new Label();
    Button button = new Button("+");
    HBox header = new HBox();
    HBox dates = new HBox();
    int buttonState = 0;
    EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() {
    public void handle(ActionEvent e){
        switch (buttonState) {
            case 0:
                button.setText("-");
                getChildren().add(description);
                getChildren().add(dates);
                buttonState = 1;
                break;
            case 1:
                button.setText("+");
                getChildren().clear();
                getChildren().add(header);
                buttonState = 0;
             
        }
        
        
    }
    };
    
    EventHandler<MouseEvent> handleRemove = new EventHandler<MouseEvent>() {
    public void handle(MouseEvent e){
        PrimaryController.getInstance().selectTask(id.getText(), name.getText(), description.getText(), deadline.getText().replace("deadline:\n ", ""), state.getText());
        
    }
    };

    public TaskBox(String id, String name, String description, String created, String deadline, String state) {
        this.setMinWidth(170);
        this.id.setText(id);
        this.id.setMinWidth(20);
        this.name.setText(name);
        this.name.setMinWidth(110);
        this.setBorder(new Border(new BorderStroke(Color.BLACK, 
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.button.setOnAction(ev);
        this.button.setMinWidth(25);
        this.description.setText(description);
        this.description.setMaxWidth(170);
        this.description.setWrapText(true);
        this.created.setText("created:\n"+ created);
        this.created.setMinWidth(100);
        this.deadline.setText("deadline:\n "+deadline);
        
        this.state.setText(state);
        this.state.setMinWidth(40);
        this.header.getChildren().add(this.id);
        this.header.getChildren().add(this.name);
        this.header.getChildren().add(this.state);
        this.header.getChildren().add(this.button);
        this.dates.getChildren().add(this.created);
        this.dates.getChildren().add(this.deadline);
        getChildren().add(header);
        this.setOnMouseClicked(handleRemove);
        if(state.equals("closed")){
        BackgroundFill bg = new BackgroundFill(Color.valueOf("ff2c2c"), new CornerRadii(0), new Insets(0));
        this.setBackground(new Background(bg));
    }
        else if(state.equals("active")){
        BackgroundFill bg = new BackgroundFill(Color.valueOf("2cCC2c"), new CornerRadii(0), new Insets(0));
        this.setBackground(new Background(bg));
    }
        else if(state.equals("new")){
        BackgroundFill bg = new BackgroundFill(Color.valueOf("05aaaa"), new CornerRadii(0), new Insets(0));
        this.setBackground(new Background(bg));
    }
        else if(state.equals("closed")){
        BackgroundFill bg = new BackgroundFill(Color.valueOf("aaaaaa"), new CornerRadii(0), new Insets(0));
        this.setBackground(new Background(bg));
    }
        else if(state.equals("tested")){
        BackgroundFill bg = new BackgroundFill(Color.valueOf("FFFF2c"), new CornerRadii(0), new Insets(0));
        this.setBackground(new Background(bg));
    }
    };
}
