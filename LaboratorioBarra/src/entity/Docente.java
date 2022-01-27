package entity;

public class Docente
{
    private String cf;
    private String nome;
    private String cognome;
    private String email;

    public Docente(String cf)
    {
        this.cf = cf;
    }
    public Docente(String cf, String nome, String cognome, String email)
    {
        this.cf = cf;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
