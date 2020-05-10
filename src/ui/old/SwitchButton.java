package ui.old;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;

public class SwitchButton extends Label {
    public boolean isSwitchedOn() {
        return switchedOn.get();
    }

    public SimpleBooleanProperty switchedOnProperty() {
        return switchedOn;
    }

    public void setSwitchedOn(boolean switchedOn) {
        this.switchedOn.set(switchedOn);
    }

    private SimpleBooleanProperty switchedOn;

    public SwitchButton() {
        Button switchBtn = new Button();
        switchBtn.setStyle("-fx-background-radius: 5em; " +
                "-fx-min-width: 15px; " +
                "-fx-min-height: 15px; " +
                "-fx-max-width: 15px; " +
                "-fx-max-height: 15px;");
        switchBtn.setPrefWidth(40);
        switchBtn.setPrefHeight(15);
        switchBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                switchedOn.set(!switchedOn.get());
            }
        });

        setGraphic(switchBtn);

        switchedOn.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov,
                                Boolean t, Boolean t1) {
                if (t1) {
                    setText("YES");
                    setStyle("-fx-background-color: red;-fx-text-fill:white;");
                    setContentDisplay(ContentDisplay.RIGHT);
                } else {
                    setText("NO");
                    setStyle("-fx-background-color: grey;-fx-text-fill:black;");
                    setContentDisplay(ContentDisplay.LEFT);
                }
            }
        });

        switchedOn.set(false);
    }

    public SimpleBooleanProperty switchOnProperty() {
        return switchedOn;
    }
}

