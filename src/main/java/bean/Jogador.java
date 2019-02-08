package bean;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Jogador {

    @CsvBindByPosition(position = 0)
    private int ID;

    @CsvBindByPosition(position = 18)
    private String eur_release_clause;

    @CsvBindByPosition(position = 8)
    private String birth_date;

    @CsvBindByPosition(position = 2)
    private String full_name;

    @CsvBindByPosition(position = 14)
    private String nationality;

    @CsvBindByPosition(position = 3)
    private String club;

    @CsvBindByPosition(position = 17)
    private String euro_wage;

    public Jogador(){

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
