import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Multi extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	JButton quit;
	JPanel down;

	
	public Multi() {
		
		getContentPane().setLayout(new BorderLayout());
		
		Prime prime = new Prime(); 
		Painting paint = new Painting(); //adds prime counter and painting to enable adding to main frame
		
		quit = new JButton("Quit");
		quit.addActionListener(this);
		quit.setAlignmentX(Component.CENTER_ALIGNMENT); //quit button
		
		JPanel down = new JPanel();
		down.setLayout(new BoxLayout(down, BoxLayout.PAGE_AXIS));

		down.add(prime);
		down.add(quit); 
		
		getContentPane().add(down, BorderLayout.SOUTH);
		getContentPane().add(paint, BorderLayout.CENTER);
		//adds everything to main frame
		}
	
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==quit) {
			System.exit(0);
		} //system exit if quit button pressed
	}
	

	
	public static void main(String[] Args) {
		Multi parent = new Multi();
		parent.setVisible(true);
		parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		parent.setSize(600, 400);
		parent.setTitle("Grafik og Primtal");	
	}
}
