package ws;

import java.util.Date;

public class Compte {
    private int code;
    private double solde;
    private Date dateCrearion;

    public Compte(int code, double solde, Date date) {
        this.code = code;
        this.solde = solde;
        this.dateCrearion = date;
    }

    public Compte() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Date getDateCrearion() {
        return dateCrearion;
    }

    public void setDateCrearion(Date dateCrearion) {
        this.dateCrearion = dateCrearion;
    }
}
