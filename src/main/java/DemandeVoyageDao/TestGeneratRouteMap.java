package DemandeVoyageDao;


import entities.DemandeVoyage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class TestGeneratRouteMap {
    public static void main(String[] args) {
        // Initialiser le DAO
        DemandeVoyageImplDao dao = new DemandeVoyageImplDao();
        
        // Récupérer les demandes pour le voyage avec l'ID 1
        List<DemandeVoyage> demandes = dao.getDemandesByVoyageId(1);
        
        // Vérifier si des demandes ont été trouvées
        if (demandes.isEmpty()) {
            System.out.println("Aucune demande trouvée pour ce voyage.");
            return;
        }
        
        // Générer la carte
        generateRouteMap(demandes);
    }
    
    public static void generateRouteMap(List<DemandeVoyage> demandes) {
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
            
            // Afficher les coordonnées pour vérification
            System.out.println("Coordonnées générées : " + jsonBuilder.toString());
         // Obtenir le chemin absolu du script
            ProcessBuilder pb = new ProcessBuilder("python3", 
                    "src/main/webapp/route_map_generator.py", 
                    coordFile.getAbsolutePath());
                
            
            
            Process process = pb.start();
            
            // Gestion de la sortie
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            
            // Gestion des erreurs
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String errorLine;
            while ((errorLine = errorReader.readLine()) != null) {
                System.err.println("Erreur : " + errorLine);
            }
            
            // Attendre la fin du processus
            int exitCode = process.waitFor();
            System.out.println("Script Python terminé avec le code : " + exitCode);
            
            // Supprimer le fichier temporaire
            coordFile.deleteOnExit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}