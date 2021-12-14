package logic;

import java.util.ArrayList;

import fish.Fish;
import fish.FishWhere;

public class FishingSystem {
	private static final FishingSystem instance = new FishingSystem();
	private static double globalWillyX, globalWillyY;
	private static double globalFishHookX, globalFishHookY;
	private static boolean globalFishing, isNearMe;
	
	private static int HookSize = 10;
	private static boolean isBackPackFull,isHookFull;
	private static int fishSea,fishHook;
	
	// FishSpawn
	private static int poolSize = 20;
	private static int fishCount;
	public static void increaseFishCount() {
		fishCount++;
	}
	public static void decreaseFishCount() {
		fishCount--;
	}

	// FISH HOOK ------------------------------------//
	
	public static boolean isHookFull() {
		return fishHook>=HookSize;
	}

	// FISH CONTAINER
	private static final ArrayList<Fish> allFishContainer = new ArrayList<Fish>();
	
	public void addFishIntoAllFishContainer(Fish fish) {
		allFishContainer.add(fish);
	}
	public static void fishUpdate() {
		for (int i = allFishContainer.size() - 1; i >= 0; i--) {
			Fish fishInLoop = allFishContainer.get(i);
			/*
			int fishSEA = 0;
			int fishHOOK = 0;
			if(fishInLoop.getFishwhere() == FishWhere.SEA) {
				fishSEA++;
			} else if(fishInLoop.getFishwhere() == FishWhere.HOOK){
				fishHOOK++;
			} else if(fishInLoop.getFishwhere() == FishWhere.HELL) {
				allFishContainer.remove(fishInLoop);
			}
			setFishSea(fishSEA);
			setFishHook(fishHOOK);
			*/
			if(fishInLoop.getFishwhere() == FishWhere.HELL) {
				allFishContainer.remove(fishInLoop);
			}
		}
	}

	
	
	
	
	
	
	//-------------------------//
	
	public static void setHookSize(int hookSize) {
		HookSize = hookSize;
	}
	public static ArrayList<Fish> getAllFishContainer(){
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
	public static int getFishSea() {
		return fishSea;
	}
	public static void setFishSea(int fishSea) {
		FishingSystem.fishSea = fishSea;
	}

	public static int getFishHook() {
		return fishHook;
	}
	public static void setFishHook(int fishHook) {
		FishingSystem.fishHook = fishHook;
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
}
