package ui;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Storage extends GridPane {
	
	private ObservableList<StorageCell> cell = FXCollections.observableArrayList();
	
	
	public Storage() {
			
		this.setPrefWidth(150);
		this.setAlignment(Pos.BOTTOM_CENTER);
		this.setVgap(12);
		this.setHgap(20);
		this.setPadding(new Insets(12));
		this.setBackground(new Background(new BackgroundFill(Color.SADDLEBROWN, null, null)));
		
		for (int x = 0; x < 2; x++) {
			for (int y = 0; y < 10; y++) {
				StorageCell cells = new StorageCell();
				cell.add(cells);
				this.add(cells, x, y);
			}
		}
	
		
//		this.setPrefWidth(48);
//		this.setPrefHeight(48);
//		this.setMinWidth(48);
//		this.setMinHeight(48);
//		this.setPadding(new Insets(8));
//		this.setBorder(new Border(new BorderStroke(Color.BURLYWOOD, BorderStrokeStyle.SOLID, 
//				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}

}
