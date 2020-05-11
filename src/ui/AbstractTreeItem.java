package ui;

import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;

public abstract class AbstractTreeItem<T> extends TreeItem<T> {

    public AbstractTreeItem(T value){
        super(value);
    }
    public AbstractTreeItem(T value, ImageView iv){
        super(value, iv);
    }
}
