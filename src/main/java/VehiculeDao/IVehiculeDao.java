package VehiculeDao;

import java.util.List;
import entities.Vehicule;

public interface IVehiculeDao {
    public Vehicule save(Vehicule v);
    public List<Vehicule> vehiculesParMc(String mc);
    public List<Vehicule> getdispoVehicules();
    public List<Vehicule> getAllVehicules();
    public Vehicule getVehicule(Long id);
    public Vehicule update(Vehicule v);
    public void deleteVehicule(Long id);
}
