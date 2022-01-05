import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Painting extends JPanel implements Runnable{
	private static final long serialVersionUID = 2118726362852244893L;
	Multi parent;
	JButton bTransfer;
	JLabel lValue;
	JPanel Painting;
	Graphics g; 
	int clow=1;
	int chigh = 255;
	int low = 0;
	int high = 400;
	int x1,x2,y1,y2,c1,c2,c3;
	Random r = new Random();
	
	public Painting() {
		
		Thread t = new Thread(this);
		t.start();
	}
	
	
	public void run() {
		while(true) {
			x1 = r.nextInt(high-low) + low;
			x2 = r.nextInt(high-low) + low;
			y1 = r.nextInt(high-low) + low;
			y2 = r.nextInt(high-low) + low;
			c1 = r.nextInt(chigh-clow) + clow;
			c2 = r.nextInt(chigh-clow) + clow;
			c3 = r.nextInt(chigh-clow) + clow;
			if(isShowing()) {
				g = getGraphics();
				Color rectcolor = new Color(c1,c2,c3);
				g.setColor(rectcolor);
				g.fillRect(x1,x2,y1,y2);
			}
			try {
				Thread.sleep(300);
			}
			catch(InterruptedException e) {}
		}
		
	}	




}


