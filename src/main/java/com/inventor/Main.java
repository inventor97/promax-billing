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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;
import java.util.ResourceBundle;

public class Main  extends Application {

    public static Stage primaryStage;
    private double x;
    private double y;
    static AnchorPane root;

    static {
        root = new AnchorPane();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Font.loadFont(String.valueOf(getClass().getResource("Poppins_regular.ttf")), 12);
        Main.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/main.fxml")));
        Scene scene =  new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("logo.png"));
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
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

    public static void main(String[] args) {
        launch(args);
    }
}
