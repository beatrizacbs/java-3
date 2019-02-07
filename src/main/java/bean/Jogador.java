package bean;

import com.opencsv.bean.CsvBindByName;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Jogador {

    @CsvBindByName
    private int ID;

    @CsvBindByName
    private String eur_release_clause;

    @CsvBindByName
    private String birth_date;

    @CsvBindByName
    private String full_name;

    @CsvBindByName
    private String nationality;

    @CsvBindByName
    private String club;

    public Jogador(){

    }

    public Jogador(int ID, String eur_release_clause, String birth_date, String full_name, String nationality, String club) {
        this.ID = ID;
        this.eur_release_clause = eur_release_clause;
        this.birth_date = birth_date;
        this.full_name = full_name;
        this.nationality = nationality;
        this.club = club;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEur_release_clause() {
        return eur_release_clause;
    }

    public void setEur_release_clause(String eur_release_clause) {
        this.eur_release_clause = eur_release_clause;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }
}
