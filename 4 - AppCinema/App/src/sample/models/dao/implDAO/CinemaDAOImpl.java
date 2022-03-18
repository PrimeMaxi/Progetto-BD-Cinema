package sample.models.dao.implDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import sample.models.dao.interfaceDAO.CinemaDAO;
import sample.models.entity.Cinema;
import sample.service.SceneCreator;

public class CinemaDAOImpl implements CinemaDAO {

  Connection connection;
  private PreparedStatement updateCinema, updateNumeroSala;
  private Statement retriveCinema;
  private final static String sqlRetriveCinema = "SELECT * FROM CINEMA where idcinema=1";
  private final static String sqlUpdateCinema = "UPDATE CINEMA SET nomecinema=?,indirizzo=?,provincia=?,numerosala=?,città=?,telefono=? where idcinema=1";
  private final static String sqlUpdateNumeroSala = "UPDATE CINEMA SET numerosala=? WHERE idcinema=1";

  public CinemaDAOImpl(Connection connection){
    this.connection = connection;
    try {
      retriveCinema = connection.createStatement();
      updateCinema = connection.prepareStatement(sqlUpdateCinema);
      updateNumeroSala = connection.prepareStatement(sqlUpdateNumeroSala);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean updatePlusNumeroSala(){
    var numero = retriveCinema().getNumeroSala();
    try {
      updateNumeroSala.setInt(1,++numero);
      return updateNumeroSala.execute();
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
      e.printStackTrace();
      return false;
    }finally{
      try {
        updateNumeroSala.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public Cinema retriveCinema() {
    Cinema cinema = new Cinema();
    try {
      ResultSet resultSet = retriveCinema.executeQuery(sqlRetriveCinema);
      while (resultSet.next()) {
        cinema.setNomeCinema(resultSet.getString(2));
        cinema.setIndirizzo(resultSet.getString(3));
        cinema.setProvincia(resultSet.getString(4));
        cinema.setNumeroSala(resultSet.getInt(5));
        cinema.setCittà(resultSet.getString(6));
        cinema.setTelefono(resultSet.getInt(7));
      }
      return cinema;
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
      e.printStackTrace();
    }finally{
      try {
        retriveCinema.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  @Override
  public void updateCinema(Cinema cinema) {
    try {
      updateCinema.setString(1,cinema.getNomeCinema());
      updateCinema.setString(2,cinema.getIndirizzo());
      updateCinema.setString(3,cinema.getProvincia());
      updateCinema.setInt(4,cinema.getNumeroSala());
      updateCinema.setString(5,cinema.getCittà());
      updateCinema.setInt(6,cinema.getTelefono());
      updateCinema.executeUpdate();
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
      e.printStackTrace();
    }finally{
      try {
        updateCinema.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
