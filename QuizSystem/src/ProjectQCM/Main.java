package ProjectQCM;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
	
	JFrame frame = new JFrame();
	JPanel container = new JPanel();
	HomePanel homePanel = new HomePanel();
	
	SignUpPanel signUpPanelEleve = new SignUpPanel(true);
	SignUpPanel signUpPanelProf = new SignUpPanel(false);
	
	LoginPanel loginPanelEleve = new LoginPanel(true);
	LoginPanel loginPanelProf = new LoginPanel(false);
	
	CardLayout cl = new CardLayout();
	
	ProfilePanel profilePanel;
	TeacherProfilePanel teacherProfilePanel;
	QuizPanel quizPanel;
		
	
	Examen selectedExamen;
	
	SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
    
	
	Main(){
		/*Le constructeur Main est divisé en deux parties : la premiere presente l'espace Etudiant,
		 * et la deuxieme presente l'espace Prof*/
				
		container.setLayout(cl);
		container.add(homePanel, "Home Panel");
		container.add(signUpPanelEleve, "SignUp Panel Eleve");
		container.add(signUpPanelProf, "SignUp Panel Prof");
		container.add(loginPanelEleve, "Login Panel Eleve");
		container.add(loginPanelProf, "Login Panel Prof");
		
		
		
		cl.show(container, "Home Panel");
		
		homePanel.button_right.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(container, "Login Panel Eleve");
			}
		});
		
		
		//Espace Etudiant: "Passer le QCM"
		signUpPanelEleve.footer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg1) {
				cl.show(container, "Login Panel Eleve");
			}
		});
		
		loginPanelEleve.footer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg1) {
				
				cl.show(container, "SignUp Panel Eleve");
			}
		});
		
		
		loginPanelEleve.login.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent arg1) {
				loginPanelEleve.loginUser();
				Eleve elv = (Eleve) loginPanelEleve.user;
				
				if (!(elv == null)) {
						
						/*try {
							elv.noter(elv.login, "ID", (float) -1);
						} catch (ClassNotFoundException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}*/
					
						try {
							profilePanel = new ProfilePanel(elv);
							container.add(profilePanel, "Profile Panel");
							cl.show(container, "Profile Panel");
							
							String date = String.valueOf(java.time.LocalDate.now()).substring(8, 10)+"/"+
									String.valueOf(java.time.LocalDate.now()).substring(5, 7)+"/"+
									String.valueOf(java.time.LocalDate.now()).substring(0, 4);
							System.out.println(date);

							
							
							profilePanel.nextTable.addMouseListener(new MouseListener() {
								@SuppressWarnings("deprecation")
								@Override
								public void mouseClicked(MouseEvent e) {
									// TODO Auto-generated method stub
									System.out.println(profilePanel.nextTable.getSelectedRow());
									selectedExamen = profilePanel.selectedExam(profilePanel.nextTable.getSelectedRow());
									System.out.println(selectedExamen.titre);
									
									Date d1 = null;
									Date d2 = null;
									try {
										d1 = sdformat.parse(date);
										d2 = sdformat.parse(selectedExamen.date);
									} catch (ParseException e2) {
										// TODO Auto-generated catch block
										e2.printStackTrace();
									}
									
									LocalTime time1 = LocalTime.now();
							        LocalTime time2 = LocalTime.of(Integer.valueOf(selectedExamen.heure.substring(0, 2)), Integer.valueOf(selectedExamen.heure.substring(3, 5)), 00);
							        System.out.println(time1);
							        System.out.println(time2);
							        System.out.println(time1.isAfter(time2));
							        System.out.println(d1);
							        d2.setYear(d1.getYear());
							        System.out.println(d2);
							        System.out.println(d1.after(d2));
							        
							        
							        
									if ((!(selectedExamen == null)) && (d1.after(d2) || (d1.compareTo(d2)== 0 && time1.isAfter(time2)))) {		
										System.out.println(selectedExamen.matiere);
										profilePanel.button.addActionListener(new ActionListener() {
											@Override
											public void actionPerformed(ActionEvent e) {
												// TODO Auto-generated method stub
												try {
													quizPanel = new QuizPanel(selectedExamen, elv);
													container.add(quizPanel, "Quiz Panel");
													cl.show(container, "Quiz Panel");
													frame.addWindowListener(quizPanel.listener);
												} catch (InterruptedException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
											}
											});
									}
								}

								@Override
								public void mousePressed(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void mouseReleased(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void mouseEntered(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void mouseExited(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}
								
							});
							
							
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}				
				}
			}
		});
		
		
		
		
		//Espace Prof: "Creer" un QCM
		signUpPanelProf.footer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg1) {
				cl.show(container, "Login Panel Prof");
			}
		});
		homePanel.button_left.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(container, "Login Panel Prof");
			}
		});
		loginPanelProf.footer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg1) {
				
				cl.show(container, "SignUp Panel Prof");
			}
		});
		loginPanelProf.login.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg1) {
				loginPanelProf.loginUser();
				Professeur prof = (Professeur) loginPanelProf.user;
				if (prof != null) {
					try {
						teacherProfilePanel = new TeacherProfilePanel(prof);
						container.add(teacherProfilePanel, "Teacher Profile Panel");
						cl.show(container, "Teacher Profile Panel");
						teacherProfilePanel.button.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								new CreatingExamFrame(prof.login);
								frame.dispose();
								}
						});
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				}
			});
		
		
		
		
		
		
		frame.setTitle("Quiz System");
		frame.setSize(700,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(container);
	}
	

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub	
		new Main();
		//new CreatingExamFrame();
	}

}
