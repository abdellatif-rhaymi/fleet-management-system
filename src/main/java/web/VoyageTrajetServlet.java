package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.net.*;

import DemandeVoyageDao.DemandeVoyageImplDao;
import entities.DemandeVoyage;

/**
 * Servlet implementation class VoyageTrajetServlet
 */
public class VoyageTrajetServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String path = request.getServletPath();
    	 if (path.equals("/trajet.trajet")) {
    	int voyageId = Integer.parseInt(request.getParameter("id"));

    	 DemandeVoyageImplDao dao = new DemandeVoyageImplDao();
         
         // Récupérer les demandes pour le voyage avec l'ID 1
         List<DemandeVoyage> demandes = dao.getDemandesByVoyageId(voyageId);
         
         // Vérifier si des demandes ont été trouvées
         if (demandes.isEmpty()) {
             System.out.println("Aucune demande trouvée pour ce voyage.");
             return;
         }
         // Générer la carte
         generateRouteMap(demandes);
         String totalDistance = request.getParameter("total_distance");
         String totalDuration = request.getParameter("total_duration");

         // Ajouter les données en tant qu'attributs de la requête
         request.setAttribute("totalDistance", voyageId);
         request.setAttribute("totalDuration", totalDuration);
         System.out.println("Distance totale h(km) : " + totalDistance);
         System.out.println("Durée totale (minutes) : " + totalDuration);
         request.getRequestDispatcher("mapof4.jsp").forward(request, response);
     }
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
                    "src/main/webapp/route_optimizer.py", 
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
