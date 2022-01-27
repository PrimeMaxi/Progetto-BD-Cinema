package entity;

import java.util.Properties;

public class Studente
{
    private String nome;
    private String cognome;
    private String matricola;
    private String email;


    public Studente(String matricola)
    {
        this.matricola = matricola;
    }
    public Studente(String matricola, String nome, String cognome, String email)
    {
        this.matricola = matricola;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
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

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString()
    {
        return matricola+"\t"+nome+"\t"+cognome+"\t"+email;
    }
}
