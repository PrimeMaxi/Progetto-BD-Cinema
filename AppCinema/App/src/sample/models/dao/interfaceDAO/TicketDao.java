package sample.models.dao.interfaceDAO;

import java.sql.Date;
import java.util.List;
import sample.models.entity.Ticket;

public interface TicketDao {

  List<Ticket> queryTicket(Date date);

  List<Ticket> queryTicket();
}
