package com.inventor.utils;

import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUtils {

    public static File openFile(AnchorPane root) {
        javafx.stage.FileChooser chooser = new javafx.stage.FileChooser();
        chooser.setTitle("Select Image File");
        chooser.getExtensionFilters().addAll(new javafx.stage.FileChooser.ExtensionFilter("Img Document files", "*.jpg", "*.png", "*.jpeg"));
        File file = chooser.showOpenDialog(root.getScene().getWindow());
        copyImg(file);
        return file;
    }

    private static boolean copyImg(File file) {
        JFileChooser fr = new JFileChooser();
        FileSystemView fw = fr.getFileSystemView();
        Path target = Paths.get(fw.getParentDirectory(new File("getDir.gdir")) + "/accounts/" + file.getName());
        try {
            Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
