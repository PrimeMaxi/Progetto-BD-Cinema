package sample.models.dao.implDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import sample.models.dao.interfaceDAO.TicketDao;
import sample.models.entity.Ticket;
import sample.models.enumerations.ORARI;

public class TicketDaoImpl implements TicketDao {

  private static final String sqlTicket = "select pr.idproiezione, pr.orarioproiezione, f.idfilm, f.titolo, pr.idsalafk from proiezione as pr inner join film as f on pr.idfilmfk = f.idfilm where pr.iniziodata <= ? and pr.finedata >= ?";
  private static final String sqlTicketOne = "select pr.idproiezione, pr.orarioproiezione, f.idfilm, f.titolo, pr.idsalafk from proiezione as pr inner join film as f on pr.idfilmfk = f.idfilm where pr.iniziodata <= ? and pr.finedata >= ? and f.titolo=? and pr.orarioproiezione=?";
  private static final String sqlOccupiedSeats = "select b.idbiglietto,p.prezzo,b.idproiezionefk, b.databiglietto,ps.filax,ps.postoy from biglietto as b inner join posto_prenotato as pp on b.idbiglietto=pp.idbigliettofk inner join proiezione as p on b.idproiezionefk=p.idproiezione inner join posto as ps on pp.idpostofk=ps.idposto where b.idproiezionefk=? and p.orarioproiezione=? and p.idsalafk=?";

  private PreparedStatement queryTicket;
  private PreparedStatement queryTicketOne;
  private PreparedStatement queryOccupiedSeats;

  public TicketDaoImpl(Connection connection){
    try {
      queryTicket = connection.prepareStatement(sqlTicket);
      queryTicketOne = connection.prepareStatement(sqlTicketOne);
      queryOccupiedSeats = connection.prepareStatement(sqlOccupiedSeats);
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
            rs.getString(4),   //TitoloFilm
            rs.getInt(5)      //idSala
        ));
      }
      return ticketList;
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  @Override
  public List<Ticket> queryTicket(){
    return queryTicket(new Date(Calendar.getInstance().getTime().getTime()));
  }

  @Override
  public Ticket queryTicketOne(Date date, String film, String orario){
    try {
      queryTicketOne.setDate(1,date);
      queryTicketOne.setDate(2,date);
      queryTicketOne.setString(3,film);
      queryTicketOne.setObject(4,ORARI.getORARI(orario), Types.OTHER);
      var rs = queryTicketOne.executeQuery();
      while(rs.next()){
        return new Ticket(
            rs.getInt(1), //idProiezione
            rs.getString(2),  //OrarioProiezione
            rs.getInt(3),     //IdFilm
            rs.getString(4),   //TitoloFilm
            rs.getInt(5), //idSala
            date                    //dataTicket
        );
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<Ticket> queryListOccupiedSeats(Integer idProiezione, String orario, Integer idSala){
    var listTicket = new ArrayList<Ticket>();
    try {
      queryOccupiedSeats.setInt(1,idProiezione);
      queryOccupiedSeats.setObject(2,ORARI.getORARI(orario),Types.OTHER);
      queryOccupiedSeats.setInt(3,idSala);
      var rs = queryOccupiedSeats.executeQuery();
      while(rs.next()){
        listTicket.add(new Ticket(
            rs.getInt(1), //idBiglietto
            rs.getDouble(2), //Prezzo
            rs.getInt(3), //idProiezione
            rs.getDate(4),//data biglietto
            rs.getString(5).charAt(0),//fila
            rs.getInt(6) //posto
        ));
      }
      return listTicket;
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
      e.printStackTrace();
    }
    return Collections.emptyList();
  }
}
