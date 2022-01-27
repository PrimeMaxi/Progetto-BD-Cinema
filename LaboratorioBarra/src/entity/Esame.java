package entity;

public class Esame {

    private String cod_esame;
    private int cfu;
    private String denominazione;
    private Docente docente;

    public Esame(String cod_esame)
    {
        this.cod_esame = cod_esame;
    }

    public Esame(String cod_esame, String denominazione, int cfu, Docente docente)
    {
        this.cod_esame =cod_esame;
        this.denominazione = denominazione;
        this.cfu =  cfu;
        this.docente = docente;
    }

    public String getCod_esame() {
        return cod_esame;
    }

    public void setCod_esame(String cod_esame) {
        this.cod_esame = cod_esame;
    }

    public int getCfu() {
        return cfu;
    }

    public void setCfu(int cfu) {
        this.cfu = cfu;
    }

    public String getDenominazione() {
        return denominazione;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }
}
