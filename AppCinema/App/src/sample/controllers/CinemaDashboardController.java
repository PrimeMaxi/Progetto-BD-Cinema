package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import sample.Application;
import sample.controllers.Items.ItemSala2Controller;
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

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    setGridPaneCinema();
  }

  public void setGridPaneCinema(){


    proiezioneDAO = new ProiezioneDAOImpl(DatabaseConnection.getConnection());
    final var listProiezioni = proiezioneDAO.queryListProiezioniFilm();

    salaDAO = new SalaDAOImpl(DatabaseConnection.getConnection());
    final var listSala = salaDAO.queryRetriveSala();
    //Colonna di sale
    setColumnSala(listSala,listProiezioni);


  }

  private void setRowsProiezioni(Integer idSala, List<Proiezione> listProiezioni) {
    int column =1;
    int row = idSala-1;
    var listProiezioniCurrent = listProiezioni.stream().filter(src-> Objects.equals(
        src.getSala().getIdSala(), idSala) && checkLocalDate(src.getInizioData(),src.getFineData())).collect(Collectors.toList());
    listProiezioni.removeAll(listProiezioniCurrent);
    var size = listProiezioniCurrent.size();
    var listOrari = listProiezioniCurrent.stream().map(Proiezione::getOrarioProiezione).toList();

    try {
      if(listProiezioniCurrent.isEmpty()){
        for (int i = 0; i < 4; i++) {
          var pane = getItemNoFilm();
          gridPaneCinema.add(
              (Node) pane,
              column++,
              row);
        }
      }else{
        for(ORARI orario : ORARI.values()){
          if(listOrari.contains(orario.toString())){
            var found = listProiezioniCurrent.stream().filter(src-> Objects.equals(src.getOrarioProiezione(),
                orario.toString())).findFirst();
            var pane = getItemFilm(found.get());
            gridPaneCinema.add(
                (Node) pane,
                column++,
                row);
          }else{
            var paneNoImage = getItemNoFilm();
            gridPaneCinema.add(
                (Node) paneNoImage,
                column++,
                row);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void setColumnSala(List<Sala> listSala, List<Proiezione> listProiezioni) {
    int row = 0;
    try {
      for (var item : listSala){
        FXMLLoader itemSalaFXML = new FXMLLoader();
        itemSalaFXML.setLocation(Application.class.getResource("viewsRefactor/ItemSala2.fxml"));
        var pane = itemSalaFXML.load();
        ItemSala2Controller itemController = itemSalaFXML.getController();
        itemController.setNumeroSalaITem(item.getIdSala());
        gridPaneCinema.add(
            (Node) pane,
            0,
            row++);
        setMarginGrid();
        GridPane.setMargin((Node) pane, new Insets(10));
        setRowsProiezioni(item.getIdSala(),listProiezioni);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  private void setMarginGrid(){
    gridPaneCinema.setMinWidth(Region.USE_COMPUTED_SIZE);
    gridPaneCinema.setPrefWidth(Region.USE_COMPUTED_SIZE);
    gridPaneCinema.setMaxWidth(Region.USE_COMPUTED_SIZE);
    gridPaneCinema.setMaxWidth(Region.USE_COMPUTED_SIZE);
    gridPaneCinema.setPrefHeight(Region.USE_COMPUTED_SIZE);
    gridPaneCinema.setMaxHeight(Region.USE_COMPUTED_SIZE);
  }

  private Object getItemFilm(Proiezione item) throws IOException {
    FXMLLoader itemProiezioneFXML = new FXMLLoader();
    itemProiezioneFXML.setLocation(
        Application.class.getResource("viewsRefactor/ItemFilm.fxml"));
    var pane = itemProiezioneFXML.load();
    ItemFilmController itemController = itemProiezioneFXML.getController();
    itemController.setNomeFilmItem(item.getFilm().getTitolo());
    return pane;
  }
  private Object getItemNoFilm() throws IOException {
    FXMLLoader itemProiezioneFXML = new FXMLLoader();
    itemProiezioneFXML.setLocation(
        Application.class.getResource("viewsRefactor/ItemNO.fxml"));
    return itemProiezioneFXML.load();
  }
  private boolean checkLocalDate(java.util.Date min, java.util.Date max){
    var currentDate = Date.valueOf(LocalDate.now());
    return currentDate.after(min) && currentDate.before(max);
  }
}