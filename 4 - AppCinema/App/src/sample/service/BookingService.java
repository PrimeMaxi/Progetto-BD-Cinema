package sample.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import sample.controllers.BookingController;
import sample.models.dao.interfaceDAO.TicketDao;
import sample.models.entity.Ticket;

public interface BookingService {

  Date convertToDateViaSqlDate(LocalDate dateToConvert);

  void setChoicheBoxListFilmOrari(DatePicker dataTicket, Date dataProiezione, List<Ticket> ticketList, ChoiceBox<String> listFilmTicket, ChoiceBox<String> listOrarioTicket,
      TicketDao ticketDao);

  void setChoicheBoxListFilmOrari(DatePicker dataTicket,List<Ticket> ticketList, ChoiceBox<String> listFilmTicket, ChoiceBox<String> listOrarioTicket,
      TicketDao ticketDao);
}
