package web;
import entities.Voyage;

import java.util.ArrayList;
import java.util.List;

public class VoyageModel {
	private String motCle;
	private List<Voyage> voyages =new ArrayList<Voyage>();
	public String getMotCle() {
		return motCle;
	}
	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}
	public List<Voyage> getVoyages() {
		return voyages;
	}
	public void setVoyages(List<Voyage> voyages) {
		this.voyages = voyages;
	}
}
