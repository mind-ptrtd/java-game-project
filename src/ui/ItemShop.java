package ui;

import javafx.scene.image.Image;
import logic.GameObject;

public class ItemShop {
	
	private String itemName;
	private Image image;
	private int price;

	public ItemShop(String itemName){
		switch(itemName) {
			case "Fish Speed" :	image = GameObject.blueFish_Right;	price = 1000;	break;
			case "Hook Speed" : image = GameObject.fishHook; 	price = 1900; 	break;
			case "Hook Size" : 	image = GameObject.fishHook; 	price = 2500;	break;
			case "Player Speed" : image = GameObject.playerPic;	price = 800;	break;
			default : 			image = null; 	price = 0; itemName = "";
		}
		
		this.itemName = itemName;
	}
	

	public String getItemName() {
		return itemName;
	}

	public int getPrice() {
		return price;
	}

	public Image getImage() {
		return image;
	}

	public String getPriceText() {
		if(price > 0 ) {
			return "\nPrice: "+ price;			
		}
		return "";
	}

}
