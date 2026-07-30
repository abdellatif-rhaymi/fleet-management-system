package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import com.google.gson.Gson;

import DemandeVoyageDao.DemandeVoyageImplDao;
import DemandeVoyageDao.IDemandeVoyageDao;
import UtilisateurDao.IUtilisateurDao;
import UtilisateurDao.UtilisateurDaoImpl;
import VehiculeDao.IVehiculeDao;
import VehiculeDao.VehiculeDaoImpl;
import VoyageDao.VoyageDaoImpl;
import VoyageDao.IVoyageDao;
import entities.DemandeVoyage;
import entities.Utilisateur;
import entities.Vehicule;
import entities.Voyage;

public class VoyageServlet extends HttpServlet {
    private IVoyageDao metier;
    private IVehiculeDao vehiculeDao;

    private IDemandeVoyageDao demandeVoyageDao;
	private IUtilisateurDao utilisateurDao;



    public void init() throws ServletException {
        metier = new VoyageDaoImpl();
        vehiculeDao = new VehiculeDaoImpl();
        utilisateurDao=new UtilisateurDaoImpl();
        demandeVoyageDao = new DemandeVoyageImplDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        if (path.equals("/voyage.voyage")) {
            VoyageModel model = new VoyageModel();  // Créer une instance du modèle
            List<Voyage> voyages = metier.getAllVoyages();
            model.setVoyages(voyages);
            request.setAttribute("model", model);
            request.setAttribute("voyages", voyages);
            request.getRequestDispatcher("Voyages.jsp").forward(request, response);

        } else  if (path.equals("/voyageChauffeur.voyage")) {
        	HttpSession session = request.getSession(false); // Récupérer la session existante
            if (session != null) {
                Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
                if (utilisateur != null && "chauffeur".equals(utilisateur.getRole())) {
                            //List<Livraison> livraisons = livraisonDao.getLivreurLivraisons(utilisateur.getIdUtilisateur());
                	VoyageModel model = new VoyageModel();  // Créer une instance du modèle
                    List<Voyage> voyages = metier.getChauffeurVoyages(utilisateur.getIdUtilisateur());
                    model.setVoyages(voyages);
                    request.setAttribute("model", model);
                    request.setAttribute("voyages", voyages);
                    request.getRequestDispatcher("VoyagesChauffeur.jsp").forward(request, response);


                            //request.setAttribute("livraisons", livraisons);
                            //request.getRequestDispatcher("LivraisonLivreur.jsp").forward(request, response);
                        } else {
                            response.sendRedirect("login.jsp");
                        }
                    
                }
            VoyageModel model = new VoyageModel();  // Créer une instance du modèle
            List<Voyage> voyages = metier.getAllVoyages();
            model.setVoyages(voyages);
            request.setAttribute("model", model);
            request.setAttribute("voyages", voyages);
            request.getRequestDispatcher("Voyages.jsp").forward(request, response);

        } else if (path.equals("/chercherVoyage.do")) {
            String motCle = request.getParameter("motCle");
            VoyageModel model = new VoyageModel();
            model.setMotCle(motCle);
            List<Voyage> voyages = metier.voyagesParMc("%" + motCle + "%");
            model.setVoyages(voyages);
            request.setAttribute("model", model);
            request.getRequestDispatcher("ChercherVoyage.jsp").forward(request, response);

        } else if (path.equals("/saisieVoyage.voyage")) {
        	VehiculeModel vehicul=new VehiculeModel();
            List<Vehicule> vehicules = vehiculeDao.getdispoVehicules();
            vehicul.setVehicules(vehicules);
            
        	UtilisateurModel model=new UtilisateurModel();  // Assurez-vous que la classe ProduitDao contient la méthode getAllProduits
    	    List<Utilisateur> utilisateurs = utilisateurDao.findLivreursDisponibles();
    	    model.setUtilisateurs(utilisateurs);
    	    
    	    request.setAttribute("vehicul", vehicul);
    	    request.setAttribute("vehicules", vehicules);
    	    request.setAttribute("model", model);
    	    request.setAttribute("utilisateurs", utilisateurs);
            request.getRequestDispatcher("ajouterVoyage.jsp").forward(request, response);

        } else if (path.equals("/saveVoyage.voyage") && request.getMethod().equals("POST")) {
        	
            Long idVehicule = Long.parseLong(request.getParameter("idVehicule"));

            Long idChauffeur = Long.parseLong(request.getParameter("idChauffeur"));
            Date dateDepart = Date.valueOf(request.getParameter("dateDepart"));
            Date dateArrivee = Date.valueOf(request.getParameter("dateArrivee"));
            String statut = request.getParameter("statut");

            Voyage voyage = metier.save(new Voyage(idVehicule, idChauffeur,dateDepart, dateArrivee, statut));
	       //utilisateurDao.updateLivreurStatut(idChauffeur, "indisponible");

            request.setAttribute("v", voyage);
            request.getRequestDispatcher("Voyages.jsp").forward(request, response);

        } else if (path.equals("/supprimerVoyage.do")) {
            Long id = Long.parseLong(request.getParameter("id"));
            metier.deleteVoyage(id);
            response.sendRedirect("voyage.do?motCle=");
        } else if (path.equals("/editerVoyage.do")) {
            Long id = Long.parseLong(request.getParameter("id"));
            Voyage v = metier.getVoyage(id);
            request.setAttribute("v", v);
            request.getRequestDispatcher("EditerVoyage.jsp").forward(request, response);
        }

        else if (path.equals("/updateVoyage.do") && request.getMethod().equals("POST")) {
            Long id = Long.parseLong(request.getParameter("id"));
            Long idDemande = Long.parseLong(request.getParameter("idDemande"));
            Long idVehicule = Long.parseLong(request.getParameter("idVehicule"));
            Long idChauffeur = Long.parseLong(request.getParameter("idChauffeur"));
            Long idTrajet = Long.parseLong(request.getParameter("idTrajet"));
            Date dateAttribution = Date.valueOf(request.getParameter("dateAttribution"));
            Date dateDepart = Date.valueOf(request.getParameter("dateDepart"));
            Date dateArrivee = Date.valueOf(request.getParameter("dateArrivee"));
            String statut = request.getParameter("statut");

            //Voyage v = new Voyage(idDemande, idVehicule, idChauffeur, idTrajet, dateAttribution, dateDepart, dateArrivee, statut);
           // v.setIdVoyage(id);
            //metier.update(v);
           // request.setAttribute("v", v);
           // request.getRequestDispatcher("ConfirmationVoyage.jsp").forward(request, response);
        
		} /*
			 * else if (path.equals("/trajetVoyage.do")) { int voyageId =
			 * Integer.parseInt(request.getParameter("voyageId"));
			 * 
			 * // Récupérer les demandes de voyage pour cet ID List<DemandeVoyage> demandes
			 * = demandeVoyageDao.getDemandesByVoyageId(voyageId);
			 * 
			 * // Vérifier si les demandes existent if (demandes != null &&
			 * !demandes.isEmpty()) { // Convertir la liste en JSON Gson gson = new Gson();
			 * String json = gson.toJson(demandes);
			 * 
			 * // Définir le type de contenu comme application/json
			 * response.setContentType("application/json");
			 * response.getWriter().write(json); } else { // Si aucune demande n'est
			 * trouvée, renvoyer un tableau vide
			 * response.setContentType("application/json");
			 * response.getWriter().write("[]"); } }
			 */
        	
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
