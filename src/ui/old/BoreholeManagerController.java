package ui.old;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BoreholeManagerController implements ControlledScreen, Observer{

    @FXML
    public TreeView<String> tree;
    @FXML
    public TextField pNumber;
    @FXML
    public TextField ppAddress;
    @FXML
    public TextField pManager;
    @FXML
    public TextField pLocation;
    private ui.old.ScreensController myController;
    private int numBH = 0;
    @FXML
    private TreeItem<String> root;
    private Project project;


    public BoreholeManagerController() {
        System.out.println("BMC constructed");

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
        this.root = new TreeItem<>("Boreholes");
        tree.setRoot(root);
    }

    public void addSample(ActionEvent actionEvent) {
        TreeItem<String> itemChild = new TreeItem<>("Child" + numBH);
        root.getChildren().add(itemChild);
        root.setExpanded(true);
        numBH++;
    }


    public void goToProjectInfoPage(ActionEvent actionEvent) {
        myController.setScreen(Main.screen2ID);
    }


    @Override
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("bmc initializer");

        tree.setEditable(true);
        tree.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> param) {
                return new TextFieldTreeCellImpl();
            }
        });

        System.out.println("reached line 108");
    }

    private void calledAmethod() {
        System.out.println("called a method");
    }

    public void initData(Project project){
        this.project = project;
        this.pManager.setText(this.project.getManager());
        this.pNumber.setText(this.project.getNumber());
        this.ppAddress.setText(this.project.getAddress());
    }

    public void submitPage(ActionEvent actionEvent) {
        this.myController.setScreen(Main.screen3ID);
    }

    public void setppAddress(String address) {
        System.out.println("set address method called");
        ppAddress.setText(address);
    }

    public String getppAddress() {
        return this.ppAddress.getText();
    }

    public void refreshProjectInfo(ActionEvent actionEvent) {
        System.out.println(this.project.getAddress());
        this.ppAddress.setText(this.project.getAddress());
        this.pNumber.setText(this.project.getNumber());
        this.pManager.setText(this.project.getManager());
    }

    @Override
    public void update() {
        this.pManager.setText(Main.project.getManager());
        System.out.println("BMC observed");
    }

//    public void refreshLabels() {
//        this.pManager.setText("BLAHBLAH");
//        this.pNumber.setText(Main.project.getNumber());
//        this.pAddress.setText(Main.project.getAddress());
//    }

    // https://docs.oracle.com/javafx/2/ui_controls/tree-view.htm#BABGHEHF
    private final class TextFieldTreeCellImpl extends TreeCell<String> {

        private TextField textField;

        public TextFieldTreeCellImpl() {
        }

        @Override
        public void startEdit() {
            super.startEdit();

            if (textField == null) {
                createTextField();
            }
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setText((String) getItem());
            setGraphic(getTreeItem().getGraphic());
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(getTreeItem().getGraphic());
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setOnKeyReleased(new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ENTER) {
                        commitEdit(textField.getText());
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
}
