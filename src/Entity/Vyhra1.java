package Entity;

import java.awt.*;

public class Vyhra1 {
	
	private Font titleFont;
	
	public  Vyhra1(Hrac p) {

	}
	
	public void draw(Graphics2D g) {
		
		titleFont = new Font("Century Gothic",
				Font.BOLD | Font.ITALIC, 20);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString(
			"Vyhr·l hr·Ë ËÌslo 1.",
			210,
			50
		);
		
	}
	
}













