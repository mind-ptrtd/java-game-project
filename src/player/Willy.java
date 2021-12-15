package player;

import animation.Animateable;
import animation.SpriteAnimation;
import input.InputUtility;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import logic.Direction;
import logic.Entity;
import logic.FishingSyncable;
import logic.Updateable;
import main.Main;
import logic.FishingSystem;
import logic.GameObject;
import logic.ShopSystem;

public class Willy extends Entity implements Updateable, Animateable, FishingSyncable {
	private ImageView imageView;
	private Animation animation;
	private boolean isNearMe;
	private float speedX;
	private static WillyState currentState;

	public Willy() {
		super();
		x = 400;
		y = 120;
		z = 100;
		speedX = 1.5f;
		currentState = WillyState.FRONT;
		createFirstSprite();
		upDateSprite();
	}

	// margin 20
	private void move(Direction dir) {
		if (dir == Direction.RIGHT) {
			if (x <= 800 - 32 - 20) {
				x += speedX;
			}
		} else {
			if (x >= 0 + 20) {
				x -= speedX;
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
			upDateSprite();
		} else if (currentState != WillyState.FISHING && InputUtility.getKeyPressed(KeyCode.SPACE)) { // Go Fishing
			currentState = WillyState.FISHING;
			FishingSystem.setGlobalFishing(true); 
			upDateSprite();
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
				upDateSprite();
			}
			// MOVE LEFT
			if (currentState == WillyState.WALKLEFT && InputUtility.getKeyPressed(KeyCode.A)) {
				move(Direction.LEFT);
			}
			if (currentState != WillyState.FISHING && currentState != WillyState.WALKLEFT
					&& InputUtility.getKeyPressed(KeyCode.A)) {
				move(Direction.LEFT);
				currentState = WillyState.WALKLEFT;
				upDateSprite();
			}
			// RESET STAND AFTER WALK
			if (currentState != WillyState.FISHING && !InputUtility.getKeyPressed(KeyCode.A)
					&& !InputUtility.getKeyPressed(KeyCode.D)) {
				currentState = WillyState.FRONT;
				upDateSprite();
			}
		}
	}

	public void draw(GraphicsContext gc) {
		upDateImageView();
	}

	public void upDateImageView() {
		Main.removeFromPane(imageView);
		imageView.relocate(getX(), getY());
		Main.addToPane(imageView);
	}

	// For Sprite Animation
	private static int coloumn;
	private static int count;
	private static int offsetX;
	private static int offsetY;
	private static int width;
	private static int height;

	public void setSpriteProporty(int column, int count, int offsetX, int offsetY, int width, int height) {
		Willy.coloumn = column;
		Willy.count = count;
		Willy.offsetX = offsetX;
		Willy.offsetY = offsetY;
		Willy.width = width;
		Willy.height = height;
	}

	public void createFirstSprite() {
		GameObject.getInstance();
		imageView = new ImageView(GameObject.playerPic);
		setSpriteProporty(1, 1, 0, 0, 32, 2 * 32);
		imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
		startAnimation();
	}

	public void upDateSprite() {
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
		animation = new SpriteAnimation(imageView, Duration.millis(1000), count, coloumn, offsetX, offsetY, width,
				height);
		animation.setCycleCount(Animation.INDEFINITE);
		animation.play();
	}

}
