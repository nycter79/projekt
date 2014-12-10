package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Hud {
	
	private Hrac player;
	
	private BufferedImage image;
	private Font font;
	
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
		g.drawString(
				player.getTahy() + "/" + "20",
				30,
				65
			);
		
	}
	
}













