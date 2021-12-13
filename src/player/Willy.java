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
import logic.Entity;
import logic.GameObject;
import logic.Updateable;
import main.Main;
import logic.FishingSystem;

public class Willy extends Entity implements Updateable, Animateable {
	private ImageView imageView;
	private Animation animation;
	private boolean isWalkLeft, isWalkRight, isFishing, isDead, isFront,isNearMe;
	private float speedX;

	public Willy() {
		super();
		this.x = 400;
		this.y = 120;
		this.z = 10;
		speedX = 1.5f;
		isWalkLeft = isWalkRight = isFishing = isDead = false;
		isFront = true;
		createFirstSprite();
		upDateSprite();
	}
	// margin 20
	public void move(direction dir) {
		if (dir == direction.RIGHT) {
			if (x <= 800 - 32 - 20) {
				x += speedX;
			}
		} else {
			if (x >= 0 + 20) {
				x -= speedX;
			}
		}
	}
	// Update Logic
	public void logicUpdate() {
		// Pull Global to local
		isNearMe = FishingSystem.getInstance().getNearMe();
		// Push local to global
		FishingSystem.getInstance().setGlobalXY(getX(), getY());
		
		
		if (isNearMe && InputUtility.getKeyPressed(KeyCode.E)) { // reset
			isWalkLeft = isWalkRight = false;
			setFishing(false);
			isFront = true;
			upDateSprite();
			System.out.println("Keep");
		}
		if (InputUtility.getKeyPressed(KeyCode.SPACE)) {
			isFront = false;
			setFishing(true);
			upDateSprite();
		}
		// MOVE RIGHT
		if (isWalkRight && InputUtility.getKeyPressed(KeyCode.D)) {
			move(direction.RIGHT);
		}
		if ((isWalkLeft || isFront) && InputUtility.getKeyPressed(KeyCode.D)) {
			move(direction.RIGHT);
			isWalkRight = true;
			isFront = isWalkLeft = false;
			upDateSprite();
		}
		// MOVE LEFT
		if (isWalkLeft && InputUtility.getKeyPressed(KeyCode.A)) {
			//
			move(direction.LEFT);
		}
		if ((isWalkRight || isFront) && InputUtility.getKeyPressed(KeyCode.A)) {
			move(direction.LEFT);
			isWalkLeft = true;
			isFront = isWalkRight = false;
			upDateSprite();
		}
		if (!InputUtility.getKeyPressed(KeyCode.A) && !InputUtility.getKeyPressed(KeyCode.D) && !isFishing) {
			isWalkLeft = isWalkRight = false;
			isFront = true;
			upDateSprite();
		}
	}

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
	// For Sprite Animation

	private static int coloumn;
	private static int count;
	private static int offsetX;
	private static int offsetY;
	private static int width;
	private static int height;

	public void setSpriteProporty(int column, int count, int offsetX, int offsetY, int width, int height) {
		this.coloumn = column;
		this.count = count;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.width = width;
		this.height = height;
	}

	public void createFirstSprite() {
		imageView = new ImageView(GameObject.getInstance().playerPic);
		setSpriteProporty(1, 1, 0, 0, 32, 2 * 32);
		imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
		startAnimation();
	}

	public void upDateSprite() {
		if (isDead) {
			imageView.setImage(null);
			animation.stop();
			imageView.setImage(GameObject.getInstance().emptySprite);
			setSpriteProporty(0, 0, 0, 0, 0, 0);
		} else if (isFishing) {
			imageView.setImage(null);
			animation.stop();
			imageView.setImage(GameObject.getInstance().playerPic);
			setSpriteProporty(4, 4, 0, 8 * 32, 32, 4 * 32);
		} else if (isWalkLeft) {
			imageView.setImage(null);
			animation.stop();
			imageView.setImage(GameObject.getInstance().playerPic);
			setSpriteProporty(4, 4, 0 * 32, 6 * 32, 32, 2 * 32);
		} else if (isWalkRight) {
			imageView.setImage(null);
			animation.stop();
			imageView.setImage(GameObject.getInstance().playerPic);
			setSpriteProporty(4, 4, 0 * 32, 2 * 32, 32, 2 * 32);
		} else if (isFront) { // STAND STILL
			imageView.setImage(null);
			animation.stop();
			imageView.setImage(GameObject.getInstance().playerPic);
			setSpriteProporty(1, 1, 0, 0, 32, 2 * 32);
		}
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
	public boolean isFishing() {
		return isFishing;
	}
	public void setFishing(boolean isFishing) {
		this.isFishing = isFishing;
		FishingSystem.getInstance().setFishing(isFishing); // Update Global
	}

}
