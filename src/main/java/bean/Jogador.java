package bean;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Jogador {

    private int ID;
    private BigDecimal eurReleaseClause;
    private LocalDate birthDate;
    private String name;
    private String nationality;
    private String club;

    public Jogador(int ID, BigDecimal eurReleaseClause, LocalDate birthDate, String name, String nationality, String club) {
        this.ID = ID;
        this.eurReleaseClause = eurReleaseClause;
        this.birthDate = birthDate;
        this.name = name;
        this.nationality = nationality;
        this.club = club;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public BigDecimal getEurReleaseClause() {
        return eurReleaseClause;
    }

    public void setEurReleaseClause(BigDecimal eurReleaseClause) {
        this.eurReleaseClause = eurReleaseClause;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
