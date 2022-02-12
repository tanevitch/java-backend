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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "puntuacion", uniqueConstraints= @UniqueConstraint(columnNames={"servicio_id", "usuario_id", "aspectoPuntuacion_id", "evento_id"}))
public class Puntuacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable=false)
	private double nota;
	
	@OneToOne
	@JoinColumn(nullable=false)
	private AspectoPuntuacion aspectoPuntuacion;
	
	@ManyToOne
    @JoinColumn(nullable=false)
	private Servicio servicio;
	
	@ManyToOne
    @JoinColumn(nullable=false)
	private Usuario usuario;
	
	@OneToOne
	@JoinColumn(nullable=false)
	private Evento evento;
	
	public Puntuacion(double nota, AspectoPuntuacion aspectoPuntuacion) {
		this.nota = nota;
		this.aspectoPuntuacion= aspectoPuntuacion;
	}
	
	public Puntuacion() {
		
	}

	public double getNota() {
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
	
	public Evento getEvento() {
		return evento;
	}
	
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
}
