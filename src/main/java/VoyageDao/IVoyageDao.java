package VoyageDao;

import java.util.List;
import entities.Voyage;

public interface IVoyageDao {
    public Voyage save(Voyage v);
    public List<Voyage> voyagesParMc(String mc);
    public List<Voyage> getAllVoyages();
    public Voyage getVoyage(Long id);
    public Voyage update(Voyage v);
    public void deleteVoyage(Long id);
    public List<Voyage> getChauffeurVoyages(Long id);
}
