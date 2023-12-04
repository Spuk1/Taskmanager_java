package taskmanager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Project;
import model.Task;
import model.TaskmanagerDAO;
import model.User;
import util.TaskBox;
import util.UserDataBox;

public class PrimaryController {
    private int isNew = 1;
    private int taskID = 0;
    private static PrimaryController instance;
    @FXML
    private TextField taskNameInput;
    @FXML
    private ComboBox<String> taskStateInput;
    @FXML
    private TextArea taskDescriptionInput;
    @FXML
    private Button taskSubmitButton;
    @FXML
    private DatePicker taskDatepickerInput;
    @FXML
    private Label editTaskHeader;
    @FXML
    private Button taskDeleteButton;
    @FXML
    private HBox teamBox;

    public PrimaryController() {
        instance = this;
    }

    public static PrimaryController getInstance() {
        return instance;
    }
    private User user = null;
    private TaskmanagerDAO DAO = new TaskmanagerDAO();
    private Project project = null;
    @FXML
    private VBox projectInfoBox;
    @FXML
    private FlowPane flexBox;
    @FXML
    private MenuButton userMenu;
    @FXML
    private ComboBox<Project> cbProjects;
    @FXML
    private Label nameLabel;
    @FXML
    private Label categoryLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label startdateLabel;
    @FXML
    private Label deadlineLabel;
    @FXML
    private ComboBox<User> cbUsers;
    private Button newTaskButton = new Button("add Task");
    EventHandler<ActionEvent> handleAddTask = new EventHandler<ActionEvent>() {
    public void handle(ActionEvent e){
        addTask();
    }
    };
    
    
    public void initialize() throws IOException{
        initUser();
        setProjects();
        ObservableList<String> items = FXCollections.observableArrayList("new", "active", "tested", "closed");
        taskStateInput.setItems (items);
        newTaskButton.setOnAction(handleAddTask);
        addTask();
        descriptionLabel.setWrapText(true);
    }
    
    public void initUser(){
        user = DAO.getUser(App.getInstance().getId());
        userMenu.setText(user.toString());
    }
    
    public void setProjects(){
        cbProjects.setItems(DAO.getProjects(user.getId()));
        if(!cbProjects.getItems().isEmpty()){
        project = cbProjects.getItems().get(0);
        initProject();
        user.setRole(DAO.getUserRole(user.getId(), project.getId()));
         if(!"Admin".equals(user.getRole())){
            teamBox.setVisible(false);
        }
        }
        
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void quit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void chosenProject(ActionEvent event) {
        project = cbProjects.getValue();
        initProject();
    }
    
    public void initProject(){
        cbProjects.setValue(project);
        nameLabel.setText(project.getName());
        categoryLabel.setText(project.getCategory());
        descriptionLabel.setText("Description: "+ project.getDescription());
        startdateLabel.setText("Start date:     " + project.getStart_date());
        deadlineLabel.setText("Deadline:       " + project.getDeadline());
        initTeam();
        initTasks();
    }

    @FXML
    private void addTeamMember(ActionEvent event) {
        DAO.addUser2Project(cbUsers.getValue().getId(), project.getId());
        initTeam();
    }
    
    private void initTasks(){
        ObservableList<Task> tasks = DAO.getTasks(project.getId());
        flexBox.getChildren().clear();
        for(Task task : tasks){
            flexBox.getChildren().add(new TaskBox(""+task.getId(), task.getName(), task.getDescription(), task.getCreated(), task.getDeadline(), task.getState_id()));
        }
        flexBox.getChildren().add(newTaskButton);
        
    }
    
    public void initTeam(){
            cbUsers.setItems(DAO.getNoneProjectUsers(project.getId()));
            ObservableList<User> users = DAO.getProjectUsers(project.getId());
            ObservableList<UserDataBox>userDataArr = FXCollections.observableArrayList();
            projectInfoBox.getChildren().clear();
            projectInfoBox.getChildren().add(new Label("Teammember:"));
            if(users != null)
            for(User user : users){
                if(user.getId() != this.user.getId()){
                UserDataBox box = new UserDataBox(user.getFirst_name(), user.getLast_name(), user.getBirthdate(), user.getRole(), user.getId());
                if(DAO.getUserRole(this.user.getId(), project.getId()).equals("Worker")){
                    box.hideRemoveButton();
                }
                userDataArr.add(box);
                }
            }
            projectInfoBox.getChildren().addAll(userDataArr);
    }
    
    public void removeTeamMember(int id){
        DAO.removeUserFromProject(id);
        initTeam();
    }
    
    public void addTask(){
        isNew = 1;
        taskDeleteButton.setVisible(false);
        editTaskHeader.setText("Create new Task:");
        taskNameInput.setText("");
        taskStateInput.setValue("");
        taskDescriptionInput.setText("");
        taskDatepickerInput.setValue(LocalDate.now());
    }

    @FXML
    private void submitTask(ActionEvent event) {
        if(isNew == 1){
            DAO.addTask(
                taskNameInput.getText(),
                taskDescriptionInput.getText(),
                taskDatepickerInput.getValue(),
                taskStateInput.getValue(),
                project.getId());
        }
        else {
            DAO.editTask(
                taskNameInput.getText(),
                taskDescriptionInput.getText(),
                taskDatepickerInput.getValue(),
                taskStateInput.getValue(),
                taskID
                );
        }
        isNew = 0;
        initTasks();
        addTask();
    }
    
    public void selectTask(String id, String name, String description, String deadline, String state){
        taskDeleteButton.setVisible(true);
        isNew = 0;
        taskID = Integer.parseInt(id);
        editTaskHeader.setText("Edit selected Task:");
        taskNameInput.setText(name);
        taskStateInput.setValue(state);
        taskDescriptionInput.setText(description);
        taskDatepickerInput.setValue(LocalDate.parse(deadline , DateTimeFormatter.ISO_DATE));
    }

    @FXML
    private void deleteTask(ActionEvent event) {
        DAO.deleteTask(taskID);
        initTasks();
    }

    @FXML
    private void editProject(ActionEvent event) throws IOException {
        if(project != null)
        App.getInstance().startProjectForm(user.getId(), project.getId());
    }

    @FXML
    private void addProject(ActionEvent event) throws IOException {
        App.getInstance().startProjectForm(user.getId(), 0);
    }
    
    @FXML
    private void deleteUser(ActionEvent event) throws IOException {
        DAO.deleteUser(user.getId());
        logout(event);
    }

    @FXML
    private void editUser(ActionEvent event) throws IOException {
        App.getInstance().startUserForm(user.getId());
    }
    
}
