package bindingofisaac;

import javafx.scene.image.Image;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;

public class ImageHelper {

	public static ImageView imageFromSource(String filename) {
		return new ImageView((Element) new Image(Image.class.getResource(filename).toExternalForm()));
	}

}
