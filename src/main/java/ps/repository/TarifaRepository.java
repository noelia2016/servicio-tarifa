package ps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ps.model.Tarifa;

import java.util.List;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Long> {

	List<Tarifa> findByValorGreaterThan(double valor);

	// Método personalizado que utiliza una consulta JPQL
	@Query("SELECT t FROM Tarifa t WHERE t.tarifa_normal > :valor")
	List<Tarifa> findTarifasConValorMayorQue(double valor);


}
