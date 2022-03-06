package sample.service.Implementations;

import java.sql.Date;
import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import sample.models.dao.interfaceDAO.TicketDao;
import sample.models.entity.Ticket;
import sample.service.BookingService;

public class DefaultBookingService implements BookingService {

  @Override
  public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
    return java.sql.Date.valueOf(dateToConvert);
  }

  public void setChoicheBoxListFilmOrari(DatePicker dataTicket, Date dataProiezione, List<Ticket> ticketList, ChoiceBox<String> listFilmTicket, ChoiceBox<String> listOrarioTicket,
      TicketDao ticketDao) {
    ticketList = ticketDao.queryTicket(dataProiezione);
    var filmList = ticketList.stream().map(Ticket::getTitolo).collect(Collectors.toList());
    listFilmTicket.setItems(FXCollections.observableList(filmList));
    List<Ticket> finalTicketList = ticketList;
    listFilmTicket.setOnAction((actionEvent -> {
      String selectedFilm = listFilmTicket.getSelectionModel().getSelectedItem();
      var selectedOrario = finalTicketList.stream().filter(src->src.getTitolo().equals(selectedFilm)).collect(
          Collectors.toList());
      listOrarioTicket.setItems(FXCollections.observableList(selectedOrario.stream().map(Ticket::getOrarioProiezione).collect(
          Collectors.toList())));
    }));
  }
  public void setChoicheBoxListFilmOrari(DatePicker dataTicket,List<Ticket> ticketList, ChoiceBox<String> listFilmTicket, ChoiceBox<String> listOrarioTicket,
      TicketDao ticketDao) {
    dataTicket.setChronology(Chronology.ofLocale(Locale.ITALIAN));
    ticketList = ticketDao.queryTicket();
    var filmList = ticketList.stream().map(Ticket::getTitolo).collect(Collectors.toList());
    listFilmTicket.setItems(FXCollections.observableList(filmList));
    List<Ticket> finalTicketList = ticketList;
    listFilmTicket.setOnAction((actionEvent -> {
      String selectedFilm = listFilmTicket.getSelectionModel().getSelectedItem();
      var selectedOrario = finalTicketList.stream().filter(src->src.getTitolo().equals(selectedFilm)).collect(
          Collectors.toList());
      listOrarioTicket.setItems(FXCollections.observableList(selectedOrario.stream().map(Ticket::getOrarioProiezione).collect(
          Collectors.toList())));
    }));
  }
}
