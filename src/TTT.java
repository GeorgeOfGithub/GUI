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
		
		//adds 3x3 grid with textfield with scrollpane underneath 

        try { //opens socket to connect to server
        	
    		sock = new Socket("ftnk-ctek01.win.dtu.dk", 1060);
    		bir = new BufferedReader(new InputStreamReader(sock.getInputStream()));
    		pw = new PrintWriter(sock.getOutputStream());

    	} catch(IOException Connect) {
    		System.out.println("Could not connect to server");
    	}
        
        try { //reads first line sent from server to determine who is playing X
    		textline = bir.readLine();	
    		if (textline.endsWith("X")) {
    			isx = true;
    		}
    		txtarea.append(textline + "\n"); //tells the player welcome and who player is.
    		
    		recieve(); //a single bir.readline with try catch around it.
    		
    		board(); // creates array for placing board
			
			recieve(); //single bir.readline
        } catch (IOException board) {
        	System.out.println("Could not get board");
        }
		
		for (int i = 0; i <= 8; i++) {
			btns[i] = new JButton(arr[i]);
			btns[i].setFocusable(false);
			btns[i].addActionListener(this);
			btns[i].setFont(new Font("Arial", Font.BOLD, 30));
			p1.add(btns[i]);
		} //this gives the buttons a digit according to server information
		//also makes larger and places on grid panel p1
		
		


	}
	
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 9; i++) {
			
            if (e.getSource() == btns[i]) { //if a button is pressed it places either X or O 
            	if (btns[i].getText() != "X" && btns[i].getText() != "O") { //according to what player is
            		if (isx) {	
                    	btns[i].setText("X");
            		} else {
            			btns[i].setText("O");
            		}
            	}
				pw.print(i+1 + "\r\n"); //sends which button is pressed to server
				//for some reason it thinks the button is one lower than what it is, so output is +1
				pw.flush();	//flushes to server	
				recieve();	//answer back				
				board(); //updates board
    			if (arr[1].equals("V")) {
    				recieve();
    			} else {
    				for (int g = 0; g <= 8; g++) {
    				btns[g].setText(arr[g]);}
    				recieve();
                } //changes buttons
    			
                if (textline.endsWith("WINS")) {
                	gameover(textline);
                } //if message with who won is received from server
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
			JOptionPane.showMessageDialog(null, s); //shows message with who won in popup to player
			for (int i = 0; i<9; i++) {
				btns[i].setText(".");
			} //resets board
			this.dispose(); //ends game and closes window
			main(null); //starts game again with main
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