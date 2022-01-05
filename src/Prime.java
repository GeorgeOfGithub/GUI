import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Prime extends JPanel implements Runnable{
    private static final long serialVersionUID = 2118726362852244893L;
    Multi parent;
    JTextArea txtArea;
    JScrollPane scroll;
    int i=1; //start number

    public Prime() {
    	
    	this.setLayout(new BorderLayout());
    	
		txtArea = new JTextArea(6,50); //creates textarea noneditable and with linewrap
		txtArea.setLineWrap(true);
		txtArea.setEditable(false);
		txtArea.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		scroll = new JScrollPane(txtArea);
		this.add(scroll); //adds scrollpane to textarea to make it stay same size

        Thread t = new Thread(this);
        t.start(); //starts thread
        
    }


    public void run() {
        while(true) { //runs forever
            if (isprime(i)) { //checks if current number is prime
            txtArea.append(i+" "); //adds to textarea if it is prime
            txtArea.setCaretPosition(txtArea.getDocument().getLength()); //autoscrolls to current number
            }
            i++ ; //goes a number up to find next prime number
            try {
                Thread.sleep(100); //stops for short time between every number
            }
            catch(InterruptedException e) {}
        }

    }

    public boolean isprime(int n) {
        if (n <= 3) return true; //if number is less than or equal to 3 it is a prime number
        if (n % 2 == 0 || n % 3 == 0) return false; //if it is dividable by 2 or 3 it is not a prime number unless it is 2 or 3

        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
            return false; //code to check if number is not prime number even if not dividable by 2 or 3

        return true;

    }



}