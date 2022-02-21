package sample.models.dao.implDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import sample.models.dao.interfaceDAO.TicketDao;
import sample.models.entity.Ticket;

public class TicketDaoImpl implements TicketDao {

  private static final String sqlTicket = "select pr.idproiezione, pr.orarioproiezione, f.idfilm, f.titolo from proiezione as pr inner join film as f on pr.idfilmfk = f.idfilm where pr.iniziodata <= ? and pr.finedata >= ?";

  private PreparedStatement queryTicket;

  public TicketDaoImpl(Connection connection){
    try {
      queryTicket = connection.prepareStatement(sqlTicket);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Ticket> queryTicket(Date date){
    var ticketList = new ArrayList<Ticket>();
    try {
      queryTicket.setDate(1,date);
      queryTicket.setDate(2,date);
      var rs = queryTicket.executeQuery();
      while(rs.next()){
        ticketList.add(new Ticket(
            rs.getInt(1), //idProiezione
            rs.getString(2),  //OrarioProiezione
            rs.getInt(3),     //IdFilm
            rs.getString(4)   //TitoloFilm
        ));
      }
      return ticketList;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  @Override
  public List<Ticket> queryTicket(){
    return queryTicket(new Date(Calendar.getInstance().getTime().getTime()));
  }
}
