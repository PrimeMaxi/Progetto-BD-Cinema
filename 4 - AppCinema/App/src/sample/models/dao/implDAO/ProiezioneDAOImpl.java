package sample.models.dao.implDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import sample.database.DatabaseUtil;
import sample.models.dao.interfaceDAO.ProiezioneDAO;
import sample.models.entity.Proiezione;
import sample.models.enumerations.ORARI;

public class ProiezioneDAOImpl implements ProiezioneDAO {

  private static final String sqlProiezioniFilm =
      "select pr.idproiezione, pr.iniziodata, pr.finedata, pr.orarioproiezione, f.idfilm, f.titolo,pr.prezzo, pr.idsalafk, pr.orainizio, pr.orafine from proiezione as pr inner join film as f on pr.idfilmfk=f.idfilm";
  private static final String sqlUpdateProiezione = "update proiezione set prezzo=?, orarioproiezione=? where idproiezione=?";
  private static final String sqlInsertProiezione = "INSERT INTO PROIEZIONE (iniziodata,fineData,orainizio,orafine,OrarioProiezione,Prezzo,idfilmfk,idsalafk) VALUES (?,?,?,?,?,?,?,?)";
  private static final String sqlDeleteProiezione = "DELETE FROM PROIEZIONE WHERE idproiezione = ? ";
  private static final String sqlUpdate = "UPDATE PROIEZIONE SET inizioData=?, fineData=?, oraInizio=?, oraFine=?, orarioProiezione=?, prezzo=?, idFilmFk=?, idSalaFk=? WHERE idproiezione=?";

  private Statement queryListProiezioniFilm;
  private PreparedStatement queryUpdateProiezione,queryInsertProiezione, queryDeleteProiezione, queryUpdate;

  public ProiezioneDAOImpl(Connection connection){
    try {
      queryListProiezioniFilm = connection.createStatement();
      queryUpdateProiezione = connection.prepareStatement(sqlUpdateProiezione);
      queryInsertProiezione = connection.prepareStatement(sqlInsertProiezione);
      queryDeleteProiezione = connection.prepareStatement(sqlDeleteProiezione);
      queryUpdate = connection.prepareStatement(sqlUpdate);
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
                rs.getInt(8),
                rs.getTime(9),
                rs.getTime(10))
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

  @Override
  public boolean queryInsertProiezione(Time oraInizio, Time oraFine, ORARI orari, Integer prezzo,
      Integer idFilmFk, Integer idSalaFk){
    return queryInsertProiezione(oraInizio,oraFine,orari,prezzo,idFilmFk,idSalaFk,null, null);
  }

  private boolean queryInsertProiezione(Time oraInizio, Time oraFine, ORARI orari, Integer prezzo,
      Integer idFilmFk, Integer idSalaFk, Date inizioData, Date fineData ){
    try {
      queryInsertProiezione.setDate(1,inizioData);
      queryInsertProiezione.setDate(2,fineData);
      queryInsertProiezione.setTime(3,oraInizio);
      queryInsertProiezione.setTime(4,oraFine);
      queryInsertProiezione.setObject(5,orari,Types.OTHER);
      queryInsertProiezione.setInt(6,prezzo);
      queryInsertProiezione.setInt(7,idFilmFk);
      queryInsertProiezione.setInt(8,idSalaFk);
      return queryInsertProiezione.execute();
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean queryUpdate(Proiezione proiezione){
    try {
      queryUpdate.setDate(1,proiezione.getInizioData());
      queryUpdate.setDate(2,proiezione.getFineData());
      queryUpdate.setTime(3,proiezione.getOraInizio());
      queryUpdate.setTime(4,proiezione.getOraFine());
      queryUpdate.setObject(5,ORARI.getORARI(proiezione.getOrarioProiezione()));
      queryUpdate.setDouble(6,proiezione.getPrezzo());
      queryUpdate.setInt(7,proiezione.getFilm().getIdFilm());
      queryUpdate.setInt(8,proiezione.getSala().getIdSala());
      return queryUpdate.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean queryDeleteProiezione(Proiezione proiezione){
    final var from = proiezione.getInizioData();
    final var to = proiezione.getFineData();
    final var currentDate = DatabaseUtil.getCurrentDate();
    if(from.equals(currentDate) && !to.equals(currentDate)){
      proiezione.setInizioData(DatabaseUtil.getDatePlusDays(1));
      queryUpdate(proiezione);
      return true;
    }else if(!from.equals((currentDate)) && !to.equals(currentDate)){
      setQueryDeleteProiezione(proiezione);
      queryInsertProiezione(proiezione.getOraInizio(),proiezione.getOraFine(),
          ORARI.getORARI(proiezione.getOrarioProiezione()),proiezione.getPrezzo(),
          proiezione.getFilm().getIdFilm(),proiezione.getIdProiezione(),
          proiezione.getInizioData(),DatabaseUtil.getDateYesterday());
      queryInsertProiezione(proiezione.getOraInizio(),proiezione.getOraFine(),
          ORARI.getORARI(proiezione.getOrarioProiezione()),proiezione.getPrezzo(),
          proiezione.getFilm().getIdFilm(),proiezione.getIdProiezione(),
          DatabaseUtil.getDatePlusDays(1),proiezione.getFineData());
      return true;
    }else{
      setQueryDeleteProiezione(proiezione);
      return true;
    }
  }

  private void setQueryDeleteProiezione(Proiezione proiezione){
    try {
      queryDeleteProiezione.setInt(1,proiezione.getIdProiezione());
      queryDeleteProiezione.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
