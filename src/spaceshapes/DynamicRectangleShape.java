package spaceshapes;

import java.awt.Color;

public class DynamicRectangleShape extends Shape {
	//=== Constant Variables
	private static final Color OUTLINE_COLOR = new Color(212,212,212); //creates an outline color
	private static final Color DEFAULT_COLOR = new Color(0,0,255); //creates default value blue
	//===
	
	//=== instance variables
	protected boolean _filled;
	private Color _color;
	//===
	
	/**
	 * Default constructor that creates a RectangleShape instance whose instance
	 * variables are set to default values.
	 */
	public DynamicRectangleShape() {
		super();
		_color = DEFAULT_COLOR;
	}
	
	/**
	 * Creates a RectangleShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed and direction for horizontal axis.
	 * @param deltaY speed and direction for vertical axis.
	 */
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY) {
		super(x,y,deltaX,deltaY);
		_color = DEFAULT_COLOR;
	}
	
	/**
	 * Creates a RectangleShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed (pixels per move call) and direction for horizontal 
	 *        axis.
	 * @param deltaY speed (pixels per move call) and direction for vertical 
	 *        axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 */
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x,y,deltaX,deltaY,width,height);
		_color = DEFAULT_COLOR;
	}
	
	/**
	 * Creates a RectangleShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed (pixels per move call) and direction for horizontal 
	 *        axis.
	 * @param deltaY speed (pixels per move call) and direction for vertical 
	 *        axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 * @param color color used for the dynamic shape
	 */
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height, Color color) {
		super(x,y,deltaX,deltaY,width,height);
		_color = color;
	}
	
	/**
	 * Creates a DynamicShape instance with specified values for instance 
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
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		super(x,y,deltaX,deltaY,width,height,text);
		_color = DEFAULT_COLOR;
	}
	
	/**
	 * Creates a DynamicShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed (pixels per move call) and direction for horizontal 
	 *        axis.
	 * @param deltaY speed (pixels per move call) and direction for vertical 
	 *        axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 * @param color color used for the dynamic shape
	 * @param text text that is displayed
	 */
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height, String text, Color color) {
		super(x,y,deltaX,deltaY,width,height,text);
		_color = color;
	}
	
	public void move(int width, int height) {
		int nextX = _x + _deltaX;
		int nextY = _y + _deltaY;

		if (nextX <= 0) {
			nextX = 0;
			_deltaX = -_deltaX;
			_filled = true;
		} else if (nextX + _width >= width) {
			nextX = width - _width;
			_deltaX = -_deltaX;
			_filled = true;
		}

		if (nextY <= 0) {
			nextY = 0;
			_deltaY = -_deltaY;
			_filled = false;
		} else if (nextY + _height >= height) {
			nextY = height - _height;
			_deltaY = -_deltaY;
			_filled = false;
		}

		_x = nextX;
		_y = nextY;
	}
	
	/**
	 * Paints the DynamicRectangleShape blue once it bounces off the left or right boundaries, and returns to its original color
	 * (outlined rectangle) when it bounces off the top or bottom boundaries. 
	 */
	public void doPaint(Painter p) {
		if (_filled) {
			//p.setColor(new Color(0, 0, 255)); 
			p.setColor(_color); //built in blue color with r=0 g=0 b=255
			p.fillRect(_x,_y,_width,_height);
			p.setColor(OUTLINE_COLOR);
		} else {
			//p.setColor(new Color(212, 212, 212));
			p.drawRect(_x,_y,_width,_height);
		}
	}
}
