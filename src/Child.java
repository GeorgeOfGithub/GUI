import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Child extends JFrame implements Runnable, ActionListener{
	private static final long serialVersionUID = 2118726362852244893L;
	MultiThreadDemo parent;
	JButton bTransfer;
	JLabel lValue;
	int i=0;

	public Child(MultiThreadDemo p) {
		parent = p;
		this.setTitle("Spawned vindue");
		this.setSize(400,200);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		bTransfer = new JButton("Overf�r til hovedvindue");
		bTransfer.addActionListener(this);
		lValue = new JLabel("0");
		lValue.setHorizontalAlignment(JLabel.CENTER);
		getContentPane().add(lValue,BorderLayout.CENTER);
		getContentPane().add(bTransfer,BorderLayout.SOUTH);	
		Thread t = new Thread(this);
		t.start();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==bTransfer) {
			parent.txtArea.append(lValue.getText()+" ");
		}	
	}

	public void run() {
		while(true) {
			i = i +1;
			lValue.setText(Integer.toString(i));
			try {
				Thread.sleep(500);
			}
			catch(InterruptedException e) {}
		}
		
	}	
}
