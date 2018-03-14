package bindingofisaac;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.text.Element;

public class ImageHelper {

	public static ImageView imageFromSource(String filename) {
		return new ImageView(new Image(Image.class.getResource(filename).toExternalForm()));
	}

}
