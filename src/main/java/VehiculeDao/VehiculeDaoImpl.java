package VehiculeDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import SingletonConnection.SingletonConnection;
import entities.Vehicule;


public class VehiculeDaoImpl implements IVehiculeDao {

    @Override
    public Vehicule save(Vehicule v) {
        Connection connection = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO vehicules(immatriculation, type ,modele, couleur, annee, statut, derniere_maintenance, capacite,image) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, v.getImmatriculation());
            ps.setString(2, v.getType());
            ps.setString(3, v.getModele());
            ps.setString(4, v.getCouleur());
            ps.setInt(5, v.getAnnee());
            ps.setString(6, v.getStatut());
            ps.setDate(7, new java.sql.Date(v.getDerniereMaintenance().getTime()));
            ps.setInt(8, v.getCapacite());
            ps.setString(9, v.getImage());
            ps.executeUpdate();
            PreparedStatement ps2=connection.prepareStatement
    				("SELECT MAX(ID) AS MAX_ID FROM vehicules");
    		ResultSet rs=ps2.executeQuery();
    		if(rs.next()) {
    			v.setIdVehicule(rs.getLong("MAX_ID"));
    		}
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public List<Vehicule> vehiculesParMc(String mc) {
        List<Vehicule> vehicules = new ArrayList<>();
        Connection connection = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM vehicules WHERE immatriculation LIKE ?");
            ps.setString(1, mc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vehicule v = new Vehicule();
                v.setIdVehicule(rs.getLong("id_vehicule"));
                v.setImmatriculation(rs.getString("immatriculation"));
                v.setType(rs.getString("type"));
                v.setModele(rs.getString("modele"));
                v.setCouleur(rs.getString("couleur"));
                v.setAnnee(rs.getInt("annee"));
                v.setStatut(rs.getString("statut"));
                v.setDerniereMaintenance(rs.getDate("derniere_maintenance"));
                v.setCapacite(rs.getInt("capacite"));
                v.setImage(rs.getString("image"));

                vehicules.add(v);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicules;
    }
    public List<Vehicule> getdispoVehicules() {
        List<Vehicule> vehicules = new ArrayList<>();
        Connection connection = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM vehicules WHERE statut = 'disponible'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vehicule v = new Vehicule();
                v.setIdVehicule(rs.getLong("id_vehicule"));
                v.setImmatriculation(rs.getString("immatriculation"));
                v.setType(rs.getString("type"));
                v.setModele(rs.getString("modele"));
                v.setCouleur(rs.getString("couleur"));
                v.setAnnee(rs.getInt("annee"));
                v.setStatut(rs.getString("statut"));
                v.setDerniereMaintenance(rs.getDate("derniere_maintenance"));
                v.setCapacite(rs.getInt("capacite"));
                v.setImage(rs.getString("image"));

                vehicules.add(v);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicules;
    }
    public List<Vehicule> getAllVehicules() {
        List<Vehicule> vehicules = new ArrayList<>();
        Connection connection = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM vehicules");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vehicule v = new Vehicule();
                v.setIdVehicule(rs.getLong("id_vehicule"));
                v.setImmatriculation(rs.getString("immatriculation"));
                v.setType(rs.getString("type"));
                v.setModele(rs.getString("modele"));
                v.setCouleur(rs.getString("couleur"));
                v.setAnnee(rs.getInt("annee"));
                v.setStatut(rs.getString("statut"));
                v.setDerniereMaintenance(rs.getDate("derniere_maintenance"));
                v.setCapacite(rs.getInt("capacite"));
                v.setImage(rs.getString("image"));

                vehicules.add(v);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicules;
    }

    @Override
    public Vehicule getVehicule(Long id) {
        Vehicule v = null;
        Connection connection = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM vehicules WHERE id_vehicule = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                v = new Vehicule();
                v.setIdVehicule(rs.getLong("id_vehicule"));
                v.setImmatriculation(rs.getString("immatriculation"));
                v.setType(rs.getString("type"));
                v.setModele(rs.getString("modele"));
                v.setCouleur(rs.getString("couleur"));
                v.setAnnee(rs.getInt("annee"));
                v.setStatut(rs.getString("statut"));
                v.setDerniereMaintenance(rs.getDate("derniere_maintenance"));
                v.setCapacite(rs.getInt("capacite"));
                v.setImage(rs.getString("image"));

            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public Vehicule update(Vehicule v) {
        Connection connection = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE vehicules SET immatriculation=?,type=?, modele=?, couleur=?, annee=?, statut=?, derniere_maintenance=?, capacite=?, image=? WHERE id_vehicule=?");
            ps.setString(1, v.getImmatriculation());
            ps.setString(2, v.getType());
            ps.setString(3, v.getModele());
            ps.setString(4, v.getCouleur());
            ps.setInt(5, v.getAnnee());
            ps.setString(6, v.getStatut());
            ps.setDate(7, new java.sql.Date(v.getDerniereMaintenance().getTime()));
            ps.setInt(8, v.getCapacite());
            ps.setString(9, v.getImage());
            ps.setLong(10, v.getIdVehicule());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public void deleteVehicule(Long id) {
        Connection connection = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM vehicules WHERE id_vehicule=?");
            ps.setLong(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
