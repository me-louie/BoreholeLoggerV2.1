package ui.old;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

public class InitialPageController implements ControlledScreen {

    ui.old.ScreensController myController;


    public InitialPageController(){
        System.out.println("Initial page controller constructed");
    }
    @FXML
    public void goToProjectInfoPage(ActionEvent actionEvent) {
        myController.setScreen(Main.screen2ID);

        
    }

    @Override
    public void setScreenPage(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initial page initializer");
    }
}
