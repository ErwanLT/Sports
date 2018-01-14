package athlete;

public class Athlete {
	
	private String nom;

	public Athlete() {
		this.nom = "";
	}
	
	public Athlete(String nom){
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String toString(){
		return "Le nom du sportif est : "+getNom();
	}

	 
}
