package ttps.spring.model;

import java.time.LocalDateTime;

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
@Table(name = "cambioDeEstado")
public class CambioDeEstado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable=false)
	private String comentario;
	@Column(nullable=false)
	private LocalDateTime fechaHora;
	
	@OneToOne
	@JoinColumn(nullable=false)
	private Estado estado;
	
	public CambioDeEstado() {
		
	}
	
	public CambioDeEstado(String comentario, LocalDateTime fechaHora, Estado estado) {
		this.comentario = comentario;
		this.fechaHora = fechaHora;
		this.estado = estado;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public long getId() {
		return id;
	}
	
	
	
}
