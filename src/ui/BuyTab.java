package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class BuyTab extends GridPane {
	private BuyItem fishPrice;
	private BuyItem hookSpeed;
	private BuyItem hookSize;
	private BuyItem playerSpeed;
	
	private ObservableList<BuyItem> buyItemList = FXCollections.observableArrayList();

	public BuyTab() {

		this.setAlignment(Pos.CENTER);
		this.setHgap(20);
		this.setPrefWidth(300);
		this.setPrefHeight(100);
		this.setLayoutX(260);
		
		this.setBorder(new Border(new BorderStroke(Color.AZURE, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, new BorderWidths(4.5))));
		this.setBackground(new Background(new BackgroundFill(Color.CADETBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

		fishPrice = new BuyItem("Fish Price");
		hookSpeed = new BuyItem("Hook Speed");
		hookSize = new BuyItem("Hook Size");
		playerSpeed = new BuyItem("Player Speed");
		buyItemList.addAll(fishPrice, hookSpeed, hookSize, playerSpeed);

		this.addRow(0, fishSpeed, hookSpeed, hookSize, playerSpeed);

	public BuyItem getHookSize() {
		return hookSize;
	}

	public BuyItem getPlayerSpeed() {
		return playerSpeed;
	}

	public ObservableList<BuyItem> getBuyItemList() {
		return buyItemList;
	}
	
}
