import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Converter extends JFrame implements ActionListener {
    
	private static final long serialVersionUID = 1L;
	
    public JButton b1, b2, b3, b4;  
    public JTextArea txtarea;  
    public JTextField txtfld1,txtfld2,txtfld3;               
    public JLabel jlabel1, jlabel2, jlabel3;
    
    public Converter() {
        
        getContentPane().setLayout(new BorderLayout());
        
        
        Dimension btnsize = new Dimension(90, 20);
        
        
        b1 = new JButton("Convert");
        	b1.addActionListener(this);
        	b1.setMaximumSize(btnsize);
        	b1.setAlignmentX(Component.RIGHT_ALIGNMENT);
    
        b2 = new JButton("Convert");
        	b2.addActionListener(this);
        	b2.setMaximumSize(btnsize);
        	b2.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        b3 = new JButton("Convert");
        	b3.addActionListener(this);
        	b3.setMaximumSize(btnsize);
        	b3.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        b4 = new JButton("Clear");
        	b4.addActionListener(this);
        	b4.setMaximumSize(btnsize);
        	b4.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
			//Create 3 convert buttons and 1 clear button


        jlabel1 = new JLabel("Binary");
        jlabel2 = new JLabel("Decimal");
        jlabel3 = new JLabel("Hexadecimal");
        
		//3 labels for 3 conversions

        txtfld1 = new JTextField(30);
        txtfld2 = new JTextField(30);
        txtfld3 = new JTextField(30);
        txtfld1.setMaximumSize( txtfld1.getPreferredSize() );
        txtfld2.setMaximumSize( txtfld2.getPreferredSize() );
        txtfld3.setMaximumSize( txtfld3.getPreferredSize() );
        
		//input and output fields

        JPanel p1 = new JPanel();
        	p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));
        	p1.add(Box.createRigidArea(new Dimension(10, 20)));
        	p1.add(b1);
        	p1.add(Box.createRigidArea(new Dimension(10, 30)));
        	p1.add(b2);
        	p1.add(Box.createRigidArea(new Dimension(10, 35)));
        	p1.add(b3);
        	p1.add(Box.createRigidArea(new Dimension(10, 30)));
        	p1.add(b4);
        
        getContentPane().add(p1, BorderLayout.EAST);
        
        JPanel p2 = new JPanel();
        	p2.setLayout(new BoxLayout(p2, BoxLayout.PAGE_AXIS));
        	p2.add(Box.createRigidArea(new Dimension(10, 20)));
        	p2.add(jlabel1);
        	p2.add(Box.createRigidArea(new Dimension(10, 33)));
        	p2.add(jlabel2);
        	p2.add(Box.createRigidArea(new Dimension(10, 40)));
        	p2.add(jlabel3);

        getContentPane().add(p2, BorderLayout.WEST);
        
        JPanel p3 = new JPanel();
        	p3.setLayout(new BoxLayout(p3, BoxLayout.PAGE_AXIS));
        	p3.add(Box.createRigidArea(new Dimension(10, 20)));
        	p3.add(txtfld1);
        	p3.add(Box.createRigidArea(new Dimension(10, 30)));
        	p3.add(txtfld2);
        	p3.add(Box.createRigidArea(new Dimension(10, 35)));
        	p3.add(txtfld3);

        getContentPane().add(p3, BorderLayout.CENTER);
        
		//3 panels with buttons, labels and textfields in
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) { //first button test
        	String s = txtfld1.getText(); // get the input into string
    		String bin = s; //rename for readability
        	if (bin.matches("[0-1]+") && bin.length()<30) { //make sure it is binary and it isn't to long

        		int dec = Integer.parseInt(bin,2); // parse binary to decimal with parseint
        		String deci = Integer.toString(dec); //converts to string to enable output to textfield
        		String hex = Integer.toHexString(dec); // parse binary to hexadecimal with toHexString
        		txtfld2.setText(deci); 
        		txtfld3.setText(hex); //output conversions to textfields
        	} else if (bin.length()>=30) { //if input is to large it outputs errors to other textfields
        		txtfld2.setText("Number too large");
        		txtfld3.setText("Number too large");
        	}else { //if input is not binary outputs error
        		txtfld2.setText("Not binary number!");
        		txtfld3.setText("Not binary number!");
        	}
        	
        } else if (e.getSource() == b2) { //second button test
        	String s = txtfld2.getText();
        	if (s.matches("[0-9]+")&& s.length()<10) { //makes sure it is number and not to long
        		int dec = Integer.parseInt(s); //converts from string to int and renames for readability
        		String bin = Integer.toBinaryString(dec); //converts from decimal int to binary string
        		String hex = Integer.toHexString(dec); //converts from decimal int to hexadecimal string
        		txtfld1.setText(bin); //output conversions to textfields
        		txtfld3.setText(hex);
        		
        	} else if (s.length()>=10) { //if input is to large it outputs errors to other textfields
        		txtfld2.setText("Number too large");
        		txtfld3.setText("Number too large");
        	} else { //if input is not decimal outputs error
        		txtfld1.setText("Not decimal number!");
        		txtfld3.setText("Not decimal number!");
        	}
        	
        } else if (e.getSource() == b3) { //third button test
        	int ishex = 1; //int for hextest
        	String s = txtfld3.getText();
        	for ( int i = 1 ; i < s.length() ; i++ ) { 
                if ( Character.digit(s.charAt(i), 16) == -1 )
                	ishex = 0;} //tests i number i hexadecimal by going through each digit
        	if (ishex == 1) { //if test show it is hexadecimal
        		try { //catch for if it is to large, more general than using int
        		String hex = s; //for readability
        		int dec = Integer.parseInt(hex,16); //outputs int dec with parseint on hexadecimal
        		String bin = Integer.toBinaryString(dec); 
        		String deci = Integer.toString(dec);
        		txtfld1.setText(bin);
        		txtfld2.setText(deci);
        		} catch (Exception hex) {
        			txtfld1.setText("Number too large");
        			txtfld2.setText("Number too large");
        		}
        	} else { //if test show not hexadecimal then error message shown in other textfields
            	txtfld1.setText("Not hexadecimal number!");
    			txtfld2.setText("Not hexadecimal number!");
        	}
        }
        else if (e.getSource() == b4) {
        	txtfld1.setText("");
        	txtfld2.setText("");
        	txtfld3.setText("");
        } //lastly a clear button to make it easier to do multiple conversions
    }

    
    public static void main(String[] args) {
    	Converter converter = new Converter();
        
    	converter.setTitle("Converter"); 
    	converter.setSize(525, 260);   
    	converter.setResizable(false);   
    	converter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	converter.setVisible(true); 
    }
}