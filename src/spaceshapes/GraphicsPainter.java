package spaceshapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.FontMetrics;

/**
 * Implementation of the Painter interface that delegates drawing to a
 * java.awt.Graphics object.
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
public class GraphicsPainter implements Painter {
	// Delegate object.
	private Graphics _g;

	/**
	 * Creates a GraphicsPainter object and sets its Graphics delegate.
	 */
	public GraphicsPainter(Graphics g) {
		this._g = g;
		_g.setColor(new Color(212, 212, 212));
	}

	/**
	 * @see spaceshapes.Painter.drawRect
	 */
	public void drawRect(int x, int y, int width, int height) {
		_g.drawRect(x, y, width, height);
	}

	/**
	 * @see spaceshapes.Painter.drawOval
	 */
	public void drawOval(int x, int y, int width, int height) {
		_g.drawOval(x, y, width, height);
	}

	/**
	 * @see spaeshapes.Painter.drawLine.
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		_g.drawLine(x1, y1, x2, y2);
	}
	
	public void fillRect(int x, int y, int width, int height) {
		_g.fillRect(x, y, width, height);
	}
	
	public Color getColor() {
		return _g.getColor();
	}
	
	public void setColor(Color color) {
		_g.setColor(color);
	}
	
	public void translate(int x, int y) {
		_g.translate(x, y);
	}
	
	/**
	 * Draws the string centred on the shape.
	 */
	public void drawCentredText(String string, int x, int y, int width, int height) {
		FontMetrics f = _g.getFontMetrics();
		int xPos = x + width/2 - f.stringWidth(string)/2;				//calculates the positions where the text will begin painting
		int yPos = 0;
		if (f.getAscent() == f.getDescent()) {
			yPos = y + height/2;
		} else if (f.getAscent() > f.getDescent()) {		//if ascent is larger
			yPos = y + (f.getAscent() - f.getDescent()) / 2 + height/2;
		} else if (f.getDescent() > f.getAscent()) {		//if descent is larger
			yPos = y + (f.getDescent() - f.getAscent()) / 2 + height/2;
		}
		_g.drawString(string, xPos, yPos);
	}
	
	public void drawImage(Image img, int x, int y, int width, int height) {
		_g.drawImage(img, x, y, width, height, null);
	}
}

