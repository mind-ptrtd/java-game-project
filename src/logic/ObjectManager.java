package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fish.*;
import main.Main;
import player.Willy;
import ui.Map;

public class ObjectManager {
	private static final List<Entity> gameObjectContainer = new ArrayList<Entity>();
	public static Random random = new Random();

	public ObjectManager() {

		Map bgMap = new Map();
		GameLogic.getInstance().add(bgMap);

		initializeFish();

		// new change
		Willy player1 = new Willy();
		this.addNewObject(player1);

		/*
		 * Background bg = new Background(); GameLogic.getInstance().add(background);
		 * tank = new Tank(320,240); mine = new Mine(100,100); addNewObject(tank);
		 * addNewObject(mine);
		 */
	}

	public void initializeFish() {
		int numberOfFish = 15;
		for (int i = 0; i < numberOfFish; i++) {
			addNewObject(FishRandomizer());
		}

		/*
		 * for (int i=0;i<5;i++) { this.addNewObject(new BlueFish()); } for (int
		 * i=0;i<5;i++) { this.addNewObject(new Trash()); } for (int i=0;i<5;i++) {
		 * this.addNewObject(new Tuna ()); }
		 */
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

	protected void addNewObject(Entity entity) {
		gameObjectContainer.add(entity);
		GameLogic.getInstance().add(entity);
	}

	// Handle Logic among Updateble
	public void update() {
		for (Entity obj : gameObjectContainer) {
			if (obj instanceof Updateable) {
				((Updateable) obj).update();
			}
		}
	}

}
