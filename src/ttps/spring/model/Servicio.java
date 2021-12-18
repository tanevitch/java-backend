package ttps.spring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "servicio")
public class Servicio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable=false)
	private String nombre;
	@Column(nullable=false)
	private String descripcion;
	@Column(nullable=true)
	private String url;
	@Column(nullable=false)
	private String whatsapp;
	@Column(nullable=true)
	private String instagram;
	@Column(nullable=true)
	private String twitter;
	@Column(columnDefinition = "boolean default false", nullable=false)
	private boolean borrado;
	@Column(nullable=false)
	private String foto;
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

	@OneToMany(mappedBy="servicio")
	@JsonIgnore
	private List<Reserva> reservas;
	

	@OneToMany
    @JoinColumn(nullable=false)
	@JsonIgnore
	private List<Puntuacion> puntuaciones;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable=false)
	@JsonBackReference
	private Usuario usuario;
	
	@OneToOne
	@JoinColumn(nullable=false)
	private TipoServicio tipoServicio;
	
	
	public Servicio(String nombre, String descripcion, String url, String whatsapp, String instagram, String twitter) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.url = url;
		this.whatsapp = whatsapp;
		this.instagram = instagram;
		this.twitter = twitter;
		this.borrado = false;
	}
	
	public Servicio() {
		
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getWhatsapp() {
		return whatsapp;
	}


	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}


	public String getInstagram() {
		return instagram;
	}


	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}


	public String getTwitter() {
		return twitter;
	}


	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}


	public List<Puntuacion> getPuntuaciones() {
		return puntuaciones;
	}


	public void agregarPuntuacion(Puntuacion puntuacion) {
		puntuaciones.add(puntuacion);
	}
	

	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}


	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}


	public long getId() {
		return id;
	}


	public List<Reserva> getReservas() {
		return reservas;
	}
	
	public void agregarReserva(Reserva reserva) {
		reservas.add(reserva);
	}
	
	public void eliminarReserva(Reserva reserva) {
		reservas.remove(reserva);
	}

	public boolean hasEmptyFields() {
		return nombre.equals("") 
				|| descripcion.equals("") 
				|| whatsapp.equals("")  
				|| foto.equals("");
	}
	
	
	public String toString() { // se agregó para poder imprimirlos en el main más fácil
		return "Nombre: "+nombre+ ", Desc: "+descripcion+", wsp " + whatsapp + " url" + url + " tuirer"+twitter+ " ig "+ instagram;
	}
	
}
