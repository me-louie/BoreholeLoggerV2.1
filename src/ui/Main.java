package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static String screen1ID = "InitialPage";
    public static String screen1File = "InitialPage.fxml";
    public static String screen2ID = "ProjectInfoScene";
    public static String screen2File = "ProjectInfoScene.fxml";
    public static String screen3ID = "SamplePage";
    public static String screen3File = "SamplePage.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Borehole Logger Beta");
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(Main.screen1ID, Main.screen1File);
        mainContainer.loadScreen(Main.screen2ID, Main.screen2File);

        mainContainer.setScreen(Main.screen1ID);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

//        Parent root = FXMLLoader.load(getClass().getResource("InitialPage.fxml"));
//        primaryStage.setTitle("Borehole Logger v.2.1");
//        primaryStage.setScene(new Scene(root, 500, 500));
//        primaryStage.show();
    }



}
