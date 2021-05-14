package sample.workspace.drawingtools;

import com.sun.javafx.geom.Ellipse2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Pixel {
	private static final SnapshotParameters SP = new SnapshotParameters();
	private static final WritableImage WI = new WritableImage(1, 1);
	private static final PixelReader PR = WI.getPixelReader();
	
	
	public static int getArgb(Node n, double x, double y){
		synchronized (WI) {
			Rectangle2D r = new Rectangle2D(x, y, 1, 1);
			SP.setViewport(r);
			n.snapshot(SP, WI);
			return PR.getArgb(0,0);
		}
	}
	
	public static Color getColor(Node n, double x, double y)
	{
		synchronized (WI)
		{
			Rectangle2D r = new Rectangle2D(x, y, 1, 1);
			SP.setViewport(r);
			n.snapshot(SP, WI);
			return PR.getColor(0, 0);
		}
	}
}
