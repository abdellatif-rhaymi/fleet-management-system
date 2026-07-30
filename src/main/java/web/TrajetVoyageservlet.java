package web;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import entities.DemandeVoyage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.net.*;

public class TrajetVoyageservlet extends HttpServlet {

    private static final String OPENROUTESERVICE_API_KEY = "YOUR_ORS_API_KEY"; // Remplace par ta clé API ORS

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 String path = request.getServletPath();
    	 if (path.equals("/trajet.trajet")) {
    	int voyageId = Integer.parseInt(request.getParameter("voyageId"));

        // Récupérer les demandes de voyage par ID
        List<DemandeVoyage> demandes = getDemandesByVoyageId(voyageId);

        // Extraire les coordonnées (latitude, longitude) des demandes
        List<double[]> coordinates = new ArrayList<>();
        for (DemandeVoyage demande : demandes) {
            coordinates.add(new double[]{demande.getLongitude(), demande.getLatitude()});
        }

        // Appeler OpenRouteService API pour obtenir le trajet
        List<Map<String, Object>> route = getRouteFromORS(coordinates);

        // Convertir le résultat en JSON et l'envoyer en réponse
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(route);
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
    }
    }
    private List<DemandeVoyage> getDemandesByVoyageId(int voyageId) {
        // Implémentation pour récupérer les demandes de voyage à partir de la BD
        List<DemandeVoyage> demandes = new ArrayList<>();
        // Requête SQL pour obtenir les demandes liées au voyageId
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transport", "", "")) {
            String sql = "SELECT latitude, longitude FROM voyage_demande WHERE voyage_id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, voyageId);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        double latitude = rs.getDouble("latitude");
                        double longitude = rs.getDouble("longitude");
                        demandes.add(new DemandeVoyage(latitude, longitude)); // Remplace par ton modèle de données
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return demandes;
    }

    private List<Map<String, Object>> getRouteFromORS(List<double[]> coordinates) throws IOException {
        // Créer un tableau JsonArray pour les coordonnées
        JsonArray waypoints = new JsonArray();
        for (double[] coord : coordinates) {
            JsonObject point = new JsonObject();
            point.addProperty("lat", coord[1]); // Latitude
            point.addProperty("lon", coord[0]); // Longitude
            waypoints.add(point);
        }

        // Construire le corps de la requête (requestBody)
        JsonObject requestBody = new JsonObject();
        requestBody.add("coordinates", waypoints);

        // Créer la requête HTTP pour appeler OpenRouteService
        URL url = new URL("https://api.openrouteservice.org/v2/directions/driving-car");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", OPENROUTESERVICE_API_KEY);
        connection.setDoOutput(true);

        // Envoyer la requête avec les coordonnées
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = requestBody.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Récupérer la réponse de l'API
        StringBuilder responseBuilder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                responseBuilder.append(inputLine);
            }
        }

        // Parser la réponse JSON avec Gson
        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(responseBuilder.toString(), JsonObject.class);

        // Retourner le trajet sous forme de Map (cela pourrait être plus spécifique si nécessaire)
        List<Map<String, Object>> route = new ArrayList<>();
        route.add(gson.fromJson(jsonResponse, Map.class));

        return route;
    }
    
}
