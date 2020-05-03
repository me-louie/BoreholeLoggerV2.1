package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Project;
import network.GeolocationManager;
import network.InvalidQueryException;
import org.json.JSONException;

public class ProjectInfoSceneController implements ControlledScreen {
    @FXML public TextField pManager;
    @FXML public TextField pAddress;
    @FXML public TextField pNumber;
    @FXML public Text invalidAddressPrompt;
    @FXML public Label longLabel;
    @FXML public Label latLabel;

    private ScreensController myController;
    private Project project;
    private GeolocationManager geoManager;

    
    @Override
    public void setScreenParent(ScreensController screenPage) {
        this.myController = screenPage;
    }


    public void goToInitialPage(ActionEvent actionEvent) {

        myController.setScreen(Main.screen1ID);
        clearLabels();
        clearTextViews();
        invalidAddressPrompt.setOpacity(0.0);
    }

    private void clearTextViews() {
        pNumber.clear();
        pManager.clear();
        pAddress.clear();
    }

    private void clearLabels() {
        latLabel.setText("Latitude: ");
        longLabel.setText("Longitude: ");
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
        try {
            String query = pAddress.getText();

            if (query.equals("")){
                throw new InvalidQueryException("Query cannot be blank");
            }
                geoManager = new GeolocationManager(query);
                invalidAddressPrompt.setOpacity(0.0);
                latLabel.setText("Latitude: " + geoManager.getLatitude());
                longLabel.setText("Longitude: "+ geoManager.getLongitude());

        } catch (InvalidQueryException | JSONException e) {
            e.printStackTrace();
            System.out.println("Invalid address!");
            System.out.println("Please enter a valid address");

            clearLabels();
            invalidAddressPrompt.setOpacity(1.0);
        }
    }
}
