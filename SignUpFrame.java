package ProjectQCM;

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
import java.awt.event.FocusEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class SignUpFrame implements ActionListener{
	
	JFrame frame = new JFrame();
	JPanel panel_left = new JPanel();
	JPanel panel_right = new JPanel();
	JLabel label_left = new JLabel();
	JLabel title_label = new JLabel("Sign Up", SwingConstants.CENTER);
	JLabel footer = new JLabel("<HTML>Vous avez déja un compte ? <U>Login</U></HTML>", SwingConstants.CENTER);
	JLabel message = new JLabel();
	
	
	JButton login = new JButton("Sign Up");
	
	IconTextField nom = new IconTextField();
	IconTextField prenom = new IconTextField();
	IconTextField username = new IconTextField();
	IconPasswordField password = new IconPasswordField();
	
	
	
	//Créer les objets ImageIcon representant les icones
	ImageIcon icon = new ImageIcon("C:\\Users\\User\\eclipse-workspace\\QuizSystem\\src\\img\\student.png");
	Image image1 = icon.getImage();
	ImageIcon user_icon = new ImageIcon(image1.getScaledInstance(230, 230, Image.SCALE_SMOOTH));
	
	ImageIcon id = new ImageIcon("C:\\Users\\User\\eclipse-workspace\\QuizSystem\\src\\img\\id.png");
	Image image2 = id.getImage();
	ImageIcon id_icon = new ImageIcon(image2.getScaledInstance(35, 35, Image.SCALE_SMOOTH));
	
	ImageIcon padlock = new ImageIcon("C:\\Users\\User\\eclipse-workspace\\QuizSystem\\src\\img\\padlock.png");
	Image image3 = padlock.getImage();
	ImageIcon padlock_icon = new ImageIcon(image3.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
	
	ImageIcon check = new ImageIcon("C:\\Users\\User\\eclipse-workspace\\QuizSystem\\src\\img\\check.png");
	Image image4 = check.getImage();
	ImageIcon check_icon = new ImageIcon(image4.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		
	ImageIcon wrong = new ImageIcon("C:\\Users\\User\\eclipse-workspace\\QuizSystem\\src\\img\\close.png");
	Image image5 = wrong.getImage();
	ImageIcon wrong_icon = new ImageIcon(image5.getScaledInstance(20, 20, Image.SCALE_SMOOTH));	// transform it back
	
	
	
	GridBagConstraints c = new GridBagConstraints();
	
	
	SignUpFrame(){
		
		Border border = title_label.getBorder();
		title_label.setBorder(new CompoundBorder(border, new EmptyBorder(60,0,0,0)));
		
		
		panel_left.setBackground(new Color(0x8A4FFF));
		panel_right.setBackground(Color.WHITE);
		
		label_left.setIcon(user_icon);
		
		c.fill = GridBagConstraints.VERTICAL;
		c.ipady = 40;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		panel_left.setLayout(new GridBagLayout());
		panel_left.add(label_left,c);
		
		panel_right.setLayout(new GridBagLayout());
		panel_right.setFocusable(true);
		//Title
		c.ipady = 15;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;
		title_label.setForeground(new Color(0x8A4FFF));
		title_label.setFont(new Font("Quicksand", Font.PLAIN, 32));
		title_label.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(0x8A4FFF)));
		panel_right.add(title_label,c);
		

		//Nom
		nom.setPreferredSize(new Dimension(300,50));
		nom.setForeground(Color.GRAY);
		nom.setFont(new Font("Quicksand Medium", Font.PLAIN, 20));
		nom.setBackground(new Color(0xE5ECF4));
		nom.setText("Nom");
		nom.setMargin(new Insets(12,6,10,10));
		nom.setIcon(id_icon);
		nom.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (nom.getText().trim().equals("Nom"))
					nom.setText("");
					nom.setForeground(Color.BLACK);
					}
			public void focusLost(FocusEvent e) {
				if (nom.getText().trim().equals("")) {
					nom.setText("Nom");
					nom.setForeground(Color.GRAY);
					}	
			}
		});
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(30, 5, 5, 5);
				
		panel_right.add(nom, c);
		
		//Prenom
		prenom.setPreferredSize(new Dimension(300,50));
		prenom.setForeground(Color.GRAY);
		prenom.setFont(new Font("Quicksand Medium", Font.PLAIN, 20));
		prenom.setBackground(new Color(0xE5ECF4));
		prenom.setText("Prénom");
		prenom.setMargin(new Insets(12,6,10,10));
		prenom.setIcon(id_icon);
		prenom.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (prenom.getText().trim().equals("Prénom"))
					prenom.setText("");
				prenom.setForeground(Color.BLACK);
			}
			public void focusLost(FocusEvent e) {
				if (prenom.getText().trim().equals("")) {
					prenom.setText("Prénom");
					prenom.setForeground(Color.GRAY);
				}	
			}
		});
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(30, 5, 5, 5);

		panel_right.add(prenom, c);
		
		//Username
		username.setPreferredSize(new Dimension(300,50));
		username.setForeground(Color.GRAY);
		username.setFont(new Font("Quicksand Medium", Font.PLAIN, 20));
		username.setBackground(new Color(0xE5ECF4));
		username.setText("Username");
		username.setMargin(new Insets(12,6,10,10));
		username.setIcon(id_icon);
		username.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (username.getText().trim().equals("Username"))
					username.setText("");
				username.setForeground(Color.BLACK);
			}
			public void focusLost(FocusEvent e) {
				if (username.getText().trim().equals("")) {
					username.setText("Username");
					username.setForeground(Color.GRAY);
				}	
			}
		});
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(30, 5, 5, 5);

		panel_right.add(username, c);
		
	
		
		//Password
		password.setPreferredSize(new Dimension(300,50));
		password.setForeground(Color.GRAY);
		password.setFont(new Font("Quicksand Medium", Font.PLAIN, 25));
		password.setBackground(new Color(0xE5ECF4));
		password.setText("password");
		password.setMargin(new Insets(12,6,10,10));
		password.setIcon(padlock_icon);
		password.addFocusListener(new java.awt.event.FocusAdapter() {
			@SuppressWarnings("deprecation")
			public void focusGained(FocusEvent e) {
				if (password.getText().equals("password"))
					password.setText("");
				password.setForeground(Color.BLACK);
			}
			@SuppressWarnings("deprecation")
			public void focusLost(FocusEvent e) {
				if (password.getText().equals("")) {
					password.setText("password");
					password.setForeground(Color.GRAY);
				}
					
			}
		});
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(30, 5, 5, 5);
		panel_right.add(password,c);
		
		//Login Button
		login.setFont(new Font("Quicksand",Font.BOLD,24));
		login.setFocusable(false);
		login.setBackground(new Color(0x8A4FFF));
		login.setPreferredSize(new Dimension(170,40));
		login.setBorder(BorderFactory.createBevelBorder(2));
		login.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        login.setBackground(new Color(0x7558b0));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        login.setBackground(new Color(0x8A4FFF));
		    }
		});
		login.setForeground(Color.white);
		login.addActionListener(this);
		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets(30, 5, 5, 5);
		panel_right.add(login, c);
		
		//footer
		footer.setFont(new Font("Quicksand Medium", Font.PLAIN, 18));
		footer.setForeground(new Color(0x8A4FFF));
		c.gridx = 0;
		c.gridy = 6;
		c.insets = new Insets(15, 5, 5, 5);
		panel_right.add(footer, c);
		
		//Message
		message.setFont(new Font("Quicksand Medium", Font.PLAIN, 20));
		message.setIconTextGap(10);
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 7;
		c.insets = new Insets(15, 5, 5, 5);
		panel_right.add(message,c);
		
		frame.setTitle("Quiz System");
		frame.setSize(700,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(1,2));
		frame.add(panel_left);
		frame.add(panel_right);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == login) {
			String nom_eleve = nom.getText();
			String prenom_eleve = prenom.getText();
			String password_eleve = String.valueOf(password.getPassword());
			String login_eleve = String.valueOf(username.getText());
			
			if (nom_eleve != "Nom") {
				if (prenom_eleve != "Prénom") {
					if (password_eleve != "password") {

						
						Eleve eleve = new Eleve(nom_eleve,prenom_eleve,login_eleve,password_eleve);
						
						try {
							
							ObjectInputStream o1 = new ObjectInputStream(new FileInputStream("./nbEleves.ser"));
							int nbEleves = (int) o1.readObject();
							o1.close();
							
							FileOutputStream f = new FileOutputStream("./eleves.ser",true);
							ObjectOutputStream o = new ObjectOutputStream(f);
							o.writeObject(eleve);
							nbEleves++;
							o.close();
							f.close();
							
							
							ObjectOutputStream o2 = new ObjectOutputStream(new FileOutputStream("./nbEleves.ser"));
							o2.writeObject(nbEleves);
							o2.close();
						}
						catch (FileNotFoundException e1) {
				            System.out.println("File not found");
				        } catch (IOException e1) {
				            System.out.println("Error initializing stream");
				        } catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
					}
				}
			}			
		}
	}
	
}
