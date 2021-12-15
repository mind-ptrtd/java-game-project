package logic;

public class ShopSystem {
	private static float fishSpeedFactor = 0.5f;
	private static float walkSpeedFactor = 1f;
	private static float hookSpeedFactor = 1f;
	private static int HookSize = 100;

	private static int money;

	// ----------------------------//
	public static int getMoney() {
		return money;
	}

	public static void setMoney(int money) {
		ShopSystem.money = money;
	}

	public static float getFishSpeedFactor() {
		return fishSpeedFactor;
	}

	public static void setFishSpeedFactor(float fishSpeedFactor) {
		ShopSystem.fishSpeedFactor = fishSpeedFactor;
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
}
