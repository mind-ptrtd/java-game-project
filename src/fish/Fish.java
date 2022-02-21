package fish;

import java.util.Random;

import animation.Animateable;
import animation.SpriteAnimation;
import fishing.FishingSyncable;
import fishing.FishingSystem;
import input.InputUtility;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import logic.Direction;
import logic.Entity;
import logic.GameObject;
import logic.Updateable;
import main.Main;
import shop.ShopSystem;

public abstract class Fish extends Entity implements Updateable, Animateable,FishingSyncable {

	protected abstract ImageView imageViewFish();

	protected abstract boolean isNeedToRotate();

	protected static float fishSpeedFactor = FishingSystem.getFishSpeedFactor();

	// For Child Class
	protected FishType fishType;
	protected String name;
	protected int price;

	private float speedX;
	private ImageView imageView;
	private Animation animation;
	private boolean isNeedToTurn, isNearMe;
	private double fishHookX, fishHookY;
	private Direction fishDirection;
	private FishState currentState;
	private final Random RANDOM = new Random();

	// Sea Level in range(7,17) Due to Height of GameScreen 600px
	public Fish() {
		if(RANDOM.nextBoolean()) {
			fishDirection = Direction.RIGHT;
		} else {
			fishDirection = Direction.LEFT;
		}
		z = RANDOM.nextInt(100);
		x = 20+RANDOM.nextInt(360);
		y = 7 * 32 + RANDOM.nextInt(10 * 32);
		currentState = FishState.SEA; // START IN SEA
		createFirstSprite();
		updateSprite();
	}
	
	private boolean checkHitBox() { // Near Hook Count as Catch (HITBOX 20*30)
		return Math.abs(fishHookX + 16 - (getX() + 16)) <= 10 && Math.abs(fishHookY + 10 - (getY() + 16)) <= 15;
	}

	private void move(Direction dir) {
		if (dir == Direction.RIGHT) {
			if (x >= 800 - 32 - 20) { // OffSet Right 20
				fishDirection = Direction.LEFT;
				isNeedToTurn = true;
				updateSprite();
			} else {
				x += speedX;
			}
		} else {
			if (x <= 0 + 20) { // OffSet Left 20
				fishDirection = Direction.RIGHT;
				isNeedToTurn = true;
				updateSprite();
			} else {
				x -= speedX;
			}
		}
	}

	private void killFish(Fish fish) {
		fish.currentState = FishState.DEAD;
		fish.setDestroyed(true);
		fish.updateSprite();
		FishingSystem.decreaseFishCount();
	}
	public void fishingSync() {
		// Pull Global to local
		fishHookX = FishingSystem.getGlobalFishHookX();
		fishHookY = FishingSystem.getGlobalFishHookY();
		isNearMe = FishingSystem.getNearMe();
	}
	// Handle Logic
	public void logicUpdate() {
		fishingSync();
		
		if (currentState==FishState.HOOK) {
			x = fishHookX;
			y = fishHookY;
		}
		if (fishType != FishType.BOMB && currentState!=FishState.HOOK && !FishingSystem.isHookFull() && checkHitBox()) {
			// CATCH FISH
			currentState = FishState.HOOK;
			//System.out.println("CATCH FISH : " + this.name);
			//System.out.println("IS FULL : " + FishingSystem.isHookFull());
			GameObject.catchFishSound.play();

		} else if (fishType == FishType.BOMB && checkHitBox()) { // BOMB ATTACH
			x = 200;
			y = 0;
			fishType = FishType.NONE; // preventing for bombing again
			killFish(this); // kill Bomb

			//new Thread(() -> {
				for (Fish fish : FishingSystem.getInstance().getAllFishContainer()) {
					if (fish.currentState == FishState.HOOK) {
						fish.currentState = FishState.DEAD;
						//System.out.println("FISH BOMB : " + fish.name);
						killFish(fish);
					}
				}
			//}).start();
			GameObject.bombSound.play();

		} else if (isNearMe && InputUtility.getKeyPressed(KeyCode.E)) { // Keep Fish
			//new Thread(() -> {
				for (Fish fish : FishingSystem.getInstance().getAllFishContainer()) {
					if (fish.currentState == FishState.HOOK) {
						fish.currentState = FishState.DEAD; // DIE
						//System.out.println("SELL : " + fish.name);
						killFish(fish);
						ShopSystem.setMoney(Math.round(ShopSystem.getMoney() + fish.price*ShopSystem.getEarnFactor()));
					}
				}
			//}).start();
			//System.out.println("YOU GOT : " + ShopSystem.getMoney());
			GameObject.pingSound.play();
		}

		if (currentState == FishState.SEA) {
			if (fishDirection == Direction.RIGHT) {
				move(Direction.RIGHT);
			} else {
				move(Direction.LEFT);
			}
		}
	}

	// For Sprite Animation
	private final int COLUMNS = 4;
	private final int COUNT = 4;
	private final int OFFSET_X = 0;
	private final int OFFSET_Y = 0;
	private final int WIDTH = 32;
	private final int HEIGHT = 32;

	// Handle Display
	public void draw(GraphicsContext gc) {
		updateImageView();
	}

	public void updateImageView() {
		Main.removeFromPane(imageView);
		imageView.relocate(getX(), getY());
		Main.addToPane(imageView);
	}

	public void createFirstSprite() {
		// GetImageView Of each Fish
		imageView = imageViewFish();
	}

	public void updateSprite() {
		if (currentState == FishState.DEAD) { // Dead Fish when Bomb or Sell
			imageView.setImage(null);
			animation.stop();
			GameObject.getInstance();
			imageView.setImage(GameObject.emptySprite);
		}
		if (isNeedToTurn) { // U-Turn
			imageView.setImage(null);
			animation.stop();
			imageView = imageViewFish();
			isNeedToTurn = false;
		}
		imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));
		if (isNeedToRotate()) {
			if (fishDirection == Direction.RIGHT) {
				imageView.setRotate(45);
			} else {
				imageView.setRotate(-45);
			}
		}
		startAnimation();
	}

	public void startAnimation() {
		if (currentState != FishState.DEAD) { // CheckAgain
			animation = new SpriteAnimation(imageView, Duration.millis(1000), COUNT, COLUMNS, OFFSET_X, OFFSET_Y, WIDTH,
					HEIGHT);
			animation.setCycleCount(Animation.INDEFINITE);
			animation.play();
		}
	}
	
	// Getter-Setter
	
	public void setSpeed(float speed) {
		this.speedX = speed;
	}
	
	public FishState getFishwhere() {
		return currentState;
	}

	public Direction getFishDirection() {
		return fishDirection;
	}
	
}
