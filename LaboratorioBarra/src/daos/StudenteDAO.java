package daos;

import java.sql.SQLException;
import java.util.List;
import entity.Studente;

public interface StudenteDAO
{
    public List<Studente> getAllStudents();
    public List<Studente> getStudenteByNome(String name) throws SQLException;
    public List<Studente> getStudenteByCognome(String cognome);
    public List<Studente> getStudenteByMatricola(String matricola);
    public List<Studente> getStudenteByNomeCognome(String nome, String cognome);

    public int inserisciStudente(Studente studente) throws SQLException;

    public int cancellaStudente(Studente studente);
}
