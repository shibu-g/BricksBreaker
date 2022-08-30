package BrickBreaker;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
public class Main {
 static JPanel p1;
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		Panels pe=new Panels();
		//p1=new JPanel();
		//p1.setBounds(10, 10, 50, 50);
		//p1.setBackground(Color.black);
		//frame.add(p1);
		frame.add(pe);
        frame.setBounds(10,10,700,600);
        frame.setTitle("Brick Breaker");
       // frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		//Panal p=new Panal();
		frame.setResizable(false);
		//frame.add(p);
		
	}
}
	class Panels extends JPanel implements ActionListener,KeyListener {
		private boolean play=false;
		private int score=0;
		private int totalbrick=27;
		private Timer timer;
		private int ballpointx=120;
		private int ballpointy=350;
		private int ballxdir=-1;
		private int ballydir=-2;
		private int playerx=350;
		private int delay=-100;
		private bricks b1;
		private boolean gameover=false;
		
		
		public Panels(){
			b1=new bricks(3,9);
			addKeyListener(this);
			   setFocusable(true);
			   this.setFocusTraversalKeysEnabled(false);
			   timer=new Timer(delay,this);
			   timer.start();
		}
		public void paint(Graphics g) {
			
			g.setColor(Color.black);
			g.fillRect(1, 1, 692, 592);
		
			
			//draw bricks
			b1.draw((Graphics2D)g);
			
			//boarder
			 g.setColor(Color.yellow);
			  g.fillRect(0, 0, 3, 592);
			  g.fillRect(0, 0, 692, 3);
			  g.fillRect(682, 0, 3, 592);
			//SCORE
			  g.setColor(Color.white);
			  g.setFont(new Font("Arial",Font.BOLD,25));
			  g.drawString(""+score, 590, 30);
			  
			  //creat paddle
			  g.setColor(Color.GREEN);
			  g.fillRect(playerx, 550, 100, 10);
			  
			  //ball
			  g.setColor(Color.yellow);
			  g.fillOval(ballpointx, ballpointy, 20, 20);
			
			  //
				if(ballpointy>=555) {
					  play=false;
					  ballxdir=0;
					  ballydir=0;
					  g.setColor(Color.RED);
					  g.setFont(new Font("Arial",Font.BOLD,25));
					  g.drawString("GameOver !! Score is :"+score, 250, 250);
					  g.setColor(Color.WHITE);
					  g.drawString("Press Enter To Restart", 250, 280);
				  }
			  if(totalbrick<=0) {
				  play=false;
				  ballxdir=0;
				  ballydir=0;
				  g.setColor(Color.green);
				  g.setFont(new Font("Arial",Font.BOLD,25));
				  g.drawString("Congratulations you Completed The Game", 230, 250);
				  g.drawString("Press Enter To Restart", 230, 280);
			  }
			  g.dispose();
			  
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			//if ball intersect with paddle
			timer.start();
			if(play){
				if(new Rectangle(ballpointx,ballpointy,20,20).intersects(new Rectangle(playerx,550,100,8))) {
					ballydir=-ballydir;
				}
				//if ball touch the bricks
				A: for(int i=0;i<b1.map.length;i++) {
					for(int j=0;j<b1.map[0].length;j++) {
						if(b1.map[i][j]>0) {
							int brickx=j*b1.brickwidth+80;
							int bricky=i*b1.brickhight+50;
							int brickwidth=b1.brickwidth;
							int brickhight=b1.brickhight;
							Rectangle rect=new Rectangle(brickx,bricky,brickwidth,brickhight);
							Rectangle ballrect=new Rectangle(ballpointx,ballpointy,20,20);
							Rectangle brickrect=rect;
							if(ballrect.intersects(brickrect)) {
								b1.setbrickvalue(0, i, j);
								totalbrick--;
								
								score+=5;
								if(ballpointx+19<=brickrect.x||ballpointx+1>=brickrect.width) {
									ballxdir=-ballxdir;
								}
								else{
									ballydir=-ballydir;
								}
								break A;
							}
						}
					}
					
				}
			}
			if(play) {
				ballpointx+=ballxdir;
				ballpointy+=ballydir;
				if(ballpointx<0) {
					ballxdir=-ballxdir;
				}
				if(ballpointy<0) {
					ballydir=-ballydir;
				}
				if(ballpointx>670) {
					ballxdir=-ballxdir;
				}
			}
			repaint();
		}
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
				if(playerx>=596) {
					playerx=596;
				}
				else {
					moveright();
				}
			}
				if(e.getKeyCode()==KeyEvent.VK_LEFT) {
					if(playerx<=3) {
						playerx=3;
					}
					else {
						moveleft();
					}
			  }
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					if(!play){
						score=0;
						totalbrick=27;
						ballpointx=120;
						ballpointy=350;
						ballxdir=-1;
						ballydir=-2;
						playerx=320;
						b1=new bricks(3,9);
					}
				}
		}
		public void moveright() {
			play=true;
			playerx+=20;
		}
		public void moveleft() {
			play=true;
			playerx-=20;
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	