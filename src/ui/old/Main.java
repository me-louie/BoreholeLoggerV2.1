package ui.old;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Project;

public class Main extends Application {

    public static String screen1ID = "InitialPage";
    public static String screen1File = "InitialPage.fxml";
    public static String screen2ID = "ProjectInfoScene";
    public static String screen2File = "ProjectInfoScene.fxml";
    public static String screen3ID = "SamplePage";
    public static String screen3File = "SamplePage.fxml";
    public static String screen4ID = "BoreholeManager";
    public static String screen4File = "BoreholeManager.fxml";
    public static Project project;

    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    public static void resizeScreen(){
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
    }

    @Override
    public void start(Stage primaryStage){

        this.primaryStage = primaryStage;

        primaryStage.setTitle("Borehole Logger Beta");
//        primaryStage.setMaximized(true);
        ui.old.ScreensController mainContainer = new ScreensController();

        mainContainer.loadScreen(Main.screen1ID, Main.screen1File);
        mainContainer.loadScreen(Main.screen2ID, Main.screen2File);
        mainContainer.loadScreen(Main.screen3ID, Main.screen3File);
//        mainContainer.loadScreen(Main.screen4ID, Main.screen4File);

        mainContainer.setScreen(Main.screen1ID);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();

//        Parent root = FXMLLoader.load(getClass().getResource("InitialPage.fxml"));
//        primaryStage.setTitle("Borehole Logger v.2.1");
//        primaryStage.setScene(new Scene(root, 500, 500));
//        primaryStage.show();
    }



}
