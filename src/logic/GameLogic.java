package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.application.Platform;

import fish.*;
import main.Main;
import player.Willy;
import ui.Map;

public class GameLogic {
	private static final List<Entity> gameObjectContainer = new ArrayList<Entity>();
	public static Random random = new Random();
	
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

	public void initializeFish() {
		int numberOfFish = 1+random.nextInt(19);
		for (int i = 0; i < numberOfFish; i++) {
			addNewObject(FishRandomizer());
		}
	}

	public Fish FishRandomizer() {
		ArrayList<FishType> allFishType = new ArrayList<FishType>(List.of(
				FishType.BLUEFISH,FishType.TUNA,FishType.TRASH,
				FishType.CARP,FishType.BASS,FishType.LIONFISH,
				FishType.MIDNIGHT,FishType.RAINBOWTROUT,FishType.SQUID
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
	}
	

	// Handle Logic among Updateble
	public void logicUpdate() {
		for (Entity obj : gameObjectContainer) {
			if (obj instanceof Updateable) {
				((Updateable) obj).logicUpdate();
			}
		}
	}

}
