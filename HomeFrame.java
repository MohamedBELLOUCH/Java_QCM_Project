package ProjectQCM;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


//IN EACH SIDE, INSERT A REPRESENTATIVE ICON 

public class HomeFrame {
	JFrame frame = new JFrame();
	JPanel panel_left = new JPanel();
	JPanel panel_right = new JPanel();
	JButton button_left = new JButton("Créez un QCM");
	JButton button_right = new JButton("Passer un QCM");
	GridBagConstraints c = new GridBagConstraints();
	
	HomeFrame(){
		panel_left.setBackground(new Color(0x8A4FFF));
		panel_right.setBackground(Color.WHITE);
		
		button_left.setBackground(Color.WHITE);
		button_right.setBackground(new Color(0x8A4FFF));
		
		button_left.setForeground(new Color(0x8A4FFF));
		button_right.setForeground(Color.WHITE);
		
		button_left.setFont(new Font("Quicksand Medium", Font.PLAIN, 24));
		button_right.setFont(new Font("Quicksand Medium",Font.PLAIN,24));
		
		button_left.setFocusable(false);
		button_right.setFocusable(false);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		
		panel_left.setLayout(new GridBagLayout());
		panel_right.setLayout(new GridBagLayout());
		
		panel_left.add(button_left, c);
		panel_right.add(button_right, c);
		
		
		frame.setTitle("Quiz System");
		frame.setSize(700,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(1,2));
		frame.add(panel_left);
		frame.add(panel_right);
		
		
	}
}
