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
    int i=1;

    public Prime() {
    	
    	this.setLayout(new BorderLayout());
    	
		txtArea = new JTextArea(6,50);
		txtArea.setLineWrap(true);
		txtArea.setEditable(false);
		txtArea.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		scroll = new JScrollPane(txtArea);
		this.add(scroll);

        Thread t = new Thread(this);
        t.start();
        
    }


    public void run() {
        while(true) {
            if (isprime(i)) {
            txtArea.append(i+" ");
            txtArea.setCaretPosition(txtArea.getDocument().getLength());
            }
            i++ ;
            try {
                Thread.sleep(1);
            }
            catch(InterruptedException e) {}
        }

    }

    public boolean isprime(int n) {
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;

        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
            return false;

        return true;

    }



}