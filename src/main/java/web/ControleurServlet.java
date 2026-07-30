package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import VehiculeDao.VehiculeDaoImpl;
import VehiculeDao.IVehiculeDao;
import entities.Vehicule;

public class ControleurServlet extends HttpServlet {
    private IVehiculeDao metier;

    public void init() throws ServletException {
        metier = new VehiculeDaoImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/vehicule.do")) {
        	VehiculeModel model=new VehiculeModel();  // Assurez-vous que la classe VehiculeDao contient la méthode getAllVehicules
            List<Vehicule> vehicules = metier.getAllVehicules();
            model.setVehicules(vehicules);
            request.setAttribute("model", model);
            request.setAttribute("vehicules", vehicules);
            request.getRequestDispatcher("Vehicules.jsp").forward(request, response);
        
        } else if (path.equals("/chercher.do")) {
            String motCle = request.getParameter("motCle");
            VehiculeModel model=new VehiculeModel();
            model.setMotCle(motCle);
            List<Vehicule> vehicules = metier.vehiculesParMc("%" + motCle + "%");
            model.setVehicules(vehicules);
            request.setAttribute("model", model);
            request.getRequestDispatcher("ChercherVehicule.jsp").forward(request, response);
        
        
        
        
        } else if (path.equals("/saisie.do")) {
            request.getRequestDispatcher("ajouterVehicule.jsp").forward(request, response);
        
        } else if (path.equals("/saveVehicule.do") && request.getMethod().equals("POST")) {
			
            String immatriculation = request.getParameter("immatriculation");
            String modele = request.getParameter("modele");
            String couleur = request.getParameter("couleur");
            int annee = Integer.parseInt(request.getParameter("annee"));
            String statut = request.getParameter("statut");
            Date derniereMaintenance = Date.valueOf(request.getParameter("derniereMaintenance"));
            int capacite = Integer.parseInt(request.getParameter("capacite"));
            String type = request.getParameter("type");
            String image = request.getParameter("image");

            Vehicule vehicule = metier.save(new Vehicule(immatriculation, type, modele, couleur, annee, statut, derniereMaintenance, capacite,image));
            request.setAttribute("v", vehicule);
            request.getRequestDispatcher("index.html").forward(request, response);
        }
        
        else if(path.equals("/supprimerVehicule.do")) {
			Long id=Long.parseLong(request.getParameter("id"));
			metier.deleteVehicule(id);
			response.sendRedirect("vehicule.do?motCle=");
		}
        else if(path.equals("/editerVehicule.do")) {
			Long id=Long.parseLong(request.getParameter("id"));
			Vehicule v= metier.getVehicule(id);
			request.setAttribute("v", v);
			//request.getRequestDispatcher("saisiearticle.jsp").forward(request, response);
			request.getRequestDispatcher("EditerVehicule.jsp").forward(request, response);
		}
        
        else if (path.equals("/updateVehicule.do") && request.getMethod().equals("POST")) {
			Long id =Long.parseLong(request.getParameter("id"));
            String immatriculation = request.getParameter("immatriculation");
            String modele = request.getParameter("modele");
            String couleur = request.getParameter("couleur");
            int annee = Integer.parseInt(request.getParameter("annee"));
            String statut = request.getParameter("statut");
            Date derniereMaintenance = Date.valueOf(request.getParameter("derniereMaintenance"));
            int capacite = Integer.parseInt(request.getParameter("capacite"));
            String type = request.getParameter("type");
            String image = request.getParameter("image");

            Vehicule v=new Vehicule(immatriculation,type, modele, couleur, annee, statut, derniereMaintenance, capacite,image);
            v.setIdVehicule(id);
            metier.update(v);
            request.setAttribute("v", v);
            request.getRequestDispatcher("index.html").forward(request, response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
    		doGet(request, response);
	}

}   
