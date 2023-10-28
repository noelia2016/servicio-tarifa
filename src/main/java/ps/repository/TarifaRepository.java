package ps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ps.model.Tarifa;

import java.util.List;

	// para definir las operaciones de persistencia se va a trabajar con el modelo de tarifa.
@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Long> {

	/*List<Tarifa> findByValorGreaterThan(double valor);

	// Método personalizado que utiliza una consulta JPQL
	@Query("SELECT t FROM Tarifa t WHERE t.tarifa_normal > :valor")
	List<Tarifa> findTarifasConValorMayorQue(double valor);*/

	@Query("SELECT t.tarifa_normal FROM Tarifa t where t.fecha_desde = (SELECT MAX(tn.fecha_desde) FROM Tarifa tn) ")
	public Double tarifaVigenteNormal();

	@Query("SELECT t.tarifa_extra FROM Tarifa t where t.fecha_desde = (SELECT MAX(tn.fecha_desde) FROM Tarifa tn) ")
	public Double tarifaVigenteExtra();
}
