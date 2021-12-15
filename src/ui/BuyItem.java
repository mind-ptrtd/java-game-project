package ui;

import fish.Fish;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import logic.GameObject;
import ui.ItemShop;

public class BuyItem extends Button {
	
	private ItemShop itemShop;

	public BuyItem(String item) {
			
		this.setPadding(new Insets(5));
		itemShop = new ItemShop(item);
			// try //
		String itemImagePath = ClassLoader.getSystemResource(itemShop.getUrl()).toString();
			// ImageView itemImage = new ImageView(itemImagePath);
		ImageView imageView = new ImageView(new Image(itemImagePath));
		imageView.setFitHeight(48);
		imageView.setFitWidth(48);
		this.setGraphic(imageView);
	
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	
	
	}
			
			
			
			
			
			
		
	
	public void highlight() {
		// TODO 
		this.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		}
		
	public void unhighlight() {
			// TODO
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		}
		
		
		
//	private void setTooltip() {
//		Tooltip	tooltip = new Tooltip();
//		tooltip.setFont(new Font(12));
//		
//		if (cropBf isDrawn()) {
//			
//		}
//		tooltip.setText(+ object.getPrice() + item.getIncomeText());
//		this.setOnMouseMoved((MouseEvent e) -> {
//			if (item != null)
//			tooltip.show(this, e.getScreenX(), e.getScreenY()+10);
//		});
//		this.setOnMouseExited((MouseEvent e) -> {
//			tooltip.hide();
//		});		
//	}

		

	


}
