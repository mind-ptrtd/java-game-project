package ui;

import javafx.scene.image.Image;
import logic.GameObject;
import shop.UpgradeType;

public class ItemShop {

	private String itemName;
	private Image image;
	private int price;
	private UpgradeType upgradeType;
	private static int levelFishPrice;
	private static int levelHookSpeed;
	private static int levelHookSize;
	private static int levelPlayerSpeed;

	public ItemShop(String itemName) {
		switch (itemName) {
		case "Fish Price":
			image = GameObject.blueFish_Right;
			price = 1000;
			upgradeType = UpgradeType.FISHPRICE;
			break;
		case "Hook Speed":
			image = GameObject.fishHook;
			price = 1900;
			upgradeType = UpgradeType.HOOKSPEED;
			break;
		case "Hook Size":
			image = GameObject.fishHook;
			price = 2500;
			upgradeType = UpgradeType.HOOKSIZE;
			break;
		case "Player Speed":
			image = GameObject.playerPic;
			price = 800;
			upgradeType = UpgradeType.PLAYERSPEED;
			break;
		case "NONE":
			image = null;
			price = 0;
			upgradeType = UpgradeType.NONE;
			break;
		default:
			image = null;
			price = 0;
			itemName = "";
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
		if (price > 0) {
			return "\nPrice: " + price;
		}
		return "";
	}

	public String getLevelText() {
		switch (upgradeType) {
		case FISHPRICE:
			return "\nLevel: " + levelFishPrice;
		case HOOKSPEED:
			return "\nLevel: " + levelHookSpeed;
		case HOOKSIZE:
			return "\nLevel: " + levelHookSize;
		case PLAYERSPEED:
			return "\nLevel: " + levelPlayerSpeed;
		default:
			return "ERROR";
		}
		
	}

	public UpgradeType getUpgradeType() {
		return upgradeType;
	}

	public static int getLevelFishPrice() {
		return levelFishPrice;
	}

	public static void setLevelFishPrice(int levelFishPrice) {
		ItemShop.levelFishPrice = levelFishPrice;
	}

	public static int getLevelHookSpeed() {
		return levelHookSpeed;
	}

	public static void setLevelHookSpeed(int levelHookSpeed) {
		ItemShop.levelHookSpeed = levelHookSpeed;
	}

	public static int getLevelHookSize() {
		return levelHookSize;
	}

	public static void setLevelHookSize(int levelHookSize) {
		ItemShop.levelHookSize = levelHookSize;
	}

	public static int getLevelPlayerSpeed() {
		return levelPlayerSpeed;
	}

	public static void setLevelPlayerSpeed(int levelPlayerSpeed) {
		ItemShop.levelPlayerSpeed = levelPlayerSpeed;
	}


	
}
