package spaceshapes.forms;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;
import javax.swing.SwingWorker;

import spaceshapes.ImageRectangleShape;
import spaceshapes.Shape;
import spaceshapes.CarrierShape;
import spaceshapes.ShapeModel;
import spaceshapes.forms.util.Form;
import spaceshapes.forms.util.FormComponent;
import spaceshapes.forms.util.FormHandler;

public class ImageShapeFormHandler implements FormHandler {
	private ShapeModel _model;
	private CarrierShape _parentOfNewShape;
	
	public ImageShapeFormHandler(
			ShapeModel model,
			CarrierShape parent) {
		_model = model;
		_parentOfNewShape = parent;
	}

	public void processForm(Form form) {
		long startTime = System.currentTimeMillis();
		
		// Read field values from the form.
		File imageFile = (File)form.getFieldValue(File.class, ImageFormElement.IMAGE);
		int width = form.getFieldValue(Integer.class, ShapeFormElement.WIDTH);
		int deltaX = form.getFieldValue(Integer.class, ShapeFormElement.DELTA_X);
		int deltaY = form.getFieldValue(Integer.class, ShapeFormElement.DELTA_Y);
		
		SwingWorker<Shape, Void> worker = new SwingWorker<Shape, Void>() {
			
			@Override
			protected Shape doInBackground() throws Exception {
				

				// Load the original image (ImageIO.read() is a blocking call).
				BufferedImage fullImage = null;
				try {
					fullImage = ImageIO.read(imageFile);
				} catch(IOException e) {
					System.out.println("Error loading image.");
				}
				
				int fullImageWidth = fullImage.getWidth();
				int fullImageHeight = fullImage.getHeight();
						
				BufferedImage scaledImage = fullImage;
						
				// Scale the image if necessary.
				if(fullImageWidth > width) {
					double scaleFactor = (double)width / (double)fullImageWidth;
					int height = (int)((double)fullImageHeight * scaleFactor);
							
					scaledImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
					Graphics2D g = scaledImage.createGraphics();
							
					// Method drawImage() scales an already loaded image. The 
					// ImageObserver argument is null because we don't need to monitor 
					// the scaling operation.
					g.drawImage(fullImage, 0, 0, width, height, null);
				}
				
				
				// Create the new Shape and add it to the model.
				ImageRectangleShape imageShape = new ImageRectangleShape(deltaX, deltaY, scaledImage);
//				_model.add(imageShape, _parentOfNewShape);
				
//				long elapsedTime = System.currentTimeMillis() - startTime;
//				System.out.println("Image loading and scaling took " + elapsedTime + "ms.");
				return imageShape;
			}
			
			@Override
			protected void done() {
				try {
					_model.add(get(), _parentOfNewShape);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				long elapsedTime = System.currentTimeMillis() - startTime;
				System.out.println("Image loading and scaling took " + elapsedTime + "ms.");
			}
		};
		worker.execute();
	}
}
