package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Borehole;
import model.SoilSample;
import model.enums.Colour;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    //Buttons
    @FXML public Button addBH;
    @FXML public Button saveAll;
    @FXML public Button deleteBH;

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


    //Images
    private Image blueSq =
            new Image(getClass().getResourceAsStream("images/#5f9ea0.PNG"));
    private Image greySq = new Image(getClass().getResourceAsStream("images/#778899.PNG"));
    private Image brownSq = new Image(getClass().getResourceAsStream("images/#d2b48c.PNG"));


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
            BHTreeItem<String> newBH = new BHTreeItem<>(id);
            root.getChildren().add(newBH);
            root.setExpanded(true);
            tree.setRoot(root);
            System.out.println("# bhs: " + GUI.project.getBoreholes().size());

    }

    public void removeBH(ActionEvent actionEvent) {
        BHTreeItem<String> selectedItem = (BHTreeItem<String>) tree.getSelectionModel().getSelectedItem();
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

    private ImageView setIcon(int height, int width, Image img) {
        ImageView iv = new ImageView();
        iv.setImage(img);
        iv.setFitWidth(height);
        iv.setFitHeight(width);
        return iv;

    }

    public void addSample(SoilSample sample) {
        BHTreeItem<String> selectedItem = (BHTreeItem<String>) tree.getSelectionModel().getSelectedItem();
        Colour c = sample.getColour();
        ImageView icon;
        switch(c){
            case BLUE:
                icon = setIcon(16, 16, blueSq);
                break;
            case GREY:
                icon = setIcon(16, 16, greySq);
                break;
            default:
                icon = setIcon(16, 16, brownSq);
        }

        selectedItem.getChildren().add(new SampleTreeItem<String>(sample.getId(), icon));
        selectedItem.setExpanded(true);
        System.out.println(sample);
    }

    // https://docs.oracle.com/javafx/2/ui_controls/tree-view.htm#BABGHEHF
    private final class TextFieldTreeCellImpl extends TreeCell<String> {

        private TextField textField;
        private ContextMenu addSampleMenu = new ContextMenu();
        private ContextMenu removeSampleMenu = new ContextMenu();

        TextFieldTreeCellImpl() {
            MenuItem add = new MenuItem("Add Sample");
            addSampleMenu.getItems().add(add);
            add.setOnAction(new EventHandler<ActionEvent>() {
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


                        String bhID = tree.getSelectionModel().getSelectedItem().getValue();
                        Borehole bh = GUI.project.getBorehole(bhID);

                        SampleController sampleController = loader.getController();
                        sampleController.setBh(bh);
                        sampleController.setParent(MainScreenController.this);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });

            MenuItem remove = new MenuItem("Remove Sample");
            removeSampleMenu.getItems().add(remove);
            remove.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String bhId = tree.getSelectionModel().getSelectedItem().getParent().getValue();
                    String sampleID = tree.getSelectionModel().getSelectedItem().getValue();

                    System.out.println("parent BH is: " + bhId);
                    System.out.println("sample id is: " + sampleID);
                    Borehole bh = GUI.project.getBorehole(bhId);
                    bh.removeSampleById(sampleID);

                    SampleTreeItem<String> selectedItem = (SampleTreeItem<String>) tree.getSelectionModel().getSelectedItem();
                    selectedItem.getParent().getChildren().remove(selectedItem);
                }
            });

        }

//        @Override
//        public void startEdit() {
//            super.startEdit();
//
//            if (textField == null) {
//                createTextField();
//            }
//            setText(null);
//            setGraphic(textField);
//            textField.selectAll();
//        }

//        @Override
//        public void cancelEdit() {
//            super.cancelEdit();
//            setText((String) getItem());
//            setGraphic(getTreeItem().getGraphic());
//        }

        @Override
        // TODO: make it so that you can only right click on BHs and not samples
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
                    if (getTreeItem().getClass()==BHTreeItem.class &&getTreeItem().getParent()!= null){
                        setContextMenu(addSampleMenu);
                    } else if (getTreeItem().getClass()==SampleTreeItem.class &&getTreeItem().getClass()!=null){
                        setContextMenu(removeSampleMenu);
                    }
                }
            }
        }
//        private void createTextField() {
//            textField = new TextField(getString());
//            textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
//
//                @Override
//                public void handle(KeyEvent t) {
//                    if (t.getCode() == KeyCode.ENTER) {
//                        commitEdit(textField.getText());
//                    } else if (t.getCode() == KeyCode.ESCAPE) {
//                        cancelEdit();
//                    }
//                }
//            });
//        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
}
