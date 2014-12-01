package TileMap;

import Main.GamePanel;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class Pozadi {
	
	private BufferedImage image;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	private double moveScale;
	
	public Pozadi(String s, double ms) {
		try{
			image = ImageIO.read(
					getClass().getResourceAsStream(s)
			);
			moveScale = ms;
		} 
		catch(Exception e){
		e.printStackTrace();	
		}
		
	}
	
	public void sePosition(double x, double y) {
		
		this.x=(x * moveScale) % GamePanel.WIDTH;
		this.y=(y * moveScale) % GamePanel.HEIGHT;;
	}
	
	public void setVektor(double dx, double dy) {
		
		this.dx=dx;
		this.dy=dy;
	}
	
	public void update() {
		x+=dx;
		y+=dy;
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(image, (int)x, (int)y, null);
		if(x<0) {
			g.drawImage(
					image,
					(int)x + GamePanel.WIDTH,
					(int)y,
					null
					);
		}
		if(x>0) {
			g.drawImage(
					image,
					(int)x - GamePanel.WIDTH,
					(int)y,
					null
					);
		
	}


	}}
	
