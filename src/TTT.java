import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;


public class TTT extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JButton[] btns = new JButton[9];
	JTextArea txtarea;
	JPanel p1;

	Scanner console = new Scanner(System.in);
	Socket sock = null;
	BufferedReader bir = null;
	PrintWriter pw = null;
	String textline = null;
	String boardplacement = null;
	char array[] = new char[9];
	String arr[] = new String[9];
	boolean isx = false;
	
	
	public TTT() {



		getContentPane().setLayout(new BorderLayout());
		
		JPanel p1 = new JPanel();
		
		p1.setLayout(new GridLayout(3,3));
		
		getContentPane().add(p1, BorderLayout.CENTER);
		
		
        txtarea = new JTextArea(5, 20);
        JScrollPane scrollpane = new JScrollPane(txtarea);
        getContentPane().add(scrollpane, BorderLayout.SOUTH);
		
        try {
        	
    		sock = new Socket("ftnk-ctek01.win.dtu.dk", 1060);
    		bir = new BufferedReader(new InputStreamReader(sock.getInputStream()));
    		pw = new PrintWriter(sock.getOutputStream());

    	} catch(IOException Connect) {
    		System.out.println("Could not connect to server");
    	}
        
        try {
    		textline = bir.readLine();	
    		if (textline.endsWith("X")) {
    			isx = true;
    		}
    		txtarea.append(textline + "\n");
    		
    		recieve();
    		
    		board();
			
			recieve();
        } catch (IOException board) {
        	System.out.println("Could not get board");
        }
		
		for (int i = 0; i <= 8; i++) {
			btns[i] = new JButton(arr[i]);
			btns[i].setFocusable(false);
			btns[i].addActionListener(this);
			btns[i].setFont(new Font("Arial", Font.BOLD, 30));
			p1.add(btns[i]);
		}
		
		


	}
	
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 9; i++) {
			
            if (e.getSource() == btns[i]) {
            	if (btns[i].getText() != "X" && btns[i].getText() != "O") {
            		if (isx) {	
                    	btns[i].setText("X");
            		} else {
            			btns[i].setText("O");
            		}
            	}
				pw.print(i+1 + "\r\n");
				pw.flush();		
				recieve();					
				board();
    			if (arr[1].equals("V")) {
    				recieve();
    			} else {
    				for (int g = 0; g <= 8; g++) {
    				btns[g].setText(arr[g]);}
    				recieve();
                }
    			
                if (textline.endsWith("WINS")) {
                	gameover(textline);
                }
            }

		}
	}
	
	public void board() {
        boardplacement = textline.substring(9);
		for (int f = 0; f < boardplacement.length(); f++) {
			arr[f] = String.valueOf(boardplacement.charAt(f));
		}
	}
	
	public void recieve() {
        try {
        	textline = bir.readLine();
        } catch (IOException b) {
        	System.out.println("Lost connection during game");
        }
	}

	public void gameover(String s) {
			JOptionPane.showMessageDialog(null, s);
			for (int i = 0; i<9; i++) {
				btns[i].setText(".");
			}
			this.dispose();
			main(null);
	}

	public static void main(String[] args) {
	
		TTT ttt = new TTT();
	
	
		ttt.setTitle("Tic-Tac-Toe"); 
		ttt.setSize(500, 500);     
		ttt.setResizable(false);    
		ttt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ttt.setVisible(true);     
	}
}