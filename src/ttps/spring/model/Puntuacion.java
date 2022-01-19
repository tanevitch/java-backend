package ttps.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "puntuacion")
public class Puntuacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable=false)
	private int nota;
	
	@OneToOne
	private AspectoPuntuacion aspectoPuntuacion;
	
	@ManyToOne
    @JoinColumn(nullable=false)
	private Servicio servicio;
	
	@ManyToOne
    @JoinColumn(nullable=false)
	private Usuario usuario;

	public Puntuacion(int nota, AspectoPuntuacion aspectoPuntuacion) {
		this.nota = nota;
		this.aspectoPuntuacion= aspectoPuntuacion;
	}
	
	public Puntuacion() {
		
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public AspectoPuntuacion getAspectoPuntuacion() {
		return aspectoPuntuacion;
	}

	public void setAspectoPuntuacion(AspectoPuntuacion aspectoPuntuacion) {
		this.aspectoPuntuacion = aspectoPuntuacion;
	}

	public long getId() {
		return id;
	}
	
	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
