package com.dwm.robot.main;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class RMain {
 public static void main(String[] args) {
	 try {
         
         Robot robot = new Robot();
         
         //KeyEvent
         //5�� ������
         robot.delay(2000);
         
         robot.keyPress(KeyEvent.VK_O);
         robot.keyPress(KeyEvent.VK_R);
         robot.keyPress(KeyEvent.VK_A);
         robot.keyPress(KeyEvent.VK_C);
         robot.keyPress(KeyEvent.VK_L);
         robot.keyPress(KeyEvent.VK_E);
         robot.keyPress(KeyEvent.VK_J);
         robot.keyPress(KeyEvent.VK_A);
         robot.keyPress(KeyEvent.VK_V);
         robot.keyPress(KeyEvent.VK_A);
         
         //Ŀ���� ������ǥ 300,300���� �̵�
         robot.mouseMove(300, 300);
         
     } catch (AWTException e) {
         e.printStackTrace();
     }
}
}
