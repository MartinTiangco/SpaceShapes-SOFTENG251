package spaceshapes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * A class that implements test cases aimed at identifying bugs in the 
 * implementations of classes Shape and OvalShape.
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
public class TestOvalShape {
	// Fixture object that is used by the tests.
	private MockPainter _painter;

	/**
	 * This method is called automatically by the JUnit test-runner immediately
	 * before each @Test method is executed. setUp() recreates the fixture so 
	 * that there no side effects from running individual tests.
	 */
	@Before
	public void setUp() {
		_painter = new MockPainter();
	}

	/**
	 * Test to determine if the shape is drawn correctly.
	 */
	@Test
	public void testCorrectlyDrawn() {
		OvalShape shape = new OvalShape(75, 90, 15, 20); //width and height is 50 - 6 sided gem (hexagon)
		shape.paint(_painter);
		assertEquals("(oval 75,90,25,35)", _painter.toString());
	}
	
	/**
	 * Test to perform a simple (non-bouncing) movement, and to ensure that a
	 * Shape's position after the movement is correct.
	 */
	@Test
	public void testSimpleMove() {
		OvalShape shape = new OvalShape(75, 90, 15, 20);
		shape.paint(_painter);
		shape.move(500, 500); //moves within the board's boundaries of x=500, y=500
		shape.paint(_painter);
		assertEquals("(oval 75,90,25,35)"
			  	  + "(oval 90,110,25,35)", _painter.toString());
	}

	/**
	 * Test to perform a bounce movement off the right-most boundary and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffRight() {
		OvalShape shape = new OvalShape(75, 90, 15, 20);
		shape.paint(_painter);
		shape.move(100, 10000); //move within the boundaries indicated by width, height
		shape.paint(_painter);
		shape.move(100, 10000);
		shape.paint(_painter);
		assertEquals("(oval 75,90,25,35)"
				  + "(oval 75,110,25,35)"
				  + "(oval 60,130,25,35)", _painter.toString());
	}

	/**
	 * Test to perform a bounce movement off the left-most boundary and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffLeft() {
		OvalShape shape = new OvalShape(10, 20, -15, 20); //default width is 25, height is 35
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		assertEquals("(oval 10,20,25,35)"
				    + "(oval 0,40,25,35)"
				   + "(oval 15,60,25,35)", _painter.toString());
	}

	/**
	 * Test to perform a bounce movement off the bottom right corner and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffBottomAndRight() {			
		OvalShape shape = new OvalShape(10, 20, -15, 20); //default width is 25, height is 35
		shape.paint(_painter);
		shape.move(40, 70);
		shape.paint(_painter);
		shape.move(40, 70);
		shape.paint(_painter);
		assertEquals("(oval 10,20,25,35)"
				    + "(oval 0,35,25,35)"
				   + "(oval 15,15,25,35)", _painter.toString());
	}
}
