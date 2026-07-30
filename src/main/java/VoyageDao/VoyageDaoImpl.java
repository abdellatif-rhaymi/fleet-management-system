package VoyageDao;

import entities.Voyage;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import SingletonConnection.SingletonConnection;

public class VoyageDaoImpl implements IVoyageDao {

    @Override
    public Voyage save(Voyage voyage) {
        Connection connection = SingletonConnection.getConnection();
        try {
            // Préparation de la requête SQL pour l'insertion d'un voyage
            PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO voyages (id_vehicule, id_chauffeur,date_depart, date_arrivee, statut) VALUES ( ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, voyage.getIdVehicule());
            ps.setLong(2, voyage.getIdChauffeur());
           ps.setDate(3, voyage.getDateDepart());
            ps.setDate(4, voyage.getDateArrivee());
            ps.setString(5, voyage.getStatut());
            ps.executeUpdate();
            
            // Récupération de l'ID généré pour l'objet Voyage
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                voyage.setIdVoyage(rs.getLong(1));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voyage;
    }

    @Override
    public Voyage getVoyage(Long id) {
        Voyage voyage = null;
        Connection connection = SingletonConnection.getConnection();
        try {
            // Préparation de la requête pour obtenir un voyage par son ID
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM voyages WHERE id_voyage = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                voyage = new Voyage();
                voyage.setIdVoyage(rs.getLong("id_voyage"));
                voyage.setIdVehicule(rs.getLong("id_vehicule"));
                voyage.setIdChauffeur(rs.getLong("id_chauffeur"));
              voyage.setDateDepart(rs.getDate("date_depart"));
                voyage.setDateArrivee(rs.getDate("date_arrivee"));
                voyage.setStatut(rs.getString("statut"));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voyage;
    }
    
    @Override
    public List<Voyage> getAllVoyages() {
        List<Voyage> voyages = new ArrayList<>();
        Connection connection = SingletonConnection.getConnection();
        try {
            // Préparation de la requête pour obtenir tous les voyages
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM voyages");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Voyage voyage = new Voyage();
                voyage.setIdVoyage(rs.getLong("id_voyage"));
                voyage.setIdVehicule(rs.getLong("id_vehicule"));
                voyage.setIdChauffeur(rs.getLong("id_chauffeur"));
            voyage.setDateDepart(rs.getDate("date_depart"));
                voyage.setDateArrivee(rs.getDate("date_arrivee"));
                voyage.setStatut(rs.getString("statut"));
                voyages.add(voyage);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voyages;
    }
	
   
    public List<Voyage> getChauffeurVoyages(Long id) {
        List<Voyage> voyages = new ArrayList<>();
        Connection connection = SingletonConnection.getConnection();
        try {
            // Préparation de la requête pour obtenir tous les voyages
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM voyages WHERE id_chauffeur = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Voyage voyage = new Voyage();
                voyage.setIdVoyage(rs.getLong("id_voyage"));
                voyage.setIdVehicule(rs.getLong("id_vehicule"));
                voyage.setIdChauffeur(rs.getLong("id_chauffeur"));
                voyage.setDateDepart(rs.getDate("date_depart"));
                voyage.setDateArrivee(rs.getDate("date_arrivee"));
                voyage.setStatut(rs.getString("statut"));
                voyages.add(voyage);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voyages;
    }
    @Override
    public Voyage update(Voyage voyage) {
        Connection connection = SingletonConnection.getConnection();
        try {
            // Préparation de la requête pour la mise à jour d'un voyage
            PreparedStatement ps = connection.prepareStatement(
                "UPDATE voyages SET id_vehicule = ?, id_chauffeur = ?,date_depart = ?, date_arrivee = ?, statut = ? WHERE id_voyage = ?");
            ps.setLong(1, voyage.getIdVehicule());
            ps.setLong(2, voyage.getIdChauffeur());
          ps.setDate(3, voyage.getDateDepart());
            ps.setDate(4, voyage.getDateArrivee());
            ps.setString(5, voyage.getStatut());
            ps.setLong(6, voyage.getIdVoyage());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voyage;
    }

    @Override
    public void deleteVoyage(Long id) {
        Connection connection = SingletonConnection.getConnection();
        try {
            // Préparation de la requête pour supprimer un voyage par son ID
            PreparedStatement ps = connection.prepareStatement("DELETE FROM voyages WHERE id_voyage = ?");
            ps.setLong(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Voyage> voyagesParMc(String mc) {
        List<Voyage> voyages = new ArrayList<>();
        Connection connection = SingletonConnection.getConnection();
        try {
            // Préparation de la requête pour rechercher des voyages par un critère
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM voyages WHERE statut LIKE ?");
            ps.setString(1, "%" + mc + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Voyage voyage = new Voyage();
                voyage.setIdVoyage(rs.getLong("id_voyage"));
                voyage.setIdVehicule(rs.getLong("id_vehicule"));
                voyage.setIdChauffeur(rs.getLong("id_chauffeur"));
                voyage.setDateDepart(rs.getDate("date_depart"));
                voyage.setDateArrivee(rs.getDate("date_arrivee"));
                voyage.setStatut(rs.getString("statut"));
                voyages.add(voyage);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voyages;
    }
}
