package ui;

import javafx.geometry.Insets;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class StorageCell extends Pane {
	
	public StorageCell() {
		this.setPrefWidth(42);
		this.setPrefHeight(42);
		this.setMinWidth(42);
		this.setMinHeight(42);
		this.setPadding(new Insets(3));
		this.setBorder(new Border(new BorderStroke(Color.BURLYWOOD, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, new BorderWidths(2.5))));
		
	}

}
