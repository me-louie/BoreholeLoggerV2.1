package ui;

import javafx.scene.image.ImageView;

public class SampleTreeItem<T> extends AbstractTreeItem<T>{
    public SampleTreeItem(T value) {
        super(value);
    }

    public SampleTreeItem(T value, ImageView iv){
        super(value, iv);
    }

}
