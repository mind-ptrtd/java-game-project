package shop;

import main.Main;

public class ShopSystem {
	private static float walkSpeedFactor = 1f;
	private static float hookSpeedFactor = 1f;
	private static int HookSize = 1;
	private static int money;
	private static float earnFactor = 1f;
	
	public static void shopUpdate() {
		Main.getManagerTab().shopUpdate();
		Main.getBuyTab().getPlayerSpeed().shopUpdate();
		Main.getBuyTab().getHookSize().shopUpdate();
		Main.getBuyTab().getHookSpeed().shopUpdate();
		Main.getBuyTab().getFishPrice().shopUpdate();
		
		// WANT TO UPDATE ADD IT HERE
	} 
	
	// ----------------------------//
	public static int getMoney() {
		return money;
	}

	public static void setMoney(int money) {
		ShopSystem.money = money;
	}

	public static float getWalkSpeedFactor() {
		return walkSpeedFactor;
	}

	public static void setWalkSpeedFactor(float walkSpeedFactor) {
		ShopSystem.walkSpeedFactor = walkSpeedFactor;
	}

	public static float getHookSpeedFactor() {
		return hookSpeedFactor;
	}

	public static void setHookSpeedFactor(float hookSpeedFactor) {
		ShopSystem.hookSpeedFactor = hookSpeedFactor;
	}

	public static int getHookSize() {
		return HookSize;
	}

	public static void setHookSize(int hookSize) {
		HookSize = hookSize;
	}

	public static float getEarnFactor() {
		return earnFactor;
	}

	public static void setEarnFactor(float earnFactor) {
		ShopSystem.earnFactor = earnFactor;
	}
	

}
