import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Converter extends JFrame implements ActionListener {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Declare components for the program's window
    public JButton b1, b2, b3, b4;  // Buttons
    public JTextArea txtarea;  // Large text area in the middle of the window
    public JTextField txtfld1,txtfld2,txtfld3;               // Small text field at the bottom of the window
    public JLabel jlabel1, jlabel2, jlabel3;
    
    public Converter() {
        // Set a BorderLayout on the main window
        getContentPane().setLayout(new BorderLayout());
        
        // Define a standard button size - 100 x 30 pixels
        Dimension btnsize = new Dimension(90, 20);
        
        // Create a button and set max size and alignment (relative to the surrounding container)
        b1 = new JButton("Convert");
        	b1.addActionListener(this);
        	b1.setMaximumSize(btnsize);
        	b1.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        // Same for the second button
        b2 = new JButton("Convert");
        	b2.addActionListener(this);
        	b2.setMaximumSize(btnsize);
        	b2.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        // Same for the last button
        b3 = new JButton("Convert");
        	b3.addActionListener(this);
        	b3.setMaximumSize(btnsize);
        	b3.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        b4 = new JButton("Clear");
        	b4.addActionListener(this);
        	b4.setMaximumSize(btnsize);
        	b4.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        jlabel1 = new JLabel("Binary");
        jlabel2 = new JLabel("Decimal");
        jlabel3 = new JLabel("Hexadecimal");
        

        txtfld1 = new JTextField(30);
        txtfld2 = new JTextField(30);
        txtfld3 = new JTextField(30);
        txtfld1.setMaximumSize( txtfld1.getPreferredSize() );
        txtfld2.setMaximumSize( txtfld2.getPreferredSize() );
        txtfld3.setMaximumSize( txtfld3.getPreferredSize() );
        
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
          
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
        	String s = txtfld1.getText();
    		String bin = s;
        	if (bin.matches("[0-1]+") && bin.length()<30) {

        		int dec = Integer.parseInt(bin,2);
        		String deci = Integer.toString(dec);
        		String hex = Integer.toHexString(dec);
        		txtfld2.setText(deci);
        		txtfld3.setText(hex);
        	} else if (bin.length()>=30) {
        		txtfld2.setText("Number too large");
        		txtfld3.setText("Number too large");
        	}else {
        		txtfld2.setText("Not binary number!");
        		txtfld3.setText("Not binary number!");
        	}
        	
        } else if (e.getSource() == b2) {
        	String s = txtfld2.getText();
        	if (s.matches("[0-9]+")&& s.length()<10) {
        		int dec = Integer.parseInt(s);
        		String bin = Integer.toBinaryString(dec);
        		String hex = Integer.toHexString(dec);
        		txtfld1.setText(bin);
        		txtfld3.setText(hex);
        		
        	} else if (s.length()>=10) {
        		txtfld2.setText("Number too large");
        		txtfld3.setText("Number too large");
        	} else {
        		txtfld1.setText("Not decimal number!");
        		txtfld3.setText("Not decimal number!");
        	}
        	
        } else if (e.getSource() == b3) {
        	int ishex = 1;
        	String s = txtfld3.getText();
        	for ( int i = 1 ; i < s.length() ; i++ ) {
                if ( Character.digit(s.charAt(i), 16) == -1 )
                	ishex = 0;}
        	if (ishex == 1) {
        		try {
        		String hex = s;
        		int dec = Integer.parseInt(hex,16);
        		String bin = Integer.toBinaryString(dec);
        		String deci = Integer.toString(dec);
        		txtfld1.setText(bin);
        		txtfld2.setText(deci);
        		} catch (Exception hex) {
        			txtfld1.setText("Number too large");
        			txtfld2.setText("Number too large");
        		}
        	} else {
            	txtfld1.setText("Not hexadecimal number!");
    			txtfld2.setText("Not hexadecimal number!");
        	}
        }
        else if (e.getSource() == b4) {
        	txtfld1.setText("");
        	txtfld2.setText("");
        	txtfld3.setText("");
        }
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