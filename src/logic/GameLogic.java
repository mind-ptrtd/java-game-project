package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import fish.*;
import player.Willy;
import ui.Map;

public class GameLogic {
	private List<Entity> gameLogicContainer = new ArrayList<Entity>();
	private final Random RANDOM = new Random();
	private float spawnTimer;

	public GameLogic() {

		Map bgMap = new Map();
		GameObject.getInstance().add(bgMap);

		initializeFish();

		// new change
		Willy player = new Willy();
		this.addNewObject(player);

		Hook fishHook = new Hook();
		this.addNewObject(fishHook);
	}

	private void spawnMoreFish() {
		addNewObject(FishRandomizer());
		FishingSystem.increaseFishCount();
	}

	private void initializeFish() {
		int numberOfFish = 10 + RANDOM.nextInt(10);
		for (int i = 0; i < numberOfFish; i++) {
			addNewObject(FishRandomizer());
		}
	}

	private Fish FishRandomizer() {
		ArrayList<FishType> allFishType = new ArrayList<FishType>(
				List.of(FishType.BLUEFISH, FishType.TUNA, FishType.TRASH, FishType.CARP, FishType.BASS,
						FishType.LIONFISH, FishType.MIDNIGHT, FishType.RAINBOWTROUT, FishType.SQUID, FishType.BOMB));
		int randIdx = RANDOM.nextInt(allFishType.size());
		FishType newFishType = allFishType.get(randIdx);
		switch (newFishType) {
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

	private void addNewObject(Entity entity) {
		gameLogicContainer.add(entity);
		GameObject.getInstance().add(entity);
		if (entity instanceof Fish) {
			FishingSystem.getInstance().getAllFishContainer().add((Fish) (entity));
		}
	}
	
	// Handle Logic among Updateble
	public void logicUpdate() {
		spawnTimer += 0.1f;
		if ((spawnTimer >= 10) && (FishingSystem.getFishCount() < FishingSystem.getPoolSize())) {
			spawnMoreFish();
			spawnTimer = 0;
		}
		for (int i = gameLogicContainer.size() - 1; i >= 0; i--) {
			Entity entityInLoop = gameLogicContainer.get(i);
			if (entityInLoop.isDestroyed()) {
				gameLogicContainer.remove(i);
			} else if (entityInLoop instanceof Updateable) {
				((Updateable) entityInLoop).logicUpdate();
			}
		}
	}
}
