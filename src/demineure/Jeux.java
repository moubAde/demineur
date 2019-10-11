package demineure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Jeux implements ActionListener{
  int max=10,min=0,bombe=20;	
  JFrame fenetre;
  JFrame jeu;
  JTable table1;
  JTable table2;
  String infos1[][]=new String[max][max];
  String titre1[]=new String[max];
  String infos2[][]=new String[max][max];
  String titre2[]=new String[max];
  JButton cases[]=new JButton[max*max];
   
  public void debut(){
	 int x,y;
	 String nbrBombe="";
	 for(int i=0;i<max;i++){
		titre1[i]="nom";	 
	 }
	 
	 for(int i=0;i<max;i++){
			titre2[i]="nom";	 
	 }
	 
	 for(x=0;x<max;x++){
		for(y=0;y<max;y++){
			infos1[x][y]="0";	   
		}  
	 }
	 do{
		 do{
	         x=min +(int)(Math.random()*(max-min));
	     }while(x<=min || x>=max);
		 nbrBombe+=x;
		 do{
	         y=min +(int)(Math.random()*(max-min));
	     }while(y<=min || y>=max);
		 nbrBombe+=y;
		 nbrBombe=this.doublons(nbrBombe);
	 }while(nbrBombe.length()<bombe);
	 for(int i=0;i<nbrBombe.length();i+=2){
		 System.out.print(nbrBombe.substring(i,i+2)+" "); 
	 }
	 for(int i=0;i<nbrBombe.length();i+=2){
		 x=Integer.parseInt(nbrBombe.substring(i,i+1));
		 y=Integer.parseInt(nbrBombe.substring(i+1,i+2));
		 infos1[x][y]="1";  
	 }
	 
	 //rempliçage des indications
	 for(int i=0;i<max;i++){
		for(int u=0;u<max;u++){
		   infos2[i][u]=this.verification(i,u);   		   	
		} 
	 }
	 
	 table1=new JTable(infos1,titre1);
	 table2=new JTable(infos2,titre2);    
	 fenetre=new JFrame("Demineure");
	 fenetre.add(table1);
	 fenetre.add(table2); 
	 fenetre.setLayout(null);
	 fenetre.setSize(400,500);	 
     table1.setBounds(100,10,200,100);
     table2.setBounds(100,210,200,100);
     fenetre.setVisible(false);
     fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
     jeu=new JFrame("Demineure");
     this.boutonVide();
     jeu.setLayout(new GridLayout(max,max));     
	 //jeu.setSize(400,500);
	 jeu.setSize(600,700);
	 jeu.setVisible(true);
     jeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  
  //creation des cases vide
  public void boutonVide(){  
		for(int i=0,x=0,y=0;i<max*max;i++){
			cases[i]=new JButton("");
			cases[i].setFont(new Font("Serif", Font.PLAIN, 15));//taille 
		    y++;
			if(y>=max){
			   x++;
			   y=0;
			}	
		}
		for(int i=0;i<max*max;i++){
		    cases[i].addActionListener(this);
		}
		for(int i=0;i<max*max;i++){
			jeu.add(cases[i]);
		}
   }
  //creation des boutons
  /*public void bouton(){  
	for(int i=0,x=0,y=0;i<max*max;i++){
		cases[i]=new JButton(infos2[x][y]);
	    y++;
		if(y>=max){
		   x++;
		   y=0;
		}
    }
	for(int i=0;i<max*max;i++){
	    cases[i].addActionListener(this);
	}
	for(int i=0;i<max*max;i++){
		jeu.add(cases[i]);
	}
  }*/
  
  //verification pour les indications
  public String verification(int x,int y){
	 int nbr=0;
	 
	 //coins haut droit 
	 if((x-1)<0 && (y+1)>=max ){
		if(infos1[x][y-1].equals("1")){
		  nbr++;   
	    }
	    if(infos1[x][y].equals("1")){
		  nbr++;   
		}
	    if(infos1[x+1][y-1].equals("1")){
		  nbr++;   
		}
	    if(infos1[x+1][y].equals("1")){
		  nbr++;   
	    } 
	 }
	 
	 //coins bas gauche 
     if((x+1)>=max && (y-1)<0 ){
    	if(infos1[x-1][y].equals("1")){
   		  nbr++;   
   	    }
   	    if(infos1[x-1][y+1].equals("1")){
   		  nbr++;   
   		}
   	    if(infos1[x][y].equals("1")){
   		  nbr++;   
   		}
   	    if(infos1[x][y+1].equals("1")){
   		  nbr++;   
   		} 
	 }

     // coins  haut gauche
	 if((x-1)<0 && (y-1)<0 ){
	    if(infos1[x][y].equals("1")){
		  nbr++;   
	    }
	    if(infos1[x][y+1].equals("1")){
			  nbr++;   
		}
	    if(infos1[x+1][y].equals("1")){
			  nbr++;   
		}
	    if(infos1[x+1][y+1].equals("1")){
			  nbr++;   
		}
	 }
	 
	 // coins bas droit
     if((x+1)>=max && (y+1)>=max ){
    	if(infos1[x-1][y-1].equals("1")){
   		  nbr++;   
   	    }
   	    if(infos1[x-1][y].equals("1")){
   		  nbr++;   
   		}
   	    if(infos1[x][y-1].equals("1")){
   		  nbr++;   
   		}
   	    if(infos1[x][y].equals("1")){
   		  nbr++;   
   		} 
	 }
	 
     //cases haut
	 if((x-1)<0 && (y-1)>=0 && (y+1)<max ){
		if(infos1[x][y-1].equals("1")){
 		  nbr++;   
	    }
	    if(infos1[x][y].equals("1")){
	   	  nbr++;   
	   	}
	    if(infos1[x][y+1].equals("1")){
	   	  nbr++;   
	   	}
	    if(infos1[x+1][y-1].equals("1")){
	   	  nbr++;   
	    }
	    if(infos1[x+1][y].equals("1")){
		   nbr++;   
		}
	    if(infos1[x+1][y+1].equals("1")){
		  nbr++;   
		}
	 }
	 
	 //cases gauche
     if((y-1)<0 && (x-1)>=0 && (x+1)<max ){
    	if(infos1[x-1][y].equals("1")){
    	   nbr++;   
   	    }
   	    if(infos1[x-1][y+1].equals("1")){
   	   	  nbr++;   
   	   	}
   	    if(infos1[x][y].equals("1")){
   	   	  nbr++;   
   	   	}
   	    if(infos1[x][y+1].equals("1")){
   	   	  nbr++;   
   	    }
   	    if(infos1[x+1][y].equals("1")){
   		   nbr++;   
   		}
   	    if(infos1[x+1][y+1].equals("1")){
   		  nbr++;   
   		} 
	 }
     
     //cases bas
     if((x+1)>=max && (y+1)<max && (y-1)>=0 ){
    	if(infos1[x-1][y-1].equals("1")){
     	   nbr++;   
   	    }
   	    if(infos1[x-1][y].equals("1")){
   	   	  nbr++;   
   	   	}
   	    if(infos1[x-1][y+1].equals("1")){
   	   	  nbr++;   
   	   	}
   	    if(infos1[x][y-1].equals("1")){
   	   	  nbr++;   
   	    }
   	    if(infos1[x][y].equals("1")){
   		   nbr++;   
   		}
        if(infos1[x][y+1].equals("1")){
     	  nbr++;   
     	} 
	 }
     
     //cases  droit
     if((y+1)>=max && (x+1)<max && (x+1)>1 ){
    	if(infos1[x-1][y-1].equals("1")){
      	   nbr++;   
   	    }
  	    if(infos1[x-1][y].equals("1")){
   	   	  nbr++;   
   	   	}
   	    if(infos1[x][y-1].equals("1")){
   	   	  nbr++;   
   	   	}
   	    if(infos1[x][y].equals("1")){
   	   	  nbr++;   
   	    }
   	    if(infos1[x+1][y-1].equals("1")){
   		   nbr++;   
   		}
        if(infos1[x+1][y].equals("1")){
     	  nbr++;   
     	} 
	 }
    
	 if((x-1)>=0 && (x+1)<max && (y-1)>=0 && (y+1)<max){
	    if(infos1[x-1][y-1].equals("1")){
		  nbr++;   
	    }
	    if(infos1[x-1][y].equals("1")){
		   nbr++;   
	    }
	    if(infos1[x-1][y+1].equals("1")){
		   nbr++;   
	    }
	    if(infos1[x][y-1].equals("1")){
		  nbr++;   
	    }
	    if(infos1[x][y].equals("1")){
		   nbr++;   
	    }
	    if(infos1[x][y+1].equals("1")){
		   nbr++;   
	    }
	    if(infos1[x+1][y-1].equals("1")){
		   nbr++;   
	    }
	    if(infos1[x+1][y].equals("1")){
		   nbr++;   
	    }
	    if(infos1[x+1][y+1].equals("1")){
		   nbr++;   
	    }
	 }   
	 return String.valueOf(nbr);	 
  }
  
  
  /*************************************************************************/
  public String[] verificationCase(int x,int y){
	  if(infos1[x][y].equals("0")){
     	String tab[]=new String[2];
	    tab[0]="";
		tab[1]="";
		//coins haut droit 
		if((x-1)<0 && (y+1)>=max ){		
			if(infos1[x][y-1].equals("0")){
			   if (infos2[x][y-1].equals("0")) {
			      tab[0]+=x;
			      tab[0]+=y-1;
			   }
			   else{
			   	  tab[1]+=x;
			      tab[1]+=y-1;
			   }  
		    }
		    if(infos1[x+1][y-1].equals("0")){
		    	if (infos2[x+1][y-1].equals("0")) {
			      tab[0]+=x+1;
			      tab[0]+=y-1;
			   }
			   else{
			   	  tab[1]+=x+1;
			      tab[1]+=y-1;
			   }   
			}
		    if(infos1[x+1][y].equals("0")){
		       if (infos2[x+1][y].equals("0")) {
			      tab[0]+=x+1;
			      tab[0]+=y;
			   }
			   else{
			   	  tab[1]+=x+1;
			      tab[1]+=y;
			   }   
		    }
		    return tab;
		 }
		 
		 //coins bas gauche 
	     if((x+1)>=max && (y-1)<0 ){
	    	if(infos1[x-1][y].equals("0")){
	    		if (infos2[x-1][y].equals("0")) {
			      tab[0]+=x-1;
			      tab[0]+=y;
			   }
			   else{
			   	  tab[1]+=x-1;
			   	  tab[1]+=y;
			   }      
	   	    }
	   	    if(infos1[x-1][y+1].equals("0")){
	   	       if (infos2[x-1][y+1].equals("0")) {
			      tab[0]+=x-1;
			      tab[0]+=y+1;
			   }
			   else{
			   	  tab[1]+=x-1;
			      tab[1]+=y+1;
			   }	
			}		
	   	    if(infos1[x][y+1].equals("0")){
	   	    	if (infos2[x][y+1].equals("0")) {
			      tab[0]+=x;
			      tab[0]+=y+1;
			   }
			   else{
			   	  tab[1]+=x;
			      tab[1]+=y+1;
			   }     	   
			   return tab;
		    }
		 }

	     // coins  haut gauche
		 if((x-1)<0 && (y-1)<0 ){
		    if(infos1[x][y+1].equals("0")){
		    	if (infos2[x][y+1].equals("0")) {
			      tab[0]+=x;
			      tab[0]+=y+1;
			   }
			   else{
			   	  tab[1]+=x;
			      tab[1]+=y+1;
			   }   
			}
		    if(infos1[x+1][y].equals("0")){
		    	if (infos2[x+1][y].equals("0")) {
			      tab[0]+=x+1;
			      tab[0]+=y;
			   }
			   else{
			   	  tab[1]+=x+1;
			      tab[1]+=y;
			   }   
			}
		    if(infos1[x+1][y+1].equals("0")){
		    	if (infos2[x+1][y+1].equals("0")) {
			      tab[0]+=x+1;
			      tab[0]+=y+1;
			   }
			   else{
			   	  tab[1]+=x+1;
			      tab[1]+=y+1;
			   }
			}
		    return tab;
		 }
		 //coins bas droit
	     if((x+1)>=max && (y+1)>=max ){
	    	if(infos1[x-1][y-1].equals("0")){
	    		if (infos2[x-1][y-1].equals("0")) {
			      tab[0]+=x-1;
			      tab[0]+=y-1;
			   }
			   else{
			   	  tab[1]+=x-1;
			      tab[1]+=y-1;
		       }
	    	}	
	   	    if(infos1[x-1][y].equals("0")){
	   	    	if (infos2[x-1][y].equals("0")) {
			      tab[0]+=x-1;
			      tab[0]+=y;
			    
			   }
			   else{
			   	  tab[1]+=x-1;
			      tab[1]+=y;
			   }
	   	    }	
	   	    if(infos1[x][y-1].equals("0")){
	   	    	if (infos2[x][y-1].equals("0")) {
			      tab[0]+=x;
			      tab[0]+=y-1;
			    
			   }
			   else{
			   	  tab[1]+=x;
			      tab[1]+=y-1;
			   }   
	   		} 
	   	    return tab;
	   	  }  
		  //cases haut
		 if((x-1)<0 && (y-1)>=0 && (y+1)<max ){
			if(infos1[x][y-1].equals("0")){
				if (infos2[x][y-1].equals("0")) {
			      tab[0]+=x;
			      tab[0]+=y-1;
			    
			   }
			   else{
			   	  tab[1]+=x;
			   	  tab[1]+=y-1;
			   }
		    }
		    if(infos1[x][y+1].equals("0")){
		    	if (infos2[x][y+1].equals("0")) {
			      tab[0]+=x;
			      tab[0]+=y+1;
			    
			   }
			   else{
			   	  tab[1]+=x;
			   	  tab[1]+=y+1;
			   }   
		   	}
		    if(infos1[x+1][y-1].equals("0")){
		    	if (infos2[x+1][y-1].equals("0")) {
			      tab[0]+=x+1;
			      tab[0]+=y-1;
			    
			   }
			   else{
			   	  tab[1]+=x+1;
			   	  tab[1]+=y-1;
			   }   
		    }
		    if(infos1[x+1][y].equals("0")){
		    	if (infos2[x+1][y].equals("0")) {
			      tab[0]+=x+1;
			      tab[0]+=y;
			    
			   }
			   else{
			   	  tab[1]+=x+1;
			   	  tab[1]+=y;
			   }  
			}
		    if(infos1[x+1][y+1].equals("0")){
		    	if (infos2[x+1][y+1].equals("0")) {
			      tab[0]+=x+1;
			      tab[0]+=y+1;
			    
			   }
			   else{
			   	  tab[1]+=x+1;
			   	  tab[1]+=y+1;
			   } 
			}
		    return tab;
		 }
		 
		 //cases gauche
	     if((y-1)<0 && (x-1)>=0 && (x+1)<max ){
	    	if(infos1[x-1][y].equals("0")){
	    		if (infos2[x-1][y].equals("0")) {
			      tab[0]+=x-1;
			      tab[0]+=y;
			      
			   }
			   else{
			   	  tab[1]+=x-1;
			      tab[1]+=y;
			   }  
	   	    }
	   	    if(infos1[x-1][y+1].equals("0")){
	   	    	if (infos2[x-1][y+1].equals("0")) {
			      tab[0]+=x-1;
			      tab[0]+=y+1;
			      
			   }
			   else{
			   	  tab[1]+=x-1;
			      tab[1]+=y+1;
			   }   
	   	   	}
	   	    if(infos1[x][y+1].equals("0")){
	   	    	if (infos2[x][y+1].equals("0")) {
			      tab[0]+=x;
			      tab[0]+=y+1;
			      
			   }
			   else{
			   	  tab[1]+=x;
			      tab[1]+=y+1;
			   }
	   	    }
	   	    if(infos1[x+1][y].equals("0")){
	   	    	if (infos2[x+1][y].equals("0")) {
			      tab[0]+=x+1;
			      tab[0]+=y;
			      
			   }
			   else{
			   	  tab[1]+=x+1;
			      tab[1]+=y;
			   }
	   		}
	   	    if(infos1[x+1][y+1].equals("0")){
	   	    	if (infos2[x+1][y+1].equals("0")) {
			      tab[0]+=x+1;
			      tab[0]+=y+1;
			      
			   }
			   else{
			   	  tab[1]+=x+1;
			      tab[1]+=y+1;
			   }
	   		} 
	   	    return tab;
		 }
	     
	     //cases bas
	     if((x+1)>=max && (y+1)<max && (y-1)>=0 ){
	    	if(infos1[x-1][y-1].equals("0")){
	    		if (infos2[x-1][y-1].equals("0")) {
			      tab[0]+=x-1;
			      tab[0]+=y-1;
		        }
			   else{
			   	  tab[1]+=x-1;
			      tab[1]+=y-1;
			   }
	   	    } 
	   	    if(infos1[x-1][y].equals("0")){
	   	    	if (infos2[x-1][y].equals("0")) {
			      tab[0]+=x-1;
			      tab[0]+=y;
			      
			   }
			   else{
			   	  tab[1]+=x-1;
			      tab[1]+=y;
			   }
	   	   	}
	   	    if(infos1[x-1][y+1].equals("0")){
	   	    	if (infos2[x-1][y+1].equals("0")) {
			      tab[0]+=x-1;
			      tab[0]+=y+1;
			      
			   }
			   else{
			   	  tab[1]+=x-1;
			      tab[1]+=y+1;
			   } 
	   	   	}
	   	    if(infos1[x][y-1].equals("0")){
	   	    	if (infos2[x][y-1].equals("0")) {
			      tab[0]+=x;
			      tab[0]+=y-1;
			      
			   }
			   else{
			   	  tab[1]+=x;
			      tab[1]+=y-1;
			   }
	   	    }
	        if(infos1[x][y+1].equals("0")){
	        	if (infos2[x][y+1].equals("0")) {
			      tab[0]+=x;
			      tab[0]+=y+1;
			      
			   }
			   else{
			   	  tab[1]+=x;
			      tab[1]+=y+1;
			   }   
	     	}
	        return tab;
		 }
	     
	     //cases  droit
	     if((y+1)>=max && (x+1)<max && (x+1)>1 ){
	    	if(infos1[x-1][y-1].equals("0")){
	    		if (infos2[x-1][y-1].equals("0")) {
			      tab[0]+=x-1;
			      tab[0]+=y-1;
	            }
			   else{
			   	  tab[1]+=x-1;
			      tab[1]+=y-1;
			   }   
	   	    }
	  	    if(infos1[x-1][y].equals("0")){
	  	    	if (infos2[x-1][y].equals("0")) {
			      tab[0]+=x-1;
			      tab[0]+=y;
			     
			   }
			   else{
			   	  tab[1]+=x-1;
			      tab[1]+=y;
			   }
	   	   	}
	   	    if(infos1[x][y-1].equals("0")){
	   	    	if (infos2[x][y-1].equals("0")) {
			      tab[0]+=x;
			      tab[0]+=y-1;
			     
			   }
			   else{
			   	  tab[1]+=x;
			      tab[1]+=y-1;
			   }   
	   	   	}
	   	    if(infos1[x+1][y-1].equals("0")){
	   	    	if (infos2[x+1][y-1].equals("0")) {
			      tab[0]+=x+1;
			      tab[0]+=y-1;
			     
			   }
			   else{
			   	  tab[1]+=x+1;
			      tab[1]+=y-1;
			   }
	   		}
	        if(infos1[x+1][y].equals("0")){
	        	if (infos2[x+1][y].equals("0")) {
			      tab[0]+=x+1;
			      tab[0]+=y;
			     
			   }
			   else{
			   	  tab[1]+=x+1;
			      tab[1]+=y;
			   }
	     	} 
	        return tab;
		 }
	    
		 if((x-1)>=0 && (x+1)<max && (y-1)>=0 && (y+1)<max){
		    if(infos1[x-1][y-1].equals("0")){
		    	if (infos2[x-1][y-1].equals("0")) {
			      tab[0]+=x-1;
			      tab[0]+=y-1;
			     
			   }
			   else{
			   	  tab[1]+=x-1;
			      tab[1]+=y-1;
			   }  
		    }
		    if(infos1[x-1][y].equals("0")){
		    	if (infos2[x-1][y].equals("0")) {
			      tab[0]+=x-1;
			      tab[0]+=y;
			    
			   }
			   else{
			   	  tab[1]+=x-1;
			      tab[1]+=y;
			   }
		    }
		    if(infos1[x-1][y+1].equals("0")){
		    	if (infos2[x-1][y+1].equals("0")) {
			      tab[0]+=x-1;
			      tab[0]+=y+1;
			    
			   }
			   else{
			   	  tab[1]+=x-1;
			      tab[1]+=y+1;
			   } 
		    }
		    if(infos1[x][y-1].equals("0")){
		    	if (infos2[x][y-1].equals("0")) {
			      tab[0]+=x;
			      tab[0]+=y-1;
			    
			   }
			   else{
			   	  tab[1]+=x;
			      tab[1]+=y-1;
			   }
		    }
		    if(infos1[x][y+1].equals("0")){
		    	if (infos2[x][y+1].equals("0")) {
			      tab[0]+=x;
			      tab[0]+=y+1;
			    
			   }
			   else{
			   	  tab[1]+=x;
			      tab[1]+=y+1;
			   }  
		    }
		    if(infos1[x+1][y-1].equals("0")){
		    	if (infos2[x+1][y-1].equals("0")) {
			      tab[0]+=x+1;
			      tab[0]+=y-1;
			    
			   }
			   else{
			   	  tab[1]+=x+1;
			      tab[1]+=y-1;
			   }  
		    }
		    if(infos1[x+1][y].equals("0")){
		    	if (infos2[x+1][y].equals("0")) {
			      tab[0]+=x+1;
			      tab[0]+=y;
			    
			   }
			   else{
			   	  tab[1]+=x+1;
			      tab[1]+=y;
			   }  
		    }
		    if(infos1[x+1][y+1].equals("0")){
		    	if (infos2[x+1][y+1].equals("0")) {
			      tab[0]+=x+1;
			      tab[0]+=y+1;
			    
			   }
			   else{
			   	  tab[1]+=x+1;
			      tab[1]+=y+1;
			   }  
		    }
		    return tab;
		 }
		 return null;
	  }
	  
	  if(infos1[x][y].equals("1")){
		 System.out.println("BOMBE");
		 return null;
	  }
	  if(infos1[x][y].equals("1")){
		 System.out.println("LBRE");
		 return null;
	  }
	  return null;
  }
	     
  public int correspondance(int x,int y){
	 int c=0,a=0,b=0;
	 for(;a<max;){
	     if(a==x && b==y){
	    	return c;   	 
	     }
		 b++;
		 c++;
		 if(b==max){
		    b=0;
		    a++;
		 }
		 //System.out.println("a:"+a+" b:"+b+" c"+c);
     }
     return c;
  }
  
  //1er fonction vider pour vider en temps réel  
  /*************************************************************************
  public void vider(int x,int y){
	  int i=0;
	  //System.out.println("ok x:"+x+" y:"+y+" "+infos1[x][y]);
		  if(infos1[x][y].equals("0")){
			//coins haut droit
			if((x-1)<0 && (y+1)>=max ){
				System.out.println("ok x:"+x+" y:"+y+" "+infos2[x][y]);
				if(infos1[x][y-1].equals("0")){
					cases[this.correspondance(x,y-1)].setText(infos2[x][y-1]);
					System.out.println("ok1");
					this.vider(x, y-1);
			    }
			    if(infos1[x+1][y-1].equals("0")){
			    	cases[this.correspondance(x+1,y-1)].setText(infos2[x+1][y-1]);
			    	System.out.println("ok2");
			    	this.vider(x+1, y-1);
				}
			    if(infos1[x+1][y].equals("0")){
			    	cases[this.correspondance(x+1,y)].setText(infos2[x+1][y]);
			    	System.out.println("ok3");
			    	this.vider(x+1, y);
			    }
			    
			 }
			 
			 //coins bas gauche 
		     if((x+1)>=max && (y-1)<0 ){
		    	if(infos1[x-1][y].equals("0")){
		    		cases[this.correspondance(x-1,y)].setText(infos2[x-1][y]);
		    		this.vider(x-1, y);
		   	    }
		   	    if(infos1[x-1][y+1].equals("0")){
		   	    	cases[this.correspondance(x-1,y+1)].setText(infos2[x-1][y+1]);
		   	    	this.vider(x-1, y+1);
		   		}
		   	    if(infos1[x][y+1].equals("0")){
		   	    	cases[this.correspondance(x,y+1)].setText(infos2[x][y+1]);
		   	    	this.vider(x, y+1);
		   		}
			 }

		     // coins  haut gauche
			 if((x-1)<0 && (y-1)<0 ){
			    if(infos1[x][y+1].equals("0")){
			    	cases[this.correspondance(x,y+1)].setText(infos2[x][y+1]);
			    	this.vider(x, y+1);
				}
			    if(infos1[x+1][y].equals("0")){
			    	cases[this.correspondance(x+1,y)].setText(infos2[x+1][y]);
			    	this.vider(x+1, y);
				}
			    if(infos1[x+1][y+1].equals("0")){
			    	cases[this.correspondance(x+1,y+1)].setText(infos2[x+1][y+1]);
			    	this.vider(x+1, y+1);
				}
			 }
			 
			 // coins bas droit
		     if((x+1)>=max && (y+1)>=max ){
		    	if(infos1[x-1][y-1].equals("0")){
		    		cases[this.correspondance(x-1,y-1)].setText(infos2[x-1][y-1]);
		    		this.vider(x-1, y-1);
		   	    }
		   	    if(infos1[x-1][y].equals("0")){
		   	    	cases[this.correspondance(x-1,y)].setText(infos2[x-1][y]);
		   	    	this.vider(x-1, y);
		   		}
		   	    if(infos1[x][y-1].equals("0")){
		   	    	cases[this.correspondance(x,y-1)].setText(infos2[x][y-1]);
		   	    	this.vider(x, y-1);
		   		}
			 }
			 
		     //cases haut
			 if((x-1)<0 && (y-1)>=0 && (y+1)<max ){
				if(infos1[x][y-1].equals("0")){
					cases[this.correspondance(x,y-1)].setText(infos2[x][y-1]);/////////////////////////////
					this.vider(x, y-1);/////////////////////////////////////////////////////////////////////
			    }
			    if(infos1[x][y+1].equals("0")){
			    	cases[this.correspondance(x,y+1)].setText(infos2[x][y+1]); 
			    	this.vider(x, y+1);/////////////////////////////////////////////////////////////////////
			   	}
			    if(infos1[x+1][y-1].equals("0")){
			    	cases[this.correspondance(x+1,y-1)].setText(infos2[x+1][y-1]);
			    	this.vider(x+1, y-1);
			    }
			    if(infos1[x+1][y].equals("0")){
			    	cases[this.correspondance(x+1,y)].setText(infos2[x+1][y]);
			    	this.vider(x+1, y);
				}
			    if(infos1[x+1][y+1].equals("0")){
			    	cases[this.correspondance(x+1,y+1)].setText(infos2[x+1][y+1]);
			    	this.vider(x+1, y+1);
				}
			 }
			 
			 //cases gauche
		     if((y-1)<0 && (x-1)>=0 && (x+1)<max ){
		    	if(infos1[x-1][y].equals("0")){
		    		cases[this.correspondance(x-1,y)].setText(infos2[x-1][y]);
		    		this.vider(x-1, y);
		   	    }
		   	    if(infos1[x-1][y+1].equals("0")){
		   	    	cases[this.correspondance(x-1,y+1)].setText(infos2[x-1][y+1]);
		   	    	this.vider(x-1, y+1);
		   	   	}
		   	    if(infos1[x][y+1].equals("0")){
		   	    	cases[this.correspondance(x,y+1)].setText(infos2[x][y+1]);
		   	    	this.vider(x, y+1);
		   	    }
		   	    if(infos1[x+1][y].equals("0")){
		   	    	cases[this.correspondance(x+1,y)].setText(infos2[x+1][y]);
		   	    	this.vider(x+1, y);
		   		}
		   	    if(infos1[x+1][y+1].equals("0")){
		   	    	cases[this.correspondance(x+1,y+1)].setText(infos2[x+1][y+1]);
		   	    	this.vider(x+1, y+1);
		   		} 
			 }
		     
		     //cases bas
		     if((x+1)>=max && (y+1)<max && (y-1)>=0 ){
		    	if(infos1[x-1][y-1].equals("0")){
		    		cases[this.correspondance(x-1,y-1)].setText(infos2[x-1][y-1]);
		    		this.vider(x-1, y-1);
		   	    }
		   	    if(infos1[x-1][y].equals("0")){
		   	    	cases[this.correspondance(x-1,y)].setText(infos2[x-1][y]);
		   	    	this.vider(x-1, y);
		   	   	}
		   	    if(infos1[x-1][y+1].equals("0")){
		   	    	cases[this.correspondance(x-1,y+1)].setText(infos2[x-1][y+1]);
		   	    	this.vider(x-1, y+1);
		   	   	}
		   	    if(infos1[x][y-1].equals("0")){
		   	    	cases[this.correspondance(x,y-1)].setText(infos2[x][y-1]);
		   	    	this.vider(x, y-1);
		   	    }
		        if(infos1[x][y+1].equals("0")){
		        	cases[this.correspondance(x,y+1)].setText(infos2[x][y+1]);
		        	this.vider(x, y+1);
		     	}
			 }
		     
		     //cases  droit
		     if((y+1)>=max && (x+1)<max && (x+1)>1 ){
		    	if(infos1[x-1][y-1].equals("0")){
		    		cases[this.correspondance(x-1,y-1)].setText(infos2[x-1][y-1]);  
		    		this.vider(x-1, y-1);
		   	    }
		  	    if(infos1[x-1][y].equals("0")){
		  	    	cases[this.correspondance(x-1,y)].setText(infos2[x-1][y]);
		  	    	this.vider(x-1, y);
		   	   	}
		   	    if(infos1[x][y-1].equals("0")){
		   	    	cases[this.correspondance(x,y-1)].setText(infos2[x][y-1]);
		   	    	this.vider(x, y-1);
		   	   	}
		   	    if(infos1[x+1][y-1].equals("0")){
		   	    	cases[this.correspondance(x+1,y-1)].setText(infos2[x+1][y-1]);
		   	    	this.vider(x+1, y-1);
		   		}
		        if(infos1[x+1][y].equals("0")){
		        	cases[this.correspondance(x+1,y)].setText(infos2[x+1][y]);
		        	this.vider(x+1, y);
		     	} 
			 }
		    
			 if((x-1)>=0 && (x+1)<max && (y-1)>=0 && (y+1)<max){
			    if(infos1[x-1][y-1].equals("0")){
			    	cases[this.correspondance(x-1,y-1)].setText(infos2[x-1][y-1]);
			    	this.vider(x-1, y-1);
			    }
			    if(infos1[x-1][y].equals("0")){
			    	cases[this.correspondance(x-1,y)].setText(infos2[x-1][y]);
			    	this.vider(x-1, y);
			    }
			    if(infos1[x-1][y+1].equals("0")){
			    	cases[this.correspondance(x-1,y+1)].setText(infos2[x-1][y+1]);
			    	this.vider(x-1, y+1);
			    }
			    if(infos1[x][y-1].equals("0")){
			    	cases[this.correspondance(x,y-1)].setText(infos2[x][y-1]);
			    	this.vider(x, y-1);
			    }
			    if(infos1[x][y+1].equals("0")){
			    	cases[this.correspondance(x,y+1)].setText(infos2[x][y+1]);
			    	this.vider(x, y+1);
			    }
			    if(infos1[x+1][y-1].equals("0")){
			    	cases[this.correspondance(x+1,y-1)].setText(infos2[x+1][y-1]);
			    	this.vider(x+1, y-1);
			    }
			    if(infos1[x+1][y].equals("0")){
			    	cases[this.correspondance(x+1,y)].setText(infos2[x+1][y]);
			    	this.vider(x+1, y);
			    }
			    if(infos1[x+1][y+1].equals("0")){
			    	cases[this.correspondance(x+1,y+1)].setText(infos2[x+1][y+1]);
			    	this.vider(x+1, y+1);
			    }
			 }
		  }
	   
  }
  /******************************************************************************************/
  public void revele(String[] mot){
	 String temp3,temp4; 
	 for(int a=0;a<mot[0].length();a+=2){
		 temp3=mot[0].substring(a,a+1);
		 temp4=mot[0].substring(a+1,a+2);
		 cases[this.correspondance(Integer.parseInt(temp3),Integer.parseInt(temp4))].setText(infos2[Integer.parseInt(temp3)][Integer.parseInt(temp4)]);
	 }
	 for(int a=0;a<mot[1].length();a+=2){
		 temp3=mot[1].substring(a,a+1);
		 temp4=mot[1].substring(a+1,a+2);
		 cases[this.correspondance(Integer.parseInt(temp3),Integer.parseInt(temp4))].setText(infos2[Integer.parseInt(temp3)][Integer.parseInt(temp4)]);
	 }
  }
  
  public String doublons(String mot){
	 String mot1="",mot2="";
	 String tabMot[]=new String[mot.length()/2];
	 for(int a=0,b=0;a<mot.length();a+=2,b++){
		tabMot[b]=mot.substring(a,a+2);
	 }
	 for(int b=0;b<tabMot.length;b++){
		mot1=tabMot[b];
		for(int a=b+1;a<tabMot.length;a++){
		   if(mot1.equals(tabMot[a])){
			  tabMot[a]="";  
		   }
		}
	 }
	 for(int b=0;b<tabMot.length;b++){
		 if(!tabMot[b].equals("")){
			  mot2+=tabMot[b];  
		 }
	 }
	 /*System.out.println("\n mot:");
	 for(int b=0;b<tabMot.length;b++){
		System.out.print(tabMot[b]+" ");
	 }*/
	 //System.out.println(mot2);
	 return mot2;
  }
  
  public void actionPerformed(ActionEvent e){
	int taille=0;
	String tab[]=new String[2];
	String temp1,temp2;
	String temptab[];
	tab[0]=tab[1]="";
	boolean arret=true;
	for (int i=0,x=0,y=0;i<max*max;i++) {
   	    if (e.getSource()==cases[i]) {
   	    	//System.out.println(i);
   	    	if(infos1[x][y].equals("1")){
   	    	  cases[i].setText("BOMBE");
   	    	  Over session=new Over();
   	    	}
   	    	if(infos1[x][y].equals("0")){
   	    	  if(infos2[x][y].equals("0")){	
	     	      cases[i].setText(infos2[x][y]);
	     	      System.out.println("x="+x+" y="+y);
	     	      //this.vider(x,y);///////////////////////////////////////////////
	     	      temptab=this.verificationCase(x,y);
	     	      tab[1]+=temptab[1];
	     	      tab[0]+=temptab[0];
	     	      taille=tab[0].length();             
	     	      /*for(int u=0;u<tab.length();u+=2){
	  	    	     System.out.print(tab.charAt(u)+""+tab.charAt(u+1)+"    "); 
	  	          }*/
	     	      while(arret==true){
		     	      for(int u=0;u<taille;u+=2){
		     	    	 temp1=tab[0].substring(u,u+1);
		     	    	 temp2=tab[0].substring(u+1,u+2);
		     	    	 temptab=this.verificationCase(Integer.parseInt(temp1),Integer.parseInt(temp2));
		     	    	 tab[0]+=temptab[0];
		     	    	 tab[1]+=temptab[1];
		     	    	 //tab+=this.verificationCase(1,3);
		     	      }
		     	      tab[1]=this.doublons(tab[1]);//pour enlever les doublons
		     	      tab[0]=this.doublons(tab[0]);
		     	      if(tab[0].length()>taille){
		     	    	  taille=tab[0].length();
		     	    	  arret=true;
		     	      }
		     	      else{
		     	    	  arret=false;  
		     	      }
	     	      }
	     	      /*System.out.println();
	     	      for(int u=0;u<tab.length();u+=2){
	     	    	 System.out.print(tab.charAt(u)+""+tab.charAt(u+1)+"   "); 
	     	      }*/
   	    	   }
   	    	   else{
   	    		  cases[i].setText(infos2[x][y]); 
   	    	   }
	     	} 
   	    	this.revele(tab);
   	    }
   	    y++;
   	    if(y==max){
   	       x++;
   	       y=0;
   	    }
	}    
  }
  
  public static void main(String []args){
	 Jeux jeux=new Jeux();
	 jeux.debut();
	 /*int nbr=jeux.correspondance(2,4);
	 System.out.println("case:"+nbr);
	 /*char[] tab=jeux.verificationCase(0,1);
	 try{
		for(int i=0;i<tab.length;i++){
		   System.out.println(tab[i]);
		} 
	 }
	 catch(Exception e){
		System.out.println("erreur"); 
	 }
	 System.out.println("\n\n");
	 tab=jeux.verificationCase(0,1);
	 try{
		for(int i=0;i<tab.length;i++){
		   System.out.println(tab[i]);
		} 
	 }
	 catch(Exception e){
		System.out.println("erreur"); 
	 }*/
  }
}
