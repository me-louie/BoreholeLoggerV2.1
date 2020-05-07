package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Project;

import java.io.IOException;

public class GUI extends Application {

    public static Project project = new Project();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Environmental Sample Logger");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}
