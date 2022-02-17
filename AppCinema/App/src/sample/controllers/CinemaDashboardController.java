package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import sample.Application;
import sample.database.DatabaseConnection;
import sample.models.dao.implDAO.SalaDAOImpl;
import sample.models.dao.interfaceDAO.SalaDAO;
import sample.models.entity.Sala;

public class CinemaDashboardController implements Initializable {

  public GridPane gridPaneCinema;
  private SalaDAO salaDAO;
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

  private void setColumnSala(List<Sala> listSala) {
    int row = 0;
    try {
      for (var item : listSala){
        FXMLLoader itemSalaFXML = new FXMLLoader();
        itemSalaFXML.setLocation(Application.class.getResource("viewsRefactor/ItemSala.fxml"));
        var pane = itemSalaFXML.load();
        ItemSalaController itemController = itemSalaFXML.getController();
        itemController.setNumeroSalaITem(item.getIdSala());
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
