package ui;

import javafx.fxml.Initializable;

public interface ControlledScreen extends Initializable{

    public abstract void setScreenPage(ScreensController screenPage);
}
