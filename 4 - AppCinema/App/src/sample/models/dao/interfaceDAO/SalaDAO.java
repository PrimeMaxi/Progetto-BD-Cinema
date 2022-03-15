package sample.models.dao.interfaceDAO;

import java.util.List;
import sample.models.entity.Sala;
import sample.models.enumerations.AUDIO;
import sample.models.enumerations.TECNOLOGIA;

public interface SalaDAO {

  List<Sala> queryRetriveSala();

  boolean queryUpdate(Integer idSala, Integer capienza, TECNOLOGIA tecnologia, AUDIO audio);
}
