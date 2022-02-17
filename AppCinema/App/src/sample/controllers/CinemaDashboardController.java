package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import sample.Application;
import sample.database.DatabaseConnection;
import sample.models.dao.implDAO.FilmDaoImpl;
import sample.models.dao.implDAO.ProiezioneDAOImpl;
import sample.models.dao.implDAO.SalaDAOImpl;
import sample.models.dao.interfaceDAO.FilmDAO;
import sample.models.dao.interfaceDAO.ProiezioneDAO;
import sample.models.dao.interfaceDAO.SalaDAO;
import sample.models.entity.Proiezione;
import sample.models.entity.Sala;
import sample.models.enumerations.ORARI;

public class CinemaDashboardController implements Initializable {

  public GridPane gridPaneCinema;
  private SalaDAO salaDAO;
  private ProiezioneDAO proiezioneDAO;
  private ItemSalaController itemSalaController;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    setGridPaneCinema();
  }

  public void setGridPaneCinema(){
    salaDAO = new SalaDAOImpl(DatabaseConnection.getConnection());
    final var listSala = salaDAO.queryRetriveSala();
    //Colonna di sale
    setColumnSala(listSala);


  }

  private void setRowsProiezioni(Integer idSala, List<Proiezione> listProiezioni, Date currentDate) {
    try {
      for (var item : listProiezioni){
        if (Objects.equals(idSala, item.getSala().getIdSala())
            && (currentDate.after(item.getInizioData()) && currentDate.before(item.getFineData()))) {

          System.out.println(item.getOrarioProiezione());
          FXMLLoader itemProiezioneFXML = new FXMLLoader();
          itemProiezioneFXML.setLocation(
              Application.class.getResource("viewsRefactor/ItemFilm.fxml"));
          var pane = itemProiezioneFXML.load();
          ItemFilmController itemController = itemProiezioneFXML.getController();
          itemController.setNomeFilmItem(item.getFilm().getTitolo());

          if(Objects.equals(item.getOrarioProiezione(), "16-18"))
            gridPaneCinema.add((Node) pane, 1, idSala);
          if (Objects.equals(item.getOrarioProiezione(), "18-20"))
            gridPaneCinema.add((Node) pane, 2, idSala);
          if (item.getOrarioProiezione() == "20-22")
            gridPaneCinema.add((Node) pane, 3, idSala);
          if (Objects.equals(item.getOrarioProiezione(), ORARI.FASCIA_22_24.toString()))
            gridPaneCinema.add((Node) pane, 4, idSala);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void setColumnSala(List<Sala> listSala) {
    proiezioneDAO = new ProiezioneDAOImpl(DatabaseConnection.getConnection());
    final var listProiezioni = proiezioneDAO.queryListProiezioniFilm();

    var currentDate = Date.valueOf(LocalDate.now());

    int row = 0;
    try {
      for (var item : listSala){
        FXMLLoader itemSalaFXML = new FXMLLoader();
        itemSalaFXML.setLocation(Application.class.getResource("viewsRefactor/ItemSala.fxml"));
        var pane = itemSalaFXML.load();
        ItemSalaController itemController = itemSalaFXML.getController();
        itemController.setNumeroSalaITem(item.getIdSala());
        setRowsProiezioni(item.getIdSala(),listProiezioni,currentDate);
          gridPaneCinema.add(
              (Node) pane,
              0,
              row++
          );
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
