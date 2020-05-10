package ui;

import exceptions.InvalidSampleException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Borehole;
import model.SoilSample;
import model.enums.Colour;
import model.enums.Moisture;
import model.enums.Strat;

public class SampleController {

    // Toggle Groups
    @FXML
    public ToggleGroup sampleColour;
    @FXML
    public ToggleGroup sampleStrat;
    @FXML
    public ToggleGroup sampleMoisture;

    // TextFields
    @FXML
    public TextField sampleID;
    @FXML
    public TextField begDepth;
    @FXML
    public TextField endDepth;


    @FXML
    Text depthErr;
    //Buttons
    @FXML
    public Button cancel;
    @FXML
    public Button submit;
    @FXML
    public ToggleButton toggle;
    @FXML
    public Text bhLocation;


    private Borehole bh;
    private MainScreenController parentController;


    public Borehole getBh() {
        return this.bh;
    }

    public void setBh(Borehole bh) {
        this.bh = bh;
        setBhLabel(bh.getId());

    }

    private void setBhLabel(String bhLocation) {
        this.bhLocation.setText("Location: " + bhLocation);
    }


    void setParent(MainScreenController parentController) {
        this.parentController = parentController;
    }

    public void closeWindow(ActionEvent actionEvent) {
        // get a handle to the stage
        Stage stage = (Stage) cancel.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void submitSample(ActionEvent actionEvent) {
        try {
            SoilSample soilSample = new SoilSample();
            getRadioButtonSelections(soilSample);
            getOdour(soilSample);
            getSampleId(soilSample);
            getSampleDepths(soilSample);
            this.bh.addSample(soilSample);
            parentController.addSample(soilSample);
            System.out.println(GUI.project.getBoreholes().size());
        } catch (InvalidSampleException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            depthErr.setText(e.getMessage());
            depthErr.setOpacity(1.0);

        }

    }

    private void getSampleDepths(SoilSample soilSample) throws InvalidSampleException {
        try {
            soilSample.setBegDepth(Integer.parseInt(begDepth.getText()));
            soilSample.setEndDepth(Integer.parseInt(endDepth.getText()));
        } catch (NumberFormatException e){
            throw new InvalidSampleException("Please enter a valid depth.");
        }

    }

    private void getSampleId(SoilSample soilSample) throws InvalidSampleException {
        if (sampleID.getText().equals("")){
            throw new InvalidSampleException("Please enter a valid ID.");
        } else {
            soilSample.setId(sampleID.getText());
        }
    }

    private void getOdour(SoilSample soilSample) {
        soilSample.setOdour(toggle.isSelected());
    }

    private void getRadioButtonSelections(SoilSample soilSample) {
            RadioButton selectedColour = (RadioButton) sampleColour.getSelectedToggle();
            RadioButton selectedStrat = (RadioButton) sampleStrat.getSelectedToggle();
            RadioButton selectedMoisture = (RadioButton) sampleMoisture.getSelectedToggle();

            String sColour = selectedColour.getText();
            String sStrat = selectedStrat.getText();
            String sMoisture = selectedMoisture.getText();

            soilSample.setColour(parseColour(sColour));
            soilSample.setStrat(parseStrat(sStrat));
            soilSample.setMoisture(parseMoisture(sMoisture));

    }


    private Colour parseColour(String colour) {
        Colour sColour;
        switch (colour) {
            case "Brown":
                sColour = Colour.BROWN;
                break;
            case "Blue":
                sColour = Colour.BLUE;
                break;
            default:
                sColour = Colour.GREY;
        }
        return sColour;
    }

    private Strat parseStrat(String strat) {
        Strat sStrat;
        switch (strat) {
            case "Clay":
                sStrat = Strat.CLAY;
                break;
            case "Silt":
                sStrat = Strat.SILT;
                break;
            case "Sand":
                sStrat = Strat.SAND;
                break;
            case "Gravel":
                sStrat = Strat.GRAVEL;
                break;
            default:
                sStrat = Strat.PEAT;
        }
        return sStrat;
    }

    private Moisture parseMoisture(String moisture) {
        Moisture sMoisture;
        switch (moisture) {
            case "Dry":
                sMoisture = Moisture.DRY;
                break;
            case "Moist":
                sMoisture = Moisture.MOIST;
            case "Wet":
                sMoisture = Moisture.WET;
                break;
            default:
                sMoisture = Moisture.SATURATED;
        }
        return sMoisture;

    }
}

