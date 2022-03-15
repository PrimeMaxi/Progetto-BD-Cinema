package sample.models.dao.implDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import sample.models.dao.interfaceDAO.ProiezioneDAO;
import sample.models.entity.Proiezione;
import sample.models.enumerations.ORARI;

public class ProiezioneDAOImpl implements ProiezioneDAO {

  private static final String sqlProiezioniFilm =
      "select pr.idproiezione, pr.iniziodata, pr.finedata, pr.orarioproiezione, f.idfilm, f.titolo,pr.prezzo, pr.idsalafk from proiezione as pr inner join film as f on pr.idfilmfk=f.idfilm";
  private static final String sqlUpdateProiezione = "update proiezione set prezzo=?, orarioproiezione=? where idproiezione=?";

  private Statement queryListProiezioniFilm;
  private PreparedStatement queryUpdateProiezione;

  public ProiezioneDAOImpl(Connection connection){
    try {
      queryListProiezioniFilm = connection.createStatement();
      queryUpdateProiezione = connection.prepareStatement(sqlUpdateProiezione);
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
      e.printStackTrace();
    }
  }
  @Override
  public List<Proiezione> queryListProiezioniFilm(){
    var proiezioniList = new ArrayList<Proiezione>();
    try {
      var rs = queryListProiezioniFilm.executeQuery(sqlProiezioniFilm);
      while (rs.next()){
        proiezioniList.add(
            new Proiezione(
                rs.getInt(1),
                rs.getString(4),
                rs.getDate(2),
                rs.getDate(3),
                rs.getInt(5),
                rs.getString(6),
                rs.getInt(7),
                rs.getInt(8))
        );
      }
      return proiezioniList;
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  @Override
  public boolean queryUpdateProiezione(Integer idProiezione, Integer prezzo, ORARI orari){
    try {
      queryUpdateProiezione.setInt(1,prezzo);
      queryUpdateProiezione.setObject(2, orari, Types.OTHER);
      queryUpdateProiezione.setInt(3,idProiezione);
      return queryUpdateProiezione.execute();
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
      e.printStackTrace();
    }
    return false;
  }
}
