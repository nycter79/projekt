package Entity;

import Stavy.Level1;
import TileMap.*;

import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy extends EnemyAbstr {
	
	public int health;
	private int maxHealth;
	private int fire;
	private boolean remove;
	private int maxFire;
	public boolean death;
	public  int myx;
	public  int myy;
	//private boolean dead;
	
	public  boolean crush2=false;
	
	
	// fireball
	private boolean firing;
	private int fireCost;
	//private int fireBallDamage;
	private ArrayList<EnemyFire> fireBalls;
	
	
	// animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {
		1, 1, 1, 1
	};
	
	// animation actions
	private static final int UP = 3;
	private static final int RIGHT = 1;
	private static final int DOWN = 2;
	private static final int LEFT = 0;
	//private static final int SLASH = 4;
	
	public Enemy(MapaDlaz tm) {
		
		super(tm);
		
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
		
		moveSpeed = 2;
		
		health = maxHealth = 2;
		fire = maxFire = 101;
		death=false;
		fireCost = 100;
		fireBalls = new ArrayList<EnemyFire>();
		
		// load sprites
		try {
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Hrac/skeleton.gif"
				)
			);
			
			sprites = new ArrayList<BufferedImage[]>();
			for(int i = 0; i < 4; i++) {
				
				BufferedImage[] bi =
					new BufferedImage[numFrames[i]];
				
				for(int j = 0; j < numFrames[i]; j++) {
					
					if(i != 5) {
						bi[j] = spritesheet.getSubimage(
								j * width,
								i * height,
								width,
								height
						);
					}
					else {
						bi[j] = spritesheet.getSubimage(
								j * width * 2,
								i * height,
								width * 2,
								height
						);
					}
					
				}
				
				sprites.add(bi);
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		animation = new Animace();
		animation.setFrames(sprites.get(LEFT));
		animation.setDelay(400);
		
	}
	
	
	public void setFiring() { 
		firing = true;
	}
	
	public void stopFiring() { 
		firing = false;
	}
	
	
	public int getHealth() { return health; }
	public int getMaxHealth() { return maxHealth; }
	public int getFire() { return fire; }
	public int getTahy() { return Level1.pocetTahu2; }
	public int getMaxFire() { return maxFire; }
	
	public void setDown() {
		super.setDown();
		faceLeft = false;
		faceRight = false;
		faceUp = false;
		faceDown = true;

	}
	public void setLeft() {
		super.setLeft();
		faceLeft = true;
		faceRight = false;
		faceUp = false;
		faceDown = false;

	}
	public void setRight() {
		super.setRight();
		faceLeft = false;
		faceRight = true;
		faceUp = false;
		faceDown = false;

	}
	public void setUp() {
		super.setUp();
		faceLeft = false;
		faceRight = false;
		faceUp = true;
		faceDown = false;

	}
	
	public  void crush2() { 
		crush2=true;
		}
	
	

	
	public int getmyx() { return myx; }
	public int getmyy() { return myy; }
	
	public void update() {
		
		// update position
		super.update();
		
		myx = x;
		myy = y;
		
		if(faceLeft) {
				animation.setFrames(sprites.get(LEFT));
				animation.setDelay(100);	
		}
		if(faceRight) {
			animation.setFrames(sprites.get(RIGHT));
			animation.setDelay(100);	
		}
		if(faceUp) {
			animation.setFrames(sprites.get(UP));
			animation.setDelay(100);	
		}
		if(faceDown) {
			animation.setFrames(sprites.get(DOWN));
			animation.setDelay(100);	
		}
	
				// fireBall attack
				fire += 1;
				if(fire > maxFire) fire = maxFire;
				if(firing) {
					if(fire > fireCost) {
						fire -= fireCost;
						EnemyFire fb = new EnemyFire(tileMap);
						fb.setPosition(x, y);
						fireBalls.add(fb);

						stopFiring();
					}
				}

				// update fireBalls
				for(int i = 0; i < fireBalls.size(); i++) {
					fireBalls.get(i).update();
					
					if(fireBalls.get(i).shouldRemove()) {
						fireBalls.remove(i);
						i--;
							}
				}	
	}


	public void draw(Graphics2D g) {
		
		for(int i = 0; i < fireBalls.size(); i++) {
			fireBalls.get(i).draw(g);
		}
		
		super.draw(g);
		
	}


	public boolean shouldRemove() {
		if(health == 0) remove = true;
		return remove;
	}
}
	

















