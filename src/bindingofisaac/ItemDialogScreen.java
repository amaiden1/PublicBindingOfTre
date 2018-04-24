package bindingofisaac;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static bindingofisaac.Constants.*;

public class ItemDialogScreen {

	private VBox outerBox;
	private ImageView background;
	private ImageView item;
	private Text title;
	private Text desc;

	public ItemDialogScreen(Item item/*, Image background*/) {

		outerBox = new VBox();
		outerBox.setAlignment(Pos.CENTER);
		this.item = item.getImageView();
		outerBox.setBackground(new Background(new BackgroundFill(Paint.valueOf("BLACK"), CornerRadii.EMPTY, Insets.EMPTY)));
		outerBox.setPrefSize(ROOM_WIDTH, ROOM_HEIGHT);

		//this.background = new ImageView(background);

		title = new Text("You have collected " + item.getItemName() + "!");
		title.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 20));
		title.setFill(Paint.valueOf("WHITE"));
		desc = new Text("This is the description");
		desc.setFont(Font.font(16));
		desc.setFill(Paint.valueOf("WHITE"));
		Text dismissLabel = new Text("click or press any key to dismiss");
		dismissLabel.setFont(Font.font(13));
		dismissLabel.setFill(Paint.valueOf("WHITE"));

		//pane.getChildren().add(this.background);
		VBox contentBox = new VBox();
		contentBox.setPadding(new Insets(8,8,8,8));
		contentBox.setAlignment(Pos.CENTER);
		contentBox.getChildren().add(this.item);
		contentBox.getChildren().add(title);
		contentBox.getChildren().add(desc);
		contentBox.getChildren().add(dismissLabel);
		outerBox.getChildren().add(contentBox);
		contentBox.relocate((ROOM_WIDTH / 2) - (contentBox.getBoundsInParent().getWidth() / 2), 500);
	}

	public VBox getPane() {
		return outerBox;
	}

}
