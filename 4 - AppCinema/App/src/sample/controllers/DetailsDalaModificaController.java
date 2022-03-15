package sample.controllers;

import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.database.DatabaseConnection;
import sample.models.dao.implDAO.SalaDAOImpl;
import sample.models.dao.interfaceDAO.SalaDAO;
import sample.models.entity.Sala;
import sample.models.enumerations.AUDIO;
import sample.models.enumerations.TECNOLOGIA;

public class DetailsDalaModificaController {

  public AnchorPane paneDetailsModifica;
  public TextField capienza;
  public ChoiceBox<TECNOLOGIA> tecnlogia;
  public ChoiceBox<AUDIO> audio;
  private Sala sala;
  private SalaDAO salaDAO;

  public void setSala(Sala sala) {
    this.sala = sala;
    capienza.setText(sala.getCapienza().toString());
    tecnlogia.setItems(FXCollections.observableList(Arrays.stream(TECNOLOGIA.values()).toList()));
    audio.setItems(FXCollections.observableList(Arrays.stream(AUDIO.values()).toList()));
  }

  public void clickConferma(MouseEvent mouseEvent) {
    salaDAO = new SalaDAOImpl(DatabaseConnection.getConnection());
    salaDAO.queryUpdate(sala.getIdSala(),Integer.parseInt(capienza.getText()),tecnlogia.getValue(),audio.getValue());
    System.out.println("Update sala: " + sala);
  }
  public void clickAnnulla(MouseEvent mouseEvent) {
    var root = (Stage) paneDetailsModifica.getScene().getWindow();
    root.close();
  }
}
