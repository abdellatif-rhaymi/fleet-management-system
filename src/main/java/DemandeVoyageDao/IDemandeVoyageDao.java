package DemandeVoyageDao;

import java.util.List;

import entities.DemandeVoyage;

public interface IDemandeVoyageDao {

	List<DemandeVoyage> getDemandesByVoyageId(int voyageId);
	 void generateRouteMap(List<DemandeVoyage> demandes);

}
