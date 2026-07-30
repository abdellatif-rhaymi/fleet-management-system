package TrajetDao;

import java.util.List;
import entities.Trajet;

public interface ITrajetDao {
    public Trajet save(Trajet v);
    public List<Trajet> trajetsParDepart(String depart);
    public List<Trajet> getAllTrajets();
    public Trajet getTrajet(Long id);
    public Trajet update(Trajet v);
    public void deleteTrajet(Long id);
}
