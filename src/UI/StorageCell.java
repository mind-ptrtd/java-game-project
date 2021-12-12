package UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class StorageCell extends Button {
	
	private boolean isDrawn;
	private final String BLUEFISH_IMAGE = "blueFish.png";
	private ObservableList<StorageCell> cell = FXCollections.observableArrayList();
	
	public StorageCell() {
		this.setPrefWidth(42);
		this.setPrefHeight(42);
		this.setMinWidth(42);
		this.setMinHeight(42);
		this.setPadding(new Insets(3));
		this.setBorder(new Border(new BorderStroke(Color.BURLYWOOD, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, new BorderWidths(2.5))));
	
		String blueFishImage = ClassLoader.getSystemResource(BLUEFISH_IMAGE).toString();
		Image bfImage = new Image(blueFishImage) ;
		WritableImage crop = new WritableImage(bfImage.getPixelReader(),0,0,32,32);
		this.draw(crop);
		
		this.setTooltip();
	}
		
		
	


		private void draw(Image image) {
			BackgroundSize bgSize = new BackgroundSize(42,42,false,false,false,false);
			BackgroundImage bgImg = new BackgroundImage(image, null, null, null, bgSize);
			BackgroundImage[] bgImgA = {bgImg};
			this.setBackground(new Background(bgImgA));
			this.setDrawn(true);
		}
			
		public boolean isDrawn() {
			return isDrawn;
		}

		public void setDrawn(boolean isDrawn) {
			this.isDrawn = isDrawn;
		}

		private void setTooltip() {
			Tooltip tooltip = new Tooltip();
			tooltip.setFont(new Font(12));
			tooltip.setText("Blue fish"+ "\n" + "Price: 200" + "\n" + "Quantity: 3");
			this.setOnMouseMoved((MouseEvent e) -> {
				tooltip.show(this, e.getScreenX(), e.getScreenY() + 10);
			});
			this.setOnMouseExited((MouseEvent e) -> {
				tooltip.hide();
			});		
		}
		
		static Button yesButton() {
			Button yes = new Button("YES");
			yes.setPrefWidth(12);
			yes.setPrefHeight(10);
		}
		
}
