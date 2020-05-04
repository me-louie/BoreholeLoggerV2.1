package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class InitialPageController implements ControlledScreen{

    ScreensController myController;


    @FXML
    public void goToProjectInfoPage(ActionEvent actionEvent) {
        myController.setScreen(Main.screen2ID);

        
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
}
