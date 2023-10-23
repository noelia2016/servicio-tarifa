package ps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import dto.requets.ErrorResponse;
import dto.response.TarifaResponse;
import ps.model.Tarifa;
import ps.repository.TarifaRepository;
import java.util.List;

@RestController
@RequestMapping("/Tarifas")
public class TarifaController {
	
	@Autowired
	private TarifaRepository TarifaRepository;

	@Value("${variable_env}")
	private String variable_env;

	@GetMapping("/variable_env")
	public String obtener_variable_env() {
		return variable_env;
	}

	@GetMapping("/string")
	public String obtener_string_hardcodeado() {
		return "Un mensaje de texto.";
	}

	// Obtener todos los Tarifaes
	@GetMapping
	public ResponseEntity<Object> obtenerTodosLosTarifaes() {
		try {
			// TODO: Pasar al service.
			TarifaResponse jr = new TarifaResponse(TarifaRepository.findAll());
			// Algun llamado al service.
			//throw new Exception("Este es un mensaje opcional");
			return ResponseEntity.ok(jr);
			
		} catch (Exception e) {
			// Ojo con esto por que puede enviar un error de BD al front,
			// se deberia controlar con e custom o error generico.
			ErrorResponse er = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
		}
	}

	// Crear un nuevo Tarifa
	@PostMapping
	public Tarifa crearTarifa(@RequestBody Tarifa Tarifa) {
		return TarifaRepository.save(Tarifa);
	}

	// Actualizar un Tarifa existente por ID
	@PutMapping("/{id}")
	public Tarifa actualizarTarifa(@PathVariable Long id, @RequestBody Tarifa TarifaActualizado) {
		TarifaActualizado.setId(id);
		return TarifaRepository.save(TarifaActualizado);
	}

	// Eliminar un Tarifa por ID
	@DeleteMapping("/{id}")
	public void eliminarTarifa(@PathVariable Long id) {
		TarifaRepository.deleteById(id);
	}

	// Obtener Tarifaes con valor mayor a 1,000,000
	@GetMapping("/valor-mayor")
	public List<Tarifa> obtenerTarifasConValorMayor() {
		// return TarifaRepository.findByValorGreaterThan(1000000);
		// return TarifaRepository.findTarifaesPorPosicion("Delantero");
		return TarifaRepository.findTarifasConValorMayorQue(1000000);
	}
}
