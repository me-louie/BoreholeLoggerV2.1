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
import java.net.URL;
import java.util.ResourceBundle;

public class ProjectInfoSceneController implements ControlledScreen {
    private static final String LATITUDE = "LATITUDE: ";
    private static final String LONGITUDE = "LONGITUDE: ";

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
    private SiteMap siteMap;



    @Override
    public void setScreenPage(ScreensController screenPage) {
        this.myController = screenPage;
    }


    public void goToInitialPage(ActionEvent actionEvent) throws FileNotFoundException {
        myController.setScreen(Main.screen1ID);
        refreshScene();
    }

    private void refreshScene() throws FileNotFoundException {
        clearLabels();
        clearTextViews();
        FileInputStream input = new FileInputStream("src/ui/images/blankImage.jpg");
        Image img = new Image(input);
        pMap.setImage(img);
        invalidAddressPrompt.setOpacity(0.0);
    }


    private void clearTextViews() {
        pNumber.clear();
        pManager.clear();
        pAddress.clear();
    }

    private void clearLabels() {
        latLabel.setText(LATITUDE);
        longLabel.setText(LONGITUDE);
    }

    @FXML
    // TODO: add to application instance
    public void submitGoToSamplePage(ActionEvent actionEvent) {
        Project project = new Project(pNumber.getText(), pManager.getText(), pAddress.getText());
        project.setSiteMap(siteMap);


        myController.setScreen(Main.screen4ID);
    }

    @FXML
    public void refreshMapImage(ActionEvent actionEvent) {
        try {
            String query = pAddress.getText();

            if (query.equals("")) {
                throw new InvalidQueryException("Query cannot be blank");
            }
            GeolocationManager geoManager = new GeolocationManager(query);
            invalidAddressPrompt.setOpacity(0.0);
            latLabel.setText(LATITUDE + geoManager.getLatitude());
            longLabel.setText(LONGITUDE + geoManager.getLongitude());

            siteMap = new SiteMap("map_" + pNumber.getText(), geoManager.getLatitude(), geoManager.getLongitude());
            FileInputStream input = new FileInputStream("src/ui/images/map_" + pNumber.getText() + ".jpg");
            Image img = new Image(input);
            pMap.setImage(img);


        } catch (InvalidQueryException | JSONException | FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Invalid address! Please enter a valid address");

            clearLabels();
            invalidAddressPrompt.setOpacity(1.0);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
