package ui;

import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ItemBar extends VBox {
	
	public ItemBar() {
		
		this.setPrefHeight(400);
		this.setAlignment(Pos.CENTER);
		this.setBorder(new Border(new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));
		Storage storage = new Storage();
		storage.setAlignment(Pos.BOTTOM_CENTER);
		Text item = new Text();
		item.setFont(Font.font("Comic Sans Ms", FontWeight.BOLD, FontPosture.REGULAR, 20 ));
		item.setText("Item");
		item.setFill(Color.AZURE);
		item.setTextAlignment(TextAlignment.CENTER);
		this.setBackground(new Background(new BackgroundFill(Color.SADDLEBROWN, null, null)));
		this.getChildren().addAll(item, storage);
	}

	

}
