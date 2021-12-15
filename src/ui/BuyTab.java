package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class BuyTab extends GridPane {
	
	private ObservableList<BuyItem> buyItemList = FXCollections.observableArrayList();
	
	public BuyTab() {
		
		this.setAlignment(Pos.CENTER);
		this.setVgap(10);
		this.setHgap(20);
		this.setPrefWidth(500);
		this.setPrefHeight(100);
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		
		
		BuyItem fishSpeed = new BuyItem("Fish Speed");
		BuyItem hookSpeed  = new BuyItem("Hook Speed");
		BuyItem hookSize = new BuyItem("Hook Size");
		BuyItem playerSpeed = new BuyItem("Player Speed");
	 	buyItemList.addAll(fishSpeed, hookSpeed, hookSize, playerSpeed);
	 	
//	 	for (BuyItem buyItem : buyItemList) {
//	 		buyItem.setOnMouseClicked(new EventHandler<MouseEvent>() {
//	 			//public void handle(ActionEvent event) 
//	 				
//	 			
//	 				
//	 		
//	 	}
	
	 	this.addRow(0, fishSpeed, hookSpeed, hookSize, playerSpeed);
	
	}
	

}
