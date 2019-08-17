package spaceshapes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


/**
 * A class that implements test cases aimed at identifying bugs in the 
 * implementations of classes Shape and GemShape.
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
public class TestGemShape {
	// Fixture object that is used by the tests.
	// _painter paints the 6 sided gem, while _painter1 paints the 4 sided gem.
	private MockPainter _painter; 
	private MockPainter _painter1;

	/**
	 * This method is called automatically by the JUnit test-runner immediately
	 * before each @Test method is executed. setUp() recreates the fixture so 
	 * that there no side effects from running individual tests.
	 */
	@Before
	public void setUp() {
		_painter = new MockPainter();
		_painter1 = new MockPainter();
	}
	/**
	 * Test to determine if the shape is drawn correctly.
	 */
	@Test
	public void testCorrectlyDrawn() {
		GemShape shape = new GemShape(0, 0, 10, 10, 50, 50); //width and height is 50 - 6 sided gem (hexagon)
		//test if hexagon is drawn correctly
		shape.paint(_painter);
		assertEquals("(line 0,25,20,0)"
				+ "(line 20,0,30,0)"
				+ "(line 30,0,50,25)"
				+ "(line 50,25,30,50)"
				+ "(line 30,50,20,50)"
				+ "(line 20,50,0,25)", _painter.toString());
		
		GemShape shape1 = new GemShape(0, 0, 10, 10, 30, 30); //width and height is 30 - 4 sided gem (rhombus)
		//test if rhombus is drawn correctly
		shape1.paint(_painter1);
		assertEquals("(line 0,15,15,0)"
				+ "(line 15,0,30,15)"
				+ "(line 30,15,15,30)"
				+ "(line 15,30,0,15)", _painter1.toString());
	}
	/**
	 * Test to perform a simple (non-bouncing) movement, and to ensure that a
	 * Shape's position after the movement is correct.
	 */
	@Test
	public void testSimpleMove() {		
		GemShape shape = new GemShape(0, 0, 10, 10, 50, 50); //width and height is 50 - 6 sided gem (hexagon)
		shape.paint(_painter);
		shape.move(500,500);
		shape.paint(_painter);
		assertEquals("(line 0,25,20,0)(line 20,0,30,0)(line 30,0,50,25)(line 50,25,30,50)(line 30,50,20,50)(line 20,50,0,25)"
				+ "(line 10,35,30,10)(line 30,10,40,10)(line 40,10,60,35)(line 60,35,40,60)(line 40,60,30,60)(line 30,60,10,35)",
				_painter.toString());
		
		GemShape shape1 = new GemShape(0, 0, 10, 10, 30, 30); //width and height is 30 - 4 sided gem (rhombus)
		shape1.paint(_painter1);
		shape1.move(500,500);
		shape1.paint(_painter1);
		assertEquals("(line 0,15,15,0)(line 15,0,30,15)(line 30,15,15,30)(line 15,30,0,15)"
				+ "(line 10,25,25,10)(line 25,10,40,25)(line 40,25,25,40)(line 25,40,10,25)",
				 _painter1.toString());
	}
	
	/**
	 * Test to perform a bounce movement off the right-most boundary and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffRight() {
		GemShape shape = new GemShape(50, 0, 10, 10, 50, 50); //width and height is 50 - 6 sided gem (hexagon)
		shape.paint(_painter);
		shape.move(100,10000);
		shape.paint(_painter);
		shape.move(100,10000);
		shape.paint(_painter);
		assertEquals("(line 50,25,70,0)(line 70,0,80,0)(line 80,0,100,25)(line 100,25,80,50)(line 80,50,70,50)(line 70,50,50,25)"
				+ "(line 50,35,70,10)(line 70,10,80,10)(line 80,10,100,35)(line 100,35,80,60)(line 80,60,70,60)(line 70,60,50,35)"
				+ "(line 40,45,60,20)(line 60,20,70,20)(line 70,20,90,45)(line 90,45,70,70)(line 70,70,60,70)(line 60,70,40,45)", 
				_painter.toString());
		
		GemShape shape1 = new GemShape(50, 0, 10, 10, 30, 30); //width and height is 30 - 4 sided gem (rhombus)
		shape1.paint(_painter1);
		shape1.move(80,10000);
		shape1.paint(_painter1);
		shape1.move(80,10000);
		shape1.paint(_painter1);
		assertEquals("(line 50,15,65,0)(line 65,0,80,15)(line 80,15,65,30)(line 65,30,50,15)"
				+ "(line 50,25,65,10)(line 65,10,80,25)(line 80,25,65,40)(line 65,40,50,25)"
				+ "(line 40,35,55,20)(line 55,20,70,35)(line 70,35,55,50)(line 55,50,40,35)",
				 _painter1.toString());
	}

	/**
	 * Test to perform a bounce movement off the left-most boundary and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffLeft() {
		GemShape shape = new GemShape(0, 0, -10, 0, 50, 50); //width and height is 50 - 6 sided gem (hexagon)
		shape.paint(_painter);
		shape.move(10000,10000);
		shape.paint(_painter);
		shape.move(10000,10000);
		shape.paint(_painter);
		assertEquals("(line 0,25,20,0)(line 20,0,30,0)(line 30,0,50,25)(line 50,25,30,50)(line 30,50,20,50)(line 20,50,0,25)"
				+ "(line 0,25,20,0)(line 20,0,30,0)(line 30,0,50,25)(line 50,25,30,50)(line 30,50,20,50)(line 20,50,0,25)"
				+ "(line 10,25,30,0)(line 30,0,40,0)(line 40,0,60,25)(line 60,25,40,50)(line 40,50,30,50)(line 30,50,10,25)", 
				_painter.toString());
		
		GemShape shape1 = new GemShape(0, 0, -10, 0, 30, 30); //width and height is 30 - 4 sided gem (rhombus)
		shape1.paint(_painter1);
		shape1.move(10000,10000);
		shape1.paint(_painter1);
		shape1.move(10000,10000);
		shape1.paint(_painter1);
		assertEquals("(line 0,15,15,0)(line 15,0,30,15)(line 30,15,15,30)(line 15,30,0,15)"
				+ "(line 0,15,15,0)(line 15,0,30,15)(line 30,15,15,30)(line 15,30,0,15)"
				+ "(line 10,15,25,0)(line 25,0,40,15)(line 40,15,25,30)(line 25,30,10,15)",
				 _painter1.toString());
	}

	/**
	 * Test to perform a bounce movement off the bottom right corner and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffBottomAndRight() {
		GemShape shape = new GemShape(30, 30, 10, 10, 50, 50); //width and height is 50 - 6 sided gem (hexagon)
		shape.paint(_painter);
		shape.move(80,80);
		shape.paint(_painter);
		shape.move(80,80);
		shape.paint(_painter);
		assertEquals("(line 30,55,50,30)(line 50,30,60,30)(line 60,30,80,55)(line 80,55,60,80)(line 60,80,50,80)(line 50,80,30,55)"
				+ "(line 30,55,50,30)(line 50,30,60,30)(line 60,30,80,55)(line 80,55,60,80)(line 60,80,50,80)(line 50,80,30,55)"
				+ "(line 20,45,40,20)(line 40,20,50,20)(line 50,20,70,45)(line 70,45,50,70)(line 50,70,40,70)(line 40,70,20,45)", 
				_painter.toString());
		
		GemShape shape1 = new GemShape(30, 30, 10, 10, 30, 30); //width and height is 30 - 4 sided gem (rhombus)
		shape1.paint(_painter1);
		shape1.move(60,60);
		shape1.paint(_painter1);
		shape1.move(60,60);
		shape1.paint(_painter1);
		assertEquals("(line 30,45,45,30)(line 45,30,60,45)(line 60,45,45,60)(line 45,60,30,45)"
				+ "(line 30,45,45,30)(line 45,30,60,45)(line 60,45,45,60)(line 45,60,30,45)"
				+ "(line 20,35,35,20)(line 35,20,50,35)(line 50,35,35,50)(line 35,50,20,35)",
				 _painter1.toString());
	}
}

