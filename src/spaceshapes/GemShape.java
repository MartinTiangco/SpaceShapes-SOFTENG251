package spaceshapes;

public class GemShape extends Shape {
	/**
	 * Default constructor that sets GemShape's parameters to be of default values.
	 */
	public GemShape() {
		super();
	}
	
	/**
	 * Creates an GemShape instance with specified values for instance 
	 * variables.
	 * @param x x position
	 * @param y y position
	 * @param deltaX speed (pixels per move call) and direction for horizontal axis
	 * @param deltaY speed (pixels per move call) and direction for vertical axis
	 */
	public GemShape(int x, int y, int deltaX, int deltaY) {
		super(x, y, deltaX, deltaY);
	}
	
	/**
	 * Creates an GemShape instance with specified values for instance 
	 * variables.
	 * @param x x position
	 * @param y y position
	 * @param deltaX speed (pixels per move call) and direction for horizontal axis
	 * @param deltaY speed (pixels per move call) and direction for vertical axis
	 * @param width width in pixels
	 * @param height height in pixels
	 */
	public GemShape (int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x, y, deltaX, deltaY, width, height);
	}
	
	/**
	 * Creates a GemShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed (pixels per move call) and direction for horizontal 
	 *        axis.
	 * @param deltaY speed (pixels per move call) and direction for vertical 
	 *        axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 * @param text text that is displayed
	 */
	public GemShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		super(x,y,deltaX,deltaY,width,height,text);
	}
	
	/**
	 * Paints this GemShape object using the supplied Painter object.
	 */
	public void doPaint(Painter painter) {
		if (_width >= 40) { 
		//Make 6 painter.drawLine( ) calls 
		painter.drawLine(_x, _y + _height/2, _x + 20, _y);
		painter.drawLine(_x + 20, _y, _x + _width - 20, _y);
		painter.drawLine(_x + _width - 20, _y, _x + _width, _y + _height/2);
		painter.drawLine(_x + _width, _y + _height/2, _x + _width - 20, _y + _height);
		painter.drawLine(_x + _width - 20, _y + _height, _x + 20, _y + _height);
		painter.drawLine(_x + 20, _y + _height, _x, _y + _height/2);
		} else { 
		//Make 4 painter.drawline( )  calls 
		painter.drawLine(_x, _y + _height/2, _x + _width/2, _y);
		painter.drawLine(_x + _width/2, _y, _x + _width, _y + _height/2);
		painter.drawLine(_x + _width, _y + _height/2, _x + _width/2, _y + _height);
		painter.drawLine(_x + _width/2, _y + _height, _x, _y + _height/2);
		
		}
	}

}
