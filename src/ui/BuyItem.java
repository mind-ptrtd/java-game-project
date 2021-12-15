package ui;

import fish.Fish;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
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
	private boolean isDrawn;

	public BuyItem(String item) {
			
		this.setPadding(new Insets(10));
		this.setPrefWidth(42);
		this.setPrefHeight(42);
		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		itemShop = new ItemShop(item);
			// try //
//		String itemImagePath = ClassLoader.getSystemResource(itemShop.getUrl()).toString();
//			// ImageView itemImage = new ImageView(itemImagePath);
//		ImageView imageView = new ImageView(new Image(itemImagePath));
		WritableImage icon = new WritableImage(itemShop.getImage().getPixelReader(),0,0,32,32);

		this.draw(icon);
		
	
	
	}
	
	private void draw(Image image) {
		BackgroundSize bgSize = new BackgroundSize(42, 42, false, false, false, false);
		BackgroundImage bgImg = new BackgroundImage(image, null, null, null, bgSize);
		BackgroundImage[] bgImgA = { bgImg };
		this.setBackground(new Background(bgImgA));
		this.setDrawn(true);
	}

	public boolean isDrawn() {
		return isDrawn;
	}

	public void setDrawn(boolean isDrawn) {
		this.isDrawn = isDrawn;
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
