package entities;

import java.io.Serializable;
import java.util.Date;

public class Vehicule implements Serializable {

    private Long idVehicule;
    private String immatriculation;
    private String type;
    private String modele;
    private String couleur;
    private int annee;
    private String statut;
    private Date derniereMaintenance;
    private int capacite;
    private String image;


    public Vehicule() {
        super();
    }

    public Vehicule(String immatriculation, String type, String modele, String couleur, int annee, String statut, Date derniereMaintenance, int capacite ,String image) {
        super();
        this.immatriculation = immatriculation;
        this.type = type;
        this.modele = modele;
        this.couleur = couleur;
        this.annee = annee;
        this.statut = statut;
        this.derniereMaintenance = derniereMaintenance;
        this.capacite = capacite;
        this.image = image;
    }

    public Long getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(Long idVehicule) {
        this.idVehicule = idVehicule;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getDerniereMaintenance() {
        return derniereMaintenance;
    }

    public void setDerniereMaintenance(Date derniereMaintenance) {
        this.derniereMaintenance = derniereMaintenance;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Vehicule [idVehicule=" + idVehicule + ", immatriculation=" + immatriculation + ", type=" + type + ", modele=" + modele +
               ", couleur=" + couleur + ", annee=" + annee + ", statut=" + statut + ", derniereMaintenance=" + derniereMaintenance +
               ", capacite=" + capacite + ", image=" + image + "]";
    }
}
