package com.inventor.viewUtils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageUtils{

    public ImageView createIcon(String iconURL, int width, int height) {
        ImageView imageView = new ImageView();
        imageView.setImage(new Image(iconURL, width,height, false, false));
        return imageView;
    }

}
