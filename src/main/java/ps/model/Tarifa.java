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

}
