package sample.models.dao.interfaceDAO;

import java.util.List;
import sample.models.entity.Film;

public interface FilmDAO {
    public void insertFilm(Film film);
    public boolean deleteFilm(Integer id);
    public Integer queryFilmById(String titolo);
    public List<Film> queryListFilm();
}
