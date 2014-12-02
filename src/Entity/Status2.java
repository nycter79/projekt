package Entity;

import java.awt.*;

public class Status2 {
	
	private Font titleFont;
	
	public Status2(Hrac2 p) {

	}
	
	public void draw(Graphics2D g) {
		
		titleFont = new Font("Century Gothic",
		Font.BOLD, 25);
		g.setFont(titleFont);
		g.setColor(Color.RED);
		g.drawString(
			"Na tahu hr·Ë ËÌslo 2.",
			155,
			70
		);
		
	}
	
}