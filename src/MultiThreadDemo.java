import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MultiThreadDemo extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	public int latest;
	JButton bNew;
	JLabel lWelcome;
	JTextArea txtArea;
	JFrame childframe;
	
	public MultiThreadDemo() {
		getContentPane().setLayout(new BorderLayout());
		bNew = new JButton("Opret nyt vindue");
		bNew.addActionListener(this);
		lWelcome = new JLabel("Velkommen til MultiThread demo");
		txtArea = new JTextArea();
		getContentPane().add(lWelcome, BorderLayout.NORTH);
		getContentPane().add(bNew, BorderLayout.SOUTH);
		getContentPane().add(txtArea, BorderLayout.CENTER);	
		}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==bNew) {
			childframe = new Child(this);
		}
	}
	
	public static void main(String[] Args) {
		MultiThreadDemo parent = new MultiThreadDemo();
		parent.setVisible(true);
		parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		parent.setSize(600, 300);
		parent.setTitle("Demo Multithreading");	
	}

}


