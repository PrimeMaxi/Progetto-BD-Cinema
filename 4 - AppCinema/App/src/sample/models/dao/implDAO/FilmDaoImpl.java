package sample.models.dao.implDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import sample.models.dao.interfaceDAO.FilmDAO;
import sample.models.entity.Film;
import sample.models.enumerations.GENERE;

public class FilmDaoImpl implements FilmDAO {

  private static final String sqlFilm = "select * from film where idfilm=?";

  private PreparedStatement insertFilm, deleteFilm, queryFilmById, updateFilm, queryFilm;
  private Statement queryListFilm, updateFilmTitolo;

  public FilmDaoImpl(Connection connection){
    try {
      insertFilm = connection.prepareStatement("INSERT INTO FILM (Titolo,Trama,Regia,Anno,Durata,Genere) VALUES (?,?,?,?,?,?)");
      deleteFilm = connection.prepareStatement("DELETE FROM FILM WHERE idfilm = ?");
      queryFilmById = connection.prepareStatement("SELECT idfilm FROM FILM WHERE titolo= ?");
      queryListFilm = connection.createStatement();
      updateFilmTitolo = connection.createStatement();
      updateFilm = connection.prepareStatement("UPDATE FILM SET titolo=?,trama=?,regia=?,anno=?,durata=?,genere=? where idfilm=?");
      queryFilm = connection.prepareStatement(sqlFilm);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void insertFilm(Film film) {
    try {
      insertFilm.setString(1,film.getTitolo());
      insertFilm.setString(2,film.getTrama().isEmpty() ? "DEFAULT" : film.getTrama());
      insertFilm.setString(3,film.getRegia());
      insertFilm.setInt(4,film.getAnnoUscita().getValue());
      insertFilm.setTime(5,film.getDurataFilm());
      insertFilm.setObject(6, film.getGenere(), Types.OTHER);
      System.out.println(insertFilm.executeUpdate());
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
    }
  }

  @Override
  public boolean deleteFilm(Integer id) {
    try {
      deleteFilm.setInt(1,id);
      return deleteFilm.executeUpdate() > 1;
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
    }
    return false;
  }

  @Override
  public Integer queryFilmById(String titolo) {
    try {
      queryFilmById.setString(1,titolo);
      ResultSet resultQuery = queryFilmById.executeQuery();
      return resultQuery.getInt(1);
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
    }
    return 0;
  }

  @Override
  public List<Film> queryListFilm() {
    List<Film> filmList = new ArrayList<>();
    try {
      ResultSet rs = queryListFilm.executeQuery("SELECT * FROM FILM");
      while (rs.next()){
        Film film = new Film();
        film.setIdFilm(Integer.parseInt(rs.getString("idfilm")));
        film.setTitolo(rs.getString("titolo"));
        film.setTrama(rs.getString("regia"));
        film.setRegia(rs.getString("regia"));
        film.setAnnoUscita(Year.of(rs.getInt("anno")));
        film.setDurataFilm(rs.getTime("durata"));
        film.setGenere(GENERE.valueOf(rs.getString("genere")));                            //Da Impostare bene.
        filmList.add(film);
      }
      return filmList;
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
    }finally{
      try {
        queryListFilm.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return Collections.emptyList();
  }

  @Override
  public void updateFilmTitolo(String newTitolo, String oldTitolo) {
    try {
      String sql = "update film SET titolo='"+newTitolo+"' WHERE titolo='"+oldTitolo+"'";
      updateFilmTitolo.executeUpdate(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }finally{
      try {
        updateFilmTitolo.close();
      } catch (SQLException e) {
        JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
        e.printStackTrace();
      }
    }
  }

  @Override
  public void updateFilm(Film film){
    try {
      updateFilm.setString(1,film.getTitolo());
      updateFilm.setString(2,film.getTrama().isEmpty() ? "DEFAULT" : film.getTrama());
      updateFilm.setString(3,film.getRegia());
      updateFilm.setInt(4,film.getAnnoUscita().getValue());
      updateFilm.setTime(5,film.getDurataFilm());
      updateFilm.setObject(6, film.getGenere(), Types.OTHER);
      updateFilm.setInt(7,film.getIdFilm());
      updateFilm.executeUpdate();
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
      e.printStackTrace();
    }finally{
      try {
        updateFilm.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public Film queryFilm(Integer idFilm){
    try{
      queryFilm.setInt(1,idFilm);
      var rs = queryFilm.executeQuery();
      while (rs.next()) {
        return new Film(
            rs.getInt(1),
            rs.getString(2),
            rs.getString(3),
            rs.getString(4),
            rs.getInt(5),
            rs.getTime(6),
            rs.getString(7));
      }
    }catch (SQLException e){
      JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
      e.printStackTrace();
    }finally{
      try {
        queryFilm.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return null;
  }
}
