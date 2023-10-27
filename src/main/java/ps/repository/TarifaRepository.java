package ps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ps.model.Tarifa;

import java.util.List;

	// para definir las operaciones de persistencia se va a trabajar con el modelo de tarifa.
@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Long> {

	List<Tarifa> findByValorGreaterThan(double valor);

	// Método personalizado que utiliza una consulta JPQL
	@Query("SELECT t FROM Tarifa t WHERE t.tarifa_normal > :valor")
	List<Tarifa> findTarifasConValorMayorQue(double valor);

	public List<Tarifa> getAll();
    public Tarifa save(Tarifa tarifa); // nueva tarifa
    public Tarifa update(Tarifa updatedTarifa, String id); // actualiza una tarifa especifica
    public void deleteById(String id); // elimina una tarifa por su id
    public Tarifa getById(String id);
    public boolean isIdFound(String id);

}
