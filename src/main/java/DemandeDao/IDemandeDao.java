package DemandeDao;

import java.util.List;
import entities.Demande;

public interface IDemandeDao {
    public Demande save(Demande d);
    public List<Demande> DemandesParMc(String mc);
    public List<Demande> getAllDemandes();
    public Demande getDemande(Long id);
    public Demande update(Demande d);
    public void deleteDemande(Long id);
    public List<Demande> getDemandesAssegned(Long id);
    public List<Demande> getDemandesNotAssegned();
    public void editerDemandeidVoyage(Long id,Long idV);
    float getPoidsTotalByVoyageId(Long idVoyage);
    float getCapaciteVehiculeById(Long idVehicule);
    float getPoidsDemandeById(Long idDemande);

}
