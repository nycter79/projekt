package Entity;

import TileMap.Dlazdice;
import TileMap.MapaDlaz;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Strela extends ObjectAbstr {
	
	private boolean hit;
	private boolean remove;
	
	private BufferedImage[] sprites;
	private BufferedImage[] hitSprites;
	
	public Strela(MapaDlaz tm) {
		
		super(tm);
		
		moveSpeed = 1;
		width = 30;
		height = 30;
		cwidth = 14;
		cheight = 14;
		
		if(faceRight){
			R=true;
			L=false;
			U=false;
			D=false;
		}
		if(faceLeft){
			R=false;
			L=true;
			U=false;
			D=false;
		}
		if(faceUp){
			R=false;
			L=false;
			U=true;
			D=false;
		}
		if(faceDown){
			R=false;
			L=false;
			U=false;
			D=true;
		}
		
		try {
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Hrac/fireball.gif"
				)
			);
			
			sprites = new BufferedImage[4];
			for(int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(
					i * width,
					0,
					width,
					height
				);
			}
			
			hitSprites = new BufferedImage[3];
			for(int i = 0; i < hitSprites.length; i++) {
				hitSprites[i] = spritesheet.getSubimage(
					i * width,
					height,
					width,
					height
				);
			}
			
			animation = new Animace();
			animation.setFrames(sprites);
			animation.setDelay(70);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setHit() {
		animation.setFrames(hitSprites);
		animation.setDelay(70);
		if(hit) return;
		hit = true;
		xdest = 0;
	}
	
	
	public boolean shouldRemove() { return remove; }
	
	public void update() {
		
		if(Hrac.R){
			
			setRight2();
			if(tileMap.getType(rowTile, colTile + 1) == Dlazdice.BLOCKED) {
				setHit();
				animation.update();
			}
			
		}
		if(Hrac.L){
			
			setLeft2();
			if(tileMap.getType(rowTile, colTile - 1) == Dlazdice.BLOCKED) {
				setHit();
			}
			
		}
		if(Hrac.U){
			
			setUp2();
			if(tileMap.getType(rowTile -1, colTile) == Dlazdice.BLOCKED) {
				setHit();
			}
			
		}
		if(Hrac.D){
			
			setDown2();
			if( tileMap.getType(rowTile + 1, colTile) == Dlazdice.BLOCKED) {
				setHit();
			}
		}
		
		super.update();
	
		animation.update();
		
		if(hit) {
			remove = true;
		}
	}
	
	

	public void draw(Graphics2D g){
		setMapPosition();
		super.draw(g);
		
	}
	
}