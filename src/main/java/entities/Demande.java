package entities;
import java.io.Serializable;
import java.util.Date;

public class Demande {

    private Long idDemande;
    private String numeroDemande;
    private Date dateDemande;
    private String adresseLivraison;
    private String ville;
    private String codePostal;
    private String pays;
    private Date dateLivraison;
    private String statut;
    private String commentaire;
    private Double latitude;
    private Double longitude;
    private Float weight;
    private Long idVoyage;

    // Constructor
    public Demande(String numeroDemande, Date dateDemande, String adresseLivraison, String ville, 
                   String codePostal, String pays, Date dateLivraison, String statut, String commentaire, Double latitude, Double longitude, Float weight) {
        
        this.numeroDemande = numeroDemande;
        this.dateDemande =  dateDemande;
        this.adresseLivraison = adresseLivraison;
        this.ville = ville;
        this.codePostal = codePostal;
        this.pays = pays;
        this.dateLivraison =  dateLivraison;
        this.statut = statut;
        this.commentaire = commentaire;
        this.latitude = latitude;
        this.longitude = longitude;
        this.weight = weight;
       
    }
    public Demande(Long id, String numeroDemande, Date dateDemande, String adresseLivraison, 
            String ville, String codePostal, String pays, Date dateLivraison, 
            String statut, String commentaire, Double latitude, Double longitude, Float weight) {
 this.idDemande = id;
 this.numeroDemande = numeroDemande;
 this.dateDemande = dateDemande;
 this.adresseLivraison = adresseLivraison;
 this.ville = ville;
 this.codePostal = codePostal;
 this.pays = pays;
 this.dateLivraison = dateLivraison;
 this.statut = statut;
 this.commentaire = commentaire;
 this.latitude = latitude;
 this.longitude = longitude;
 this.weight = weight;
}
    public Demande(Long id, String numeroDemande, Date dateDemande, String adresseLivraison, 
            String ville, String codePostal, String pays, Date dateLivraison, 
            String statut, String commentaire, Double latitude, Double longitude, Float weight ,Long idVoyage) {
 this.idDemande = id;
 this.numeroDemande = numeroDemande;
 this.dateDemande = dateDemande;
 this.adresseLivraison = adresseLivraison;
 this.ville = ville;
 this.codePostal = codePostal;
 this.pays = pays;
 this.dateLivraison = dateLivraison;
 this.statut = statut;
 this.commentaire = commentaire;
 this.latitude = latitude;
 this.longitude = longitude;
 this.weight = weight;
 this.idVoyage=idVoyage;
}




	public Demande() {
        super();
    }



	// Getters and Setters
    public Long getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(Long idDemande) {
        this.idDemande = idDemande;
    }
    public Long getIdVoyage() {
        return idVoyage;
    }

    public void setIdVoyage(Long idVoyage) {
        this.idVoyage = idVoyage;
    }

    public String getNumeroDemande() {
        return numeroDemande;
    }

    public void setNumeroDemande(String numeroDemande) {
        this.numeroDemande = numeroDemande;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    // Getter et Setter pour longitude
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    // Getter et Setter pour weight
    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    // toString method
    @Override
    public String toString() {
        return "Demande{" +
                "idDemande=" + idDemande +
                ", numeroDemande='" + numeroDemande + '\'' +
                ", dateDemande=" + dateDemande +
                ", adresseLivraison='" + adresseLivraison + '\'' +
                ", ville='" + ville + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", pays='" + pays + '\'' +
                ", dateLivraison=" + dateLivraison +
                ", statut='" + statut + '\'' +
                ", commentaire='" + commentaire + '\''  +
                ", latitude='" + latitude + '\''  +
                ", longitude='" + longitude + '\''  +
                ", weight='" + weight + '\''  +
                ", idVoyage='" + idVoyage + '\''  +

                '}';
    }
}
