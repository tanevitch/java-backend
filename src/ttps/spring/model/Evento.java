package ttps.spring.model;

import java.util.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "evento")

public class Evento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable=false)
	private String nombre;
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date fechaHora;
	@Column(nullable=false)
	private String direccion;
	@Column(nullable=false)
	private String cp;
	@Column(nullable=false)
	private String provincia;
	@Column(nullable=false)
	private String geolocalizacion;
	@Column(columnDefinition = "boolean default false", nullable=false)
	private boolean borrado;
	
	@OneToOne
	private TipoEvento tipoEvento;
	
	@ManyToOne
    @JoinColumn(nullable=false)
	private Usuario usuario;
	
	public Evento() {
		
	}
	
	public Evento(String nombre, Date fechaHora, String direccion, String cp, String provincia,
			String geolocalizacion, Usuario usuario) {
		this.nombre = nombre;
		this.fechaHora = fechaHora;
		this.direccion = direccion;
		this.cp = cp;
		this.provincia = provincia;
		this.geolocalizacion = geolocalizacion;
		this.usuario = usuario;
		this.borrado = false;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getGeolocalizacion() {
		return geolocalizacion;
	}
	public void setGeolocalizacion(String geolocalizacion) {
		this.geolocalizacion = geolocalizacion;
	}
	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}
	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	public Usuario getUsuario() {
		return usuario;
	}	
	
	public void setUsuario(Usuario usuario) {
		this.usuario= usuario;
	}	
	
	public long getId() {
		return id;
	}

	public boolean hasEmptyFields() {
		return nombre.equals("")
				|| fechaHora == null
				|| direccion.equals("")
				|| cp.equals("")
				|| provincia.equals("")
				|| usuario == null;
	}

	
	public boolean hasInvalidFields() {
		return nombre.length() > 255
				|| direccion.length() > 255
				|| cp.length() > 255
				|| provincia.length() > 255
				|| fechaHora.before(new Date());
	}
	
	public void setBorrado(boolean estado) {
		borrado = estado;		
	}
	
	public boolean getBorrado() {
		return borrado;
	}

	public boolean perteneceA(Usuario user) {
		return user.getId() == usuario.getId();
	}
	
	
	
}
