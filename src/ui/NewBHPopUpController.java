package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Project;

import java.net.URL;
import java.util.ResourceBundle;

public class NewBHPopUpController implements Initializable {
    @FXML
    public TextField boreholeID;
    public Text err;
    @FXML public Button submitButton;
    @FXML public Button cancelButton;
    private MainScreenController parentController;
    private Project project;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        this.project = GUI.pm.getProject();
    }

    public void closePopUp(ActionEvent actionEvent) {
        // get a handle to the stage
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void setNewBHID(ActionEvent actionEvent) {
        String id = this.boreholeID.getText();
        if (this.project.isBhIdUnique(id)){
            parentController.addBHToTree(id);
            Stage stage = (Stage) submitButton.getScene().getWindow();
            stage.close();
        } else {
            this.err.setText("Please enter a valid/unique ID");
            this.err.setOpacity(1.0);
        }

    }

    void setParent(MainScreenController parentController){
        this.parentController = parentController;
    }
}
