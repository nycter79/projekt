package Entity;

import java.awt.*;

public class Status {
	
	private Font titleFont;
	
	public Status(Hrac p) {

	}
	
	public void draw(Graphics2D g) {
		
		titleFont = new Font("Challange Extra Bold",
		Font.BOLD | Font.ITALIC, 20);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString(
			"Na tahu hr·Ë ËÌslo 1.",
			210,
			50
		);
		
	}
	
}













