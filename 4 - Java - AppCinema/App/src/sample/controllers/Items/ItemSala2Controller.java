package sample.controllers.Items;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sample.controllers.CinemaDashboardController;
import sample.models.entity.Sala;

public class ItemSala2Controller extends CinemaDashboardController implements Initializable  {

  public Label numeroSala;
  public AnchorPane paneItemSala;
  private CinemaDashboardController cinemaDashboardController;
  private Integer sala;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
  }

  public void setNumeroSalaITem(Integer numeroSalaItem) {
    this.sala=numeroSalaItem;
    numeroSala.setText(numeroSalaItem.toString());
  }

  public void clickItemSala(MouseEvent mouseEvent) {
    cinemaDashboardController.setItemSalaSelected(sala);
  }

  public void setCinemaDashboardController(
      CinemaDashboardController cinemaDashboardController) {
    this.cinemaDashboardController = cinemaDashboardController;
  }
}
