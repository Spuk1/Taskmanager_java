package taskmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import model.User;

/**
 * JavaFX App
 */


public class App extends Application {

    public static Scene scene;
    private int id;
    private int projectId = 0;
    private Stage projectStage;
    private Stage userStage;
    private static App instance;
    
    public App() {
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }

    public int getProjectId() {
        return projectId;
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("secondary"), 1024, 800);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    public void setRoot(FXMLLoader loader, int p_id) throws IOException {
        id = p_id;
        scene.setRoot(loader.load());
        
        
    }

    public int getId() {
        return id;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    
    
    
    static User getUser() throws IOException{
        FXMLLoader loader = new FXMLLoader(App.class.getResource("secondary" + ".fxml"));
        loader.load();
        SecondaryController controller = loader.getController();
        User us = controller.getUser();
        return us;
    }
    

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public void startProjectForm(int userId, int projectId) throws IOException{
        setProjectId(projectId);
        Scene scene = new Scene(loadFXML("projectForm"), 600, 600);
        projectStage = new Stage();
        projectStage.setTitle("Project Form");
        projectStage.setScene(scene);
        projectStage.show();
    }
        public void startUserForm(int userId) throws IOException{
        id = userId;
        Scene scene = new Scene(loadFXML("userForm"), 600, 600);
        userStage = new Stage();
        userStage.setTitle("User Form");
        userStage.setScene(scene);
        userStage.show();
    }
    
    public void quitProjectForm(){
        projectStage.close();
    }
    public void quitUserForm(){
        userStage.close();
    }

}