package taskmanager;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.TaskmanagerDAO;
import model.User;

public class SecondaryController {
    private TaskmanagerDAO taskmanagerDAO = new TaskmanagerDAO();
    private User user = null;
    @FXML
    private ComboBox<User> cbUsers;
    @FXML
    private Button login;
    @FXML
    private AnchorPane createUserPane;
    @FXML
    private Button registerButton;
    @FXML
    private TextField firstNameInput;
    @FXML
    private TextField lastNameInput;
    @FXML
    private DatePicker birthdateInput;
    @FXML
    private Button toggleRegisterButton;

    
    public void initialize(){
        cbUsers.setItems(taskmanagerDAO.getUsers());
        createUserPane.setVisible(false);
    }

    @FXML
    private void chooseUser(ActionEvent event) {
        user = cbUsers.getValue();
    }

    @FXML
    private void initUser(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
       App.getInstance().setRoot(loader, user.getId());
    }

    public User getUser() {
        return user;
    }

    @FXML
    private void registerUser(ActionEvent event) {
        taskmanagerDAO.addUser(firstNameInput.getText(), lastNameInput.getText(), birthdateInput.getValue().toString());
        cbUsers.setItems(taskmanagerDAO.getUsers());
        createUserPane.setVisible(false);
        toggleRegisterButton.setText("+");
        firstNameInput.setText("");
        lastNameInput.setText("");
        birthdateInput.setValue(null);
    }

    @FXML
    private void toggleRegister(ActionEvent event) {
        if(toggleRegisterButton.getText().equals("+")){
            createUserPane.setVisible(true);
            toggleRegisterButton.setText("-");
        }
        else {
            createUserPane.setVisible(false);
            toggleRegisterButton.setText("+");
        }
    }

 
    
    
}