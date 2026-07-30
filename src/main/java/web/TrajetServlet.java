package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import TrajetDao.ITrajetDao;
import TrajetDao.TrajetDaoImpl;
import entities.Trajet;

@WebServlet("/trajet.do")
public class TrajetServlet extends HttpServlet {

    private ITrajetDao metier;

    @Override
    public void init() throws ServletException {
        metier = new TrajetDaoImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        if (path.equals("/trajet.do")) {
            // Afficher la liste des trajets
            TrajetModel model = new TrajetModel();
            List<Trajet> trajets = metier.getAllTrajets();
            model.setTrajets(trajets);
            request.setAttribute("model", model);
            request.setAttribute("trajets", trajets);
            request.getRequestDispatcher("Trajets.jsp").forward(request, response);
        
        } else if (path.equals("/chercherTrajet.do")) {
            // Recherche des trajets par mot-clé
            String motCle = request.getParameter("motCle");
            TrajetModel model = new TrajetModel();
            model.setMotCle(motCle);
            List<Trajet> trajets = metier.trajetsParDepart("%" + motCle + "%");
            model.setTrajets(trajets);
            request.setAttribute("model", model);
            request.getRequestDispatcher("ChercherTrajet.jsp").forward(request, response);
        
        } else if (path.equals("/saisieTrajet.do")) {
            // Formulaire pour ajouter un trajet
            request.getRequestDispatcher("ajouterTrajet.jsp").forward(request, response);
        
        } else if (path.equals("/saveTrajet.do") && request.getMethod().equals("POST")) {
            // Sauvegarder un trajet
            String depart = request.getParameter("depart");
            String destination = request.getParameter("destination");
            String itineraire = request.getParameter("itineraire");
            double distanceKm = Double.parseDouble(request.getParameter("distanceKm"));
            int dureeEstimeeMin = Integer.parseInt(request.getParameter("dureeEstimeeMin"));
            
            Trajet trajet = new Trajet(depart, destination, itineraire, distanceKm, dureeEstimeeMin);
            trajet = metier.save(trajet);
            request.setAttribute("trajet", trajet);
            request.getRequestDispatcher("ConfirmationTrajet.jsp").forward(request, response);
        
        } else if (path.equals("/supprimerTrajet.do")) {
            // Supprimer un trajet
            Long id = Long.parseLong(request.getParameter("id"));
            metier.deleteTrajet(id);
            response.sendRedirect("trajet.do");
        
        } else if (path.equals("/editerTrajet.do")) {
            // Formulaire pour éditer un trajet
            Long id = Long.parseLong(request.getParameter("id"));
            Trajet trajet = metier.getTrajet(id);
            request.setAttribute("trajet", trajet);
            request.getRequestDispatcher("EditerTrajet.jsp").forward(request, response);
        }
        
        else if (path.equals("/updateTrajet.do") && request.getMethod().equals("POST")) {
            // Mettre à jour un trajet existant
            Long id = Long.parseLong(request.getParameter("id"));
            String depart = request.getParameter("depart");
            String destination = request.getParameter("destination");
            String itineraire = request.getParameter("itineraire");
            double distanceKm = Double.parseDouble(request.getParameter("distanceKm"));
            int dureeEstimeeMin = Integer.parseInt(request.getParameter("dureeEstimeeMin"));
            
            Trajet trajet = new Trajet(id, depart, destination, itineraire, distanceKm, dureeEstimeeMin);
            metier.update(trajet);
            request.setAttribute("trajet", trajet);
            request.getRequestDispatcher("ConfirmationTrajet.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
