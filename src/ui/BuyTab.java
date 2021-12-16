package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

		fishPrice = new BuyItem("Fish Price");
		hookSpeed = new BuyItem("Hook Speed");
		hookSize = new BuyItem("Hook Size");
		playerSpeed = new BuyItem("Player Speed");
		buyItemList.addAll(fishPrice, hookSpeed, hookSize, playerSpeed);

//	 	for (BuyItem buyItem : buyItemList) {
//	 		buyItem.setOnMouseClicked(new EventHandler<MouseEvent>() {
//	 			//public void handle(ActionEvent event) 
//	 				
//	 			
//	 				
//	 		
//	 	}

		this.addRow(0, fishPrice, hookSpeed, hookSize, playerSpeed);

	}

	public BuyItem getFishPrice() {
		return fishPrice;
	}

	public BuyItem getHookSpeed() {
		return hookSpeed;
	}

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
