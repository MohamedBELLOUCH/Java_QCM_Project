package ProjectQCM;

import java.io.Serializable; //Interface utilisé pour la serialization des objets pour les sauvegarder

public class Eleve implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String nom;
	public String prenom;
	public String login; //Identificateur unique à chaque eleve, Ca sera aussi utilisé pour le login
	public String password; //Mot de passe de l'eleve
	public int note;
	
	
	public Eleve(String nom, String prenom, String login, String password) {
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.password = password;
		note = 0;
	}


	public Eleve() {
		// TODO Auto-generated constructor stub
	};
	
}
