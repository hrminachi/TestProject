import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Gameplay extends JPanel implements KeyListener,ActionListener {
	
	private int[] snakexlenght = new int[750];
	private int[] snakeylenght = new int[750];
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private ImageIcon rightmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	private ImageIcon leftmouth;
	
	private int lenghtofsnake = 100;
	
	private Timer timer;
	private int delay = 100;
	private ImageIcon snakeimage;
	
	private int [] foodxpos = {25,50,75,100,
			125,150,175,200,225,250,275,
			300,325,350,375,400,425,450,475,500,
			525,550,575,600,625,650,675,700,725,
			750,775,800,825,850};
	
	private int [] foodypos = {75,100,
			125,150,175,200,225,250,275,
			300,325,350,375,400,425,450,475,500,
			525,550,575,600,625};
	
	private ImageIcon foodimage;
	
	private Random random = new Random();
	
	private int xpose = random.nextInt(34);
	private int ypose = random.nextInt(23);
	
	private int score =0;
	
	private int moves =0;
	
	private ImageIcon titleImage;
	
	public Gameplay () {
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer (delay,this);
		timer.start();
		
	}
	
	public void paint(Graphics g) 
	{
		if (moves == 0) 
		{
			snakexlenght[20] = 50;
			snakexlenght[1] = 75;
			snakexlenght[0] = 100;
			
			snakeylenght[2] = 100;
			snakeylenght[1] = 100;
			snakeylenght[0] = 100;
			
			
			
		}
		
		//draw title image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		
		// draw the title image
		titleImage = new ImageIcon("banner.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		//draw border for gameplay
		g.setColor(Color.WHITE);
		g.drawRect(24,74, 851, 575);
		
		//draw background for the gameplay
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		rightmouth = new ImageIcon("snake-right.png");
		rightmouth.paintIcon(this, g,snakexlenght[0], snakeylenght[0]);
		
		// draw scores
		g.setColor(Color.ORANGE);
		g.setFont(new Font("areal",Font.BOLD,20));
		g.drawString("Scores: "+score, 600, 35);
		
		// draw lenght 
		g.setColor(Color.ORANGE);
		g.setFont(new Font("areal",Font.BOLD,20));
		g.drawString("Lenght of Snake: "+lenghtofsnake, 600, 60);
		
		
		rightmouth = new ImageIcon("snake-right.png");
		rightmouth.paintIcon(this, g, snakexlenght[0], snakeylenght[0]);
		for (int a = 0; a < lenghtofsnake; a++) 
		{
			if (a==0 && right) 
			{
				rightmouth = new ImageIcon("snake-right.png");
				rightmouth.paintIcon(this, g,snakexlenght[a], snakeylenght[a]);
				
			}
			
		}
		for (int a = 0; a < lenghtofsnake; a++) 
		{
			if (a==0 && left) 
			{
				leftmouth = new ImageIcon("snake-left.png");
				leftmouth.paintIcon(this, g,snakexlenght[a], snakeylenght[a]);
				
			}
			
		}
		for (int a = 0; a < lenghtofsnake; a++) {
			if (a==0 && down) {
				downmouth = new ImageIcon("snake-down.png");
				downmouth.paintIcon(this, g,snakexlenght[a], snakeylenght[a]);
				
			}
			
		}
		for (int a = 0; a < lenghtofsnake; a++) {
			if (a==0 && up) {
				upmouth = new ImageIcon("snake-up.png");
				upmouth.paintIcon(this, g,snakexlenght[a], snakeylenght[a]);
				
			}
			if (a!=0) 
			{
				snakeimage = new ImageIcon("snake-body.png");
				snakeimage.paintIcon(this, g,snakexlenght[a], snakeylenght[a]);
				
				
			}
			
		}
		
		foodimage = new ImageIcon("apple.png");
		
		if((foodxpos[xpose]==snakexlenght[0]&&foodypos[ypose]==snakeylenght[0])) 
		{
			score++;
			lenghtofsnake++;
			xpose = random.nextInt(34);
			ypose = random.nextInt(23); 
		}
		
		foodimage.paintIcon(this,g, foodxpos[xpose], foodypos[ypose]);
		
		for (int b = 1 ; b <lenghtofsnake; b++) 
		{
			if (snakexlenght[b]==snakexlenght[0] && snakeylenght[b]==snakeylenght[0]) 
			{
				right= false;
				left=false;
				up=false;
				down=false;
				
				g.setColor(Color.red);
				g.setFont(new Font("areal",Font.BOLD,50));
				g.drawString("GameOver",300,300);
				
				g.setFont(new Font("areal",Font.BOLD,20));
				g.drawString("space to RESTART",350,350);
				
			}
			
		}
		g.dispose();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		timer.start();
		if (right) 
		{
			for (int r = lenghtofsnake-1; r>=0;r--) 
			{
				snakeylenght[r+1]=snakeylenght[r];
			}
			for (int r = lenghtofsnake; r>=0;r--) 
			{
				if (r==0) 
				{
					snakexlenght[r] = snakexlenght[r]+25;
				}
				else
				{
					snakexlenght[r] = snakexlenght[r-1];
				}
				if (snakexlenght[r] > 850) 
				{
					snakexlenght[r] = 25;
					
				}
				
			}
			repaint();
			
		}
		if (left) 
		{
			for (int r = lenghtofsnake-1; r>=0;r--) 
			{
				snakeylenght[r+1]=snakeylenght[r];
			}
			for (int r = lenghtofsnake; r>=0;r--) 
			{
				if (r==0) 
				{
					snakexlenght[r] = snakexlenght[r]-25;
				}
				else
				{
					snakexlenght[r] = snakexlenght[r-1];
				}
				if (snakexlenght[r] < 25) 
				{
					snakexlenght[r] = 850;
					
				}
				
			}
			repaint();
			
			
		}
		if (up) 
		{
			for (int r = lenghtofsnake-1; r>=0;r--) 
			{
				snakexlenght[r+1]=snakexlenght[r];
			}
			for (int r = lenghtofsnake; r>=0;r--) 
			{
				if (r==0) 
				{
					snakeylenght[r] = snakeylenght[r]-25;
				}
				else
				{
					snakeylenght[r] = snakeylenght[r-1];
				}
				if (snakeylenght[r] < 75) 
				{
					snakeylenght[r] = 625;
					
				}
				
			}
			repaint();
			
			
		}
		if (down) 
		{
			for (int r = lenghtofsnake-1; r>=0;r--) 
			{
				snakexlenght[r+1]=snakexlenght[r];
			}
			for (int r = lenghtofsnake; r>=0;r--) 
			{
				if (r==0) 
				{
					snakeylenght[r] = snakeylenght[r]+25;
				}
				else
				{
					snakeylenght[r] = snakeylenght[r-1];
				}
				if (snakeylenght[r] > 625) 
				{
					snakeylenght[r] = 75;
					
				}
				
			}
			repaint();
			
		}
		}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()== KeyEvent.VK_SPACE) 
		{
			moves=0;
			score=0;
			lenghtofsnake = 3;
			repaint();
			
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			moves++;
			right = true;
			if(!left) 
			{
				right= true;
			}
			else
			{
				right =false;
				left = true;
			}
			
			up = false;
			down = false;
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			moves++;
			left = true;
			if(!right) 
			{
				left= true;
			}
			else
			{
				left =false;
				right = true;
			}
			
			up = false;
			down = false;
			
		}
		if (e.getKeyCode()==KeyEvent.VK_UP)
		{
			moves++;
			up = true;
			if(!down) 
			{
				up= true;
			}
			else
			{
				up =false;
				down = true;
			}
			
			left = false;
			right = false;
			
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			moves++;
			down = true;
			if(!up) 
			{
				down= true;
			}
			else
			{
				down =false;
				up = true;
			}
			
			left = false;
			right = false;
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
