package logic;

import java.util.ArrayList;

import fish.Fish;

public class FishingSystem {
	// Hook And Fish And Willy
	// Will be soon implemented
	// When I need 
	private static final FishingSystem instance = new FishingSystem();
	private static final ArrayList<Fish> HookInventory = new ArrayList<Fish>();
	private static double globalWillyX, globalWillyY;
	private static double globalFishHookX, globalFishHookY;
	private static boolean globalFishing, isNearMe;
	
	// Fish Inventory 
	private static final ArrayList<Fish> BackPackInventory= new ArrayList<Fish>();
	
	public static void addFishToHook(Fish fish) {
			getHookinventory().add(fish);			
	}
	public static void removeFishFromHook(Fish fish) {
		getHookinventory().remove(fish);
	}
	public static void addFishToBackPack(Fish fish) {
		if(!getBackpackinventory().contains(fish)) {
			getBackpackinventory().add(fish);			
		}
	}
	public static void removeFishFromBackPack(Fish fish) {
		getBackpackinventory().remove(fish);
	}
	
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
