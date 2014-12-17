package Entity;

import java.awt.*;

public class Vyhra {
	
	private Font titleFont;
	
	public  Vyhra() {

	}
	
	public void draw(Graphics2D g) {
		
			
		titleFont = new Font("Century Gothic",
				Font.BOLD | Font.ITALIC, 20);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString(
			"Hra skonèila",
			210,
			50
		);
		
	}
	
	
}













