package fish;

import java.util.Random;

import animation.SpriteAnimation;
import input.InputUtility;
import animation.Animateable;
import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import logic.Entity;
import logic.FishingSystem;
import logic.GameLogic;
import logic.GameObject;
import logic.Updateable;
import main.Main;
import logic.FishingSystem;

public abstract class Fish extends Entity implements Updateable, Animateable {

	protected abstract ImageView imageViewFish();

	protected abstract boolean isNeedToRotate();

	protected final static float speedFactor = 0.5f;

	public static Random random = new Random();

	// For Child Class
	protected FishType fishType;
	protected String name;
	protected int price;
	protected boolean isLeft, isRight;

	private float speedX;
	private ImageView imageView;
	private Animation animation;
	private boolean isHook, isKeep, isTurnLeft, isTurnRight,isNearMe;
	private double fishHookX, fishHookY;

	// Sea Level in range(7,17) Due to Height of GameScreen 600px
	public Fish() {
		isLeft = random.nextBoolean();
		isRight = !isLeft;
		z = 20;
		x = random.nextInt(400);
		y = 7 * 32 + random.nextInt(10 * 32);
		isKeep = isHook = false;
		createFirstSprite();
		upDateSprite();
	}

	// Handle Logic
	public void logicUpdate() {
		// Pull Global to local
		fishHookX = FishingSystem.getInstance().getGlobalFishHookX();
		fishHookY = FishingSystem.getInstance().getGlobalFishHookY();
		isNearMe = FishingSystem.getInstance().getNearMe();
		
		if (isHook) {
			x = fishHookX;
			y = fishHookY;
		}

		if (Math.abs(fishHookX + 16 - getX()) <= 10 && Math.abs(fishHookY + 10 - getY()) <= 10) {
			isHook = true;
			// Stick with Hook
			x = fishHookX;
			y = fishHookY;			
			FishingSystem.getInstance().addFishToHook(this);
			//System.out.println("CATCH THIS FISH : "+this);
		}
		if (isNearMe && InputUtility.getKeyPressed(KeyCode.E)) { // Keep Fish
			
			for (Fish fish : FishingSystem.getHookinventory()) {
				Platform.runLater(new Runnable() {
					public void run() {
						FishingSystem.getInstance().removeFishFromHook(fish);
						FishingSystem.getInstance().addFishToBackPack(fish);
						System.out.println("ADD THIS FISH TO BACKPACK : "+fish);
						fish.setDestroyed(true);
						fish.isHook = false;
						fish.isKeep = true;
						upDateSprite();
					}
				});
			}
			System.out.println("HOOK :"+FishingSystem.getInstance().getHookinventory());
			System.out.println("BACKPACK :"+FishingSystem.getInstance().getBackpackinventory());
		}

		// Swimming Area Offset = 20 (Preventing Collision edges)
		if (x <= 0 + 20) {
			isTurnRight = true;
			isRight = true;
			isLeft = false;
			upDateSprite();
		}
		if (x >= 800 - 32 - 20) {
			isTurnLeft = true;
			isLeft = true;
			isRight = false;
			upDateSprite();
		}
		if (!isHook && !isKeep) {
			if (isRight) {
				x += speedX;
			} else {
				x -= speedX;
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
	@Override
	public void draw(GraphicsContext gc) {
		// System.out.println(getX());
		// System.out.println(getY());
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
		// Hook (Dead)
		if (isKeep) {
			imageView.setImage(null);
			animation.stop();
			imageView.setImage(GameObject.getInstance().emptySprite);
		}
		// TurnRight
		if (isTurnRight) {
			imageView.setImage(null);
			animation.stop();
			imageView = imageViewFish();
			isTurnRight = false;
		}
		// TurnLeft
		if (isTurnLeft) {
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
		if (!isKeep) {
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
}
