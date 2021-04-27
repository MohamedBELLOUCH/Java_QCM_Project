import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class Quiz implements ActionListener {
	JFrame frame = new JFrame();
	
	JPanel header = new JPanel();
	JPanel section = new JPanel();
	JPanel footer = new JPanel();
	JPanel buttons = new JPanel();
	JPanel suivant_panel = new JPanel();
	JLabel header_label = new JLabel();
	JLabel question_label = new JLabel();
	JButton Option1 = new JButton();
	JButton Option2 = new JButton();
	JButton Option3 = new JButton();
	JButton Option4 = new JButton();
	JButton suivant = new JButton();
	
	Quiz(){
		
		//Set margins
		Border border = header_label.getBorder();
		Border margin1 = new EmptyBorder(50,50,10,50);
		Border margin2 = new EmptyBorder(0,60,20,60);
		header_label.setBorder(new CompoundBorder(border, margin1));
		question_label.setBorder(new CompoundBorder(border, margin2));
		buttons.setBorder(new CompoundBorder(border, new EmptyBorder(10,100,20,100)));
		suivant_panel.setBorder(new CompoundBorder(border, new EmptyBorder(0,0,20,60)));
		
		//Header
		header.setPreferredSize(new Dimension(100,100));
		header.setBackground(Color.white);
		
		header_label.setText("Question 1 sur 10");
		header_label.setForeground(new Color(0xaeb5bd));
		header_label.setBackground(Color.WHITE);
		header_label.setFont(new Font("Quicksand Medium",Font.PLAIN,25));
		header_label.setOpaque(true);		
		header.setLayout(new BorderLayout());
		header.add(header_label, BorderLayout.WEST);
		
		//Questions Section
		section.setPreferredSize(new Dimension(100,100));
		section.setBackground(Color.white);
		
		question_label.setText("1.  Quelle est la societe qui a crée Java ?");
		question_label.setForeground(new Color(0x8A4FFF));
		question_label.setFont(new Font("Quicksand",Font.PLAIN,24));
		question_label.setBackground(Color.white);
		question_label.setOpaque(true);		
		
		
		Option1.setText("Sun Microsystems");
		Option2.setText("Apple");
		Option3.setText("Microsoft");
		Option4.setText("Oracle");
		suivant.setText("Suivant");
		
		Option1.setFont(new Font("Quicksand Medium",Font.PLAIN,21));
		Option2.setFont(new Font("Quicksand Medium",Font.PLAIN,21));
		Option3.setFont(new Font("Quicksand Medium",Font.PLAIN,21));
		Option4.setFont(new Font("Quicksand Medium",Font.PLAIN,21));
		suivant.setFont(new Font("Quicksand",Font.BOLD,20));

		
		Option1.setFocusable(false);
		Option2.setFocusable(false);
		Option3.setFocusable(false);
		Option4.setFocusable(false);
		suivant.setFocusable(false);
		
		Option1.setBackground(new Color(0xE5ECF4));
		Option2.setBackground(new Color(0xE5ECF4));
		Option3.setBackground(new Color(0xE5ECF4));
		Option4.setBackground(new Color(0xE5ECF4));
		suivant.setBackground(new Color(0x8A4FFF));
		
		suivant.setPreferredSize(new Dimension(160,60));
		suivant.setBorder(BorderFactory.createBevelBorder(2));
		suivant.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        suivant.setBackground(new Color(0x7558b0));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        suivant.setBackground(new Color(0x8A4FFF));
		    }
		});
		suivant.setForeground(Color.white);
		
		//suivant.setBounds(100, 0, 100, 50);
		
	    //Option1.setBorder(BorderFactory.createLineBorder(null));
		
		buttons.setBounds(0,0,100,100);
		
		//section.setLayout(new BorderLayout(50,50));
		section.setLayout(new BorderLayout());
		section.add(question_label, BorderLayout.NORTH);
		section.add(buttons, BorderLayout.CENTER);
		section.setBackground(Color.WHITE);
		buttons.setLayout(new GridLayout(4,1,20,20));
		buttons.setBackground(Color.WHITE);
		buttons.add(Option1);
		buttons.add(Option2);
		buttons.add(Option3);
		buttons.add(Option4);
		
		
		
		//Footer Section
		footer.setPreferredSize(new Dimension(100,100));
		footer.setBackground(Color.WHITE);
		footer.setLayout(new BorderLayout());
		suivant_panel.setBackground(Color.WHITE);
		suivant_panel.add(suivant);
		footer.add(suivant_panel, BorderLayout.EAST);
		
		
		//frame.setBackground(Color.GREEN);
		frame.getContentPane().setBackground(Color.WHITE);
		
		frame.setTitle("Quiz System");
		frame.setSize(700,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout(20,20));
		
		frame.add(header, BorderLayout.NORTH);
		frame.add(section, BorderLayout.CENTER);
		frame.add(footer, BorderLayout.SOUTH);
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
