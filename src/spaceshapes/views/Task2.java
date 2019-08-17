package spaceshapes.views;

import javax.swing.event.TreeModelListener;
//import javax.swing.tree.TreeModel;
//import javax.swing.tree.TreePath;

import java.util.List;

import javax.swing.event.TreeModelEvent;

import spaceshapes.Shape;
//import spaceshapes.CarrierShape;
import spaceshapes.ShapeModel;
import spaceshapes.ShapeModelEvent;
import spaceshapes.ShapeModelEvent.EventType;
import spaceshapes.ShapeModelListener;

/**
 * Implementation of Task2 of SpaceShapes III
 * @author Martin Tiangco
 *
 */
public class Task2 extends Task1 implements ShapeModelListener {
	
	public Task2(ShapeModel root) {
		super(root);
	}

	/**
	 * Updates the TreeModel by converting a ShapeModelEvent to a TreeModelEvent
	 */
	public void update(ShapeModelEvent event) {
		ShapeModel source = event.source();							//retrieves the source of the event
		
		Shape[] children = new Shape[1]; 							//retrieves the shape being added/removed
		children[0] = event.operand();
		
		int[] childIndices = new int[1]; 							//retrieves the shape's index before removal, or after addition
		childIndices[0] = event.index(); 
		
		Shape[] pathArray = new Shape[1];
		if (!(event.parent() == null)) {							//checks if the Shape has parents
			List<Shape> path = event.parent().path();				//if so, create the path
			pathArray = path.toArray(new Shape[path.size()]);
		} else {
			pathArray = null;										//otherwise create a null array 
		}
		
		TreeModelEvent e = new TreeModelEvent(source, pathArray, childIndices, children); //Create the TreeModelEvent
		
		if (event.eventType().equals(EventType.ShapeAdded)) {
			for (TreeModelListener l : _treeModelListener) {
				l.treeNodesInserted(e);
			}
		} else if (event.eventType().equals(EventType.ShapeRemoved)) {
			for (TreeModelListener l : _treeModelListener) {
				l.treeNodesRemoved(e);
			}
		} /**else if (event.eventType().equals(EventType.ShapeMoved)) {
			
	
		}*/
	}
}
