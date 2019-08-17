package spaceshapes;

/**
 * Class to create an oval space-shape.
 * @author Martin Tiangco
 *
 */
public class OvalShape extends Shape {

	/**
	 * Default constructor that sets OvalShape's parameters to be of default values.
	 */
	public OvalShape() {
		super();
	}
	
	/**
	 * Creates an OvalShape instance with specified values for instance 
	 * variables.
	 * @param x x position
	 * @param y y position
	 * @param deltaX speed (pixels per move call) and direction for horizontal axis
	 * @param deltaY speed (pixels per move call) and direction for vertical axis
	 */
	public OvalShape(int x, int y, int deltaX, int deltaY) {
		super(x, y, deltaX, deltaY);
	}
	
	/**
	 * Creates an OvalShape instance with specified values for instance 
	 * variables.
	 * @param x x position
	 * @param y y position
	 * @param deltaX speed (pixels per move call) and direction for horizontal axis
	 * @param deltaY speed (pixels per move call) and direction for vertical axis
	 * @param width width in pixels
	 * @param height height in pixels
	 */
	public OvalShape (int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x, y, deltaX, deltaY, width, height);
	}
	
	/**
	 * Creates an OvalShape instance with specified values for instance 
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
	public OvalShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		super(x,y,deltaX,deltaY,width,height,text);
	}
	
	/**
	 * Paints this OvalShape object using the supplied Painter object.
	 */
	public void doPaint(Painter painter) {
		painter.drawOval(_x,_y,_width,_height);
	}
}
