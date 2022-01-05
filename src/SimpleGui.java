/*
 * Simple java program to illustrate a graphical user interface (GUI) with the SWING framework
 * 
 * (C) Copyright 2019, Lars Staalhagen
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SimpleGui extends JFrame implements ActionListener {
	
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Declare components for the program's window
    public JButton b1, b2, b3, b4, b5, clrbtn;  // Buttons
    public JTextArea txtarea;               // Large text area in the middle of the window
    public JTextField txtfld;               // Small text field at the bottom of the window
    final String filetext;
   //  public JTextField txtfld2;
    
    public SimpleGui() {
        this.filetext = "";
		// Set a BorderLayout on the main window
        getContentPane().setLayout(new BorderLayout());
        
        // Define a standard button size - 100 x 30 pixels
        Dimension btnsize = new Dimension(100, 30);
        
        // Create a button and set max size and alignment (relative to the surrounding container)
        b1 = new JButton("Button 1");
        b1.addActionListener(this);
        b1.setMaximumSize(btnsize);
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Same for the second button
        b2 = new JButton("Button 2");
        b2.addActionListener(this);
        b2.setMaximumSize(btnsize);
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Same for the clear button
        clrbtn = new JButton("Clear");
        clrbtn.addActionListener(this);
        clrbtn.setMaximumSize(btnsize);
        clrbtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Same for the last button
        b3 = new JButton("Quit");
        b3.addActionListener(this);
        b3.setMaximumSize(btnsize);
        b3.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Create a container (JPanel) to hold the first four buttons
        JPanel p1 = new JPanel();
        // Set a top-down box-layout on the container
        p1.setLayout(new BoxLayout(p1, BoxLayout.LINE_AXIS));
        // Create a small filler (above the first button)
        p1.add(Box.createRigidArea(new Dimension(5, 20)));
        // Add first button
        p1.add(b1);
     // Create a small filler (between 1st and 2nd button)
        p1.add(Box.createRigidArea(new Dimension(5, 20)));
        // Add second button
        p1.add(b2);
        // Filler
        p1.add(Box.createRigidArea(new Dimension(5, 20)));
        // Add third button
        p1.add(clrbtn);
        // Filler
        p1.add(Box.createRigidArea(new Dimension(5, 20)));
        // Add final button
        p1.add(b3);
        
        // Add container (panel with buttons) to (left side of) main window
        getContentPane().add(p1, BorderLayout.NORTH);
        
        // Create a JTextArea (inside a JScrollPane) in the center part
        txtarea = new JTextArea();
        JScrollPane scrollpane = new JScrollPane(txtarea);
        getContentPane().add(scrollpane, BorderLayout.CENTER);
        
        // Create a textfield (approx. size = 40 characters)
        txtfld = new JTextField(40);
        
        // Create a button
        b4 = new JButton("Copy Text");
        b4.addActionListener(this);
        
        // Create a container for the text field and the button with a simple BorderLayout
        JPanel p2 = new JPanel();
        p2.setLayout(new BorderLayout());
        p2.add(txtfld, BorderLayout.CENTER);
        p2.add(b4, BorderLayout.EAST);
        
        // Add the container to the bottom part of the main window
        getContentPane().add(p2, BorderLayout.SOUTH);
        
        b5 = new JButton("Read File");
        b5.addActionListener(this);
        b5.setMaximumSize(btnsize);
        b5.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3, BoxLayout.PAGE_AXIS));
        p3.add(Box.createRigidArea(new Dimension(110, 5)));
        p3.add(b5);
        p3.add(Box.createRigidArea(new Dimension(110, 5)));
        getContentPane().add(p3, BorderLayout.EAST);
    }
    
    // actionPerformed method to handle when the user presses any of the buttons
    public void actionPerformed(ActionEvent e) {
    	
        if (e.getSource() == b3) {
            // User pressed the quit button
            System.exit(0);
        } else if (e.getSource() == b1) {
        	// Append a text to the main text area
            txtarea.append("User pressed button 1\n");
        } else if (e.getSource() == b2) {
        	// Append a text to the main text area
            txtarea.append("User pressed button 2\n");
            
        } else if (e.getSource() == b5) {
        	// Copy the text from the JTextField and append it to the large JTextArea
            String s = txtfld.getText();  // Retrieve the user's input
            Path filePath = Paths.get("H:/", s +".txt");
            try {
            String filetext = Files.readString(filePath);
            txtarea.append("File reads: " + filetext + "\n");  // Append to main text area
            } catch (IOException f) {
            	txtarea.append("File " +s+ ".txt not found!" + "\n");	
            }
           
            txtfld.setText(""); // Clear the contents of the text field
        } else if (e.getSource() == b4) {
        	// Copy the text from the JTextField and append it to the large JTextArea
            String s = txtfld.getText();  // Retrieve the user's input
            txtarea.append("User wrote: " + s + "\n");  // Append to main text area
            txtfld.setText(""); // Clear the contents of the text field
        } else if (e.getSource() == clrbtn) {
            txtarea.setText(""); // Clear the contents of the large text area
        }
        
    }

    
    public static void main(String[] args) {
        SimpleGui simplegui = new SimpleGui();
        
        simplegui.setTitle("SimpleGui"); // Set title on window
        simplegui.setSize(600, 400);     // Set size
        simplegui.setResizable(false);    // Allow the window to be resized
		simplegui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        simplegui.setVisible(true);      // Make the window visible
    }
}