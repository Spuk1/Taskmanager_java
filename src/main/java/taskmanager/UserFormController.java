/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package taskmanager;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Project;
import model.TaskmanagerDAO;
import model.User;

/**
 * FXML Controller class
 *
 * @author leon
 */
public class UserFormController implements Initializable {

    @FXML
    private DatePicker deadlineInput;
    @FXML
    private Label headerLabel;
    
    private User user = null;
    
    private TaskmanagerDAO DAO = new TaskmanagerDAO();
    private Button deleteButton;
    @FXML
    private TextField firstNameInput;
    @FXML
    private TextField lastNameInput;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(App.getInstance().getId() != 0){
            user = DAO.getUser(App.getInstance().getId());
            firstNameInput.setText(user.getFirst_name());
            lastNameInput.setText(user.getLast_name());
            deadlineInput.setValue(LocalDate.parse(user.getBirthdate() , DateTimeFormatter.ISO_DATE));
            headerLabel.setText("Edit User:  " + user.getLast_name());
            
        }
        else App.getInstance().quitProjectForm();
    }    

    @FXML
    private void submitButton(ActionEvent event) throws IOException {
        DAO.editUser(this.firstNameInput.getText(), this.lastNameInput.getText(), deadlineInput.getValue().toString(), App.getInstance().getId());
        PrimaryController.getInstance().initUser();
        App.getInstance().quitUserForm();
    }

}
