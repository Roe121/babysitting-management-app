package projet.ejb.data;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table( name = "compte" )
public class Compte  {
	
	//-------
	// Champs
	//-------

	@Id
	@GeneratedValue( strategy = IDENTITY)
	@Column( name = "idcompte")
	private int			id;
	
	@Column( name = "pseudo")
	private String		pseudo;
	
	@Column( name = "motdepasse")
	private String		motDePasse;
	
	@Column( name = "flagadmin" )
	private boolean		flagAdmin;
	
	//-------
	// Constructeurs
	//-------
	
	public Compte() {
	}

	public Compte(int id, String pseudo, String motDePasse,boolean flagAdmin) {
		this.id = id;
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.flagAdmin = flagAdmin;
		
	}
		
	//-------
	// Getters & setters
	//-------
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public boolean isFlagAdmin() {
		return flagAdmin;
	}

	public void setFlagAdmin(boolean flagAdmin) {
		this.flagAdmin = flagAdmin;
	}

	//-------
	// equals() et hashcode()
	//-------

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compte other = (Compte) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
