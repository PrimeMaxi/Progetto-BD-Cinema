package sample.models.dao.implDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import sample.models.dao.interfaceDAO.CinemaDAO;
import sample.models.entity.Cinema;

public class CinemaDAOImpl implements CinemaDAO {

  Connection connection;
  private PreparedStatement updateCinema;
  private Statement retriveCinema;
  private final static String sqlRetriveCinema = "SELECT * FROM CINEMA where idcinema=1";

  public CinemaDAOImpl(Connection connection) throws SQLException {
    this.connection = connection;
    retriveCinema = connection.createStatement();
    updateCinema = connection.prepareStatement("");
  }

  @Override
  public Cinema retriveCinema() {
    Cinema cinema = new Cinema();
    try {
      ResultSet resultSet = retriveCinema.executeQuery(sqlRetriveCinema);
      while (resultSet.next()) {
        cinema.setIdCinema(resultSet.getInt(1));
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
    }
    return null;
  }

  @Override
  public void updateCinema(Cinema cinema) {

  }
}
