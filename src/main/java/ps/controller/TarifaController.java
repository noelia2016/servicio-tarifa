package ps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.requets.ErrorResponse;
import dto.response.TarifaResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import ps.model.Tarifa;
import ps.service.TokenServicio;
import ps.repository.TarifaRepository;
import java.util.List;

@RestController
//@RequestMapping("/tarifa")
@Tag(name = "Servicio Tarifa", description = "Encargado de todo lo referente a las tarifas por el uso del monopatin")
public class TarifaController {
	
	@Autowired
	private TarifaRepository TarifaRepository;

	@Value("${variable_env}")
	private String variable_env;

	@Autowired
    private TokenService token;

	@GetMapping("/variable_env")
	public String obtener_variable_env() {
		return variable_env;
	}

	@GetMapping("/string")
	public String obtener_string_hardcodeado() {
		return "Un mensaje de texto.";
	}

	// Obtener todos los Tarifas
	@GetMapping("/tarifas")
	public ResponseEntity<Object> obtenerTodosLasTarifas(@RequestHeader("Authorization") String authorization) {
		
		try {
			// si el token es valido
			if (token.autorizado(authorization) == null){
            	return null;
			}else{
				TarifaResponse p = new TarifaResponse(TarifaRepository.findAll());

				return ResponseEntity.ok(p);
			}
			
			
		} catch (Exception e) {
			// Ojo con esto por que puede enviar un error de BD al front,
			// se deberia controlar con e custom o error generico.
			ErrorResponse er = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
		}
		
	}

	// Crear una nueva Tarifa
	@PostMapping
	public Tarifa crearTarifa(@RequestBody Tarifa tarifa, @RequestHeader("Authorization") String authorization) {
		
		// si es administrador puede guardar tarifa
		if (token.autorizado(authorization).contains("ADMIN")) {
            return TarifaRepository.save(tarifa);
        }
        System.out.println("token no autorizado");
        return null;
		
	}

	// Actualizar Tarifa existente por ID
	@PutMapping("/{id}")
	public Tarifa actualizarTarifa(@PathVariable Long id, @RequestBody Tarifa tarifaAct, @RequestHeader("Authorization") String authorization) {

		// si es administrador puede guardar tarifa
		if (token.autorizado(authorization).contains("ADMIN")) {
			tarifaAct.setId(id);
			return TarifaRepository.save(tarifaAct);
		}
		System.out.println("token no autorizado");
        return null;
	}

	// Eliminar un Tarifa por ID
	@DeleteMapping("/{id}")
	public void eliminarTarifa(@PathVariable Long id, @RequestHeader("Authorization") String authorization ) {

		// si es administrador puede eliminar la tarifa
		if (token.autorizado(authorization).contains("ADMIN")) {
			TarifaRepository.deleteById(id);
		}
		System.out.println("token no autorizado");
 
	}

	// Obtener Tarifas con valor mayor a 1,000,000
	@GetMapping("/tarifa-normal")
	public Double tarifaVigenteNormal(){
		return TarifaRepository.tarifaVigenteNormal();
	}

	// Obtener Tarifaes con valor mayor a 1,000,000
	@GetMapping("/tarifa-extra")
	public Double tarifaVigenteExtra(){
		return TarifaRepository.tarifaVigenteExtra();
	}
}
