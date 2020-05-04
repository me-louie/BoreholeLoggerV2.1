package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.Project;
import network.GeolocationManager;
import network.InvalidQueryException;
import network.SiteMap;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ProjectInfoSceneController implements ControlledScreen {
    @FXML
    public TextField pManager;
    @FXML
    public TextField pAddress;
    @FXML
    public TextField pNumber;
    @FXML
    public Text invalidAddressPrompt;
    @FXML
    public Label longLabel;
    @FXML
    public Label latLabel;
    @FXML
    public ImageView pMap;

    private ScreensController myController;
    private Project project;


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
    public void submitGoToSamplePage(ActionEvent actionEvent) {
        System.out.println(pNumber.getText());
        System.out.println(pManager.getText());
        System.out.println(pAddress.getText());

        project = new Project(pNumber.getText(), pManager.getText(), pAddress.getText());

        myController.setScreen(Main.screen3ID);
    }

    @FXML
    public void refreshImage(ActionEvent actionEvent) {
        try {
            String query = pAddress.getText();

            if (query.equals("")) {
                throw new InvalidQueryException("Query cannot be blank");
            }
            GeolocationManager geoManager = new GeolocationManager(query);
            invalidAddressPrompt.setOpacity(0.0);
            latLabel.setText("Latitude: " + geoManager.getLatitude());
            longLabel.setText("Longitude: " + geoManager.getLongitude());
            System.out.println(geoManager.getLatitude());
            System.out.println(geoManager.getLongitude());

            SiteMap siteMap = new SiteMap("currMap", geoManager.getLatitude(), geoManager.getLongitude());

            FileInputStream input = new FileInputStream("src/ui/images/currMap.jpg");
            Image img = new Image(input);
            pMap.setImage(img);


        } catch (InvalidQueryException | JSONException | FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Invalid address! Please enter a valid address");

            clearLabels();
            invalidAddressPrompt.setOpacity(1.0);
        }
    }
}
