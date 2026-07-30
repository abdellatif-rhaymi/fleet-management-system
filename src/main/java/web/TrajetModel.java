package web;
import entities.Trajet;

import java.util.ArrayList;
import java.util.List;

public class TrajetModel {
	private String motCle;
	private List<Trajet> trajets =new ArrayList<Trajet>();
	public String getMotCle() {
		return motCle;
	}
	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}
	public List<Trajet> getTrajets() {
		return trajets;
	}
	public void setTrajets(List<Trajet> trajets) {
		this.trajets = trajets;
	}
}
