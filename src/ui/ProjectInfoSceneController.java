package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Project;
import network.Geolocation;

public class ProjectInfoSceneController implements ControlledScreen {
    @FXML public TextField pManager;
    @FXML public TextField pAddress;
    @FXML public TextField pNumber;

    private ScreensController myController;
    private Project project;
    private Geolocation geolocation;

    private String test;
    
    @Override
    public void setScreenParent(ScreensController screenPage) {
        this.myController = screenPage;
    }


    public void goToInitialPage(ActionEvent actionEvent) {
        myController.setScreen(Main.screen1ID);
    }
    @FXML
    public void submitGoToSamplePage(ActionEvent actionEvent){
        System.out.println(pNumber.getText());
        System.out.println(pManager.getText());
        System.out.println(pAddress.getText());

        project = new Project(pNumber.getText(), pManager.getText(), pAddress.getText());

        myController.setScreen(Main.screen3ID);
    }

    public void refreshImage(ActionEvent actionEvent) {
        geolocation = new Geolocation(pAddress.getText());
    }
}
