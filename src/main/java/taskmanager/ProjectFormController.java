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
public class ProjectFormController implements Initializable {

    @FXML
    private TextField nameInput;
    @FXML
    private TextField categoryInput;
    @FXML
    private TextArea descriptionInput;
    @FXML
    private DatePicker deadlineInput;
    @FXML
    private Label headerLabel;
    
    private Project project = null;
    
    private TaskmanagerDAO DAO = new TaskmanagerDAO();
    @FXML
    private Button deleteButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(App.getInstance().getProjectId() != 0){
            project = DAO.getProject(App.getInstance().getProjectId());
            if(project == null) App.getInstance().quitProjectForm();
            nameInput.setText(project.getName());
            categoryInput.setText(project.getCategory());
            descriptionInput.setText(project.getDescription());
            deadlineInput.setValue(LocalDate.parse(project.getDeadline() , DateTimeFormatter.ISO_DATE));
            headerLabel.setText("Edit project:  " + project.getName());
            headerLabel.setMaxWidth(400);
            
        }
        else deleteButton.setVisible(false);
    }    

    @FXML
    private void submitButton(ActionEvent event) throws IOException {
        if(project == null) DAO.addProject(nameInput.getText(), categoryInput.getText(), descriptionInput.getText(), LocalDate.now().toString(), deadlineInput.getValue().toString(), App.getInstance().getId());
        else DAO.editProject(nameInput.getText(), categoryInput.getText(), descriptionInput.getText(), LocalDate.now().toString(), deadlineInput.getValue().toString(),App.getInstance().getId(), project.getId());
        PrimaryController.getInstance().setProjects();
        
        App.getInstance().quitProjectForm();
    }

    @FXML
    private void deleteProject(ActionEvent event) {
        DAO.deleteProject(App.getInstance().getProjectId());
        PrimaryController.getInstance().setProjects();
        App.getInstance().quitProjectForm();
    }
}
