package DemandeVoyageDao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.File;

import DemandeVoyageDao.IDemandeVoyageDao;
import SingletonConnection.SingletonConnection;
import VehiculeDao.IVehiculeDao;
import entities.DemandeVoyage;
import entities.Vehicule;

public class DemandeVoyageImplDao implements IDemandeVoyageDao {

	public List<DemandeVoyage> getDemandesByVoyageId(int voyageId) {
		 List<DemandeVoyage> demandes = new ArrayList<>();
		 Connection connection = SingletonConnection.getConnection();
	        try {
	            // Préparation de la requête SQL pour l'insertion d'un voyage
	            PreparedStatement ps = connection.prepareStatement(
	       "SELECT * FROM demandese WHERE voyage_id = ?");
	            ps.setInt(1, voyageId);
	            ResultSet rs = ps.executeQuery();
	                while (rs.next()) {
	                	DemandeVoyage demande = new DemandeVoyage();
	                	demande.setLatitude(rs.getDouble("latitude"));
	                	demande.setLongitude(rs.getDouble("longitude"));
	                    demandes.add(demande);
	                }
	                ps.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	            
	      return demandes;
	        
	}
	/*
	 * public void generateRouteMap(List<DemandeVoyage> demandes) { try { //
	 * Préparer les coordonnées List<String> coordinates = demandes.stream() .map(d
	 * -> String.format("[%f, %f]", d.getLongitude(), d.getLatitude()))
	 * .collect(Collectors.toList());
	 * 
	 * // Construire la commande Python ProcessBuilder pb = new
	 * ProcessBuilder("python3", "route_service.py", String.join(" ", coordinates));
	 * 
	 * Process process = pb.start(); // Optionnel : gérer la sortie du processus
	 * BufferedReader reader = new BufferedReader(new
	 * InputStreamReader(process.getInputStream())); String line; while ((line =
	 * reader.readLine()) != null) { System.out.println(line); } } catch (Exception
	 * e) { e.printStackTrace(); } }
	 */
	

	public void generateRouteMap(List<DemandeVoyage> demandes) {
	    try {
	        // Construire manuellement le JSON
	        StringBuilder jsonBuilder = new StringBuilder("[");
	        for (int i = 0; i < demandes.size(); i++) {
	            DemandeVoyage demande = demandes.get(i);
	            jsonBuilder.append(String.format("[%f, %f]", 
	                demande.getLongitude(), 
	                demande.getLatitude()));
	            
	            if (i < demandes.size() - 1) {
	                jsonBuilder.append(",");
	            }
	        }
	        jsonBuilder.append("]");

	        // Créer un fichier temporaire
	        File coordFile = File.createTempFile("coordinates", ".json");
	        try (PrintWriter writer = new PrintWriter(coordFile)) {
	            writer.write(jsonBuilder.toString());
	        }
	        
	        // Lancer le script Python
	        ProcessBuilder pb = new ProcessBuilder("python3", 
	            "route_service.py", 
	            coordFile.getAbsolutePath());
	        
	        Process process = pb.start();
	        
	        // Gestion de la sortie
	        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	        String line;
	        while ((line = reader.readLine()) != null) {
	            System.out.println(line);
	        }
	        
	        // Supprimer le fichier temporaire
	        coordFile.deleteOnExit();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
}
