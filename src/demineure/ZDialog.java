package demineure;

import javax.swing.*;

class ZDialog extends JDialog {
	  public ZDialog(JFrame parent, String title, boolean modal){
	  	super(parent, title, modal);
	    this.setSize(350,300);
	    //this.setLocationRelativeTo(null);
	    this.setResizable(false);
	  }
}

