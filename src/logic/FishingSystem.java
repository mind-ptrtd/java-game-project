package logic;

import java.util.ArrayList;

import fish.Fish;

public class FishingSystem {
	// Hook And Fish And Willy
	// Will be soon implemented
	// When I need 
	private static final FishingSystem instance = new FishingSystem();
	private static double globalWillyX, globalWillyY;
	private static double globalFishHookX, globalFishHookY;
	private static boolean globalFishing, isNearMe;
	
	// Fish Inventory 
	private static ArrayList<Fish> BackPackInventory= new ArrayList<Fish>();
	private static ArrayList<Fish> HookInventory = new ArrayList<Fish>();
	private static int BackPackSize = 1000;
	private static int HookSize = 10;
	private static boolean isBackPackFull,isHookFull;
	
	// FishSpawn
	private static int poolSize = 20;
	private static int fishCount;
	public static void increaseFishCount() {
		fishCount++;
	}
	public static void decreaseFishCount() {
		fishCount--;
	}
	public static int getFishCount() {
		return fishCount;
	}
	
	public static int getPoolSize() {
		return poolSize;
	}
	// FISH HOOK ------------------------------------//
	public static void addFishToHook(Fish fish) {
		if(!isHookFull) {
			HookInventory.add(fish);
		} else {
			System.out.println("HOOK IS FULL");
		}
	}
	public static void removeFishFromHook(Fish fish) {
		HookInventory.remove(fish);
	}
	public static void removeAllFishFromHook() {
		HookInventory.clear();
	}
	public static boolean isHookFull() {
		return HookInventory.size()>=HookSize;
	}
	public static void setHookSize(int hookSize) {
		HookSize = hookSize;
	}
	// BACKPACK -------------------------------------//
	public static void addFishToBackPack(Fish fish) {
		if(!isBackPackFull) {
			if(!BackPackInventory.contains(fish)) {
				BackPackInventory.add(fish);			
			}
		} else {
			System.out.println("BACKPACK IS FULL");
		}
		
	}
	public static void removeFishFromBackPack(Fish fish) {
		BackPackInventory.remove(fish);
	}
	public static boolean isBackPackFull() {
		return BackPackInventory.size()>=BackPackSize;
	}
	public static void setBackPackSize(int backPackSize) {
		BackPackSize = backPackSize;
	}
	//-------------------------------------------------//
	
	public static FishingSystem getInstance() {
		return instance;
	}

	public static ArrayList<Fish> getHookinventory() {
		return HookInventory;
	}
	public static boolean getGlobalFishing() {
		return globalFishing;
	}

	public static void setFishing(boolean globalFishing) {
		FishingSystem.globalFishing = globalFishing;
	}

	public double getGlobalWillyX() {
		return globalWillyX;
	}

	public double getGlobalWillyY() {
		return globalWillyY;
	}

	public void setGlobalXY(double x, double y) {
		globalWillyX = x;
		globalWillyY = y;
	}

	public static double getGlobalFishHookX() {
		return globalFishHookX;
	}

	public static void setGlobalFishHookXY(double globalFishHookX, double globalFishHookY) {
		FishingSystem.globalFishHookX = globalFishHookX;
		FishingSystem.globalFishHookY = globalFishHookY;
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
	public static ArrayList<Fish> getBackpackinventory() {
		return BackPackInventory;
	}
}
