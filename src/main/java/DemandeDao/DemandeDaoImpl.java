package DemandeDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import SingletonConnection.SingletonConnection;

import java.util.Date;

import entities.Demande;
import entities.Vehicule;

public class DemandeDaoImpl implements IDemandeDao {
	
	public Demande save(Demande d) {
	    Connection connection = SingletonConnection.getConnection();
	    try {
	        PreparedStatement ps = connection.prepareStatement(
	            "INSERT INTO demandese (numero_demande, date_demande, adresse_livraison, ville, code_postal, pays, date_livraison, statut, commentaire, latitude, longitude, weight) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)",
	            Statement.RETURN_GENERATED_KEYS
	        );
	        
	        ps.setString(1, d.getNumeroDemande());
	        ps.setDate(2, new java.sql.Date(d.getDateDemande().getTime()));
	        ps.setString(3, d.getAdresseLivraison());
	        ps.setString(4, d.getVille());
	        ps.setString(5, d.getCodePostal());
	        ps.setString(6, d.getPays());
            ps.setDate(7, new java.sql.Date(d.getDateLivraison().getTime()));
	        ps.setString(8, d.getStatut());
	        ps.setString(9, d.getCommentaire());
	        ps.setDouble(10, d.getLatitude());  // Latitude
	        ps.setDouble(11, d.getLongitude()); // Longitude
	        ps.setFloat(12, d.getWeight()); 
	        
	        ps.executeUpdate();
	        PreparedStatement ps2=connection.prepareStatement
    				("SELECT MAX(ID) AS MAX_ID FROM demandes");
	        ResultSet rs=ps2.executeQuery();
    		if(rs.next()) {
    			d.setIdDemande(rs.getLong("MAX_ID"));
    		}
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	    return d;
	}

	@Override
	public List<Demande> DemandesParMc(String mc) {
		List<Demande> demandes = new ArrayList<>();
	    Connection connection = SingletonConnection.getConnection();
	    try {PreparedStatement ps = connection.prepareStatement("SELECT * FROM demandese WHERE ville LIKE ?");
	    		ps.setString(1, "%" + mc + "%");
	    		ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Demande d = new Demande();
	            d.setIdDemande(rs.getLong("id_demande"));
	            d.setNumeroDemande(rs.getString("numero_demande"));
	            d.setDateDemande(rs.getDate("date_demande"));
	            d.setAdresseLivraison(rs.getString("adresse_livraison"));
	            d.setVille(rs.getString("ville"));
	            d.setCodePostal(rs.getString("code_postal"));
	            d.setPays(rs.getString("pays"));
	            d.setDateLivraison(rs.getDate("date_demande"));
	            d.setStatut(rs.getString("statut"));
	            d.setCommentaire(rs.getString("commentaire"));
	            demandes.add(d);
	        }
	    
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return demandes;
	}

	@Override
	public List<Demande> getAllDemandes() {
		List<Demande> demandes = new ArrayList<>();
	    Connection connection = SingletonConnection.getConnection();
	    try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM demandese");
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            Demande d = new Demande();
	            d.setIdDemande(rs.getLong("id_demande"));
	            d.setNumeroDemande(rs.getString("numero_demande"));
	            d.setDateDemande(rs.getDate("date_demande"));
	            d.setAdresseLivraison(rs.getString("adresse_livraison"));
	            d.setVille(rs.getString("ville"));
	            d.setCodePostal(rs.getString("code_postal"));
	            d.setPays(rs.getString("pays"));
	            d.setDateLivraison(rs.getDate("date_demande"));
	            d.setStatut(rs.getString("statut"));
	            d.setCommentaire(rs.getString("commentaire"));
	            demandes.add(d);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return demandes;
	}
	
	public List<Demande> getDemandesNotAssegned() {
		List<Demande> demandes = new ArrayList<>();
	    Connection connection = SingletonConnection.getConnection();
	    try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM demandese WHERE voyage_id IS NULL ");
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            Demande d = new Demande();
	            d.setIdDemande(rs.getLong("id_demande"));
	            d.setNumeroDemande(rs.getString("numero_demande"));
	            d.setDateDemande(rs.getDate("date_demande"));
	            d.setAdresseLivraison(rs.getString("adresse_livraison"));
	            d.setVille(rs.getString("ville"));
	            d.setCodePostal(rs.getString("code_postal"));
	            d.setPays(rs.getString("pays"));
	            d.setDateLivraison(rs.getDate("date_demande"));
	            d.setStatut(rs.getString("statut"));
	            d.setCommentaire(rs.getString("commentaire"));
	            d.setLatitude(rs.getDouble("latitude"));
	            d.setLongitude(rs.getDouble("longitude"));
	            d.setWeight(rs.getFloat("weight"));
                d.setIdVoyage(rs.getLong("voyage_id"));

	            demandes.add(d);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return demandes;
	}


	@Override
	public Demande getDemande(Long id) {
		Demande d = null;
        Connection connection = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM demandese WHERE id_demande = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	d = new Demande();
	            d.setIdDemande(rs.getLong("id_demande"));
	            d.setNumeroDemande(rs.getString("numero_demande"));
	            d.setDateDemande(rs.getDate("date_demande"));
	            d.setAdresseLivraison(rs.getString("adresse_livraison"));
	            d.setVille(rs.getString("ville"));
	            d.setCodePostal(rs.getString("code_postal"));
	            d.setPays(rs.getString("pays"));
	            d.setDateLivraison(rs.getDate("date_demande"));
	            d.setStatut(rs.getString("statut"));
	            d.setCommentaire(rs.getString("commentaire"));
	            d.setLatitude(rs.getDouble("latitude"));
	            d.setLongitude(rs.getDouble("longitude"));
	            d.setWeight(rs.getFloat("weight"));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    
	}
	public void editerDemandeidVoyage(Long id, Long idV) {
	    Connection connection = SingletonConnection.getConnection();
	    try {
	        String query = idV == null 
	            ? "UPDATE demandese SET voyage_id = NULL WHERE id_demande = ?" 
	            : "UPDATE demandese SET voyage_id = ? WHERE id_demande = ?";

	        PreparedStatement preparedStatement = connection.prepareStatement(query);

	        if (idV != null) {
	            preparedStatement.setLong(1, idV);
	            preparedStatement.setLong(2, id);
	        } else {
	            preparedStatement.setLong(1, id);
	        }

	        preparedStatement.executeUpdate();
	        preparedStatement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	/*
	 * public void editerDemandeidVoyage(Long id, Long idV) { Connection connection
	 * = SingletonConnection.getConnection(); try { PreparedStatement
	 * preparedStatement = connection.
	 * prepareStatement("UPDATE demandese SET voyage_id = ? WHERE id_demande = ?");
	 * preparedStatement.setLong(1, idV); preparedStatement.setLong(2, id);
	 * preparedStatement.executeUpdate(); preparedStatement.close(); } catch
	 * (SQLException e) { e.printStackTrace(); } }
	 */

	
	public Demande update(Demande demande) {
		Connection connection = SingletonConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE demandese SET numero_demande = ?, date_demande = ?, adresse_livraison = ?, ville = ?,code_postal = ?, pays = ?, date_livraison = ?, statut = ?, commentaire = ?, latitude = ?, longitude = ?, weight = ? WHERE id_demande = ?"); 

            preparedStatement.setString(1, demande.getNumeroDemande());
            preparedStatement.setDate(2, new java.sql.Date(demande.getDateDemande().getTime()));
            preparedStatement.setString(3, demande.getAdresseLivraison());
            preparedStatement.setString(4, demande.getVille());
            preparedStatement.setString(5, demande.getCodePostal());
            preparedStatement.setString(6, demande.getPays());
            preparedStatement.setDate(7, new java.sql.Date(demande.getDateLivraison().getTime()));
            preparedStatement.setString(8, demande.getStatut());
            preparedStatement.setString(9, demande.getCommentaire());
            preparedStatement.setDouble(10, demande.getLatitude());  // Latitude
            preparedStatement.setDouble(11, demande.getLongitude()); // Longitude
            preparedStatement.setFloat(12, demande.getWeight());
            preparedStatement.setLong(13, demande.getIdDemande());

            preparedStatement.executeUpdate();
        	preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return demande;
	}

	@Override
	public void deleteDemande(Long id) {
		// TODO Auto-generated method stub
		Connection connection = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM demandese WHERE id_demande=?");
            ps.setLong(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public List<Demande> getDemandesAssegned(Long id) {
		 List<Demande> demandes = new ArrayList<>();
		    Connection connection = SingletonConnection.getConnection();

		    try {
				String query = "SELECT * FROM demandese WHERE voyage_id = ?";

		    	PreparedStatement ps = connection.prepareStatement(query);
		        ps.setLong(1, id);
		        ResultSet rs = ps.executeQuery(); 
		            while (rs.next()) {
		                Demande d = new Demande();
		                d.setIdDemande(rs.getLong("id_demande"));
		                d.setNumeroDemande(rs.getString("numero_demande"));
		                d.setDateDemande(rs.getDate("date_demande"));
		                d.setAdresseLivraison(rs.getString("adresse_livraison"));
		                d.setVille(rs.getString("ville"));
		                d.setCodePostal(rs.getString("code_postal"));
		                d.setPays(rs.getString("pays"));
		                d.setDateLivraison(rs.getDate("date_livraison")); // Corrigé pour prendre "date_livraison"
		                d.setStatut(rs.getString("statut"));
		                d.setCommentaire(rs.getString("commentaire"));
		                d.setLatitude(rs.getDouble("latitude"));
		                d.setLongitude(rs.getDouble("longitude"));
		                d.setWeight(rs.getFloat("weight"));
		                d.setIdVoyage(rs.getLong("voyage_id"));

		                demandes.add(d);
		            }
		            ps.close();
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
		    return demandes;
		}

	public float getCapaciteVehiculeById(Long idVehicule) {
	    float capacite = 0;
	    Connection connection = SingletonConnection.getConnection();
try {
	    String query = "SELECT v.capacite\n"
	    		+ "        FROM vehicules v\n"
	    		+ "        INNER JOIN voyages vo ON v.id_vehicule = vo.id_vehicule\n"
	    		+ "        WHERE vo.id_voyage = ?";
	    PreparedStatement stmt = connection.prepareStatement(query);
	        stmt.setLong(1, idVehicule);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            capacite = rs.getFloat("capacite");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return capacite;
	}
	public float getPoidsTotalByVoyageId(Long idVoyage) {
	    float totalPoids = 0;
	    Connection connection = SingletonConnection.getConnection();
try {
	    String query = "SELECT SUM(weight) AS total_weight FROM demandese WHERE voyage_id = ?";
	    PreparedStatement stmt = connection.prepareStatement(query);
	        stmt.setLong(1, idVoyage);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            totalPoids = rs.getFloat("total_weight");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return totalPoids;
	}
	public float getPoidsDemandeById(Long idDemande) {
	    Connection connection = SingletonConnection.getConnection();
	    float poids = 0;
	    try {
	    String query = "SELECT weight FROM demandese WHERE id_demande = ?";
	    PreparedStatement stmt = connection.prepareStatement(query);
	        stmt.setLong(1, idDemande);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            poids = rs.getFloat("weight");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return poids;
	}



	


}
