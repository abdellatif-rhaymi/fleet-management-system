package entities;

import java.sql.Date;

public class Voyage {

    private Long idVoyage;
    private Long idVehicule;
    private Long idChauffeur;
    private Date dateDepart;
    private Date dateArrivee;
    private String statut;

    // Constructeurs
    public Voyage() {}

    public Voyage(Long idVehicule, Long idChauffeur, Date dateDepart, Date dateArrivee, String statut) {
        this.idVehicule = idVehicule;
        this.idChauffeur = idChauffeur;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
        this.statut = statut;
    }

    public Voyage(Long idVoyage, Long idVehicule, Long idChauffeur, Date dateDepart, Date dateArrivee, String statut) {
        this.idVoyage = idVoyage;
        this.idVehicule = idVehicule;
        this.idChauffeur = idChauffeur;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
        this.statut = statut;
    }

    // Getters et Setters
    public Long getIdVoyage() {
        return idVoyage;
    }

    public void setIdVoyage(Long idVoyage) {
        this.idVoyage = idVoyage;
    }


    public Long getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(Long idVehicule) {
        this.idVehicule = idVehicule;
    }

    public Long getIdChauffeur() {
        return idChauffeur;
    }

    public void setIdChauffeur(Long idChauffeur) {
        this.idChauffeur = idChauffeur;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Date getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    // Méthode toString() pour faciliter l'affichage
    @Override
    public String toString() {
        return "Voyage{" +
                "idVoyage=" + idVoyage +
                ", idVehicule=" + idVehicule +
                ", idChauffeur=" + idChauffeur +
                ", dateDepart=" + dateDepart +
                ", dateArrivee=" + dateArrivee +
                ", statut='" + statut + '\'' +
                '}';
    }
}
