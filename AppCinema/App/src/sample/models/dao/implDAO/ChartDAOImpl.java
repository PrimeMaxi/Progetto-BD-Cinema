package sample.models.dao.implDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import sample.models.entity.FasciOrari;
import sample.models.entity.Ricavi;

public class ChartDAOImpl {

  private final static String sqlRicavi = "select f.titolo as Film, SUM(b.prezzo) as Ricavi from film as f inner join proiezione as pr on f.idfilm=pr.idfilmfk inner join biglietto as b on pr.idproiezione = b.idproiezionefk GROUP BY f.titolo ORDER BY Ricavi DESC";
  private final static String sqlFasciORari = "select orarioproiezione, COUNT(orarioproiezione) from proiezione group by orarioproiezione";
  private Statement queryRicavi, queryFasciOrari;

  public ChartDAOImpl(Connection connection) {
    try {
      queryRicavi = connection.createStatement();
      queryFasciOrari = connection.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  public List<Ricavi> queryRicavi(){
    var ricaviList = new ArrayList<Ricavi>();
    try {
      var rs = queryRicavi.executeQuery(sqlRicavi);
      while(rs.next()){
        ricaviList.add(
            new Ricavi(
                rs.getString(1),
                rs.getFloat(2)));
      }
      return ricaviList;
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
    }finally{
      try {
        queryRicavi.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return Collections.emptyList();
  }
  public List<FasciOrari> queryFasciOrari(){
    var fasciOrari = new ArrayList<FasciOrari>();
    try {
      var rs = queryRicavi.executeQuery(sqlFasciORari);
      while(rs.next()){
        fasciOrari.add(
            new FasciOrari(
                rs.getString(1),
                rs.getInt(2)
            )
        );
      }
      return fasciOrari;
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
    }finally{
      try {
        queryRicavi.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return Collections.emptyList();
  }
}
