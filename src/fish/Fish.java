package fish;

import java.util.Random;

import animation.Animateable;
import animation.SpriteAnimation;
import input.InputUtility;
import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import logic.Direction;
import logic.Entity;
import logic.FishingSystem;
import logic.GameLogic;
import logic.GameObject;
import logic.ShopSystem;
import logic.Updateable;
import main.Main;
import logic.FishingSystem;

public abstract class Fish extends Entity implements Updateable, Animateable {

	protected abstract ImageView imageViewFish();
	protected abstract boolean isNeedToRotate();
	
	protected static float fishSpeedFactor = ShopSystem.getFishSpeedFactor();
	public static Random random = new Random();

	// For Child Class
	protected FishType fishType;
	protected String name;
	protected int price;
	protected boolean isLeft, isRight;

	private float speedX;
	private ImageView imageView;
	private Animation animation;
	private boolean isHook, isDead, isTurnLeft, isTurnRight, isNearMe;
	private double fishHookX, fishHookY;
	
	private FishWhere fishwhere;

	// Sea Level in range(7,17) Due to Height of GameScreen 600px
	public Fish() {
		isLeft = random.nextBoolean();
		isRight = !isLeft;
		z = 20;
		x = random.nextInt(400);
		y = 7 * 32 + random.nextInt(10 * 32);
		fishwhere = FishWhere.SEA; // START IN SEA
		createFirstSprite();
		upDateSprite();
	}

	public boolean checkHitBox() { // Near Hook Count as Catch (HITBOX 20*30)
		return Math.abs(fishHookX + 16 - (getX() + 16)) <= 10 && Math.abs(fishHookY + 10 - (getY() + 16)) <= 15;
	}

	public void move(Direction dir) {
		if (dir == Direction.RIGHT) {
			if (x >= 800 - 32 - 40) {
				isTurnLeft = isLeft = true;
				isRight = false;
				upDateSprite();
			} else {
				x += speedX;
			}
		} else {
			if (x <= 0 + 40) {
				isTurnRight = isRight = true;
				isLeft = false;
				upDateSprite();
			} else {
				x -= speedX;
			}
		}
	}
	private void killFish(Fish fish) {
		fish.isHook = false;
		fish.isDead = true;
		fish.fishwhere = FishWhere.DEAD;
		fish.setDestroyed(true);
		fish.upDateSprite();
		FishingSystem.getInstance().decreaseFishCount();
	}

	// Handle Logic
	public void logicUpdate() {
		// Pull Global to local
		fishHookX = FishingSystem.getInstance().getGlobalFishHookX();
		fishHookY = FishingSystem.getInstance().getGlobalFishHookY();
		isNearMe = FishingSystem.getInstance().getNearMe();
		fishSpeedFactor = ShopSystem.getFishSpeedFactor();
		
		if (isHook) {
			x = fishHookX;
			y = fishHookY;
		}
		if (fishType != fishType.BOMB && !isHook && !FishingSystem.getInstance().isHookFull() && checkHitBox()) { 
			// CATCH FISH
			isHook = true;
			fishwhere = FishWhere.HOOK;
			System.out.println("CATCH FISH : " + this.name);
			System.out.println("IS FULL : "+FishingSystem.getInstance().isHookFull());
			GameObject.getInstance().catchFishSound.play();
			
		} else if (this.fishType == fishType.BOMB && checkHitBox()) { // BOMB ATTACH
			// Play bomb Sound
			x = 200;
			y = 0;
			this.fishType = FishType.NONE; // preventing for bombing again
			killFish(this); // kill Bomb
			
			for (Fish fish : FishingSystem.getInstance().getAllFishContainer()) {
				//new Thread(()->{
				if(fish.fishwhere == fishwhere.HOOK) {
					fish.fishwhere = FishWhere.DEAD;
					System.out.println("FISH BOMB : " + fish.name);
					killFish(fish);
				}
				//).start();
			}
			GameObject.getInstance().bombSound.play();
				
		} else if (isNearMe && InputUtility.getKeyPressed(KeyCode.E)) { // Keep Fish
			for (Fish fish : FishingSystem.getInstance().getAllFishContainer()) {
				//new Thread(()->{
					if(fish.fishwhere == FishWhere.HOOK) {
						fish.fishwhere = FishWhere.DEAD;	// DIE
						System.out.println("SELL : " + fish.name);
						killFish(fish);
						ShopSystem.setMoney(ShopSystem.getMoney()+fish.price);
					}
				//}).start();
			}
			System.out.println("YOU GOT : "+ShopSystem.getMoney());
			GameObject.getInstance().pingSound.play();
		}

		if (!isHook && !isDead) {
			if (isRight) {
				move(Direction.RIGHT);
			} else {
				move(Direction.LEFT);
			}
		}
	}

	// For Sprite Animation
	private static final int COLUMNS = 4;
	private static final int COUNT = 4;
	private static final int OFFSET_X = 0;
	private static final int OFFSET_Y = 0;
	private static final int WIDTH = 32;
	private static final int HEIGHT = 32;

	// Handle Display
	public void draw(GraphicsContext gc) {
		upDateImageView();
	}

	public void upDateImageView() {
		Main.removeFromPane(imageView);
		imageView.relocate(getX(), getY());
		Main.addToPane(imageView);
	}

	public void createFirstSprite() {
		// GetImageView Of each Fish
		imageView = imageViewFish();
	}

	public void upDateSprite() {
		if (isDead) { // Keep (Dead)
			imageView.setImage(null);
			animation.stop();
			imageView.setImage(GameObject.getInstance().emptySprite);
		}
		if (isTurnRight) { // TurnRight
			imageView.setImage(null);
			animation.stop();
			imageView = imageViewFish();
			isTurnRight = false;
		}
		if (isTurnLeft) { // TurnLeft
			imageView.setImage(null);
			animation.stop();
			imageView = imageViewFish();
			isTurnLeft = false;
		}
		imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));
		if (isNeedToRotate()) {
			if (isRight) {
				imageView.setRotate(45);
			} else {
				imageView.setRotate(-45);
			}
		}
		startAnimation();
	}

	public void startAnimation() {
		if (!isDead) {
			animation = new SpriteAnimation(imageView, Duration.millis(1000), COUNT, COLUMNS, OFFSET_X, OFFSET_Y, WIDTH,
					HEIGHT);
			animation.setCycleCount(Animation.INDEFINITE);
			animation.play();
		}
	}

	public float getSpeed() {
		return speedX;
	}

	public void setSpeed(float speed) {
		this.speedX = speed;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ImageView getImageView() {
		return imageView;
	}
	public FishWhere getFishwhere() {
		return fishwhere;
	}
	public void setFishwhere(FishWhere fishwhere) {
		this.fishwhere = fishwhere;
	}
	

}
