package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Borehole;

import java.io.IOException;

public class MainScreenController {
    //Buttons
    @FXML public Button addBH;
    @FXML public Button clearBH;
    @FXML public Button saveAll;

    //Labels
    @FXML public Label addressLabel;
    @FXML public Label managerLabel;
    @FXML public Label numberLabel;


    //TextFields
    @FXML public TextField pAddress;
    @FXML public TextField pManager;
    @FXML public TextField pNumber;

    //Tree
    @FXML public TreeItem<String> root;
    @FXML public TreeView<String> tree;

    public void openBHPopUp(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewBHPopUp.fxml"));
        Parent root = loader.load();
        Stage popUpStage = new Stage();
        Scene scene = new Scene(root);
        popUpStage.setScene(scene);
        popUpStage.setTitle("Enter New Borehole ID");
        popUpStage.show();

        NewBHPopUpController puc = loader.getController();
        puc.setParent(this);

    }

    void addBHToTree(String id) {
            GUI.project.getBoreholes().add(new Borehole(id));
            TreeItem<String> newBH = new TreeItem<>(id);
            root.getChildren().add(newBH);
            root.setExpanded(true);
            tree.setRoot(root);
            System.out.println(GUI.project.getBoreholes().toString());

    }
}
