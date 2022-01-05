import java.net.*;
import java.io.*;
import java.util.*;



public class Server {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		Scanner netin = null;
		ServerSocket SS = null;
		BufferedReader bir = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		PrintWriter pw = null;
		boolean serverAktiv = true;
		boolean readAll = false;
		int nRead = 0;
		int length = 1024;
		byte[] buffer = new byte[length];
		int l = 0;
		String filtype= "";
		File fil = null;
		int kode = 200;
		String indhold = null;
		// a lot of variables 

		


		try {

		SS = new ServerSocket(1080);
		
		} catch (Exception SSstart) {
			System.out.println("SS start fejl");
		}
		// starting the server on socket 1093
		
		try {

			while (serverAktiv) {
				
				Socket klientsock = SS.accept();		
				// open for klient
				
				bir = new BufferedReader(new InputStreamReader(klientsock.getInputStream()));
				pw = new PrintWriter(klientsock.getOutputStream());
				bos = new BufferedOutputStream(klientsock.getOutputStream());
				// readers and writers created
				
				
				netin = new Scanner(klientsock.getInputStream());	
				String textline = netin.nextLine();
				indhold = split(textline);

				System.out.println(indhold);	
				fil = new File(indhold);
				// scan the input and split the filetype from that and find the file.
				// also we print the file

				
				try {
					if (indhold.endsWith(".gif") ) {
						filtype = "image/gif";
					} else
					if (indhold.endsWith(".png") ) {
						filtype = "image/png";
					} else 
					if (indhold.endsWith(".bmp") ) {
						filtype = "image/bmp";
					} else
					if (indhold.endsWith(".jpg") ) {
						filtype = "image/jpg";
					} else 
					if (indhold.endsWith(".html")) {
						filtype = "text/html";
					} else {
						kode = 404;
						fil = new File("custom404.html");		
					} 
					// we look for the type of the file and if it is not recognized we set the file to be shown to be 404
					
				} catch (Exception file) {
					System.out.println("File is wrong");
				}
				
				
				l = (int) fil.length();
				// find the length of the file

				pw.write("HTTP/1.0 "+ kode +"\r\n");
				pw.write("Content-length: " +  l +"\r\n");
				pw.write("Content-type: "+ filtype +"\r\n");
				pw.write("Date: Wed, 02 Nov 2016 11:27:06 GMT\r\n");
				pw.write("\r\n");
				pw.flush();
				// write the header
				
				
				try {
					FileInputStream fin = new FileInputStream(fil);
	
					
					 while (readAll == false) {
							nRead = fin.read(buffer);
							if (nRead == -1) {
								fin.close();
								readAll = true;
							} else {

								bos.write(buffer, 0, nRead);
								bos.flush();
							}
						}
					 // send the file to the klient
					 
				} catch (Exception startnowork) {

					System.out.println("fileinputstream wrong");
					klientsock.close();
					SS.close();
					console.close();
					// close everything if the file doesn't work
					
				}
				
				buffer = new byte[length];
				nRead = 0;
				readAll=false;
				bos.flush();
				klientsock.close();
				// close everything at the end and empty the buffer and reset.

				}
			
			System.out.println("server closes");
			serverAktiv = false;
			
			// when the server goes inactive it closes, this doesn't happend normally
			
			SS.close();
			console.close();
			
		} catch (Exception wrong) {
			System.out.println("Program terminated");
			// We have a try and catch in case something goes wrong at all
		}

	}

	public static String split(String n) {
		String[] splitted = n.split("\\s+");
		String text = splitted[1].substring(1);
		return text;
		
		// this method splits the file name from what the klient sends as a string.
		
	}
}

