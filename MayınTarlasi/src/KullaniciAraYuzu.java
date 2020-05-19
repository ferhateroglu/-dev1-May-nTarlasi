import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class KullaniciAraYuzu extends JFrame {
	ImageIcon smile = new ImageIcon("C:\\Users\\ferha\\eclipse-workspace\\Java-projeleri\\MayinTarlasi\\smile.png");
	ImageIcon sad = new ImageIcon("C:\\Users\\ferha\\eclipse-workspace\\Java-projeleri\\MayinTarlasi\\sad.png");
	ImageIcon flag = new ImageIcon("C:\\\\Users\\\\ferha\\\\eclipse-workspace\\\\Java-projeleri\\\\MayinTarlasi\\flag.png");

	public boolean resetter = false;
	public boolean flagger = false;
	
	Date startDate = new Date();
	Date endDate;
	
	int spacing = 5;
	
	int neighs = 0;
	
	String vicMes = "Yeterli Değil";
	
	public int mx = -100;
	public int my = -100;
	
	public int smilayX = 605;
	public int smilayY = 5;
	public int smilayCenterX = smilayX+45;
	public int smilayCenterY = smilayY+60;
	
	public int flaggerX = 445;
	public int flaggerY = 5;
	public int flaggerCenterX = flaggerX + 35;
	public int flaggerCenterY = flaggerY + 35 ;

	
	public int timeX = 1130;
	public int timeY = 5;
	
	public int vicMesX = 750;
	public int vicMesY = -50;

	
	public int sec = 0;


	public boolean happines = true;
	public boolean kazanmak = false;
	public boolean kaybetmek = false;

	
	Random rand = new Random();
	
	int[][] mayinlar = new int[16][9];
	int[][] mayinCevresi = new int[16][9];
	boolean[][] mayinBul = new boolean[16][9];
	boolean[][] bayrak = new boolean[16][9];
	
	public KullaniciAraYuzu() {		
		this.setTitle("Mayın Tarlası");
		this.setSize(1286,829);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		
		for(int i = 0; i < 16; i++) {
			for(int j = 0; j < 9; j++) {
				if(rand.nextInt(200)<15) {
					mayinlar[i][j] = 1;
				}
				else {
					mayinlar[i][j] = 0;
				}
				mayinBul[i][j] = false;
			}
		}	
		
		for(int i = 0; i < 16; i++) {
			for(int j = 0; j < 9; j++) {
				neighs = 0;
				for(int m = 0; m < 16; m++) {
					for(int n = 0; n < 9; n++) {
						if(!(m == i && n == j)) {
						if(isN(i,j,m,n) == true) {
							neighs++;
							}
						}
					}
				}
				mayinCevresi[i][j] = neighs;
			}
		}	
		
		Board board = new Board();
		this.setContentPane(board);
		
		MouseHareketi move = new MouseHareketi();
		this.addMouseMotionListener(move);
		
		Tikla click = new Tikla();
		this.addMouseListener(click);
	}
	public class Board extends JPanel{
		
		public void paintComponent(Graphics g) {
			g.setColor(Color.gray);
			g.fillRect(0, 0, 1280, 800);
			 for(int i = 0; i < 16; i++) {
				for(int j = 0; j < 9; j++) {
					g.setColor(Color.lightGray);	
					
					if(mayinlar[i][j] == 1) {
						//g.setColor(Color.red);	
					}
					if(mayinBul[i][j] == true) {
						g.setColor(Color.white);			
						if(mayinlar[i][j] == 1) {
							g.setColor(Color.red);
						}
					}
					if(mx >= spacing+i*80 && mx < spacing+i*80+80-2*spacing && my >= spacing+j*80+80+26 && my < spacing+j*80+26+80+80-2*spacing) {
						g.setColor(Color.decode("#D6D6D6"));
					} 
					g.fillRect(spacing+i*80, spacing+j*80+80 , 80-2*spacing, 80-2*spacing);
					
					if(mayinBul[i][j] == true ) {
						g.setColor(Color.black);
						if(mayinlar[i][j] == 0 && mayinCevresi[i][j] != 0) {
							if(mayinCevresi[i][j] == 1) {
								g.setColor(Color.blue);	
							}
							else if(mayinCevresi[i][j] == 2){
								g.setColor(Color.GREEN);	
							}
							else if(mayinCevresi[i][j] == 3){
								g.setColor(Color.red);	
							}
							else if(mayinCevresi[i][j] == 4){
								g.setColor(new Color(0,0,128));	
							}
							else if(mayinCevresi[i][j] == 5){
								g.setColor(new Color(178,34,34));	
							}
							else if(mayinCevresi[i][j] == 6){
								g.setColor(new Color(72,209,204));	
							}
							else if(mayinCevresi[i][j] == 8){
								g.setColor(Color.DARK_GRAY);	
							}
							
							g.setFont(new Font("tahoma", Font.BOLD, 40));
							g.drawString(Integer.toString(mayinCevresi[i][j]), i*80+27, j*80+80+55);
						}
						else if(mayinlar[i][j] == 1){
							g.fillRect(spacing+i*80+10+20, spacing+j*80+80+20 , 20, 40);
							g.fillRect(spacing+i*80+20, spacing+j*80+80+10+20 , 40, 20);
							g.fillRect(spacing+i*80+5+20, spacing+j*80+80+5+20 , 30, 30);
							g.fillRect(spacing+i*80+38, spacing+j*80+80+15 , 4, 50);
							g.fillRect(spacing+i*80+15, spacing+j*80+80+38 , 50, 4);

						}
					}
				}
			}
			 
			 if(happines == true) {
				 g.drawImage(smile.getImage(),605,5,80,80,null);
			 }
			 else {
				 g.drawImage(sad.getImage(),605,5,85,85,null);
		     }
			 
			 //flager paint
			 g.drawImage(flag.getImage(),flaggerX+17,flaggerY+10,50,50,null);
			 g.setColor(Color.lightGray);
			 if(flagger == true) {
				 g.setColor(Color.black);
			 }
			 
				if(flagger == true) {
					g.setColor(Color.red);
				}
			 g.drawOval(flaggerX, flaggerY, 70, 70);
			 g.drawOval(flaggerX+1, flaggerY+1, 68, 68);
			 g.drawOval(flaggerX+1, flaggerY+1, 67, 67);
			 g.drawOval(flaggerX+2, flaggerY+2, 66, 66);
			 g.drawOval(flaggerX+2, flaggerY+2, 67, 67);

			 g.setColor(Color.black);
			 g.fillRect(timeX, timeY, 140, 70);
			 if(kaybetmek == false && kazanmak == false) {
				 sec = 999 - ((int)(new Date().getTime()-startDate.getTime())/1000);
			 }
			 if(sec <= 0) {
				 sec = 0;
				 happines = false;
			 }
			 g.setColor(Color.white);
			 if(kazanmak == true) {
				 g.setColor(Color.green);
			 }
			 else if(kaybetmek == true) {
				 g.setColor(Color.red);
				 
			 }
			 g.setFont(new Font("tahoma",Font.PLAIN,80));
			 g.drawString(Integer.toString(sec), timeX, timeY+65);
			 
			 if(kazanmak == true) {
				 g.setColor(Color.green);
				 vicMes = "KAZANDIN!";
			 }
			 else if(kaybetmek == true) {
				 g.setColor(Color.red);
				 vicMes = "KAYBETTİN!";	 
			 }
			 if(kazanmak == true || kaybetmek == true) {
				 vicMesY = -50 +(int) (new Date().getTime() - endDate.getTime()) / 10;
				 if(vicMesY > 65) {
					 vicMesY = 65;
				 }
				 g.setFont(new Font("tahoma", Font.PLAIN,60));
				 g.drawString(vicMes, vicMesX,vicMesY);
			 }
		}
	}
	public class MouseHareketi implements MouseMotionListener{

		public void mouseDragged(MouseEvent arg0) {		
			
		}

		public void mouseMoved(MouseEvent e) {	
			
			mx = e.getX();
			my = e.getY();
			//System.out.println("x: "+mx+" y: "+my);
		}
		
	}
	public class Tikla implements MouseListener{

		public void mouseClicked(MouseEvent e) {

			if(inBoxX() != -1 && inBoxY() != -1) {
			    System.out.println("mousa kutucukta: "+inBoxX()+","+inBoxY()+" Number of mine neighs: " + mayinCevresi[inBoxX()][inBoxY()]);
			    System.out.println("mayin değeri: "+mayinCevresi[inBoxX()][inBoxY()]);
			    if(flagger == true && mayinBul[inBoxX()][inBoxY()] == false) {
			    	if(bayrak[inBoxX()][inBoxY()] = false) {
			    		bayrak[inBoxX()][inBoxY()] = true;
			    	}
			    	else {
			    		bayrak[inBoxX()][inBoxY()] = false;
			    	}
			    }
			    else {
			    	mayinBul[inBoxX()][inBoxY()] = true;
			    }
			}

			if(inSmiley() == true) {
				resetAll();
			}
			if(inFlagger() == true) {
				if(flagger == false) {
					flagger = true;
					System.out.println("In flagger = "+flagger);
				}
				else {
					flagger = false;
					System.out.println("In flagger = "+flagger);

				}
			}
		}

		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void checkVictoryStatus() {
		if(kaybetmek == false) {
			for(int i = 0; i < 16; i++) {
				for(int j = 0; j < 9; j++) {
					if(mayinBul[i][j] == true && mayinlar[i][j] == 1) {
						kaybetmek = true;
						happines = false;
						endDate = new Date();
					}
				}
			}
		}
		if(totalBoxesRevealed() >= 144 - totalMines() && kazanmak == false) {
			kazanmak = true;
			endDate = new Date();

		}
	}
	
	public int totalMines() {
		int total = 0;
		for(int i = 0; i < 16; i++) {
			for(int j = 0; j < 9; j++) {
				if(mayinlar[i][j] == 1) {
					total++;
				}
			}
		}	
		return total;
	}
	
	public int totalBoxesRevealed() {
		int total = 0;
		for(int i = 0; i < 16; i++) {
			for(int j = 0; j < 9; j++) {
				if(mayinBul[i][j] == true) {
					total++;
				}
			}
		}		
		return total;		
	}
	
	public void resetAll() {
		
		resetter = true;
		flagger = false;
		startDate = new Date();
		vicMesY = -50;
		vicMes = "Yeterli Değil";
		happines = true;
		kazanmak = false;
	    kaybetmek = false;

	    
	    
	    for(int i = 0; i < 16; i++) {
			for(int j = 0; j < 9; j++) {
				if(rand.nextInt(100)<7) {
					mayinlar[i][j] = 1;
				}
				else {
					mayinlar[i][j] = 0;
				}
				mayinBul[i][j] = false;
				bayrak[i][j ] = false;
			}
		}	
		
		for(int i = 0; i < 16; i++) {
			for(int j = 0; j < 9; j++) {
				neighs = 0;
				for(int m = 0; m < 16; m++) {
					for(int n = 0; n < 9; n++) {
						if(!(m == i && n == j)) {
						if(isN(i,j,m,n) == true) {
							neighs++;
							}
						}
					}
				}
				mayinCevresi[i][j] = neighs;
			}
		}	
		resetter = false;
	}
	
	public boolean inSmiley() {
		int dif = (int)Math.sqrt(Math.abs(mx-smilayCenterX)*Math.abs(mx-smilayCenterX)+Math.abs(my-smilayCenterY)*Math.abs(my-smilayCenterY));
		if(dif < 35) {
			return true;
		}
		return false;
	}
	
	public boolean inFlagger() {
		int dif = (int)Math.sqrt(Math.abs(mx-flaggerCenterX)*Math.abs(mx-flaggerCenterX)+Math.abs(my-flaggerCenterY)*Math.abs(my-flaggerCenterY));
		if(dif < 35) {
			return true;
		}
		return false;
	}
	public int inBoxX() {
		
		for(int i = 0; i < 16; i++) {
			for(int j = 0; j < 9; j++) {					
				if(mx >= spacing+i*80 && mx < spacing+i*80+80-2*spacing && my >= spacing+j*80+80+26 && my < spacing+j*80+26+80+80-2*spacing) {
					return i;
				} 
			}
		}
		return -1;
	}
	public int inBoxY() {
		
		for(int i = 0; i < 16; i++) {
			for(int j = 0; j < 9; j++) {					
				if(mx >= spacing+i*80 && mx < spacing+i*80+80-2*spacing && my >= spacing+j*80+80+26 && my < spacing+j*80+26+80+80-2*spacing) {
					return j;
				} 
			}
		}
		return -1;	
	}
	public boolean isN(int mX, int mY, int cX, int cY) {
		if(mX - cX < 2 && mX - cX > -2 && mY - cY < 2 && mY - cY > -2 && mayinlar[cX][cY] ==1) {
			return true;
		}
		return false;
	}
}
