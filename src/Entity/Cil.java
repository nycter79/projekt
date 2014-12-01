package Entity;

import TileMap.*;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Cil extends ObjectAbstr {
	

	

	
	
	// animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {
		1
	};
	
	// animation actions
	private static final int UP = 0;

	
	public Cil(MapaDlaz tm) {
		
		super(tm);
		
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
		
		moveSpeed = 3;
		
		
		// load sprites
		try {
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Hrac/konec.gif"
				)
			);
			
			sprites = new ArrayList<BufferedImage[]>();
			for(int i = 0; i < 1; i++) {
				
				BufferedImage[] bi =
					new BufferedImage[numFrames[i]];
				
				for(int j = 0; j < numFrames[i]; j++) {
					
					if(i != 1) {
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
		animation.setFrames(sprites.get(UP));
		animation.setDelay(400);
		
	}
	
	
	public void setFiring() { 

	}
	
	public void stopFiring() { 

	}
	
	
	
	public void setDown() {
		super.setDown();


	}
	public void setLeft() {


	}
	public void setRight() {


	}
	public void setUp() {


	}
	
	
	public void update() {
		
		// update position
		super.update();
	
	
	}


	public void draw(Graphics2D g) {

		
		super.draw(g);
		
	}
}
	

















