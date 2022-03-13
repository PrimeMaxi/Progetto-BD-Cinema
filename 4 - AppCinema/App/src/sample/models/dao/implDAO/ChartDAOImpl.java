package sample.models.dao.implDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import sample.models.dao.interfaceDAO.ChartDAO;
import sample.models.entity.FasciOrari;
import sample.models.entity.MaxAffluenza;
import sample.models.entity.Ricavi;
import sample.models.entity.SalaAmount;

public class ChartDAOImpl implements ChartDAO {

  private final static String sqlRicavi = "select f.titolo as Film, SUM(pr.prezzo) as Ricavi from film as f inner join proiezione as pr on f.idfilm=pr.idfilmfk inner join biglietto as b on pr.idproiezione = b.idproiezionefk GROUP BY f.titolo ORDER BY Ricavi DESC";
  private final static String sqlFasciORari = "select pr.orarioproiezione, SUM(b.idproiezionefk) as affluenza from proiezione as pr inner join biglietto as b on pr.idproiezione = b.idproiezionefk  group by pr.orarioproiezione ORDER by affluenza DESC";
  private final static String sqlChartSalaOrari = "select pr.idsalafk ,pr.orarioproiezione, SUM(b.idproiezionefk) as MAXaffluenza from proiezione as pr inner join biglietto as b on pr.idproiezione = b.idproiezionefk inner join sala as s on pr.idsalafk = s.idsala group by pr.orarioproiezione , pr.idsalafk ORDER by MAXaffluenza DESC";
  private final static String sqlAmountSala = "select idsala from sala";

  private Statement queryRicavi, queryFasciOrari, queryChartSalaOrari, queryAmountSala;

  public ChartDAOImpl(Connection connection) {
    try {
      queryRicavi = connection.createStatement();
      queryFasciOrari = connection.createStatement();
      queryChartSalaOrari = connection.createStatement();
      queryAmountSala = connection.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  @Override
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
  @Override
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
  @Override
  public List<MaxAffluenza> queryChartSalaOrari(){
    var maxAffluenza = new ArrayList<MaxAffluenza>();
    try {
      var rs = queryChartSalaOrari.executeQuery(sqlChartSalaOrari);
      while(rs.next()){
        maxAffluenza.add(
            new MaxAffluenza(
                rs.getInt(1),
                rs.getString(2),
                rs.getInt(3)
            )
        );
      }
      return maxAffluenza;
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
    }finally{
      try {
        queryChartSalaOrari.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return Collections.emptyList();
  }
  @Override
  public List<SalaAmount> queryAmountSala(){
    var salaAmount = new ArrayList<SalaAmount>();
    try {
      var rs = queryAmountSala.executeQuery(sqlAmountSala);
      while(rs.next()){
        salaAmount.add(
            new SalaAmount(
                rs.getInt(1)
            )
        );
      }
      return salaAmount;
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
    }finally{
      try {
        queryAmountSala.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return Collections.emptyList();
  }
}
