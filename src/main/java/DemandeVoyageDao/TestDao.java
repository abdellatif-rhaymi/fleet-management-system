package DemandeVoyageDao;

import java.util.List;
import java.io.FileWriter;
import java.io.IOException;


import entities.DemandeVoyage;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;


public class TestDao {
			public static void main(String[] args) {
				// Instancier le DAO pour récupérer les données
		        DemandeVoyageImplDao dao = new DemandeVoyageImplDao();
		        List<DemandeVoyage> demandes = dao.getDemandesByVoyageId(1); // Récupérer les données avec l'ID du voyage
		        
		        if (demandes.isEmpty()) {
		            System.out.println("Aucune donnée trouvée pour ce voyage.");
		        } else {
		            // Créer un JSONArray pour stocker les coordonnées
		            JSONArray coordsArray = new JSONArray();
		            
		            for (DemandeVoyage demande : demandes) {
		                // Créer un JSONObject pour chaque coordonnée
		                JSONObject coord = new JSONObject();
		                coord.put("latitude", demande.getLatitude());
		                coord.put("longitude", demande.getLongitude());
		                
		                // Ajouter l'objet dans le tableau
		                coordsArray.put(coord);
		            }
		            
		            // Créer un objet JSON principal
		            JSONObject jsonOutput = new JSONObject();
		            jsonOutput.put("coords", coordsArray);
		            
		            // Afficher ou sauvegarder le JSON
		            System.out.println(jsonOutput.toString(4)); // Indentation pour une meilleure lisibilité
		        }
		    }
		}
		/*
		 * DemandeVoyageImplDao dao=new DemandeVoyageImplDao(); //DemandeVoyage
		 * a2=dao.save(new DemandeVoyage("chienBaro"));
		 * //System.out.println(a2.toString());
		 * 
		 * System.out.println("coordones"); List<DemandeVoyage>
		 * arts=dao.getDemandesByVoyageId(1); if (arts.isEmpty()) {
		 * System.out.println("Aucun DemandeVoyage trouvé dans la base de données."); }
		 * else { // Afficher tous les DemandeVoyages
		 * System.out.println("Liste des DemandeVoyages :"); for (DemandeVoyage a :
		 * arts) { System.out.println("latitude: " + a.getLatitude() + ", longitude: " +
		 * a.getLongitude()); } }
		 * 
		 * }
		 */
		


