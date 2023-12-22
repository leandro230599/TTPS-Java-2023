package ttps.spring.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="Usuarios")
public class Usuarios {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="usuario_id")
	private Long id;
	
	private String username;
	private String first_name;
	private String last_name;
	private String email;
	
	@JsonIgnore
	private String password;
	
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name = "Amigos",
        joinColumns = @JoinColumn(name = "usuario_id",
        						  referencedColumnName="usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "amigo_id",
        								 referencedColumnName="usuario_id")
    )
    @JsonIgnore
	private List<Usuarios> amigos;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name = "Compartio_grupo",
        joinColumns = @JoinColumn(name = "usuarioUno_id",
        		                  referencedColumnName="usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "usuarioDos_id",
        								 referencedColumnName="usuario_id")
    )
    @JsonIgnore
	private List<Usuarios> personasQueCompartiGrupo;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name = "Solicitudes_amigo",
        joinColumns = @JoinColumn(name = "usuario_id",
        						  referencedColumnName="usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "solicitud_user_id",
        								 referencedColumnName="usuario_id")
    )
    @JsonIgnore
	private List<Usuarios> solicitudesAmistad;
    
    
    @OneToMany(mappedBy="gastoPersona", fetch=FetchType.EAGER)
    @JsonIgnore
	private List<Gasto> gastoPersonas;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
    	name="Usuario_Grupo",
    	joinColumns=@JoinColumn(name="usuario_id",
    							referencedColumnName="usuario_id"),
    	inverseJoinColumns=@JoinColumn(name="grupo_id",
    								   referencedColumnName="grupo_id"))
    @JsonIgnore
	private List<Grupos> grupos;
    
    @OneToMany(mappedBy="deudaPersona", fetch=FetchType.EAGER)
    @JsonIgnore
	private List<Deuda> deudas;
    
    @OneToMany(mappedBy="pagoPersona", fetch=FetchType.EAGER)
    @JsonIgnore
	private List<Pago> pagos;
    
    
    
    
    
    
	
	public Usuarios () {
		this.amigos = new ArrayList<Usuarios>();
		this.personasQueCompartiGrupo = new ArrayList<Usuarios>();
		this.solicitudesAmistad = new ArrayList<Usuarios>();
		this.gastoPersonas = new ArrayList<Gasto>();
		this.grupos = new ArrayList<Grupos>();
		this.deudas = new ArrayList<Deuda>();
		this.pagos = new ArrayList<Pago>();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Usuarios> getAmigos() {
		return amigos;
	}
	public void setAmigos(List<Usuarios> amigos) {
		this.amigos = amigos;
	}
	public List<Usuarios> getPersonasQueCompartiGrupo() {
		return personasQueCompartiGrupo;
	}
	public void setPersonasQueCompartiGrupo(List<Usuarios> personas) {
		this.personasQueCompartiGrupo = personas;
	}
	public List<Usuarios> getSolicitudesAmistad() {
		return solicitudesAmistad;
	}
	public void setSolicitudesAmistad(List<Usuarios> solicitudes) {
		this.solicitudesAmistad = solicitudes;
	}
	public List<Gasto> getGastoPersonas() {
		return gastoPersonas;
	}
	public void setGastoPersonas(List<Gasto> gastos) {
		this.gastoPersonas = gastos;
	}
	public List<Grupos> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<Grupos> grupos) {
		this.grupos = grupos;
	}
	public List<Deuda> getDeudas() {
		return deudas;
	}
	public void setDeudas(List<Deuda> deudas) {
		this.deudas = deudas;
	}
	public List<Pago> getPagos() {
		return pagos;
	}
	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}
	public boolean agregarAmigo(Usuarios user) {
		if (!this.amigos.contains(user)) {
			this.amigos.add(user);
			user.getAmigos().add(this);
			return true;
		}
		return false;
	}
	public boolean eliminarAmigo(Usuarios user) {
		if (this.amigos.contains(user)) {
			this.amigos.remove(user);
			user.getAmigos().remove(this);
			return true;
		}
		return false;
	}
	
	
	public Usuarios crearUsuario(String username, String first_name, String last_name, String email, String password) {
		this.username = username;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
		return this;
	}
	
	public Grupos crearGrupo(String nombre, Categoria categoria) {
		Grupos grupo = new Grupos();
		grupo = grupo.crearGrupo(nombre, categoria);
		this.getGrupos().add(grupo);
		grupo.getMiembros().add(this);
		return grupo;		
	}
	
	public boolean agregarAGrupo(Grupos grupo) {
		if (!this.getGrupos().contains(grupo)){
			grupo.getMiembros().add(this);
			this.getGrupos().add(grupo);
			return true;
		}
		return false;
	}
	
	public boolean salirseDeGrupo(Grupos grupo) {
		if (this.getGrupos().contains(grupo)) {
			grupo.getMiembros().remove(this);
			this.grupos.remove(grupo);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean equals(Object user){
		Usuarios aux = (Usuarios) user;
	    if(this.id.equals(aux.getId())){
	        return true;
	    }
	    else{
	        return false;
	    }
	}
	
	public void crearPago(Pago pago) {}

}
