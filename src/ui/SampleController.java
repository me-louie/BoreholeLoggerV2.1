package ui;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class SampleController {

    @FXML public ToggleGroup sampleColour;
    @FXML public ToggleGroup sampleStrat;
    @FXML public ToggleGroup sampleMoisture;
    private MainScreenController parentController;

    void setParent(MainScreenController parentController) {
        this.parentController = parentController;
    }
}

