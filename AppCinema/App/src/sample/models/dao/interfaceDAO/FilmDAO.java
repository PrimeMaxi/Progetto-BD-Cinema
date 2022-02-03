package sample.models.dao.interfaceDAO;

import sample.models.entity.Film;

public interface FilmDAO {
    public void insertFilm(Film film);
    public boolean deleteFilm(Integer id);
    public Integer queryFilmById(String titolo);
}
