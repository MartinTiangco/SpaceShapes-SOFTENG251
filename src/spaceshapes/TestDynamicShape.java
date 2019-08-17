package spaceshapes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * A class that implements test cases aimed at identifying bugs in the 
 * implementations of classes Shape and DynamicRectangleShape.
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
public class TestDynamicShape {
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
		DynamicRectangleShape shape = new DynamicRectangleShape(75, 90, 15, 20);
		shape.paint(_painter);
		assertEquals("(rectangle 75,90,25,35)", _painter.toString());
	}
	
	/**
	 * Test to perform a simple (non-bouncing) movement, and to ensure that a
	 * Shape's position after the movement is correct.
	 */
	@Test
	public void testSimpleMove() {
		DynamicRectangleShape shape = new DynamicRectangleShape(75, 90, 15, 20);
		shape.paint(_painter);
		shape.move(500, 500); //moves within the board's boundaries of x=500, y=500
		shape.paint(_painter);
		assertEquals("(rectangle 75,90,25,35)"
				+ "(rectangle 90,110,25,35)", 
				_painter.toString());
	}

	/**
	 * Test to perform a bounce movement off the right-most boundary and to
	 * ensure that the Shape's position after the movement is correct. The DynamicRectangleShape should
	 * change from outline to blue.
	 */
	@Test
	public void testShapeMoveWithBounceOffRight() {
		DynamicRectangleShape shape = new DynamicRectangleShape(75, 90, 15, 20);
		shape.paint(_painter);
		shape.move(100, 10000); //move within the boundaries indicated by width, height
		shape.paint(_painter);
		shape.move(100, 10000);
		shape.paint(_painter);
		assertEquals("(rectangle 75,90,25,35)"
				+ "(color java.awt.Color[r=0,g=0,b=255])(fill Rect 75,110,25,35)(color java.awt.Color[r=212,g=212,b=212])"
				+ "(color java.awt.Color[r=0,g=0,b=255])(fill Rect 60,130,25,35)(color java.awt.Color[r=212,g=212,b=212])", _painter.toString());
	}

	/**
	 * Test to perform a bounce movement off the left-most boundary and to
	 * ensure that the Shape's position after the movement is correct. The DynamicRectangleShape should
	 * change from outline to blue.
	 */
	@Test
	public void testShapeMoveWithBounceOffLeft() {
		DynamicRectangleShape shape = new DynamicRectangleShape(10, 20, -15, 20); //default width is 25, height is 35
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		assertEquals("(rectangle 10,20,25,35)"
				+ "(color java.awt.Color[r=0,g=0,b=255])(fill Rect 0,40,25,35)(color java.awt.Color[r=212,g=212,b=212])"
				+ "(color java.awt.Color[r=0,g=0,b=255])(fill Rect 15,60,25,35)(color java.awt.Color[r=212,g=212,b=212])", _painter.toString());
	}
	
	/**
	 * Test to perform a bounce movement off the top-most boundary and to
	 * ensure that the Shape's position after the movement is correct. The DynamicRectangleShape should
	 * change or stay to color outline.
	 */
	@Test
	public void testShapeMoveWithBounceOffTop() {
		DynamicRectangleShape shape = new DynamicRectangleShape(10, 20, 0, -20); //default width is 25, height is 35
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		assertEquals("(rectangle 10,20,25,35)"
				+ "(rectangle 10,0,25,35)"
				+ "(rectangle 10,20,25,35)", _painter.toString());
	}
	
	/**
	 * Test to perform a bounce movement off the bottom-most boundary and to
	 * ensure that the Shape's position after the movement is correct. The DynamicRectangleShape should
	 * change or stay to color outline.
	 */
	@Test
	public void testShapeMoveWithBounceOffBottom() {
		DynamicRectangleShape shape = new DynamicRectangleShape(100, 100, 0, 20); //default width is 25, height is 35
		shape.paint(_painter);
		shape.move(10000, 145);
		shape.paint(_painter);
		shape.move(10000, 145);
		shape.paint(_painter);
		assertEquals("(rectangle 100,100,25,35)"
				+ "(rectangle 100,110,25,35)"
				+ "(rectangle 100,90,25,35)", _painter.toString());
	}

	/**
	 * Test to perform a bounce movement off the bottom wall, then the right wall and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffBottomAndRight() {			
		DynamicRectangleShape shape = new DynamicRectangleShape(90, 100, 10, 20, 10, 10); //width is 10, height is 10
		shape.paint(_painter);
		shape.move(120, 120);
		shape.paint(_painter);
		shape.move(120, 120);
		shape.paint(_painter);
		assertEquals("(rectangle 90,100,10,10)"
				+ "(rectangle 100,110,10,10)"
				+ "(color java.awt.Color[r=0,g=0,b=255])(fill Rect 110,90,10,10)(color java.awt.Color[r=212,g=212,b=212])", _painter.toString());
	}
	
	/**
	 * Test to perform a bounce movement off the bottom wall, then the left wall and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffBottomAndLeft() {			
		DynamicRectangleShape shape = new DynamicRectangleShape(20, 100, -10, 20, 10, 10); //width is 10, height is 10
		shape.paint(_painter);
		shape.move(10000, 120);
		shape.paint(_painter);
		shape.move(10000, 120);
		shape.paint(_painter);
		assertEquals("(rectangle 20,100,10,10)"
				+ "(rectangle 10,110,10,10)"
				+ "(color java.awt.Color[r=0,g=0,b=255])(fill Rect 0,90,10,10)(color java.awt.Color[r=212,g=212,b=212])", _painter.toString());
	}
	
	/**
	 * Test to perform a bounce movement off the top wall, then the right wall and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffTopAndRight() {			
		DynamicRectangleShape shape = new DynamicRectangleShape(90, 20, 10, -20, 10, 10); //width is 10, height is 10
		shape.paint(_painter);
		shape.move(120, 10000);
		shape.paint(_painter);
		shape.move(120, 10000);
		shape.paint(_painter);
		assertEquals("(rectangle 90,20,10,10)"
				+ "(rectangle 100,0,10,10)"
				+ "(color java.awt.Color[r=0,g=0,b=255])(fill Rect 110,20,10,10)(color java.awt.Color[r=212,g=212,b=212])", _painter.toString());
	}
	
	/**
	 * Test to perform a bounce movement off the top wall, then the left wall corner and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffTopAndLeft() {			
		DynamicRectangleShape shape = new DynamicRectangleShape(20, 20, -10, -20, 10, 10); //width is 10, height is 10
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		assertEquals("(rectangle 20,20,10,10)"
				+ "(rectangle 10,0,10,10)"
				+ "(color java.awt.Color[r=0,g=0,b=255])(fill Rect 0,20,10,10)(color java.awt.Color[r=212,g=212,b=212])", _painter.toString());
	}
}

