package tests;

import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import GUI.GuiGoo;
//Bot testing starts after a moment of idle
//DO NOT MOVE CURSER WHILE BOT IS TESTING
//bot tester goes thru the movements to create the end state checkmate
//it then tries to make a move which is illegal after the end state.

public class GuiTest extends JFrame 
		implements ActionListener,FocusListener{
	
	
	 public static void main(String args[]){
		 
		 
		 
		    System.out.println("Start test program GuiTest");
		    
		    JFrame fram = new JFrame();
		    fram.setPreferredSize(new Dimension(800, 800));
		   
			fram.add(new GuiGoo());
			fram.setTitle("Gui Checkmate Test");
			fram.setVisible(true);
			fram.pack();
			fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
			
			
		    new GuiTest();
		    try {
				Robot bot = new Robot();
				
				bot.setAutoDelay(40);
			    bot.setAutoWaitForIdle(true);
			    bot.delay(2000);
			    
			    bot.mouseMove(550, 600);
			    
			    bot.mouseMove(550, 600);
			    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			    bot.delay(500);
			    bot.mouseRelease(InputEvent.BUTTON1_MASK);
			    
				bot.mouseMove(550,500);
				bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				bot.delay(500);
				//try{Thread.sleep(250);}catch(InterruptedException e){}
				bot.mouseRelease(InputEvent.BUTTON1_MASK);
				
				bot.mouseMove(430,150);
				bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				bot.delay(500);
				bot.mouseRelease(InputEvent.BUTTON1_MASK);
				
				bot.mouseMove(430,250);
				bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				bot.delay(500);
				bot.mouseRelease(InputEvent.BUTTON1_MASK);
				
				bot.mouseMove(630,600);
				bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				bot.delay(500);
				bot.mouseRelease(InputEvent.BUTTON1_MASK);
				
				bot.mouseMove(630,430);
				bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				bot.delay(500);
				bot.mouseRelease(InputEvent.BUTTON1_MASK);
				
				bot.mouseMove(330,100);
				bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				bot.delay(500);
				bot.mouseRelease(InputEvent.BUTTON1_MASK);
				
				bot.mouseMove(700,430);
				bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				bot.delay(500);
				bot.mouseRelease(InputEvent.BUTTON1_MASK);
				
				bot.mouseMove(410,430);
				bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				bot.delay(500);
				bot.mouseRelease(InputEvent.BUTTON1_MASK);
				
				bot.mouseMove(50, 690);
			    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			    bot.delay(500);
			    bot.mouseRelease(InputEvent.BUTTON1_MASK);
			    
			    bot.mouseMove(50, 590);
			    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			    bot.delay(500);
			    bot.mouseRelease(InputEvent.BUTTON1_MASK);
				
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		    
		    
		    System.out.println("\nEnd test program InvalidMoveTest");
		  }//end main

	 
	 
	 
	 
	 
	
	public void focusGained(FocusEvent arg0) {
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}


