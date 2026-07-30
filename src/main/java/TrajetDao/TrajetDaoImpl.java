package TrajetDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import SingletonConnection.SingletonConnection;
import entities.Trajet;

public class TrajetDaoImpl implements ITrajetDao {

    @Override
    public Trajet save(Trajet t) {
        Connection connection = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO trajets(depart, destination, itineraire, distance_km, duree_estimee_min) VALUES(?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, t.getDepart());
            ps.setString(2, t.getDestination());
            ps.setString(3, t.getItineraire());
            ps.setDouble(4, t.getDistanceKm());
            ps.setInt(5, t.getDureeEstimeeMin());
            ps.executeUpdate();

            // Récupérer l'ID généré
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                t.setIdTrajet(rs.getLong(1));
            }

            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public List<Trajet> trajetsParDepart(String depart) {
        List<Trajet> trajets = new ArrayList<>();
        Connection connection = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM trajets WHERE depart LIKE ?");
            ps.setString(1, depart);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Trajet t = new Trajet();
                t.setIdTrajet(rs.getLong("id_trajet"));
                t.setDepart(rs.getString("depart"));
                t.setDestination(rs.getString("destination"));
                t.setItineraire(rs.getString("itineraire"));
                t.setDistanceKm(rs.getDouble("distance_km"));
                t.setDureeEstimeeMin(rs.getInt("duree_estimee_min"));
                trajets.add(t);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trajets;
    }

    @Override
    public List<Trajet> getAllTrajets() {
        List<Trajet> trajets = new ArrayList<>();
        Connection connection = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM trajets");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Trajet t = new Trajet();
                t.setIdTrajet(rs.getLong("id_trajet"));
                t.setDepart(rs.getString("depart"));
                t.setDestination(rs.getString("destination"));
                t.setItineraire(rs.getString("itineraire"));
                t.setDistanceKm(rs.getDouble("distance_km"));
                t.setDureeEstimeeMin(rs.getInt("duree_estimee_min"));
                trajets.add(t);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trajets;
    }

    @Override
    public Trajet getTrajet(Long id) {
        Trajet t = null;
        Connection connection = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM trajets WHERE id_trajet = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                t = new Trajet();
                t.setIdTrajet(rs.getLong("id_trajet"));
                t.setDepart(rs.getString("depart"));
                t.setDestination(rs.getString("destination"));
                t.setItineraire(rs.getString("itineraire"));
                t.setDistanceKm(rs.getDouble("distance_km"));
                t.setDureeEstimeeMin(rs.getInt("duree_estimee_min"));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public Trajet update(Trajet t) {
        Connection connection = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                "UPDATE trajets SET depart=?, destination=?, itineraire=?, distance_km=?, duree_estimee_min=? WHERE id_trajet=?"
            );
            ps.setString(1, t.getDepart());
            ps.setString(2, t.getDestination());
            ps.setString(3, t.getItineraire());
            ps.setDouble(4, t.getDistanceKm());
            ps.setInt(5, t.getDureeEstimeeMin());
            ps.setLong(6, t.getIdTrajet());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public void deleteTrajet(Long id) {
        Connection connection = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM trajets WHERE id_trajet=?");
            ps.setLong(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
