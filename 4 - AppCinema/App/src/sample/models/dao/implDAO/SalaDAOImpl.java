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
import sample.models.dao.interfaceDAO.SalaDAO;
import sample.models.entity.Sala;
import sample.models.enumerations.AUDIO;
import sample.models.enumerations.TECNOLOGIA;

public class SalaDAOImpl implements SalaDAO {

  private static final String sqlRetriveSala = "select idsala,capienza,tecnologia,audio from sala";
  private static final String sqlUpdateSala = "update sala set capienza=?, tecnologia=?, audio=? where idsala=?";

  private Statement queryRetriveSala;
  private PreparedStatement queryUpdate;

  public SalaDAOImpl(Connection connection){
    try {
      queryRetriveSala = connection.createStatement();
      queryUpdate = connection.prepareStatement(sqlUpdateSala);
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

  @Override
  public boolean queryUpdate(Integer idSala, Integer capienza, TECNOLOGIA tecnologia, AUDIO audio){
    try {
      queryUpdate.setInt(1,capienza);
      queryUpdate.setObject(2,tecnologia, Types.OTHER);
      queryUpdate.setObject(3, audio,Types.OTHER);
      queryUpdate.setInt(4,idSala);
      return queryUpdate.execute();
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
      e.printStackTrace();
      return false;
    }
  }

}
