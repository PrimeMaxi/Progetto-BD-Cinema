package sample.controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Application;
import sample.database.DatabaseConnection;
import sample.models.dao.implDAO.CinemaDAOImpl;
import sample.models.dao.interfaceDAO.CinemaDAO;
import sample.models.entity.Cinema;
import sample.service.SceneCreator;

public class DashboardController implements Initializable {

  public Label nomeCinema;
  public ImageView modificaCinema;
  public HBox loadCinema;
  public Pane dashboardPane;
  private CinemaDAO cinemaDAO;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    initializeCinema();
    tastoModifica();
    loadDashboard();
  }

  public void buttonCinemaAction(ActionEvent actionEvent) {
  }

  public void buttonTicketAction(ActionEvent actionEvent) {
  }


  private void initializeCinema(){
    try{
      cinemaDAO = new CinemaDAOImpl(DatabaseConnection.getConnection());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    Cinema cinema = cinemaDAO != null ? cinemaDAO.retriveCinema() : null;
    nomeCinema.setText(cinema != null ? cinema.getNomeCinema() : null);
  }
  private void tastoModifica(){
    modificaCinema.addEventHandler(
        MouseEvent.MOUSE_CLICKED,
        (EventHandler<Event>) event -> {
          System.out.println("Tasto modifica premuto");
          try {
            SceneCreator.launchScene("views/modificaCinema.fxml");
          } catch (IOException e) {
            e.printStackTrace();
          }
        });
  }

  private void loadDashboard(){
    try {
      final var pane = FXMLLoader.load(Objects.requireNonNull(
          Application.class.getResource("viewsRefactor/ChartsDashboard.fxml")));
      dashboardPane.getChildren().add((Node) pane);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void buttonDashboardAction(ActionEvent actionEvent) {
    try {
      final var pane = FXMLLoader.load(
          Objects.requireNonNull(Application.class.getResource("viewsRefactor/ChartsDashboard.fxml")));
      dashboardPane.getChildren().add((Node) pane);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
