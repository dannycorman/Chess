package tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



import GUI.GuiGoo;
//Bot testing starts after a moment of idle
//DO NOT MOVE CURSER WHILE BOT IS TESTING
//bot tester goes thru the movements to create the end state, checkmate

//Robot tester should do a false move

public class InvalidMoveTest extends JFrame 
		implements ActionListener,FocusListener{
	
	
	 public static void main(String args[]){
		 
		 
		 
		    System.out.println("Start test program InvalidMoveTest");
		    
		    JFrame fram = new JFrame();
		    fram.setPreferredSize(new Dimension(800, 800));
			fram.add(new GuiGoo());
			fram.setVisible(true);
			fram.setTitle("Invalid Movement Test");
			fram.pack();
			fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
		    new InvalidMoveTest();
		    try {
				Robot bot = new Robot();
				
				bot.setAutoDelay(40);
			    bot.setAutoWaitForIdle(true);
			    bot.delay(2000);
			    
			    bot.mouseMove(350, 600);
			    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			    bot.delay(500);
			    bot.mouseRelease(InputEvent.BUTTON1_MASK);
			    
				bot.mouseMove(430,450);
				bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				bot.delay(500);
				bot.mouseRelease(InputEvent.BUTTON1_MASK);
				

				
				
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		    
		    
		    System.out.println("\nEnd test program InvalidMoveTest");
		  }

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}//end main

} 
	 
	 
	 
	 