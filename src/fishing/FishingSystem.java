package fishing;

import java.util.ArrayList;

import fish.Fish;
import fish.FishState;
import shop.ShopSystem;

public class FishingSystem {
	private static final FishingSystem instance = new FishingSystem();
	private static double globalWillyX, globalWillyY;
	private static double globalFishHookX, globalFishHookY;
	private static boolean isglobalFishing, isNearMe;

	private final ArrayList<Fish> allFishContainer = new ArrayList<Fish>();
	private static int HookSize;
	private static int fishHook;
	private static int fishCount;
	private static int poolSize = 100;
	
	

	public static void fishUpdate() {
		// Pull From Global
		HookSize = ShopSystem.getHookSize();
		int fishHook = 0;
		for (int i = getInstance().getAllFishContainer().size() - 1; i >= 0; i--) {
			Fish fishInLoop = getInstance().getAllFishContainer().get(i);
			if (fishInLoop.getFishwhere() == FishState.HOOK) {
				fishHook += 1;
			} else if (fishInLoop.getFishwhere() == FishState.DEAD) {
				getInstance().getAllFishContainer().remove(fishInLoop);
			}
		}
		// System.out.println(fishHOOK);
		setFishHook(fishHook);
	}
	public static void increaseFishCount() {
		fishCount++;
	}

	public static void decreaseFishCount() {
		fishCount--;
	}

	public static boolean isHookFull() {
		return fishHook >= HookSize;
	}

	// -------GETTER-SETTER---------//

	public static void setHookSize(int hookSize) {
		HookSize = hookSize;
	}

	public ArrayList<Fish> getAllFishContainer() {
		return allFishContainer;
	}

	public static int getFishCount() {
		return fishCount;
	}

	public static int getPoolSize() {
		return poolSize;
	}

	public static FishingSystem getInstance() {
		return instance;
	}
	
	public static boolean getIsGlobalFishing() {
		return isglobalFishing;
	}
	
	public static void setGlobalFishing(boolean globalFishing) {
		FishingSystem.isglobalFishing = globalFishing;
	}
	
	public static int getFishHook() {
		return fishHook;
	}

	public static void setFishHook(int fishHook) {
		FishingSystem.fishHook = fishHook;
	}

	public static void setGlobalWillyXY(double x, double y) {
		FishingSystem.globalWillyX = x;
		FishingSystem.globalWillyY = y;
	}

	public static void setGlobalFishHookXY(double globalFishHookX, double globalFishHookY) {
		FishingSystem.globalFishHookX = globalFishHookX;
		FishingSystem.globalFishHookY = globalFishHookY;
	}

	public static double getGlobalWillyX() {
		return globalWillyX;
	}

	public static double getGlobalFishHookX() {
		return globalFishHookX;
	}

	public static double getGlobalWillyY() {
		return globalWillyY;
	}

	public static double getGlobalFishHookY() {
		return globalFishHookY;
	}

	public static boolean getNearMe() {
		return isNearMe;
	}

	public static void setNearMe(boolean isNearMe) {
		FishingSystem.isNearMe = isNearMe;
	}
}
