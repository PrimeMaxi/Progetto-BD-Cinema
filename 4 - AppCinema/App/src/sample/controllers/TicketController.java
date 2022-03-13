package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.chrono.Chronology;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import sample.Application;
import sample.database.DatabaseConnection;
import sample.models.dao.implDAO.PostoDaoImpl;
import sample.models.dao.implDAO.TicketDaoImpl;
import sample.models.dao.interfaceDAO.PostoDao;
import sample.models.dao.interfaceDAO.TicketDao;
import sample.models.entity.Posto;
import sample.models.entity.Ticket;
import sample.service.Implementations.DefaultTicketService;
import sample.service.TicketService;

public class TicketController implements Initializable {

  public static final String fila = "A";

  public GridPane gridPosti;
  private PostoDao postoDao;
  private AnchorPane seatItem;
  private TicketService ticketService = new DefaultTicketService();
  private Ticket ticket;


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
  }

  public void setGridPosti(){
    postoDao = new PostoDaoImpl(DatabaseConnection.getConnection());
    var sala = postoDao.queryRetrivePostiByIdSala(ticket.getIdSalaFk());
    var listFila = sala.stream().map(Posto::getFilaX).distinct().collect(Collectors.toList());

    int column = 0;
    int row = 1;

    for(Character fila : listFila){
      FXMLLoader fxmlLoader = new FXMLLoader();
      fxmlLoader.setLocation(Application.class.getResource("viewsRefactor/FilaItem.fxml"));
      try {
        AnchorPane anchorPane = fxmlLoader.load();
        FilaItemController filaItemController = fxmlLoader.getController();
        filaItemController.setFilaSeat(fila);
        gridPosti.add(anchorPane,column,row++);
      } catch (IOException e) {
        e.printStackTrace();
      }

    }
    column = 1;
    row = 1;

    for(Posto item : sala){
      try {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("viewsRefactor/SeatItem.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();

        SeatController seatController = fxmlLoader.getController();
        seatController.setNumberSeat(item.getPostoY());

        if(column == 15){
          column=1;
          row++;
        }

        gridPosti.add(anchorPane,column++,row);

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void setTicket(Ticket ticket) {
    this.ticket = ticket;
    System.out.println(ticket);
    setGridPosti();
  }
}
