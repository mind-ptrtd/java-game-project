package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import fish.*;
import player.Willy;
import ui.Map;

public class GameLogic {
	private static final List<Entity> gameObjectContainer = new ArrayList<Entity>();
	public static Random random = new Random();
	private float spawnTimer;

	public GameLogic() {

		Map bgMap = new Map();
		GameObject.getInstance().add(bgMap);

		initializeFish();

		// new change
		Willy player = new Willy();
		this.addNewObject(player);

		FishHook fishHook = new FishHook();
		this.addNewObject(fishHook);
	}
	
	public void spawnMoreFish() {
		addNewObject(FishRandomizer());
		FishingSystem.increaseFishCount();
	}
	public void initializeFish() {
		int numberOfFish = 10 + random.nextInt(10);
		for (int i = 0; i < numberOfFish; i++) {
			addNewObject(FishRandomizer());
		}
	}

	public Fish FishRandomizer() {
		ArrayList<FishType> allFishType = new ArrayList<FishType>(List.of(
				FishType.BLUEFISH,FishType.TUNA,FishType.TRASH,
				FishType.CARP,FishType.BASS,FishType.LIONFISH,
				FishType.MIDNIGHT,FishType.RAINBOWTROUT,FishType.SQUID,FishType.BOMB
		));
		int randIdx = random.nextInt(allFishType.size());
		FishType newFishType = allFishType.get(randIdx);
		switch(newFishType) {
		  case BLUEFISH:
			  return new BlueFish();
		  case TUNA:
			  return new Tuna();
		  case TRASH:
			  return new Trash();
		  case CARP:
			  return new Carp();
		  case BASS:
			  return new Bass();
		  case LIONFISH:
			  return new LionFish();
		  case RAINBOWTROUT:
			  return new RainbowTrout();
		  case MIDNIGHT:
			  return new Midnight();
		  case SQUID:
			  return new Squid();
		  case BOMB:
		  	  return new Bomb();
		  default:
		    System.out.println("ERROR");
		    return new BlueFish();
		    // Blue Fish is Default
		}
	}

	public static void removeFromGameObject(Entity entity) {
		gameObjectContainer.remove(entity);
		GameObject.getInstance().remove(entity);
	}

	protected void addNewObject(Entity entity) {
		gameObjectContainer.add(entity);
		GameObject.getInstance().add(entity);
		if(entity instanceof Fish) {
			FishingSystem.getInstance().getAllFishContainer().add((Fish)(entity));
		}

	}
	// Handle Logic among Updateble
	public void logicUpdate() {
		spawnTimer+=0.1f;
		if((spawnTimer>=10) && (FishingSystem.getFishCount()<FishingSystem.getPoolSize())) {
			spawnMoreFish();
			spawnTimer=0;
		}
		for (Entity obj : gameObjectContainer) {
			if (obj instanceof Updateable) {
				((Updateable) obj).logicUpdate();
			}
		}
	}

}
