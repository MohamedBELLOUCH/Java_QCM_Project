package ProjectQCM;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;

public class TeacherProfilePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Professeur prof;
	

	
	JPanel all = new JPanel();
	JPanel container = new JPanel();
	
	JPanel info = new JPanel();
	JLabel nomLabel = new JLabel();
	JLabel prenomLabel = new JLabel();
	JLabel usernameLabel = new JLabel();
	
	JPanel iconPanel = new JPanel();
	JLabel iconLabel = new JLabel();
	
			
	String[] header = {"Matiere", "Titre", "Date"};

	
	
	GridBagConstraints c = new GridBagConstraints();
	
	
	JScrollPane scrollPane;
	
	
	Object[][] donnees;
	
	
	
	ImageIcon icon = new ImageIcon("C:\\Users\\User\\eclipse-workspace\\QuizSystem\\src\\img\\chalkboard-teacher-solid.png");
	Image image1 = icon.getImage();
	ImageIcon user_icon = new ImageIcon(image1.getScaledInstance(110, 90, Image.SCALE_SMOOTH));
	
	static JButton button = new JButton("Créer un nouveau QCM");
	
	
	TeacherProfilePanel(Professeur prof) throws ClassNotFoundException{
		this.prof = prof;
		
		donnees = new Object[this.prof.listeExamens().toArray().length][3];
				
		int i = 0;
		for (Examen examen : this.prof.listeExamens()) {
				Object[] row = {examen.matiere, examen.titre, examen.date+" "+examen.heure};
				donnees[i] = row;
				i++;
		}
			
		
		c.gridx = 0;
		c.gridy = 0;
		
		nomLabel.setForeground(new Color(0x8A4FFF));
		nomLabel.setFont(new Font("Quicksand",Font.BOLD,20));
		nomLabel.setText("Nom : " + prof.nom);
		
		prenomLabel.setForeground(new Color(0x8A4FFF));
		prenomLabel.setFont(new Font("Quicksand",Font.BOLD,20));
		prenomLabel.setText("Préom : " + prof.prenom);
		
		usernameLabel.setForeground(new Color(0x8A4FFF));
		usernameLabel.setFont(new Font("Quicksand",Font.BOLD,20));
		usernameLabel.setText("Username : " + prof.login);
		
		info.setBackground(Color.WHITE);
		info.setLayout(new GridLayout(3,1));
		info.add(nomLabel);
		info.add(prenomLabel);
		info.add(usernameLabel);
		
		container.setLayout(new GridBagLayout());
		container.add(info, c);
		
		
		c.gridx = 1;
		c.insets = new Insets(0,300,0,0);
		iconLabel.setIcon(user_icon);
		iconPanel.add(iconLabel);
		iconPanel.setBackground(Color.WHITE);
		container.add(iconPanel, c);
		
		
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		c.insets = new Insets(50,0,0,0);
		
		button.setFocusable(false);
		button.setBackground(new Color(0x8A4FFF));
		button.setFont(new Font("Quicksand",Font.PLAIN,18));
		button.setForeground(Color.WHITE);
		button.setPreferredSize(new Dimension(90,50));
		

		
		createExamsPrecedentsTable();
		
		
		
		
		
		
		container.add(scrollPane, c);
		
		c.gridy = 3;
		c.insets = new Insets(10,0,0,0);
		container.add(button,c);
		
		container.setBackground(Color.WHITE);
		
		this.setBackground(Color.WHITE);
		this.add(container);			
	}
	
	
	
	public JTable createExamsPrecedentsTable() throws ClassNotFoundException {
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		c.ipadx = 150;
		c.insets = new Insets(15,0,0,0);
		
		JTable table = new JTable(donnees,header);
		
		scrollPane = new JScrollPane(table);

		
		
		
		table.getTableHeader().setBackground(new Color(0xE5ECF4));
		table.getTableHeader().setFont(new Font("Quicksand",Font.PLAIN,17));
		table.getTableHeader().setForeground(new Color(0x8A4FFF));
		table.setFont(new Font("Quicksand",Font.PLAIN,17));
		table.getTableHeader().setPreferredSize(new Dimension(600,60));
		table.setRowHeight(60);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);

		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); 
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.setBackground(new Color(0xEFFFFA));
       
		table.setEnabled(false);
		
		scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
		    @Override
		    protected void configureScrollBarColors() {
		        this.thumbColor = new Color(0x8A4FFF);
		    }
		});		
		return table;
	}
		
	
}
