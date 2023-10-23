package dto.response;

import java.util.List;

import ps.model.Tarifa;

public class TarifaResponse {

	private List<Tarifa> tarifas;

	public TarifaResponse(List<Tarifa> tarifas) {
		this.tarifas = tarifas;
	}

	public List<Tarifa> getTarifas() {
		return tarifas;
	}

	public void setTarifas(List<Tarifa> tarifas) {
		this.tarifas = tarifas;
	}
}
