package app.view;

import app.model.DataFile;
import app.model.MapFile;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


import java.io.File;

public class MapController {

    @FXML
    private ListView<MapFile> mapList;
    @FXML
    private ListView<DataFile> dataList;

    public void start(Stage primaryStage){

    }
}
