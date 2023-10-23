package ps.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tarifa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double tarifa_normal;
	private double tarifa_extra;
	private Date fecha_desde;
	private Date fecha_hasta;

	// Constructor vacío
	public Tarifa() {
	}


	// Constructor con todos los campos
	public Tarifa(double valor_normal, double valor_extra, Date fecha_ds, Date fecha_hs) {
		
		this.tarifa_normal = valor_normal;
		this.tarifa_extra = valor_extra;
		this.fecha_desde = fecha_ds;
		this.fecha_hasta = fecha_hs;
	}

	// Getters y setters
	public double getTarifaNormal() {
		return tarifa_normal;
	}

	public void setTarifaNormal(Long valor) {
		this.tarifa_normal = valor;
	}

	public double getTarifaExtra() {
		return tarifa_extra;
	}

	public void setTarifaExtra(Long valor) {
		this.tarifa_extra = valor;
	}

	public Date getFechaDesde() {
		return fecha_desde;
	}

	public void setFechaDesde(double fecha) {
		this.fecha_desde = fecha;
	}

	public Date getFechaHasta() {
		return fecha_hasta;
	}

	public void setFechaHasta(double fecha) {
		this.fecha_hasta = fecha;
	}


}
