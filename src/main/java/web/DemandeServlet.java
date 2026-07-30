package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import DemandeDao.IDemandeDao;
import DemandeVoyageDao.DemandeVoyageImplDao;
import DemandeDao.DemandeDaoImpl;
import entities.Demande;
import entities.DemandeVoyage;
import entities.Demande;

/**
 * Servlet implementation class DemandeServlet
 */
public class DemandeServlet extends HttpServlet {
	private IDemandeDao metier;

    public void init() throws ServletException {
        metier =  new DemandeDaoImpl();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/demande.demande")) {
			
		    // Initialize a model to hold the list of demandes
		    DemandeModel model = new DemandeModel();
		    List<Demande> demandes = metier.getAllDemandes();  // Ensure DemandeDao has this method to retrieve all demandes
		    model.setDemandes(demandes);
		    request.setAttribute("model", model);
		    request.setAttribute("demandes", demandes);
		    request.getRequestDispatcher("Demandes.jsp").forward(request, response);

		}else if (path.equals("/idVehicule.demande")) {
			Long id=Long.parseLong(request.getParameter("id"));

		    // Initialize a model to hold the list of demandes
		    DemandeModel model = new DemandeModel();
		    List<Demande> demandes = metier.getDemandesAssegned(id);  // Ensure DemandeDao has this method to retrieve all demandes
		    model.setDemandes(demandes);
		    request.setAttribute("idVoyage", id);
		    request.setAttribute("model", model);
		    request.setAttribute("demandes", demandes);
		    request.getRequestDispatcher("DemandesVoyage.jsp").forward(request, response);

		}else if (path.equals("/addDV.demande")) {
			Long id=Long.parseLong(request.getParameter("id"));

		    // Initialize a model to hold the list of demandes
		    DemandeModel model = new DemandeModel();
		    List<Demande> demandes = metier.getDemandesNotAssegned();  // Ensure DemandeDao has this method to retrieve all demandes
		    model.setDemandes(demandes);
		    request.setAttribute("idVoyage", id);
		    request.setAttribute("model", model);
		    request.setAttribute("demandes", demandes);
		    request.getRequestDispatcher("DemandesnonVoyage.jsp").forward(request, response);

		} else if (path.equals("/chercher.demande")) {
		    // Get the search keyword for filtering demandes
		    String motCle = request.getParameter("motCle");
		    DemandeModel model = new DemandeModel();
		    model.setMotCle(motCle);
		    
		    // Fetch demandes based on the keyword
		    List<Demande> demandes = metier.DemandesParMc("%" + motCle + "%");  // Ensure this method filters demandes by keyword
		    model.setDemandes(demandes);
		    request.setAttribute("model", model);
		    request.getRequestDispatcher("ChercherDemande.jsp").forward(request, response);
		
		} else if (path.equals("/saisie.demande")) {
		    request.getRequestDispatcher("ajouterDemande.jsp").forward(request, response);

		} else if (path.equals("/save.demande") && request.getMethod().equals("POST")) {
		    String numeroDemande = request.getParameter("numero_demande");
		    Date dateDemande = Date.valueOf(request.getParameter("date_demande"));
		    String adresseLivraison = request.getParameter("adresse_livraison");
		    String ville = request.getParameter("ville");
		    String codePostal = request.getParameter("code_postal");
		    String pays = request.getParameter("pays");
		    Date dateLivraison = Date.valueOf(request.getParameter("date_livraison"));		    
		    String statut = request.getParameter("statut");
		    String commentaire = request.getParameter("commentaire");
		    double latitude = Double.parseDouble(request.getParameter("latitude"));
		    double longitude = Double.parseDouble(request.getParameter("longitude"));
		    float weight = Float.parseFloat(request.getParameter("weight"));

		    Demande demande = metier.save(new Demande(numeroDemande, dateDemande, adresseLivraison, ville, codePostal, pays, dateLivraison, statut, commentaire, latitude, longitude, weight));
		    request.setAttribute("demande", demande);
		    request.getRequestDispatcher("index.html").forward(request, response);
		} else if(path.equals("/supprimer.demande")) {
			Long id=Long.parseLong(request.getParameter("id"));
			metier.deleteDemande(id);
			response.sendRedirect("demande.demande?motCle=");
		}
		else if(path.equals("/editerDemande.demande")) {
			Long id=Long.parseLong(request.getParameter("id"));
			Demande d= metier.getDemande(id);
			request.setAttribute("demande", d);
			//request.getRequestDispatcher("saisiearticle.jsp").forward(request, response);
			request.getRequestDispatcher("eEditerDemande.jsp").forward(request, response);
		
		}else if (path.equals("/editerDemandeidVoyage.demande")) {
		    try {
		        Long id = Long.parseLong(request.getParameter("id"));
		        String idVParam = request.getParameter("idV");

		        // Interpréter idV comme null s'il est vide ou absent
		        Long idV = (idVParam == null || idVParam.isEmpty()) ? null : Long.parseLong(idVParam);

		        // Récupérer le poids de la demande à ajouter
		        float poidsNouvelleDemande = metier.getPoidsDemandeById(id);

		        // Vérifier la capacité du véhicule si idV n'est pas null
		        if (idV != null) {
		            float capaciteVehicule = metier.getCapaciteVehiculeById(idV);
		            float poidsActuelVoyage = metier.getPoidsTotalByVoyageId(idV);
					request.setAttribute("idVoyage", idV);

		            // Vérifier si l'ajout dépasse la capacité
		            if (poidsActuelVoyage + poidsNouvelleDemande > capaciteVehicule) {
		            	request.getRequestDispatcher("error.jsp").forward(request, response);;
		                return;
		            }

		        }

		        // Ajouter ou modifier la demande dans le voyage
		        metier.editerDemandeidVoyage(id, idV);
		        response.sendRedirect("voyage.voyage");
		    } catch (NumberFormatException e) {
		        // Gérer les erreurs de conversion
		        e.printStackTrace();
		        response.sendRedirect("errorPage.jsp?message=Format d'identifiant incorrect !");
		    } catch (Exception e) {
		        // Gérer toutes les autres exceptions
		        e.printStackTrace();
		        response.sendRedirect("errorPage.jsp?message=Une erreur s'est produite !");
		    }
		}else if (path.equals("/update.demande") && request.getMethod().equals("POST")) {
		    try {
		        // Récupération des paramètres
		        Long id = Long.parseLong(request.getParameter("id_demande"));
		        String numeroDemande = request.getParameter("numero_demande");
		        Date dateDemande = Date.valueOf(request.getParameter("date_demande"));
		        String adresseLivraison = request.getParameter("adresse_livraison");
		        String ville = request.getParameter("ville");
		        String codePostal = request.getParameter("code_postal");
		        String pays = request.getParameter("pays");
		        Date dateLivraison = Date.valueOf(request.getParameter("date_livraison"));
		        String statut = request.getParameter("statut");
		        String commentaire = request.getParameter("commentaire");
		        double latitude = Double.parseDouble(request.getParameter("latitude"));
			    double longitude = Double.parseDouble(request.getParameter("longitude"));
			    float weight = Float.parseFloat(request.getParameter("weight"));

		         //Construction de l'objet demande
			    Demande demande = metier.update(new Demande(id,numeroDemande, dateDemande, adresseLivraison, ville, codePostal, pays, dateLivraison, statut, commentaire, latitude, longitude, weight));


		        // Redirection après succès
		        response.sendRedirect("demande.demande");
		    } catch (Exception e) {
		        // Gestion des erreurs
		        e.printStackTrace();
		        request.setAttribute("error", "Erreur lors de la mise à jour de la demande : " + e.getMessage());
		        request.getRequestDispatcher("/editDemande.jsp").forward(request, response);
		    }
		}  else if(path.equals("/trajet.demande")) {
		 DemandeVoyageImplDao dao = new DemandeVoyageImplDao();
		 	DemandeModel model = new DemandeModel();
	        // Récupérer les demandes pour le voyage avec l'ID 1
	        List<DemandeVoyage> demandes = dao.getDemandesByVoyageId(1);
	        
	        // Vérifier si des demandes ont été trouvées
	        if (demandes.isEmpty()) {
	            System.out.println("Aucune demande trouvée pour ce voyage.");
	            return;
	        }
	        
	        // Générer la carte
	        model.generateRouteMap(demandes);
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
