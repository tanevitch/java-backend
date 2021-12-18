package ttps.spring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tipoServicio")
public class TipoServicio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false)
	private String nombre;
	
	@OneToMany
    @JoinColumn(nullable=true)
	@JsonIgnore
	private List<AspectoPuntuacion> aspectoAValorar = new ArrayList<AspectoPuntuacion>();

	
	public TipoServicio(String nombre, List<AspectoPuntuacion> aspectoAValorar) {
		this.nombre = nombre;
		this.aspectoAValorar = aspectoAValorar;
	}
	
	public TipoServicio() {
		
	}
	
	public TipoServicio(String nombre) {
		this.nombre=nombre;
	}
	
	public long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<AspectoPuntuacion> getAspectoAValorar() {
		return aspectoAValorar;
	}
	
	
	
}
