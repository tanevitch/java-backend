package ttps.spring.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reserva")
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable=false)
	private String email;
	@Column(nullable=false)
	private String telefono;
	@Column(nullable=false)
	private String detalle;
	@Column(nullable=false)
	private LocalDate fechaHora;
	
	@ManyToOne
    @JoinColumn(nullable=false)
	private Usuario usuario;
	
	@ManyToOne
    @JoinColumn(nullable=false)
	private Servicio servicio;
	
	@OneToOne
	@JoinColumn(nullable=false)
	private Evento evento;
	
	@OneToOne
	@JoinColumn(nullable=false)
	private FormaDePago formaDePago;
	
	@OneToOne
	@JoinColumn(nullable=false)
	private CambioDeEstado cambioDeEstado;
	
	
	public Reserva() {
		
	}
	
	public Reserva(String email, String telefono, String detalle, LocalDate fechaHora, Usuario usuario, Evento evento,
			FormaDePago formaDePago, CambioDeEstado cambioDeEstado) {
		this.email = email;
		this.telefono = telefono;
		this.detalle = detalle;
		this.fechaHora = fechaHora;
		this.usuario = usuario;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public LocalDate getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(LocalDate fechaHora) {
		this.fechaHora = fechaHora;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public FormaDePago getFormaDePago() {
		return formaDePago;
	}
	public void setFormaDePago(FormaDePago formaDePago) {
		this.formaDePago = formaDePago;
	}
	public CambioDeEstado getCambioDeEstado() {
		return cambioDeEstado;
	}
	public void setCambioDeEstado(CambioDeEstado cambioDeEstado) {
		this.cambioDeEstado = cambioDeEstado;
	}
	public long getId() {
		return id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	
	
}
