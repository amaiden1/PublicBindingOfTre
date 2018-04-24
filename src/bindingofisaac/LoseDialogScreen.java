package bindingofisaac;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static bindingofisaac.Constants.ROOM_HEIGHT;
import static bindingofisaac.Constants.ROOM_WIDTH;

public class LoseDialogScreen {

	private VBox outerBox;
	private ImageView background;
	private Text title;
	private Text desc;

	public LoseDialogScreen(int floorLevel) {

		outerBox = new VBox();
		outerBox.setAlignment(Pos.CENTER);
		outerBox.setBackground(new Background(new BackgroundFill(Paint.valueOf("BLACK"), CornerRadii.EMPTY, Insets.EMPTY)));
		outerBox.setPrefSize(ROOM_WIDTH, ROOM_HEIGHT);

		//this.background = new ImageView(background);

		title = new Text("GAME OVER!");
		title.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 60));
		title.setFill(Paint.valueOf("WHITE"));
		desc = new Text("You made it to Floor " + floorLevel);
		desc.setFont(Font.font(30));
		desc.setFill(Paint.valueOf("WHITE"));

		//pane.getChildren().add(this.background);
		VBox contentBox = new VBox();
		contentBox.setPadding(new Insets(8,8,8,8));
		contentBox.setAlignment(Pos.CENTER);
		contentBox.getChildren().add(title);
		contentBox.getChildren().add(desc);
		outerBox.getChildren().add(contentBox);
		contentBox.relocate((ROOM_WIDTH / 2) - (contentBox.getBoundsInParent().getWidth() / 2), 500);
	}

	public VBox getPane() {
		return outerBox;
	}

}
