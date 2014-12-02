package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Hud2 {
	
	private Hrac2 player2;
	
	private BufferedImage image;
	private Font font;
	
	public Hud2(Hrac2 p) {
		player2 = p;
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
		g.setColor(Color.WHITE);
		g.drawString(
			player2.getHealth() + "/" + player2.getMaxHealth(),
			30,
			25
		);
		g.drawString(
			player2.getFire() / 100 + "/" + player2.getMaxFire() / 100,
			30,
			45
		);
		g.drawString(
				player2.getTahy() + "/" + "5",
				30,
				65
			);
		
	}
	
}













