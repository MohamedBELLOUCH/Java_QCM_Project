package ProjectQCM;

public class Question {
	public String enonce; //Enoncé de la question
	public String[] choix;
	public int reponse; //Numero de la reponse correcte
	public int note_question;
	
	public Question(String enonce, String[] choix, int reponse, int note) {
		this.enonce = enonce;
		this.choix = choix;
		this.reponse = reponse;
		this.note_question = note;
	}

}