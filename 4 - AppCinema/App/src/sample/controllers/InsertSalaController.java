package sample.controllers;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.database.DatabaseConnection;
import sample.helpers.StageHelper;
import sample.models.dao.implDAO.CinemaDAOImpl;
import sample.models.dao.implDAO.SalaDAOImpl;
import sample.models.dao.interfaceDAO.CinemaDAO;
import sample.models.dao.interfaceDAO.SalaDAO;
import sample.models.entity.Sala;
import sample.models.enumerations.AUDIO;
import sample.models.enumerations.TECNOLOGIA;

public class InsertSalaController implements Initializable {

  public AnchorPane paneSala;
  public TextField numeroSala;
  public TextField capienza;
  public ChoiceBox<TECNOLOGIA> tecnologia;
  public ChoiceBox<AUDIO> audio;
  private SalaDAO salaDAO;
  private CinemaDAO cinemaDAO;

  public void conferma(ActionEvent actionEvent) {
    cinemaDAO = new CinemaDAOImpl(DatabaseConnection.getConnection());
    cinemaDAO.updatePlusNumeroSala();
    salaDAO = new SalaDAOImpl(DatabaseConnection.getConnection());
    final var sala = new Sala(
        Integer.parseInt(numeroSala.getText()),
        Integer.parseInt(capienza.getText()),
        tecnologia.getValue().toString(),
        audio.getValue().getAudio());
    salaDAO.queryInsertSala(sala);
    StageHelper.close(paneSala);
  }

  public void annulla(ActionEvent actionEvent) {
    StageHelper.close(paneSala);
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    tecnologia.setItems(FXCollections.observableList(Arrays.stream(TECNOLOGIA.values()).toList()));
    audio.setItems(FXCollections.observableList(Arrays.stream(AUDIO.values()).toList()));
    tecnologia.setValue(TECNOLOGIA.DEFAULT);
    audio.setValue(AUDIO.DEFAULT);
  }
}
