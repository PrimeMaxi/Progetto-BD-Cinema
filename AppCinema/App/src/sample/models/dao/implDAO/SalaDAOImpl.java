package sample.models.dao.implDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import sample.models.dao.interfaceDAO.SalaDAO;
import sample.models.entity.Sala;

public class SalaDAOImpl implements SalaDAO {

  private static final String sqlRetriveSala = "select idsala,capienza,tecnologia,audio from sala";

  private Statement queryRetriveSala;

  public SalaDAOImpl(Connection connection){
    try {
      queryRetriveSala = connection.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Sala> queryRetriveSala(){
    var salaList = new ArrayList<Sala>();
    try {
      var rs = queryRetriveSala.executeQuery(sqlRetriveSala);
      while(rs.next()){
        salaList.add(
            new Sala(
                rs.getInt(1),
                rs.getInt(2),
                rs.getString(3),
                rs.getString(4)
            )
        );
      }
      return salaList;
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
    }finally{
      try {
        queryRetriveSala.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return Collections.emptyList();
  }

}
