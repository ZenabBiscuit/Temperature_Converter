package com.intershala.javafxapp;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyMain extends Application {

    public static void main(String args[]){
        launch(args);

    }

    @Override
    public void init() throws Exception {
        System.out.println("init");
        super.init();
    }

    private MenuBar createMenu(){
        Menu fileMenu = new Menu("File");

        MenuItem quitMenuItem = new MenuItem("Quit");
        quitMenuItem.setOnAction(event -> Platform.exit());
        fileMenu.getItems().addAll(quitMenuItem);

        Menu helpMenu = new Menu("Help");
        MenuItem aboutApp = new MenuItem("About");
        helpMenu.getItems().addAll(aboutApp);
        aboutApp.setOnAction(event -> aboutApp());

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu,helpMenu);
        return menuBar;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("start");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("app_layout.fxml"));

        VBox rootNode = loader.load();
        MenuBar menuBar = createMenu();
        rootNode.getChildren().add(0, menuBar);


        Scene scene = new Scene(rootNode);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Temperature Converter");
        primaryStage.show();
    }


    private void aboutApp() {
        Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
        alertDialog.setTitle("Temperature Converter Tool");
        alertDialog.setContentText("This is a tool that converts temperature from Celsius to Fahrenheit and from Fahrenheit to Celsius");
        alertDialog.show();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("stop");
        super.stop();
    }
}
