package sample.models.dao.interfaceDAO;

public interface FilmDAO {
    public void insertFilm(String titolo,String trama, String regia, String anno, String durata, String genere);
    public boolean deleteFilm();
}
