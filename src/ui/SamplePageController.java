package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SamplePageController implements ControlledScreen {

    @FXML
    public Label pNumber;
    @FXML
    public Label pAddress;
    @FXML
    public Label pManager;
    @FXML
    public Label pDate;
    @FXML
    public Label pLocation;
    @FXML
    public Label pMethod;
    @FXML
    private ScreensController myController;

    @Override
    public void setScreenParent(ScreensController screenPage) {
        this.myController = screenPage;
    }

    public void goToProjectScene(ActionEvent actionEvent) {
        myController.setScreen(Main.screen2ID);
    }
}
