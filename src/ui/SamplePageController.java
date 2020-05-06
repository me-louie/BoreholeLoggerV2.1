package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Project;

import java.net.URL;
import java.util.ResourceBundle;

public class SamplePageController implements ControlledScreen {

    private Project project;

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
    public Label pageTitle;
    @FXML
    private ScreensController myController;

    @Override
    public void setScreenPage(ScreensController screenPage) {
        this.myController = screenPage;
    }

    public void goToProjectInfoPage(ActionEvent actionEvent) {
        myController.setScreen(Main.screen2ID);
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
