package dao_impl;

import daos.StudenteDAO;
import entity.Studente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StudenteDAOOracleImpl implements StudenteDAO
{

    private Connection connection;
    private PreparedStatement getStudenteByNomePS, inserisciStudentePS;

    public StudenteDAOOracleImpl(Connection connection) throws SQLException {
        this.connection=connection;
        getStudenteByNomePS = connection.prepareStatement("SELECT * FROM studente where NOME like ?;");
        inserisciStudentePS = connection.prepareStatement("INSERT INTO studente VALUES (?, ?, ?, ?);");
    }


    @Override
    public List<Studente> getAllStudents() {
        return null;
    }

    @Override
    public List<Studente> getStudenteByNome(String name) throws SQLException {
        return null;
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
    public int inserisciStudente(Studente studente) throws SQLException {
        return 0;
    }

    @Override
    public int cancellaStudente(Studente studente) {
        return 0;
    }
}
