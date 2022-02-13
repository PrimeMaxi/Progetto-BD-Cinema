package sample.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import sample.service.SceneCreator;

public class Main {

  public Button buttonEntra;


  public void entra(ActionEvent actionEvent) throws IOException {
    SceneCreator.launchScene("viewsRefactor/Dashboard.fxml");
  }
}
