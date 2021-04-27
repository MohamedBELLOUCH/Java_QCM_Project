package ProjectQCM;

import java.io.File;
import java.util.Scanner;

public class Examen {
	public String matiere;
	public String professeur;
	public boolean mode_sequentiel;
	public boolean mode_duree_totale;
	public int duree_totale;
	private final String mot_de_passe = "P@ssWord";
	static public int nombre_questions;
	public Question[] questions;
	public Eleve[] utilisateurs;
	
	//Constructors
	
	public Examen(String matiere, String professeur, File qsts, int duree){
		this.matiere = matiere;
		this.professeur = professeur;
		mode_sequentiel = false;
		mode_duree_totale = false;
		//remplire le tableau Questions d'après le fichier
	}
	
	public Examen(String matiere, String professeur, Question[] qsts) {
		this.matiere = matiere;
		this.professeur = professeur;
		questions = qsts;
	}
	
	//methods
	
	void passer_exam(Eleve elv) {
		System.out.println("\nExamen de "+this.matiere);
		System.out.println("Professeur : "+this.professeur+"\n");
		elv.note = 0;
		Scanner sc = new Scanner(System.in);
		String m = "";
		do {
			System.out.println("Mot de passe : ");
			m = sc.next();
		}
		while ( !m.equals(mot_de_passe) );
		int i = 1;
		int reponse;
		for (Question q : questions) {
			System.out.println("Question n°"+i+" :   ("+q.note_question+" points )");
			System.out.println(q.enonce);
			int j = 1;
			for (String ch : q.choix) {
				System.out.println("\t"+j+" ) "+ch);
				j++;
			}
			System.out.println("Tapez votre reponse : ");
			reponse = sc.nextInt();
			if (reponse == q.reponse) {
				System.out.println("Correct");
				elv.note += q.note_question;
			}
			else {
				System.out.println("Incorrect\nLa réponse correcte est : "+q.choix[q.reponse]);
			}
			i++;
		}
		
	}
	
}
