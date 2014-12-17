package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Stavy.Level1;

public class Hud {
	
	private Hrac player;
	
	private BufferedImage image;
	private Font font;
	private Font titleFont;
	
	public Hud(Hrac p) {
		player = p;
		
		try {
			image = ImageIO.read(
				getClass().getResourceAsStream(
					"/Status/bar.gif"
				)
			);
			font = new Font("Arial", Font.PLAIN, 14);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g) {
		
		int pom=0;
		
		if(Level1.tah==true) pom=1;
		if(Level1.tah2==true) pom=2;
		
		g.drawImage(image, 0, 10, null);
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString(
			player.getHealth() + "/" + player.getMaxHealth(),
			30,
			25
		);
		g.drawString(
			player.getFire() / 100 + "/" + player.getMaxFire() / 100,
			30,
			45
		);
		if(Level1.tah==true){
		g.drawString(
				player.getTahy() + "/" + "20",
				30,
				65
			);
		}
		if(Level1.tah2==true){
		g.drawString(
				player.getTahy2() + "/" + "20",
				30,
				65
			);
		}
		
		titleFont = new Font("Challange Extra Bold",
		Font.BOLD | Font.ITALIC, 20);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString(
			"Na tahu hr·Ë ËÌslo " + pom + ".",
			210,
			50
		);
		
	}
	
}













