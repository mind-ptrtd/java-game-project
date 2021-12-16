package ui;

import fish.Fish;
import javafx.event.EventHandler;
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
import javafx.scene.text.Font;
import logic.GameObject;
import main.Main;
import shop.ShopSystem;
import ui.ItemShop;

public class BuyItem extends Button {
	
	private ItemShop itemShop;
	private boolean isDrawn;
	private boolean isClicked;

	public BuyItem(String item) {
			
		this.setPadding(new Insets(10));
		this.setPrefWidth(42);
		this.setPrefHeight(42);
		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		itemShop = new ItemShop(item);
		WritableImage icon = new WritableImage(itemShop.getImage().getPixelReader(),0,0,32,32);

		this.draw(icon);
		this.setTooltip();
		
		this.buttonClicked();
		
	
	
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
			
	// need to be fixed
	
	private void setTooltip() {
		Tooltip	tooltip = new Tooltip();
		tooltip.setFont(new Font(12));
		tooltip.setText(itemShop.getItemName() + itemShop.getPriceText());
		this.setOnMouseMoved((MouseEvent e) -> {
			if (itemShop.getItemName() != null) {
				tooltip.show(this, e.getScreenX(), e.getScreenY()+10);	
			}
		});
		
		this.setOnMouseExited((MouseEvent e) -> {
			tooltip.hide();
		});		
	}
	
	private void buttonClicked() {
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				System.out.println("CLICKED");
				ManagerTab.setSelectedItemShop(itemShop);
				Main.getSellPopUp().setVisible(true);
				Main.getGameScreen().requestFocus();
				setClicked(true);
			}
		});
	}

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}
	


	

		
	


}
