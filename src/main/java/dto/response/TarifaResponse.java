package dto.response;

import java.util.List;

import ps.model.Tarifa;

public class TarifaResponse {

	private List<Tarifa> Tarifaes;

	public TarifaResponse(List<Tarifa> Tarifas) {
		this.Tarifaes = Tarifas;
	}

	public List<Tarifa> getTarifaes() {
		return Tarifas;
	}

	public void setTarifas(List<Tarifa> Tarifas) {
		this.Tarifas = Tarifas;
	}
}
