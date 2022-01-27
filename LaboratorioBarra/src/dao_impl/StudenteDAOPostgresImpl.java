package dao_impl;

import daos.StudenteDAO;
import entity.Studente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudenteDAOPostgresImpl implements StudenteDAO
{
    private Connection connection;
    private PreparedStatement getStudenteByNomePS, inserisciStudentePS;

    public StudenteDAOPostgresImpl(Connection connection) throws SQLException {
        this.connection=connection;
        getStudenteByNomePS = connection.prepareStatement("SELECT * FROM studente where nome like ?");
        inserisciStudentePS = connection.prepareStatement("INSERT INTO studente VALUES (?, ?, ?, ?)");
    }

    @Override
    public List<Studente> getAllStudents() {
        return null;
    }

    @Override
    public List<Studente> getStudenteByNome(String name) throws SQLException
    {
        getStudenteByNomePS.setString(1, name);
        ResultSet rs= getStudenteByNomePS.executeQuery();
        List<Studente> lista = new ArrayList<Studente>();
        while(rs.next())
        {
            Studente s = new Studente(rs.getString("matricola")); //rs.getString(1)
            s.setNome(rs.getString("nome"));
            s.setCognome(rs.getString("cognome"));
            s.setEmail(rs.getString("email"));
            lista.add(s);
        }
        rs.close();
        return lista;
    }

    @Override
    public List<Studente> getStudenteByCognome(String cognome) {
        return null;
    }

    @Override
    public List<Studente> getStudenteByMatricola(String matricola) {
        return null;
    }

    @Override
    public List<Studente> getStudenteByNomeCognome(String nome, String cognome) {
        return null;
    }

    @Override
    public int inserisciStudente(Studente studente) throws SQLException
    {
        inserisciStudentePS.setString(1, studente.getMatricola());
        inserisciStudentePS.setString(2, studente.getNome());
        inserisciStudentePS.setString(3, studente.getCognome());
        inserisciStudentePS.setString(4, studente.getEmail());
        int row = inserisciStudentePS.executeUpdate();
        return row;
    }

    @Override
    public int cancellaStudente(Studente studente) {
        return 0;
    }
}
