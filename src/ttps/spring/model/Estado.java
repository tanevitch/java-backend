package ttps.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estado")
public class Estado {
	public static final String FINALIZADA = "FINALIZADA";
	public static final String CONFIRMADA = "CONFIRMADA";
	public static final String SINCONFIRMAR = "SINCONFIRMAR";
	public static final String RECHAZADA = "RECHAZADA";
	public static final String CANCELADA = "CANCELADA";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable=false)
	private String nombre;
	
	public Estado() {
		
	}
	
	public Estado(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public long getId() {
		return id;
	}
	
	
}
