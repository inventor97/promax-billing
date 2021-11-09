package com.inventor;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main  extends Application {

    public static Stage primaryStage;
    private double x;
    private double y;
    public static ResourceBundle bundle;
    static AnchorPane root;
    public static HostServices hostServices;

    static {
        root = new AnchorPane();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
        Scene scene =  new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("logo.png"));
        primaryStage.setAlwaysOnTop(false);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Main.this.x = event.getSceneX();
                Main.this.y = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - Main.this.x);
                primaryStage.setY(event.getScreenY() - Main.this.y);
            }
        });
        primaryStage.show();
    }
}
