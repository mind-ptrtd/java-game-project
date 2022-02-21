package player;

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
import logic.Updateable;
import main.Main;
import shop.ShopSystem;
import logic.GameObject;

public class Willy extends Entity implements Updateable, Animateable, FishingSyncable {
	private boolean isNearMe;
	private float speedX;
	private static WillyState currentState;
	private ImageView imageView;
	private Animation animation;

	public Willy() {
		super();
		x = 400;
		y = 120;
		z = 100;
		speedX = 1.5f;
		currentState = WillyState.FRONT;
		createFirstSprite();
		updateSprite();
	}

	// margin 20
	private void move(Direction dir) {
		if (dir == Direction.RIGHT) {
			if (x + speedX <= 800 - 32 - 20) {
				x += speedX;
			} else {
				x = 800-32-20;
			}
		} else {
			if (x - speedX >= 0 + 20) {
				x -= speedX;
			} else {
				x = 0+20;
			}
		}
	}

	public void fishingSync() {
		// Pull Global to local
		isNearMe = FishingSystem.getNearMe();
		speedX = 1.5f * ShopSystem.getWalkSpeedFactor();
		// Push local to global
		FishingSystem.setGlobalWillyXY(getX(), getY());
		if(currentState == WillyState.FISHING) { 
			FishingSystem.setGlobalFishing(true); 		
		} else {
			FishingSystem.setGlobalFishing(false);
		}
	}

	// Update Logic
	public void logicUpdate() {
		fishingSync();

		// Control
		if (currentState == WillyState.FISHING && isNearMe && InputUtility.getKeyPressed(KeyCode.E)) { // keep Hook
			currentState = WillyState.FRONT;
			FishingSystem.setGlobalFishing(false);
			updateSprite();
		} else if (currentState != WillyState.FISHING && InputUtility.getKeyPressed(KeyCode.SPACE)) { // Go Fishing
			currentState = WillyState.FISHING;
			FishingSystem.setGlobalFishing(true); 
			updateSprite();
		} else {
			// MOVE RIGHT
			if (currentState == WillyState.WALKRIGHT && InputUtility.getKeyPressed(KeyCode.D)
					&& !InputUtility.getKeyPressed(KeyCode.SPACE)) {
				move(Direction.RIGHT);
			}
			if (currentState != WillyState.FISHING && currentState != WillyState.WALKRIGHT
					&& InputUtility.getKeyPressed(KeyCode.D)) {
				move(Direction.RIGHT);
				currentState = WillyState.WALKRIGHT;
				updateSprite();
			}
			// MOVE LEFT
			if (currentState == WillyState.WALKLEFT && InputUtility.getKeyPressed(KeyCode.A)) {
				move(Direction.LEFT);
			}
			if (currentState != WillyState.FISHING && currentState != WillyState.WALKLEFT
					&& InputUtility.getKeyPressed(KeyCode.A)) {
				move(Direction.LEFT);
				currentState = WillyState.WALKLEFT;
				updateSprite();
			}
			// RESET STAND AFTER WALK
			if (currentState != WillyState.FISHING && !InputUtility.getKeyPressed(KeyCode.A)
					&& !InputUtility.getKeyPressed(KeyCode.D)) {
				currentState = WillyState.FRONT;
				updateSprite();
			}
		}
	}

	public void draw(GraphicsContext gc) {
		updateImageView();
	}

	public void updateImageView() {
		Main.removeFromPane(imageView);
		imageView.relocate(getX(), getY());
		Main.addToPane(imageView);
	}

	// For Sprite Animation
	private int column;
	private int count;
	private int offsetX;
	private int offsetY;
	private int width;
	private int height;

	private void setSpriteProporty(int column, int count, int offsetX, int offsetY, int width, int height) {
		this.column = column;
		this.count = count;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.width = width;
		this.height = height;
	}

	public void createFirstSprite() {
		GameObject.getInstance();
		imageView = new ImageView(GameObject.playerPic);
		setSpriteProporty(1, 1, 0, 0, 32, 2 * 32);
		imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
		startAnimation();
	}

	public void updateSprite() {
		if (currentState == WillyState.FISHING) {
			imageView.setImage(null);
			animation.stop();
			GameObject.getInstance();
			imageView.setImage(GameObject.playerPic);
			setSpriteProporty(4, 4, 0, 8 * 32, 32, 4 * 32);
		} else if (currentState == WillyState.WALKLEFT) {
			imageView.setImage(null);
			animation.stop();
			GameObject.getInstance();
			imageView.setImage(GameObject.playerPic);
			setSpriteProporty(4, 4, 0 * 32, 6 * 32, 32, 2 * 32);
		} else if (currentState == WillyState.WALKRIGHT) {
			imageView.setImage(null);
			animation.stop();
			GameObject.getInstance();
			imageView.setImage(GameObject.playerPic);
			setSpriteProporty(4, 4, 0 * 32, 2 * 32, 32, 2 * 32);
		} else if (currentState == WillyState.FRONT) { // STAND STILL
			imageView.setImage(null);
			animation.stop();
			GameObject.getInstance();
			imageView.setImage(GameObject.playerPic);
			setSpriteProporty(1, 1, 0, 0, 32, 2 * 32);
		}
		imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
		startAnimation();
	}

	public void startAnimation() {
		imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
		animation = new SpriteAnimation(imageView, Duration.millis(1000), count, column, offsetX, offsetY, width,
				height);
		animation.setCycleCount(Animation.INDEFINITE);
		animation.play();
	}

}
