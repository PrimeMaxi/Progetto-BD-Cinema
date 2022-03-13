package sample.models.dao.interfaceDAO;

import java.sql.Date;
import java.util.List;
import sample.models.entity.Ticket;

public interface TicketDao {

  List<Ticket> queryTicket(Date date);

  List<Ticket> queryTicket();

  Ticket queryTicketOne(Date date, String film, String orario);

  List<Ticket> queryListOccupiedSeats(Integer idProiezione, String orario, Integer idSala);
}
