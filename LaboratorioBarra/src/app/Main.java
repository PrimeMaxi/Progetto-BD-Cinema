package app;

import dao_impl.StudenteDAOOracleImpl;
import dao_impl.StudenteDAOPostgresImpl;
import daos.StudenteDAO;
import dbConfig.DBBuilder;
import dbConfig.DBConnection;
import entity.Studente;
import exceptions.ConnectionException;

import java.sql.SQLException;
import java.sql.Connection;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {
        DBConnection dbconn = null;
        Connection connection = null;
        DBBuilder builder = null;

        try
        {
            dbconn = DBConnection.getInstance();
            connection = dbconn.getConnection();
            builder = new DBBuilder(connection);
            builder.createTableStudente();
            builder.createTableDocente();
            builder.createTableEsame();

            StudenteDAO dao = null;

            if(args[0].equals("ps"))
                dao = new StudenteDAOPostgresImpl(connection);
            else
                dao = new StudenteDAOOracleImpl(connection);

            /*Studente s1  =  new Studente("N86001111", "Pippo", "Baudo", "p.baudo@studenti.unina.it");
            Studente s2  =  new Studente("N86001112", "Pino", "Silvestre", "p.silvestre@studenti.unina.it");
            Studente s3  =  new Studente("N86001113", "Lino", "Musso", "l.musso@studenti.unina.it");
            int res =  dao.inserisciStudente(s1);
            System.out.println(res);
            int res2 = dao.inserisciStudente(s2);
            System.out.println(res2);
            int res3 = dao.inserisciStudente(s3);
            System.out.println(res3);*/

            List<Studente> lista = dao.getStudenteByNome("P%");
            
            for(Studente ss : lista)
            {
                System.out.println(ss.toString());
            }

        }
        catch (SQLException exception)
        {
            System.out.println("SQLException: "+ exception.getMessage());
        }
        catch (ConnectionException ex)
        {
            System.out.println("CE: "+ex);
        }

    }

}
