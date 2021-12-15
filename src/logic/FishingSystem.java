package logic;

import java.util.ArrayList;

import fish.Fish;
import fish.FishWhere;

public class FishingSystem {
	private static final FishingSystem instance = new FishingSystem();
	private static double globalWillyX, globalWillyY;
	private static double globalFishHookX, globalFishHookY;
	private static boolean isglobalFishing, isNearMe;

	private static int HookSize;
	private static int fishHook;

	// FishSpawn
	private static int poolSize = 100;
	private static int fishCount;

	public static void increaseFishCount() {
		fishCount++;
	}

	public static void decreaseFishCount() {
		fishCount--;
	}

	// FISH HOOK ------------------------------------//

	public static boolean isHookFull() {
		return fishHook >= HookSize;
	}

	// FISH CONTAINER
	private final ArrayList<Fish> allFishContainer = new ArrayList<Fish>();

	public static void fishUpdate() {
		// Pull From Global
		HookSize = ShopSystem.getHookSize();

		int fishHOOK = 0;
		for (int i = getInstance().getAllFishContainer().size() - 1; i >= 0; i--) {
			Fish fishInLoop = getInstance().getAllFishContainer().get(i);
			if (fishInLoop.getFishwhere() == FishWhere.HOOK) {
				fishHOOK += 1;
			} else if (fishInLoop.getFishwhere() == FishWhere.DEAD) {
				getInstance().getAllFishContainer().remove(fishInLoop);
			}
		}
		// System.out.println(fishHOOK);
		setFishHook(fishHOOK);
	}

	// -------------------------//

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

	public void setGlobalXY(double x, double y) {
		globalWillyX = x;
		globalWillyY = y;
	}

	public static void setGlobalFishHookXY(double globalFishHookX, double globalFishHookY) {
		FishingSystem.globalFishHookX = globalFishHookX;
		FishingSystem.globalFishHookY = globalFishHookY;
	}

	public double getGlobalWillyX() {
		return globalWillyX;
	}

	public static double getGlobalFishHookX() {
		return globalFishHookX;
	}

	public double getGlobalWillyY() {
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
