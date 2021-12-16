package ui;

import fishing.FishingSystem;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import main.Main;
import shop.ShopSystem;
import shop.ShopUpdateable;
import shop.UpgradeType;


public class ManagerTab extends HBox implements ShopUpdateable {
	private Text moneyText;
	private Button buyBtn;
	private int moneyShow;
	private static ItemShop SelectedItemShop;

	public ManagerTab() {
		this.setAlignment(Pos.CENTER_RIGHT);
		this.setPrefWidth(800);
		this.setPrefHeight(50);
		this.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));
		this.setBackground(new Background(new BackgroundFill(Color.DIMGRAY, null, null)));
		
		Text space = new Text("   ");
		initMoneyText();
		initBuyBtn();
		
		SelectedItemShop = new ItemShop("NONE");
		
		this.getChildren().addAll(moneyText, buyBtn, space);
	}

	private void initMoneyText() {
		Text moneyText = new Text();
		// will be changing to sum of fish price soon
		moneyText.setFont(Font.font("Comic Sans Ms", FontWeight.LIGHT, FontPosture.REGULAR, 18));
		moneyText.setFill(Color.LIGHTGOLDENRODYELLOW);
		setMoneyText(moneyText);
		
	}
	
	private void initBuyBtn() {
		Button buyBtn = new Button("Buy");
		
		buyBtn.setFont(Font.font("Comic Sans Ms", FontWeight.BOLD, FontPosture.REGULAR, 15));
		buyBtn.setAlignment(Pos.CENTER);
		buyBtn.setPrefWidth(70);
		buyBtn.setPrefHeight(30);
		buyBtn.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
		buyBtn.setBorder(new Border(new BorderStroke(Color.AZURE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		setBuyBtn(buyBtn);
		buyBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(!Main.getBuyTab().isVisible()) {
					Main.getBuyTab().setVisible(true);
					//System.out.println("CLICKED");
					Main.getGameScreen().requestFocus();
				} else {
					Main.getBuyTab().setVisible(false);
					//System.out.println("CLICKED");
					Main.getGameScreen().requestFocus();
				}
			}
		});
		buyBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				buyBtn.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
				Main.getGameScreen().requestFocus();
			}
		});
		buyBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				buyBtn.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
				Main.getGameScreen().requestFocus();
			}
		});
	}
	public void shopUpdate() {
		moneyShow = ShopSystem.getMoney();
		moneyText.setText("Fish Catched: " + FishingSystem.getFishHook()+" / "+ShopSystem.getHookSize() + "   " + "Money: " + moneyShow + "          ");
		if (SelectedItemShop.getUpgradeType() == UpgradeType.NONE) {
			// Nothing Happen
		} else if (BuyPopUp.getIsBuy() && ShopSystem.getMoney() >= SelectedItemShop.getPrice()) {			
			ShopSystem.setMoney(ShopSystem.getMoney()-SelectedItemShop.getPrice());
			switch (SelectedItemShop.getUpgradeType()) {
			case FISHPRICE:
				ShopSystem.setEarnFactor(ShopSystem.getEarnFactor()*1.1f);
				ItemShop.setLevelFishPrice(ItemShop.getLevelFishPrice()+1);
				break;
			case HOOKSPEED:
				ShopSystem.setHookSpeedFactor(ShopSystem.getHookSpeedFactor()*1.1f);
				ItemShop.setLevelHookSpeed(ItemShop.getLevelHookSpeed()+1);
				break;
			case HOOKSIZE:
				ShopSystem.setHookSize(ShopSystem.getHookSize()*2);
				ItemShop.setLevelHookSize(ItemShop.getLevelHookSize()+1);
				break;
			case PLAYERSPEED:
				ShopSystem.setWalkSpeedFactor(ShopSystem.getWalkSpeedFactor()+0.05f);
				ItemShop.setLevelPlayerSpeed(ItemShop.getLevelPlayerSpeed()+1);
				break;
			default:
				// Do Nothing
			}
			BuyPopUp.setBuy(false);			
			System.out.println("LEVEL : "+ItemShop.getLevelPlayerSpeed());
			System.out.println("money spend");
			setSelectedItemShop(new ItemShop("NONE"));
		} 
	}

	// Getter-Setter
	private void setMoneyText(Text moneyText) {
		this.moneyText = moneyText;
		
	}
	
	private void setBuyBtn(Button buyBtn) {
		this.buyBtn = buyBtn;
		
	}

	public static ItemShop getSelectedItemShop() {
		return SelectedItemShop;
	}

	public static void setSelectedItemShop(ItemShop selectedItemShop) {
		SelectedItemShop = selectedItemShop;
	}
	

}
