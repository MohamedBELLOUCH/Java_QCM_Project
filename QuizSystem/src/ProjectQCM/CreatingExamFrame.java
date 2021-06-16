package ProjectQCM;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CreatingExamFrame extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String matiere;
	public String titre;
	public String identifiant;
	public boolean mode_sequentiel;
	public int duree_totale; //Durée en minutes
	public int nombre_questions;
	public Question[] questions;
	public String[] logins_Eleves;
	String date;
	String heure;
	
	JButton createbutton = new JButton();
	JPanel accueil = new JPanel();
	
	String loginProfesseur;
	
	private class NbrField extends JPanel {
		
		JTextField valeur;
		JButton up;
		JButton down;
		
		int x;
		int y;
		
		int init; //initialization
		int min;
		int max;
		
		NbrField(int x, int y, int taille, int init, int min, int max) {
			
			this.init = init;
			this.min = min;
			this.max = max;
			
			this.setBounds(x, y, taille+2+(taille/2), taille);
			
			valeur = new JTextField();
			valeur.setBounds(0, 0, taille, taille);
			valeur.setText(""+init);
			up = new JButton();
			up.setBounds(taille + 1, 0, taille/2, (taille/2) - 1);
			up.setFocusable(false);
			down = new JButton();
			down.setBounds(taille + 1, taille/2, taille/2, (taille/2) - 1);
			up.setFocusable(false);
			
			this.setLayout(null);
			this.add(valeur);
			this.add(up);
			this.add(down);
			
		}
		
		private boolean compatible(String s) {
			for (int i = 0; i < s.length(); i++) {
				if ( s.charAt(i) != '0' && s.charAt(i) != '1' && s.charAt(i) != '2' && s.charAt(i) != '3'
					&& s.charAt(i) != '4'&& s.charAt(i) != '5' && s.charAt(i) != '6' && s.charAt(i) != '7' 
					&& s.charAt(i) != '8' && s.charAt(i) != '9') return false;
			}
			return true;
		}
		
		public void setActionNbrField(ActionEvent e) {
			
			int n;
			
			if ( !compatible(this.valeur.getText())) {
				this.valeur.setText(""+this.init);
				n = this.init;
			}
			
			n = Integer.parseInt(this.valeur.getText());
			if ( n > this.max-1 ) n = this.max-1;
			if ( n < this.min ) n = this.min;
			
			if ( e.getSource() == this.up) n++;
			if ( e.getSource() == this.down && n > this.min) n--;
			if ( n/10 == 0) this.valeur.setText("0"+n);
			else this.valeur.setText(""+n);
				
		}
		
		public void addActionListenerNbrField(ActionListener l) {
			this.up.addActionListener(l);
			this.down.addActionListener(l);
		}
		
	}
	
	private class Preliminaires extends JPanel {
		
		public Checkbox mode;
		public JTextField matiereField;
		public JTextField titreField;
		public JTextField identifiantField;
		
		public NbrField jour;
		public NbrField mois;
		public NbrField annee;
		public NbrField heure;
		public NbrField min;
		public NbrField nbrqstsField;
		public NbrField dureeField;

		public JLabel dureeLabel;
		public JLabel errIdentifiant;
		
		public JButton modeButton;
		public JButton suivant;
		public JButton annuler;
		
		
		Preliminaires () {
			
			JLabel description;
			JLabel matiereLabel;
			JLabel titreLabel;
			JLabel nbrqstsLabel;
			JLabel modeLabel;
			JLabel dateLabel;
			JLabel horloge;
			JLabel identifiantLabel;
			
			JPanel pan = new JPanel();
			
			
			pan.setBounds(0, 40, 600, 400);
			pan.setLayout(null);
			
			suivant = new JButton();
			annuler = new JButton();
			mode = new Checkbox();
			
			description = new JLabel("Création d'un examen : ");
			description.setFont(new Font("", Font.PLAIN, 25));
			description.setBounds(50, 30, 300, 50);
			
			matiereLabel = new JLabel("Matière : ");
			matiereLabel.setBounds(50, 50, 100, 30);
			matiereField = new JTextField();
			matiereField.setBounds(140, 50, 150, 30);
			
			titreLabel = new JLabel("Titre :");
			titreLabel.setBounds(320, 50, 100, 30);
			titreField = new JTextField();
			titreField.setBounds(380, 50, 150, 30);
			
			identifiantLabel = new JLabel("Identifiant : ");
			identifiantLabel.setBounds(50, 100, 200, 30);
			identifiantField = new JTextField();
			identifiantField.setBounds(140, 100, 150, 30);
			errIdentifiant = new JLabel("Identifaint déjà utilisé");
			errIdentifiant.setBounds(410, 260, 150, 30);
			errIdentifiant.setBackground(Color.red);
			
			dateLabel = new JLabel("Date : ");
			dateLabel.setBounds(50, 170, 80, 30);
			jour = new NbrField(120, 170, 30, 1, 1, 31);
			jour.valeur.setText("01");
			mois = new NbrField(170, 170, 30, 1, 1, 12);
			mois.valeur.setText("01");
			annee = new NbrField(220, 170, 30, 21, 21, 99);
			annee.valeur.setText("21");
			
			horloge = new JLabel("Heure : ");
			horloge.setBounds(320, 170, 80, 30);
			heure = new NbrField(380, 170, 30, 8, 0, 24);
			heure.valeur.setText("08");
			min = new NbrField(430, 170, 30, 0, 0, 59);
			min.valeur.setText("00");
			
			modeLabel = new JLabel("Mode : ");
			modeLabel.setBounds(50, 250, 100, 30);
			modeButton = new JButton("Mode Séquentiel");
			modeButton.setBounds(120, 250, 150, 30);
			modeButton.setFocusable(false);
			modeButton.setBackground(new Color(0xE5ECF4));
			
			dureeLabel = new JLabel("Durée en minutes : ");
			dureeLabel.setBounds(320, 250, 150, 30);
			dureeField = new NbrField(460, 250, 30, 60, 0, 3000);
			dureeField.valeur.setText("60");
			
			nbrqstsLabel = new JLabel("Nombre de questions : ");
			nbrqstsLabel.setBounds(50, 330, 210, 30);
			nbrqstsField = new NbrField(220, 330, 30, 3, 1, 100);
			nbrqstsField.valeur.setText("3");
			
			pan.add(matiereLabel);
			pan.add(matiereField);
			pan.add(titreLabel);
			pan.add(titreField);
			pan.add(dateLabel);
			pan.add(jour);
			pan.add(mois);
			pan.add(annee);
			pan.add(horloge);
			pan.add(heure);
			pan.add(min);
			pan.add(modeLabel);
			pan.add(dureeLabel);
			pan.add(dureeField);
			pan.add(nbrqstsLabel);
			pan.add(nbrqstsField);
			pan.add(identifiantLabel);
			pan.add(identifiantField);
			pan.add(modeButton);
			pan.add(errIdentifiant);
			
			pan.setBackground(Color.WHITE);
			for (Component component : pan.getComponents()) {
				component.setFont(new Font("Quicksand", Font.PLAIN, 15));
				component.setForeground(new Color(0x8A4FFF));
				if (component instanceof JTextField) {
					component.setBackground(new Color(0xE5ECF4));
					component.setForeground(Color.BLACK);
				}
				else if (component instanceof NbrField) {
					((NbrField) component).valeur.setBackground(new Color(0xEFFFFA));
					((NbrField) component).valeur.setForeground(Color.BLACK);
					((NbrField) component).valeur.setFont(new Font("Quicksand Medium", Font.PLAIN, 13));	
				}
			}
			description.setFont(new Font("Quicksand", Font.PLAIN, 23));
			description.setForeground(new Color(0x8A4FFF));
			
			
			this.add(description);
			this.add(pan);
			
			suivant.setText("Suivant");
			suivant.setBounds(400, 470, 130, 50);
			suivant.setFocusable(false);
			suivant.setBackground(new Color(0x4FFF5D));
			suivant.setFont(new Font("Quicksand", Font.PLAIN, 17));
			suivant.setForeground(Color.WHITE);

			
			annuler.setText("Annuler");
			annuler.setBounds(80, 470, 130, 50);
			annuler.setFocusable(false);
			annuler.setBackground(new Color(0xFF4F4F));
			annuler.setFont(new Font("Quicksand", Font.PLAIN, 17));
			annuler.setForeground(Color.WHITE);
			
			mode.setBounds(0, 0, 20, 20);
			
			this.setSize(600, 600);
			this.setLayout(null);
			
			this.add(suivant);
			this.add(annuler);
			
			this.setBackground(Color.WHITE);
			
		}
		
	}
	
	private class QuestionPanel extends JPanel{
		
		public int nbr_choix;
		public int reponse;
		public int ch; //choix courant dans le parcours
		public int numero;
		public String[] choix;
		
		public JTextArea enonceArea;
		public JTextField noteField;
		public JTextArea choixArea;
		
		public NbrField minutes;
		public NbrField secondes;
		
		public JLabel titreQuestion;
		public JLabel enonceLabel;
		public JLabel noteLabel;
		public JLabel dureeLabel;
		public JLabel numeroChoix;
		
		public JButton suivant;
		public JButton precedent;
		public JButton choixCorrect;
		public JButton choixSuivant;
		public JButton choixPrecedent;
		public JButton ajouterChoix;
		public JButton supprimerChoix;
		
		JPanel pan;
		QuestionPanel(int numero) {
			
			this.numero = numero;
			this.nbr_choix = 3;
			this.reponse = 0;
			this.choix = new String[6];
			
			for ( int i = 0; i<6; i++) {
				if ( i < nbr_choix ) this.choix[i] = "";
				else this.choix[i] = null;
			}
			
			suivant = new JButton();
			precedent = new JButton();
			
			titreQuestion = new JLabel("Question n° "+numero);
			titreQuestion.setBounds(50, 30, 300, 50);
			titreQuestion.setFont(new Font("", Font.PLAIN, 25));
			this.setLayout(null);
			this.add(titreQuestion);

			dureeLabel = new JLabel("Durée : ");
			dureeLabel.setBounds(50, 30, 100, 30);
			minutes = new NbrField(115, 30, 30, 1, 0, 300);
			JLabel minutesLabel = new JLabel("min");
			minutesLabel.setBounds(170, 30, 100, 30);
			secondes = new NbrField(205, 30, 30, 0, 0, 59);
			JLabel secondesLabel = new JLabel("sec");
			secondesLabel.setBounds(260, 30, 100, 30);
			
			noteLabel = new JLabel("Note : ");
			noteLabel.setBounds(340, 30, 100, 30);
			noteField = new JTextField();
			noteField.setBounds(395,  30, 50, 30);
			noteField.setText("0");
			noteField.setMargin(new Insets(0,7,0,0));
			
			enonceLabel = new JLabel("Enoncé :");
			enonceLabel.setBounds(35, 100, 100, 30);
			enonceArea = new JTextArea(4, 35);
			enonceArea.setBackground(new Color(0xEFFFFA));
			enonceArea.setMargin(new Insets(0,5,0,0));
			
			JPanel enoncePanel = new JPanel();
			enoncePanel.setLayout(new FlowLayout());
			enoncePanel.setBounds(100, 100, 420, 80);
			JScrollPane enonceScroll = new JScrollPane(enonceArea);
			enoncePanel.add(enonceScroll);
			enoncePanel.setBackground(Color.WHITE);
			
			choixPrecedent = new JButton("Précédent");
			choixPrecedent.setBounds(118, 200, 120, 30);
			choixSuivant = new JButton("Suivant");
			choixSuivant.setBounds(405, 200, 100, 30);
			numeroChoix = new JLabel("Choix n°");
			numeroChoix.setBounds(270, 200, 100, 30);
			
			supprimerChoix = new JButton("Supprimer");
			supprimerChoix.setBounds(405, 330, 110, 30);
			
			
			ajouterChoix = new JButton("Ajouter");
			ajouterChoix.setBounds(118, 330, 100, 30);
			
			
			choixCorrect = new JButton("Choix correct");
			choixCorrect.setBounds(240, 330, 142, 30);
			JLabel choixLabel1 = new JLabel("Enoncé");
			JLabel choixLabel = new JLabel("du choix :  ");
			choixLabel1.setBounds(35, 210, 100, 100);
			choixLabel.setBounds(35, 230, 100, 100);
			
			choixArea = new JTextArea(4, 35);
			choixArea.setBackground(new Color(0xEFFFFA));
			choixArea.setMargin(new Insets(0,5,0,0));
			JPanel choixPanel = new JPanel();
			choixPanel.setLayout(new FlowLayout());
			choixPanel.setBounds(100, 240, 420, 90);
			JScrollPane choixScroll = new JScrollPane(choixArea);
			choixPanel.add(choixScroll);
			choixPanel.setBackground(Color.WHITE);
			
			pan = new JPanel();
			pan.setBounds(0, 70, 600, 550);
			pan.setLayout(null);
			
			pan.add(dureeLabel);
			pan.add(minutes);
			pan.add(minutesLabel);
			pan.add(secondes);
			pan.add(secondesLabel);
			pan.add(noteLabel);
			pan.add(noteField);
			pan.add(enonceLabel);
			pan.add(enoncePanel);
			pan.add(choixPrecedent);
			pan.add(choixSuivant);
			pan.add(numeroChoix);
			pan.add(supprimerChoix);
			pan.add(ajouterChoix);
			pan.add(choixLabel);
			pan.add(choixLabel1);
			pan.add(choixPanel);
			pan.add(choixCorrect);
			
			pan.setBackground(Color.WHITE);
			for (Component component : pan.getComponents()) {
				
				if (component instanceof JTextField) {
					component.setBackground(new Color(0xE5ECF4));
					component.setForeground(Color.BLACK);
					component.setFont(new Font("Quicksand Medium", Font.PLAIN, 13));
				}
				else if (component instanceof NbrField) {
					((NbrField) component).valeur.setBackground(new Color(0xEFFFFA));
					((NbrField) component).valeur.setForeground(Color.BLACK);
					((NbrField) component).valeur.setFont(new Font("Quicksand Medium", Font.PLAIN, 13));	
				}
				else if (component instanceof JButton) {
					component.setForeground(Color.WHITE);
					component.setFocusable(false);
					component.setFont(new Font("Quicksand", Font.PLAIN, 14));
				}
				else {
					component.setFont(new Font("Quicksand", Font.PLAIN, 15));
					component.setForeground(new Color(0x8A4FFF));
				}
			}
			titreQuestion.setFont(new Font("Quicksand", Font.PLAIN, 23));
			titreQuestion.setForeground(new Color(0x8A4FFF));
			
			supprimerChoix.setBackground(new Color(0xFF4F4F));
			
			ajouterChoix.setBackground(new Color(0x3665ff));
			
			choixCorrect.setBackground(new Color(0x009e1a));
			
			choixPrecedent.setBackground(new Color(0xedcc37));
			
			choixSuivant.setBackground(new Color(0x4FFF5D));

			
			
			suivant.setText("Suivant");
			suivant.setBounds(390, 470, 150, 50);
			suivant.setFocusable(false);
			suivant.setBackground(new Color(0x4FFF5D));
			suivant.setForeground(Color.WHITE);
			suivant.setFont(new Font("Quicksand", Font.PLAIN, 18));
			
			precedent.setText("Précédent");
			precedent.setBounds(60, 470, 150, 50);
			precedent.setFocusable(false);
			precedent.setBackground(new Color(0xedcc37));
			precedent.setForeground(Color.WHITE);
			precedent.setFont(new Font("Quicksand", Font.PLAIN, 18));

			
			this.setSize(600, 600);
			this.setLayout(null);
			
			this.add(suivant);
			this.add(precedent);
			this.add(pan);
			
			this.setBackground(Color.WHITE);
			
			ch = 0;
		}
		
		public void addActionListenerQuestionPanel(ActionListener l) {
			
			this.minutes.addActionListenerNbrField(l);
			this.secondes.addActionListenerNbrField(l);
			this.choixSuivant.addActionListener(l);
			this.choixPrecedent.addActionListener(l);
			this.ajouterChoix.addActionListener(l);
			this.supprimerChoix.addActionListener(l);
			this.choixCorrect.addActionListener(l);
			this.suivant.addActionListener(l);
			this.precedent.addActionListener(l);
			
		}
		
		public void setActionQuestionPanel(ActionEvent e) {
			
			minutes.setActionNbrField(e);
			secondes.setActionNbrField(e);
			
			if ( ch == 0 ) {
				this.choixPrecedent.setEnabled(false);
				if ( nbr_choix == 1) this.choixSuivant.setEnabled(false);
				this.numeroChoix.setText("Choix "+(ch+1)+" sur "+this.nbr_choix);
				if ( e.getSource() == this.choixSuivant) {
					choix[ch] = this.choixArea.getText();
					this.choixArea.setText(choix[ch+1]);
					ch++;
					this.numeroChoix.setText("Choix "+2+" sur "+this.nbr_choix);
					this.choixPrecedent.setEnabled(true);
				}
			}
			
			else {

				if ( ch > 0 && ch < this.nbr_choix -1) {
					this.choixPrecedent.setEnabled(true);
					this.choixSuivant.setEnabled(true);
					this.numeroChoix.setText("Choix "+(ch+1)+" sur "+this.nbr_choix);
					if ( e.getSource() == this.choixSuivant) {
						choix[ch] = this.choixArea.getText();
						this.choixArea.setText(choix[ch+1]);
						ch ++;
						this.numeroChoix.setText("Choix "+(ch+1)+" sur "+this.nbr_choix);
					}
					if ( e.getSource() == this.choixPrecedent) {
						choix[ch] = this.choixArea.getText();
						this.choixArea.setText(choix[ch-1]);
						if ( ch == 1) this.choixPrecedent.setEnabled(false);
						ch --;
						this.numeroChoix.setText("Choix "+(ch+1)+" sur "+this.nbr_choix);
					}
				}
			}
			
			if ( ch == this.nbr_choix - 1) {
				
				this.choixSuivant.setEnabled(false);
				this.numeroChoix.setText("Choix "+(ch+1)+" sur "+this.nbr_choix);
				if ( e.getSource() == this.choixPrecedent) {
					choix[ch] = this.choixArea.getText();
					ch --;
					this.choixArea.setText(choix[ch]);
					this.numeroChoix.setText("Choix "+(ch+1)+" sur "+this.nbr_choix);
					this.choixSuivant.setEnabled(true);
				}
				
			}
			
			if ( e.getSource() == this.ajouterChoix ) {
				
				if ( this.nbr_choix < 6) {
					this.nbr_choix ++;
					this.choix[nbr_choix-1] = "";
					if (ch < nbr_choix -1 ) {
						this.choixSuivant.setEnabled(true);
						this.choixPrecedent.setEnabled(true);
					}
					if (nbr_choix == 6) this.ajouterChoix.setEnabled(false);
					this.supprimerChoix.setEnabled(true);
					this.numeroChoix.setText("Choix "+(ch+1)+" sur "+this.nbr_choix);
				}
				if ( ch == 0 ) this.choixPrecedent.setEnabled(false);
			}
			
			if ( e.getSource() == this.supprimerChoix ) {
				
				if ( this.nbr_choix > 1 ) {
					this.nbr_choix --;
					this.choix[nbr_choix-1] = null;
					if ( this.reponse == this.nbr_choix) this.reponse = this.nbr_choix-1;
					if ( ch == nbr_choix) {
						ch --;
					}
					if (nbr_choix == 1 ) {
						this.supprimerChoix.setEnabled(false);
						this.choixSuivant.setEnabled(false);
					}
					if ( this.nbr_choix == 1 ) this.choixPrecedent.setEnabled(false);
					this.numeroChoix.setText("Choix "+(ch+1)+" sur "+this.nbr_choix);
				}
				this.ajouterChoix.setEnabled(true);
			}
			if ( e.getSource() == this.choixCorrect) {
				if ( this.reponse != ch ) {
					this.reponse = ch;
				}
			}
			
			if ( this.reponse != ch ) this.choixCorrect.setBackground(Color.white);		
			if ( this.reponse == ch ) this.choixCorrect.setBackground(new Color(0x009e1a));
		}
		
	}
	
	private class ElevesPanel extends JPanel {
		
		public JTextField loginField;
		public JTextArea loginArea;
		
		public JButton ajouter;
		public JButton suivant;
		public JButton precedent;
		
		ElevesPanel () {
			
			JLabel description = new JLabel("Identifiants des Candidats : ");
			description.setBounds(50, 30, 400, 50);
			description.setFont(new Font("", Font.PLAIN, 25));
			description.setForeground(new Color(0x8A4FFF));
			description.setFont(new Font("Quicksand",Font.PLAIN,20));
			
			loginField = new JTextField();
			loginField.setBounds(80, 100, 260, 30);
			loginField.setBackground(new Color(0xEFFFFA));
			loginField.setMargin(new Insets(0,5,0,0));
			loginField.setFont(new Font("Quicksand Medium",Font.PLAIN,15));
			
			ajouter = new JButton();
			ajouter.setBounds(370, 100, 100, 30);
			ajouter.setText("Ajouter");
			ajouter.setFocusable(false);
			ajouter.setBackground(new Color(0x3665ff));
			ajouter.setForeground(Color.WHITE);
			ajouter.setFont(new Font("Quicksand",Font.PLAIN,15));
			
			loginArea = new JTextArea(18, 36);
			loginArea.setBackground(new Color(0xE5ECF4));
			loginArea.setMargin(new Insets(0,5,0,0));
			JScrollPane sc = new JScrollPane(loginArea);
			JPanel areaPane = new JPanel();
			areaPane.setBounds(80, 150, 400, 300);
			areaPane.setLayout(new FlowLayout());
			areaPane.add(sc);
			
			
			suivant = new JButton();
			suivant.setText("Suivant");
			suivant.setBounds(390, 470, 150, 50);
			suivant.setFocusable(false);
			suivant.setBackground(new Color(0x4FFF5D));
			suivant.setForeground(Color.WHITE);
			suivant.setFont(new Font("Quicksand",Font.PLAIN,17));
			
			precedent = new JButton();
			precedent.setText("Précédent");
			precedent.setBounds(60, 470, 150, 50);
			precedent.setFocusable(false);
			precedent.setBackground(new Color(0xedcc37));
			precedent.setForeground(Color.WHITE);
			precedent.setFont(new Font("Quicksand",Font.PLAIN,17));
			
			this.setSize(600, 600);
			this.setLayout(null);
			
			this.add(description);
			this.add(loginField);
			this.add(ajouter);
			this.add(areaPane);
			this.add(suivant);
			this.add(precedent);
			
			this.setBackground(Color.WHITE);
			
			
		}
		
	}
	
	static class Validation extends JPanel {
		
		JButton valider;
		JButton precedent;
		JLabel label = new JLabel();
		
		ImageIcon icon = new ImageIcon("C:\\Users\\User\\eclipse-workspace\\QuizSystem\\src\\img\\done-icon.png");
		Image image = icon.getImage();
		ImageIcon doneIcon = new ImageIcon(image.getScaledInstance(230, 230, Image.SCALE_SMOOTH));
		
		Validation() {
			
			valider = new JButton();
			valider.setText("Tout enregistrer et terminer");
			valider.setFocusable(false);
			valider.setBounds(250, 470, 270, 50);
			valider.setBackground(new Color(0x4FFF5D));
			valider.setFont(new Font("Quicksand",Font.PLAIN,17));
			valider.setForeground(Color.WHITE);
			
			
			precedent = new JButton();
			precedent.setText("Précédent");
			precedent.setBounds(60, 470, 150, 50);
			precedent.setFocusable(false);
			precedent.setBackground(new Color(0xedcc37));
			precedent.setFont(new Font("Quicksand",Font.PLAIN,17));
			precedent.setForeground(Color.WHITE);
			
			label.setIcon(doneIcon);
			label.setBounds(170, 100, 600, 300);
			
			this.setSize(600, 600);
			/*this.setLayout(new BorderLayout());
			
			this.add(label, BorderLayout.CENTER);
			this.add(valider, BorderLayout.SOUTH);
			this.add(precedent, BorderLayout.SOUTH);*/
			this.setLayout(null);
			
			this.add(label);
			this.add(valider);
			this.add(precedent);
			
			this.setBackground(Color.WHITE);
		}
		
	}

	Preliminaires preliminaires = new Preliminaires();
	QuestionPanel[] TabPanelsQuestions;
	ElevesPanel elevespanel = new ElevesPanel();
	Validation validation = new Validation();

	public int q;
	
	CreatingExamFrame(String loginProfesseur) {
		this.loginProfesseur = loginProfesseur;
		
		accueil = new JPanel();
		accueil.setLayout(null);
		accueil.setSize(600, 600);
		accueil.setBackground(Color.white);
		
		createbutton = new JButton();
		createbutton.setText("Créer un Examen");
		createbutton.setFocusable(false);
		createbutton.setBounds(200, 300, 200, 50);
		createbutton.addActionListener(this);
		createbutton.setBackground(new Color(0x8A4FFF));
		accueil.add(createbutton);
		
		preliminaires.modeButton.setText((mode_sequentiel) ? "Mode séquentiel": "Mode simple");
		
		this.setContentPane(accueil);
		this.setLayout(null);
		this.setTitle("Examen");
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		preliminaires.suivant.addActionListener(this);
		preliminaires.annuler.addActionListener(this);
		preliminaires.jour.addActionListenerNbrField(this);
		preliminaires.mois.addActionListenerNbrField(this);
		preliminaires.annee.addActionListenerNbrField(this);
		preliminaires.heure.addActionListenerNbrField(this);
		preliminaires.min.addActionListenerNbrField(this);
		preliminaires.nbrqstsField.addActionListenerNbrField(this);
		preliminaires.dureeField.addActionListenerNbrField(this);
		preliminaires.modeButton.addActionListener(this);
		
		elevespanel.suivant.addActionListener(this);
		elevespanel.precedent.addActionListener(this);
		elevespanel.ajouter.addActionListener(this);
		validation.valider.addActionListener(this);
		validation.precedent.addActionListener(this);
		
		TabPanelsQuestions = new QuestionPanel[1];
		TabPanelsQuestions[0] = new QuestionPanel(1);
		
	}

	public void actionPerformed(ActionEvent e) {
		
		preliminaires.jour.setActionNbrField(e);
		preliminaires.mois.setActionNbrField(e);
		preliminaires.annee.setActionNbrField(e);
		preliminaires.heure.setActionNbrField(e);
		preliminaires.min.setActionNbrField(e);
		preliminaires.nbrqstsField.setActionNbrField(e);
		preliminaires.dureeField.setActionNbrField(e);
		
		for ( int j = 0; j < TabPanelsQuestions.length; j++) TabPanelsQuestions[j].setActionQuestionPanel(e);
		
		//preliminaires
		
		if (e.getSource() == createbutton) {
			matiere = "";
			titre = "";
			identifiant = "";
			mode_sequentiel = true;
			duree_totale = 60;
			nombre_questions = 5;
			questions = null;
			logins_Eleves = null;
			date = "01/01/21";
			heure = "08:00";
			
			preliminaires.errIdentifiant.setVisible(false);
			TabPanelsQuestions = new QuestionPanel[nombre_questions];
			
			for (int j = 0; j < nombre_questions; j++ ) {
				TabPanelsQuestions[j] = new QuestionPanel(j+1);
				TabPanelsQuestions[j].addActionListenerQuestionPanel(this);
				TabPanelsQuestions[j].setActionQuestionPanel(e);
			}
			
			this.setContentPane(preliminaires);
		}
		
		if (e.getSource() == preliminaires.modeButton) {
			if (mode_sequentiel) {
				preliminaires.modeButton.setText("Mode séquentiel");
				mode_sequentiel = false;
				preliminaires.dureeField.down.setEnabled(false);
				preliminaires.dureeField.up.setEnabled(false);
				preliminaires.dureeField.valeur.setEditable(false);
				
			}
			else {
				preliminaires.modeButton.setText("Mode simple");
				mode_sequentiel = true;
				preliminaires.dureeField.down.setEnabled(true);
				preliminaires.dureeField.up.setEnabled(true);
				preliminaires.dureeField.valeur.setEditable(true);
			}
		}
		
		if (e.getSource() == preliminaires.annuler) this.setContentPane(accueil);
		
		if (e.getSource() == preliminaires.suivant) {
			
			matiere = preliminaires.matiereField.getText();
			titre = preliminaires.titreField.getText();
			duree_totale = Integer.parseInt(preliminaires.dureeField.valeur.getText());
			nombre_questions = Integer.parseInt(preliminaires.nbrqstsField.valeur.getText());
			
			date = preliminaires.jour.valeur.getText() + "/"
					+ preliminaires.mois.valeur.getText() +"/"
					+ preliminaires.annee.valeur.getText();
			
			heure = preliminaires.heure.valeur.getText() + ":" + preliminaires.min.valeur.getText();
			
			identifiant = preliminaires.identifiantField.getText();
			
			TabPanelsQuestions = new QuestionPanel[nombre_questions];
			questions = new Question[nombre_questions];
			
			for (int j = 0; j < nombre_questions; j++ ) {
				questions[j] = new Question("", null, 0, 0);
				TabPanelsQuestions[j] = new QuestionPanel(j+1);
				TabPanelsQuestions[j].addActionListenerQuestionPanel(this);
				TabPanelsQuestions[j].setActionQuestionPanel(e);
				if ( this.mode_sequentiel ) {
					TabPanelsQuestions[j].minutes.valeur.setEditable(false);
					TabPanelsQuestions[j].minutes.up.setEnabled(false);
					TabPanelsQuestions[j].minutes.down.setEnabled(false);
					TabPanelsQuestions[j].secondes.valeur.setEditable(false);
					TabPanelsQuestions[j].secondes.up.setEnabled(false);
					TabPanelsQuestions[j].secondes.down.setEnabled(false);
				}
			}
			
			/*
			 **
			 **
			
			try {
				
				if ( preliminaires.identifiantField.getText().equals("") ) {
					preliminaires.errIdentifiant.setText("Il faut saisir un identifaint");
					preliminaires.errIdentifiant.setVisible(true);
				}
				
				else {
					if ( Examen.Id_exists(identifiant) ) {
						preliminaires.errIdentifiant.setText("Cet identifiant est déjà utilisé");
						preliminaires.errIdentifiant.setVisible(true);
					}
					if ( !Examen.Id_exists(identifiant) ) {
						preliminaires.errIdentifiant.setVisible(false);
						this.setContentPane(TabPanelsQuestions[0]);
					}
				}
				
				
			} 
			
			catch ( Exception excpt) {
				excpt.printStackTrace();
			}
			
			**
			**
			 */
			
			this.setContentPane(TabPanelsQuestions[0]);
			
		}
		
		if ( e.getSource() == TabPanelsQuestions[0].suivant) {
			
				String[] choix = TabPanelsQuestions[0].choix;
				choix[TabPanelsQuestions[0].ch] = TabPanelsQuestions[0].choixArea.getText();
				for ( int i = 0; i < TabPanelsQuestions[0].nbr_choix; i++) choix[i] = TabPanelsQuestions[0].choix[i];
				int reponse = TabPanelsQuestions[0].reponse+1;
				int note = Integer.parseInt(TabPanelsQuestions[0].noteField.getText());
				questions[0].nbrChoix = TabPanelsQuestions[0].nbr_choix;
				questions[0] = new Question(TabPanelsQuestions[0].enonceArea.getText(), choix, reponse, note);
				questions[q].duree_question = Integer.parseInt(TabPanelsQuestions[q].minutes.valeur.getText())*60 +
						Integer.parseInt(TabPanelsQuestions[q].secondes.valeur.getText());
				
				if ( this.nombre_questions > 1) {
					q++;
					this.setContentPane(TabPanelsQuestions[q]);
				}
				
				else this.setContentPane(this.elevespanel);	
			}
			
		if ( e.getSource() == TabPanelsQuestions[0].precedent) {
			
			
			this.setContentPane(preliminaires);
			
		}
		
		if ( q > 0 && q < nombre_questions-1) {
			
			if ( e.getSource() == TabPanelsQuestions[q].suivant) {
				String[] choix = new String[TabPanelsQuestions[q].nbr_choix];
				questions[q].nbrChoix = TabPanelsQuestions[q].nbr_choix;
				for ( int i = 0; i < TabPanelsQuestions[q].nbr_choix; i++) choix[i] = TabPanelsQuestions[q].choix[i];
				int reponse = TabPanelsQuestions[q].reponse+1;
				int note = Integer.parseInt(TabPanelsQuestions[q].noteField.getText());
				questions[q] = new Question(TabPanelsQuestions[q].enonceArea.getText(), choix, reponse, note);
				questions[q].duree_question = Integer.parseInt(TabPanelsQuestions[q].minutes.valeur.getText())*60 +
									Integer.parseInt(TabPanelsQuestions[q].secondes.valeur.getText());
				q++;
				this.setContentPane(TabPanelsQuestions[q]);
			}
			
			if ( e.getSource() == TabPanelsQuestions[q].precedent) {
				q--;
				this.setContentPane(TabPanelsQuestions[q]);
			}
		}
		
		if ( q == nombre_questions-1 ) {
			
			if ( e.getSource() == TabPanelsQuestions[q].suivant) {
				String[] choix = new String[TabPanelsQuestions[q].nbr_choix];
				questions[q].nbrChoix = TabPanelsQuestions[q].nbr_choix;
				for ( int i = 0; i < TabPanelsQuestions[q].nbr_choix; i++) choix[i] = TabPanelsQuestions[q].choix[i];
				int reponse = TabPanelsQuestions[q].reponse+1;
				int note = Integer.parseInt(TabPanelsQuestions[q].noteField.getText());
				questions[q] = new Question(TabPanelsQuestions[q].enonceArea.getText(), choix, reponse, note);
				questions[q].duree_question = Integer.parseInt(TabPanelsQuestions[q].minutes.valeur.getText())*60 +
						Integer.parseInt(TabPanelsQuestions[q].secondes.valeur.getText());
				this.setContentPane(elevespanel);
			}
			if ( e.getSource() == TabPanelsQuestions[q].precedent) {
				if ( this.nombre_questions == 1 ) this.setContentPane(preliminaires);
				else {
					q--;
					this.setContentPane(TabPanelsQuestions[q]);
				}
			}
		}
		
		if ( e.getSource() == elevespanel.ajouter) {
			
			String s = elevespanel.loginArea.getText();
			elevespanel.loginArea.setText(s+elevespanel.loginField.getText()+"\n");
			elevespanel.loginField.setText("");
		}
		
		if ( e.getSource() == elevespanel.suivant ) {
			String logins = elevespanel.loginArea.getText();
			logins_Eleves = logins.split("\n");
			this.setContentPane(validation);
		}
		
		if ( e.getSource() == elevespanel.precedent ) {
			this.setContentPane(TabPanelsQuestions[q]);
		}
		
		if ( e.getSource() == validation.precedent) this.setContentPane(elevespanel);

		if ( e.getSource() == validation.valider) {
			
			this.dispose();
			
			
			String login_professeur = this.loginProfesseur;
			
			try {
				Examen exam = new Examen(matiere, login_professeur, titre, identifiant,
						mode_sequentiel, duree_totale, nombre_questions, questions, logins_Eleves, date, heure);
				for (String elvLogin : logins_Eleves) {
					Eleve.noter(elvLogin, identifiant, (float) -1);
				}
						
			} 
			
			catch (ClassNotFoundException e1) {
				System.out.println("Erreur");
			}
			
			
		}
	}

}
