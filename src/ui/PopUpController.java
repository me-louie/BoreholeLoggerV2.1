package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PopUpController {

    @FXML public TextField boreholeID;
    @FXML public Button submitButton;
    @FXML public Button cancelButton;
    private BoreholeManagerController bmc;

    public void closePopUp(ActionEvent actionEvent) {
        // get a handle to the stage
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void setNewBHID(ActionEvent actionEvent) {
        bmc.addBHToTree(this.boreholeID.getText());
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }


    public void setParent(BoreholeManagerController bmc){
        this.bmc = bmc;
    }
}
