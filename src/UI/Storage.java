package ui;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Storage extends GridPane {
	
	private ObservableList<StorageCell> cell = FXCollections.observableArrayList();
	private final String BLUEFISH_IMAGE = "blueFish.png";
	private boolean isDrawn;
	
	
	
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
	}
}
//		String blueFishImage = ClassLoader.getSystemResource(BLUEFISH_IMAGE).toString();
//		Image bfImage = new Image(blueFishImage) ;
//		this.draw(bfImage);
//		
//	}
//		
//		
//		private void draw (Image image) {
//			BackgroundSize bgSize = new BackgroundSize(12,20,false,false,false,false);
//			BackgroundImage bgImg = new BackgroundImage(image, null, null, null, bgSize);
//			BackgroundImage[] bgImgA = {bgImg};
//			this.setBackground(new Background(bgImgA));
//			this.setDrawn(true);
//		}
//			
//		public boolean isDrawn() {
//			return isDrawn;
//		}
//
//		public void setDrawn(boolean isDrawn) {
//			this.isDrawn = isDrawn;
//		}
 	
		
//		this.setPrefWidth(48);
//		this.setPrefHeight(48);
//		this.setMinWidth(48);
//		this.setMinHeight(48);
//		this.setPadding(new Insets(8));
//		this.setBorder(new Border(new BorderStroke(Color.BURLYWOOD, BorderStrokeStyle.SOLID, 
//				CornerRadii.EMPTY, BorderWidths.DEFAULT)));



