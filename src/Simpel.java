import java.net.*;
import java.io.*;
import java.util.*;

public class Simpel {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		Socket sock = null;
		BufferedReader bir = null;
		PrintWriter pw = null;
		String textline = null;
		String spiller = null;
		String boardplacement = null;
		boolean gameon = true;
		/*
		 * Here we create all the variables that are used in the program. We create
		 * socket to go to the server, the buffered reader to get the input from the
		 * server, the printwriter to create the output to the server, some strings to
		 * print from server in console and to create array to create the game
		 * graphically and a boolean that controls if the game is going.
		 * 
		 */
		try {

			sock = new Socket("ftnk-ctek01.win.dtu.dk", 1060);
			bir = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			pw = new PrintWriter(sock.getOutputStream());
			// Here it uses sock, bir, and pw to find the server and get input and output of
			// it.

			textline = bir.readLine();
			System.out.println(textline);
			textline = bir.readLine();
			
			// Prints the first 2 lines from the server
			
			boardplacement = textline.substring(9, 18);
			char[] array = new char[boardplacement.length()];
			for (int i = 0; i < boardplacement.length(); i++) {
				array[i] = boardplacement.charAt(i);
			}
			System.out.println(textline.substring(0, 8));
			for (int i = 0; i < 9; i++) {
				if (array[i] == '.') {
					System.out.print("[ ]");
				} else {
					System.out.print("[" + array[i] + "]");}
				if (i == 2 || i == 5) {
					System.out.println("");
				} }
			System.out.println();
			// Creates an array from the board in the second line
			// Then it prints the boardstate and replaces "." with " " to make the boardstate clearer.
			
			textline = bir.readLine();
			System.out.println(textline);
			// Prints the last line from the server

			while (gameon == true) {

				spiller = console.nextLine();
				pw.print(spiller + "\r\n");
				pw.flush();
				// After we get the first 3 lines, we get the input from the player in the
				// console and send it to the server with .print and .flush

				textline = bir.readLine();
				boardplacement = textline.substring(9, 18);		
				for (int i = 0; i < boardplacement.length(); i++) {
					array[i] = boardplacement.charAt(i);
				}
				System.out.println(textline);
				for (int i = 0; i < 9; i++) {
					if (array[i] == '.') {
						System.out.print("[ ]");
					} else {
						System.out.print("[" + array[i] + "]");}
					if (i == 2 || i == 5) {
						System.out.println("");
					} }
				System.out.println();
				// We get the first line from the server and change the array to show the new
				// boardstate and print it the same way as before

				textline = bir.readLine();
				System.out.println(textline);
				// Then we print the "YOUR TURN"

				if (textline.endsWith("WINS")) {
					gameon = false;
					sock.close();
					console.close();
				}
				// Lastly we test if the game is over by testing if the server sends a message
				// ending with "Wins" by either the player or the computer if they have won
			}

		} catch (Exception wrong) {
			System.out.println("Program terminated");
			// We have a try and catch in case something goes wrong
		}
	}
}
