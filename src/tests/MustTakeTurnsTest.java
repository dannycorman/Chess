package tests;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.event.InputEvent;

import javax.swing.JFrame;

import GUI.GuiGoo;

//Bot testing starts after a moment of idle
//DO NOT MOVE CURSER WHILE BOT IS TESTING
//Black attempts to move which should fail. Followed by white's move which should, 
//succeed, followed by whites attempt to move again which should fail

public class MustTakeTurnsTest {
	
	public static void main(String args[]){
		 
		 
		 
	    System.out.println("Start test program InvalidMoveTest");
	    
	    JFrame fram = new JFrame();
	    fram.setPreferredSize(new Dimension(800, 800));
		fram.add(new GuiGoo());
		fram.setVisible(true);
		fram.setTitle("Must Take Turns Test");
		
		fram.pack();
		fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
	    new InvalidMoveTest();
	    try {
			Robot bot = new Robot();
			
			bot.setAutoDelay(40);
		    bot.setAutoWaitForIdle(true);
		    bot.delay(2000);
		    
		    bot.mouseMove(50, 190);
		    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		    bot.delay(500);
		    bot.mouseRelease(InputEvent.BUTTON1_MASK);
		    
			bot.mouseMove(50,250);
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			bot.delay(500);
			bot.mouseRelease(InputEvent.BUTTON1_MASK);
			
			
			bot.mouseMove(410,430);
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			bot.delay(500);
			bot.mouseRelease(InputEvent.BUTTON1_MASK);
			

			bot.mouseMove(630,600);
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			bot.delay(500);
			bot.mouseRelease(InputEvent.BUTTON1_MASK);
//			
			bot.mouseMove(630,430);
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			bot.delay(500);
			bot.mouseRelease(InputEvent.BUTTON1_MASK);
			
			
			bot.mouseMove(630,430);
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			bot.delay(500);
			bot.mouseRelease(InputEvent.BUTTON1_MASK);
			
			bot.mouseMove(630,330);
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			bot.delay(500);
			bot.mouseRelease(InputEvent.BUTTON1_MASK);

			
			
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	    
	    
	    System.out.println("\nEnd test program InvalidMoveTest");
	  }


}
