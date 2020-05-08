package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Borehole;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
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
    @FXML public Button deleteBH;

    //Controllers


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
            System.out.println("# bhs: " + GUI.project.getBoreholes().size());

    }

    public void removeBH(ActionEvent actionEvent) {
        TreeItem<String> selectedItem = tree.getSelectionModel().getSelectedItem();
        if (selectedItem == null){
            System.out.println("nothing to remove!");
        } else if (selectedItem == root) {
            System.out.println("can't remove the root!");
        } else {
            System.out.println(selectedItem.getValue());
            root.getChildren().remove(selectedItem);
            GUI.project.removeBhById(selectedItem.getValue());
            System.out.println("# bhs:" + GUI.project.getBoreholes().size());

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tree.setEditable(true);
        tree.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> param) {
                return new MainScreenController.TextFieldTreeCellImpl();
            }
        });

    }

    // https://docs.oracle.com/javafx/2/ui_controls/tree-view.htm#BABGHEHF
    private final class TextFieldTreeCellImpl extends TreeCell<String> {

        private TextField textField;
        private ContextMenu addSampleMenu = new ContextMenu();

        public TextFieldTreeCellImpl() {
            MenuItem menuItem = new MenuItem("Add Sample");
            addSampleMenu.getItems().add(menuItem);
            menuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
                    try {
                        Parent root = loader.load();
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setTitle("Add a new Sample");
                        stage.show();

                        SampleController sampleController = loader.getController();
                        sampleController.setParent(MainScreenController.this);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
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
                    if (
                            getTreeItem().isLeaf()&&getTreeItem().getParent()!= null
                    ){
                        setContextMenu(addSampleMenu);
                    }
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
