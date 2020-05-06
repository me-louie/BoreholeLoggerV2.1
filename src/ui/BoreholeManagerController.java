package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BoreholeManagerController implements ControlledScreen {

    @FXML
    public TreeView<String> tree;
    private ScreensController myController;
    private int numBH = 0;
    @FXML
    private TreeItem<String> root;


    public BoreholeManagerController() {
        System.out.println("constructed");
//        tree.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>(){
//            @Override
//            public TreeCell<String> call(TreeView<String> p) {
//                return new TreeViewSample.TextFieldTreeCellImpl();
//            }
//        });
    }



    @Override
    public void setScreenPage(ScreensController screenPage) {
        this.myController = screenPage;
    }

    public void goToLog(ActionEvent actionEvent) {
        this.myController.setScreen(Main.screen3ID);
    }

    public void createPopUp(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PopUp.fxml"));
        Parent parent = fxmlLoader.load();


        Stage newStage = new Stage();
        Scene scene = new Scene(parent, 300, 200);
        newStage.setTitle("Enter New Borehole ID");
        newStage.setScene(scene);
        newStage.show();

        PopUpController puc = fxmlLoader.<PopUpController>getController();
        puc.setParent(this);
    }


    public void addBHToTree(String id) {
        TreeItem<String> newBH = new TreeItem<>(id);
        root.getChildren().add(newBH);
        root.setExpanded(true);
        tree.setRoot(root);
    }

    public void clearTree(ActionEvent actionEvent) {
    }

    public void addSample(ActionEvent actionEvent) {
        TreeItem<String> itemChild = new TreeItem<>("Child" + numBH);
        root.getChildren().add(itemChild);
        numBH++;
    }


    public void goToProjectInfoPage(ActionEvent actionEvent) {
        myController.setScreen(Main.screen2ID);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("second");
    }
}
