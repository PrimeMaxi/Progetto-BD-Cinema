package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import sample.database.DatabaseConnection;
import sample.models.dao.implDAO.PostoDaoImpl;
import sample.models.dao.interfaceDAO.PostoDao;

public class TicketController implements Initializable {

  public GridPane gridPosti;
  private PostoDao postoDao;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    setGridPosti();
  }
  public void setGridPosti(){
    postoDao = new PostoDaoImpl(DatabaseConnection.getConnection());
    final var postiList = postoDao.queryRetrivePosto();

  }
}
