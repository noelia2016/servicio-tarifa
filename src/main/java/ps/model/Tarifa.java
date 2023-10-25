package ps.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tarifa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private double tarifa_normal;

	@Column
	private double tarifa_extra;

	@Column
	private Date fecha_desde;

	@Column
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

	/* geters y seters */

	
	public double getTarifaNormal() {
		return tarifa_normal;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTarifaNormal(double tarifaN) {
		this.tarifa_normal = tarifaN;
	}


	public double getTarifaExtra() {
		return tarifa_extra;
	}


	public void setTarifaExtra(double tarifaE) {
		this.tarifa_extra = tarifaE;
	}


	public Date getFechaDesde() {
		return fecha_desde;
	}


	public void setFechaDesde(Date fechaDesde) {
		this.fecha_desde = fechaDesde;
	}


	public Date getFechaHasta() {
		return fecha_hasta;
	}


	public void setFechaHasta(Date fechaHasta) {
		this.fecha_hasta = fechaHasta;
	}


	
}


