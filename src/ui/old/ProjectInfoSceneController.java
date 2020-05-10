package ui.old;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Project;
import model.ProjectManager;
import network.GeolocationManager;
import network.InvalidQueryException;
import network.SiteMap;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    private ui.old.ScreensController myController;
    private ProjectManager pm;
    private SiteMap siteMap;



    public ProjectInfoSceneController(){
        System.out.println("PISC constructed");
    }
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
        FileInputStream input = new FileInputStream("src/ui/resources.resources.images/blankImage.jpg");
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

    public void submitGoToSamplePage(ActionEvent actionEvent) throws IOException {
        Project project = new Project(pNumber.getText(), pManager.getText(), pAddress.getText());
        project.setSiteMap(siteMap);
//
//        Main.project = new Project(pNumber.getText(), pManager.getText(), pAddress.getText());
//        Main.project.setSiteMap(siteMap);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("BoreholeManager.fxml"));
        Parent root = loader.load();
        BoreholeManagerController bmc = loader.getController();
        bmc.initData(project);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();


//        myController.setScreen(Main.screen4ID);

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
            FileInputStream input = new FileInputStream("src/ui/resources.resources.images/map_" + pNumber.getText() + ".jpg");
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
        System.out.println("pisc initializer called");

    }

//    @Override
//    public void update() {
//        pManager.setText(Main.project.getManager());
//    }
}
