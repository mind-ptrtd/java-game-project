package ui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.Main;
import shop.ShopUpdateable;

public class BuyItem extends Button implements ShopUpdateable{

	private ItemShop itemShop;
	private boolean isDrawn;
	private Tooltip tooltip;
	
	public BuyItem(String item) {

		this.setPadding(new Insets(10));
		this.setPrefWidth(42);
		this.setPrefHeight(42);
		this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, new BorderWidths(1.5))));

		itemShop = new ItemShop(item);
		WritableImage icon = new WritableImage(itemShop.getImage().getPixelReader(), 0, 0, 32, 32);

		this.draw(icon);
		this.setTooltip();

		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				ManagerTab.setSelectedItemShop(itemShop);
				Main.getSellPopUp().setVisible(true);
				Main.getGameScreen().requestFocus();
			}
		});

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
		this.tooltip = new Tooltip();
		tooltip.setFont(new Font(12));
		tooltip.setText(itemShop.getItemName() + itemShop.getPriceText() + itemShop.getLevelText());
		this.setOnMouseMoved((MouseEvent e) -> {
			if (itemShop.getItemName() != null) {
				tooltip.show(this, e.getScreenX(), e.getScreenY() + 10);
			}
		});

		this.setOnMouseExited((MouseEvent e) -> {
			tooltip.hide();
		});
	}

	public void shopUpdate() {
		tooltip.setText(itemShop.getItemName() + itemShop.getPriceText() + itemShop.getLevelText());
	}

}
