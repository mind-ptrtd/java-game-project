package ui;

public class ItemShop {
	
	private String itemName;
	private String url;
	private int price;
	private int income;

	ItemShop(String itemName){
		switch(itemName) {
			case "Fish Speed" :	url = "blueFish_Right.png"; price = 1000;	break;
			case "Hook Speed" : url = "fishHook.png"; 	price = 1900; 	break;
			case "Hook Size" : 	url = "fishHook.png"; 	price = 2500;	break;
			case "Player Speed" : url = "willy.png"; price = 800;	break;
			default : 			url = ""; 	price = 0; itemName = "";
		}
		
		this.itemName = itemName;
	}
	

	public String getItemName() {
		return itemName;
	}

	public int getPrice() {
		return price;
	}

	public String getUrl() {
		return url;
	}

	public int getIncome() {
		return income;
	}

	public String getPriceText() {
		if(price > 0 ) {
			return "\nPrice: "+ price;			
		}
		return "";
	}

}
