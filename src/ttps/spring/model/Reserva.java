package ttps.spring.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@Column(nullable=true)
	private String detalle;
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
	private Date fechaHora;
	
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
	private Estado estado;
	
	
	public Reserva() {
		this.estado = new Estado(Estado.SINCONFIRMAR);
	}
	
	public Reserva(String email, String telefono, String detalle, Date fechaHora, Usuario usuario, Evento evento,
			FormaDePago formaDePago) {
		this.email = email;
		this.telefono = telefono;
		this.detalle = detalle;
		this.fechaHora = fechaHora;
		this.usuario = usuario;
		this.estado = new Estado(Estado.SINCONFIRMAR);
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
	public Date getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Date fechaHora) {
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
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public long getId() {
		return id;
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public boolean hasEmptyFields() {
		return email.equals("")
				|| telefono.equals("")
				|| servicio == null
				|| evento == null
				|| usuario == null;
	}
	
	public boolean hasInvalidFields() {
		
		return email.length() > 255
			|| telefono.length() > 255;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	
}
