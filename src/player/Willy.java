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

public class Willy extends Entity implements Updateable, Animateable,FishingSyncable {
	private ImageView imageView;
	private Animation animation;
	private boolean isWalkLeft, isWalkRight, isFishing, isDead, isFront, isNearMe;
	private float speedX;

	public Willy() {
		super();
		x = 400;
		y = 120;
		z = 10;
		speedX = 1.5f;
		isFront = true;
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
		FishingSystem.getInstance().setGlobalXY(getX(), getY());
	}
	// Update Logic
	public void logicUpdate() {
		
		// Control
		if (isNearMe && InputUtility.getKeyPressed(KeyCode.E)) { // keep Hook
			isWalkLeft = isWalkRight = false;
			isFront = true;
			setFishing(false);
			upDateSprite();
		} else if (InputUtility.getKeyPressed(KeyCode.SPACE)) { // Go Fishing
			isWalkLeft = isWalkRight = false;
			isFront = false;
			setFishing(true);
			upDateSprite();
		} else {
			// MOVE RIGHT
			if (!InputUtility.getKeyPressed(KeyCode.SPACE) && isWalkRight && InputUtility.getKeyPressed(KeyCode.D)
					&& !InputUtility.getKeyPressed(KeyCode.SPACE)) {
				move(Direction.RIGHT);
			}
			if ((isWalkLeft || isFront) && !InputUtility.getKeyPressed(KeyCode.SPACE)
					&& InputUtility.getKeyPressed(KeyCode.D) && !InputUtility.getKeyPressed(KeyCode.SPACE)) {
				move(Direction.RIGHT);
				isWalkRight = true;
				isFront = isWalkLeft = false;
				upDateSprite();
			}
			// MOVE LEFT
			if (!InputUtility.getKeyPressed(KeyCode.SPACE) && isWalkLeft && InputUtility.getKeyPressed(KeyCode.A)
					&& !InputUtility.getKeyPressed(KeyCode.SPACE)) {
				move(Direction.LEFT);
			}
			if (!InputUtility.getKeyPressed(KeyCode.SPACE) && (isWalkRight || isFront)
					&& InputUtility.getKeyPressed(KeyCode.A)) {
				move(Direction.LEFT);
				isWalkLeft = true;
				isFront = isWalkRight = false;
				upDateSprite();
			}
			// RESET STAND AFTER WALK
			if (!InputUtility.getKeyPressed(KeyCode.A) && !InputUtility.getKeyPressed(KeyCode.D) && !isFishing) {
				isWalkLeft = isWalkRight = false;
				isFront = true;
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
		if (isDead) {
			imageView.setImage(null);
			animation.stop();
			GameObject.getInstance();
			imageView.setImage(GameObject.emptySprite);
			setSpriteProporty(0, 0, 0, 0, 0, 0);
		} else if (isFishing) {
			imageView.setImage(null);
			animation.stop();
			GameObject.getInstance();
			imageView.setImage(GameObject.playerPic);
			setSpriteProporty(4, 4, 0, 8 * 32, 32, 4 * 32);
		} else if (isWalkLeft) {
			imageView.setImage(null);
			animation.stop();
			GameObject.getInstance();
			imageView.setImage(GameObject.playerPic);
			setSpriteProporty(4, 4, 0 * 32, 6 * 32, 32, 2 * 32);
		} else if (isWalkRight) {
			imageView.setImage(null);
			animation.stop();
			GameObject.getInstance();
			imageView.setImage(GameObject.playerPic);
			setSpriteProporty(4, 4, 0 * 32, 2 * 32, 32, 2 * 32);
		} else if (isFront) { // STAND STILL
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
		if (!isDead) {
			animation = new SpriteAnimation(imageView, Duration.millis(1000), count, coloumn, offsetX, offsetY, width,
					height);
			animation.setCycleCount(Animation.INDEFINITE);
			animation.play();
		}
	}

	// Getter - Setter
	public void setFishing(boolean isFishing) {
		this.isFishing = isFishing;
		FishingSystem.setGlobalFishing(isFishing); // Update Global
	}

}
