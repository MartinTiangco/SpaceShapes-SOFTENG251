package spaceshapes.views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import spaceshapes.Shape;
import spaceshapes.CarrierShape;
import spaceshapes.ShapeModel;

/**
 * Implementation of Task1 of SpaceShapes III
 * @author Martin Tiangco
 *
 */
public class Task1 implements TreeModel {
	private ShapeModel _adaptee;
	
	// === Protected listeners for use in Task2 === //
	protected List<TreeModelListener> _treeModelListener;
	// ======================================= //
	
	public Task1(ShapeModel root) {
		_adaptee = root;
		_treeModelListener = new ArrayList<TreeModelListener>();
	}
	
	public void addTreeModelListener(TreeModelListener l) {
		_treeModelListener.add(l);
	}
	
	public void removeTreeModelListener(TreeModelListener l) {
		_treeModelListener.remove(l);
	}


	/**
	 * Returns the child of a parent Shape at a given index. 
	 * If the parent is a CarrierShape it will return the Shape at the given index if index given is
	 * within the bounds. Otherwise, it will return null.
	 * If the parent is a simple shape it will return null.
	 * @param parent parent of the child we are looking for
	 * @param index index of the child
	 */
	public Object getChild(Object parent, int index) {
		if (parent instanceof CarrierShape) {
			if (index >= 0 && index < getChildCount(parent)) {	
				return (( CarrierShape ) parent).shapeAt(index);
			} else {
				return null;	//if index parameter is out of bounds
			}
		}
		return null;	//if simple shape is given as parent parameter
	}

	/**
	 * Return the number of children of the parent parameter. Only shapes of 
	 * type CarrierShape may have children. For other shapes zero will be returned
	 * as they do not have the capability to have children.
	 * @param parent The parent shape
	 */
	public int getChildCount(Object parent) {
		int result = 0;
		
		if (parent instanceof CarrierShape) {
			result = (( CarrierShape ) parent).shapeCount();
		}
		return result;
	}

	/**
	 * Returns the index of child of the parent. 
	 * If the child does not exist, return -1.
	 * If either parent or child is null, returns -1. 
	 * If the parent is a simple shape, return -1.
	 * @param parent the parent Shape
	 * @param child the child Shape of the parent
	 */
	public int getIndexOfChild(Object parent, Object child) {
		if (parent instanceof CarrierShape) {
			CarrierShape carrier = ( CarrierShape ) parent;
			if (carrier.contains(( Shape ) child)) {			//if parent CarrierShape contains the child Shape
				return carrier.indexOf(( Shape ) child);
			} else {
				return -1;										//if parent CarrierShape does not contain it
			}
		}
		return -1;		//if parent is not a CarrierShape
	}

	/**
	 * Returns the root of the ShapeModel.
	 */
	public Object getRoot() {
		return _adaptee.root();
	}

	/**
	 * Checks if the node is the leaf. If the node is a simple shape, it will always
	 * be true since they can't have children. If the node is a CarrierShape, it will return false even if it
	 * currently does not have children, as they have the capability to store children.
	 */
	public boolean isLeaf(Object node) {
		return !(node instanceof CarrierShape);
	}

	/**
	 * Empty body implementation - fine for Task 1
	 */
	public void valueForPathChanged(TreePath path, Object newValue) {
		
	}
}
