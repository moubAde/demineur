package demineure;

import javax.swing.*;

public class Over {
	ZDialog sms;
	JLabel game;
	JButton retry;
	JButton menu;
	public Over(){
		 sms=new ZDialog(null,"Resultat",true);
	     game=new JLabel("Game Over"); 
	     retry=new JButton("Recommence");
	     menu=new JButton("Abandonne");
	     sms.add(game);
	     sms.add(retry);
	     sms.add(menu);
	     sms.setLayout(null);
	     game.setBounds(50,20,100,50);
	     retry.setBounds(0,70,150,70);
	     menu.setBounds(200,70,150,70);
	     sms.setVisible(true);
	} 
	public void message(){
		
	}
}
