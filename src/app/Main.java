package app;

import app.view.MapController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MapFile.fxml"));
        AnchorPane rootLayout = loader.load();

        MapController mapControl = loader.getController();

        primaryStage.setTitle("Hello World");

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
