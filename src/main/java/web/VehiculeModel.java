package web;
import entities.Vehicule;

import java.util.ArrayList;
import java.util.List;

public class VehiculeModel {
	private String motCle;
	private List<Vehicule> vehicules =new ArrayList<Vehicule>();
	public String getMotCle() {
		return motCle;
	}
	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}
	public List<Vehicule> getVehicules() {
		return vehicules;
	}
	public void setVehicules(List<Vehicule> vehicules) {
		this.vehicules = vehicules;
	}
}
