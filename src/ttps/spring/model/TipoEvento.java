package ttps.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipoEvento")
public class TipoEvento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable=false)
	private String nombre;
	@Column(nullable=false)
	private boolean esPrivado;
	
	public TipoEvento() {
		
	}
	
	public TipoEvento(String nombre, boolean esPrivado) {
		this.nombre = nombre;
		this.esPrivado = esPrivado;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isEsPrivado() {
		return esPrivado;
	}
	public void setEsPrivado(boolean esPrivado) {
		this.esPrivado = esPrivado;
	}
	public long getId() {
		return id;
	}
	
	
	

}
