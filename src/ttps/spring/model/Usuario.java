package ttps.spring.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonView;

 
@Entity
@Table(name = "user")

public class Usuario  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable=false)
	private String nombre;
	@Column(nullable=false)
	private String apellido; 
	@Column(nullable=false, unique=true)
	private String mail;
	@Column(nullable=false)
	private String contrasena;
	//eliminado boolean
	@Column(columnDefinition = "boolean default false", nullable=false)
	private boolean borrado;
	
	
	@OneToMany(mappedBy="usuario",fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true) 
	@JsonManagedReference 
	private List<Servicio> servicios;
	
	@OneToMany(mappedBy="usuario", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
	@JsonIgnore
	private List<Evento> eventos;
	
	@OneToMany(mappedBy="usuario")
	@JsonIgnore
	private List<Puntuacion> puntuaciones;
	
	public Usuario(String nombre, String apellido, String mail, String contrasena) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.contrasena = contrasena;
		this.servicios=  new ArrayList<Servicio>();
		this.eventos=  new ArrayList<Evento>();
		this.borrado= false;
	}
	
	public Usuario() {
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public long getId() {
		return id;
	}
	
	public boolean getBorrado() {
		return borrado;
	}
	
	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}
	
	public void agregarServicio(Servicio servicio) {
		servicios.add(servicio);
	}
	
	public void eliminarServicio(Servicio servicio) {
		Servicio s = servicios.stream().filter(ser -> ser.getId() == servicio.getId()).findFirst().orElse(null);
		if (s!=null)
			servicios.remove(s);
	}

	public List<Evento> getEventos() {
		return eventos;
	}
	
	
	public void agregarEvento(Evento evento) {
		eventos.add(evento);
	}
	
	public void eliminarEvento(Evento evento) {
		eventos.remove(evento);
	}
	 
	public String toString() { // se agregó para poder imprimirlos en el main más fácil
		return "Nombre: "+nombre+ ", Apellido: "+apellido+", Email: "+mail+" || ID: "+id;
	}

	public boolean hasEmptyFields() {
		return nombre.equals("")
			    || apellido.equals("") 
				|| mail.equals("") 
				|| contrasena.equals("");
	}
	
	public boolean hasInvalidFields() {
		return nombre.length() > 255
			   || apellido.length() > 255
			   || mail.length() > 255
			   || contrasena.length() > 255;
	}
	
}
