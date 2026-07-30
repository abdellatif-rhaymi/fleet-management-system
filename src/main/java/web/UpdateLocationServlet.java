package web;
import com.google.gson.Gson;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.AccessFlag.Location;
import java.io.*;
import com.google.gson.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// Servlet pour recevoir la position
public class UpdateLocationServlet extends HttpServlet {
    // Stockage temporaire des positions (simulé, peut être remplacé par une base de données)
    private static Map<Integer, Location> driverLocations = new HashMap<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String path = request.getServletPath();
      	 if (path.equals("/trajet.glocate")) {
      	
    	try {
            // Lire le JSON envoyé par le client
            BufferedReader reader = request.getReader();
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }

            System.out.println("JSON reçu : " + json.toString());

            // Convertir le JSON en objet Java
            Gson gson = new Gson();
            Location location = gson.fromJson(json.toString(), Location.class);

            // Simuler le stockage de la position (par exemple, pour le chauffeur ID = 1)
            int driverId = 1;
            driverLocations.put(driverId, location);

            // Répondre au client avec un message de succès
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"success\",\"message\":\"Position mise à jour\"}");
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
} 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Simuler la récupération de la position du chauffeur ID = 1
            int driverId = 1;
            Location location = driverLocations.get(driverId);

            if (location != null) {
                // Convertir l'objet Location en JSON
                Gson gson = new Gson();
                String json = gson.toJson(location);

                // Répondre au client avec la position
                response.setContentType("application/json");
                response.getWriter().write(json);
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                // Aucun emplacement trouvé pour ce chauffeur
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"status\":\"error\",\"message\":\"Aucune position trouvée pour ce chauffeur.\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    


}
