package entities;

public class Trajet {
    private Long idTrajet;
    private String depart;
    private String destination;
    private String itineraire; // JSON String
    private double distanceKm;
    private int dureeEstimeeMin;
    
    public Trajet() {
        super();
    }

    public Trajet(long idTrajet, String depart, String destination, String itineraire, double distanceKm, int dureeEstimeeMin) {
        this.idTrajet = idTrajet;
        this.depart = depart;
        this.destination = destination;
        this.itineraire = itineraire;
        this.distanceKm = distanceKm;
        this.dureeEstimeeMin = dureeEstimeeMin;
    }

    public Trajet(String depart, String destination, String itineraire, double distanceKm, int dureeEstimeeMin) {
        this(0, depart, destination, itineraire, distanceKm, dureeEstimeeMin);
    }

    // Getters and Setters
    public long getIdTrajet() {
        return idTrajet;
    }

    public void setIdTrajet(Long id) {
        this.idTrajet = id;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getItineraire() {
        return itineraire;
    }

    public void setItineraire(String itineraire) {
        this.itineraire = itineraire;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public int getDureeEstimeeMin() {
        return dureeEstimeeMin;
    }

    public void setDureeEstimeeMin(int dureeEstimeeMin) {
        this.dureeEstimeeMin = dureeEstimeeMin;
    }
}
