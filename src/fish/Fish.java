package fish;

import java.util.Random;

import animation.SpriteAnimation;
import animation.Animateable;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import logic.Entity;
import logic.GameLogic;
import logic.Updateable;
import main.Main;

public abstract class Fish extends Entity implements Updateable, Animateable {
	
	protected abstract ImageView imageViewFish();
	protected abstract boolean isNeedToRotate();
	
	public static Random random = new Random();
	protected FishType fishType; 
	protected String name;
	protected int price;
	protected boolean isLeft, isRight, isTurnLeft, isTurnRight;
	private float speedX;
	private ImageView imageView;
	private Animation animation;
	private boolean isHook, isKeep;

	// Sea Level in range(7,17) Due to Height of GameScreen 600px
	public Fish() {
		this.isLeft = random.nextBoolean();
		ToggleRight();
		this.z = 20;
		this.x = random.nextInt(400);
		this.y = 7 * 32 + random.nextInt(10 * 32);
		this.isKeep = false;
		this.isHook = false;
		setSpeed(0.1f + random.nextFloat());
		createFirstSprite();
		upDateSprite();
	}
	
	public void ToggleRight() {
		this.isRight = !isLeft;
	}

	public void ToggleLeft() {
		this.isLeft = !isRight;
	}

	// Handle Logic
	public void update() {
		if (isHook) {
			// hooked();
		}
		if (isKeep) {
			// KeepInStorage (Despawn)
		}
		// Swimming Area Offset = 10 (Preventing Collision edges)
		if (x <= 0 + 10) {
			isTurnRight = true;
			isRight = true;
			ToggleLeft();
			upDateSprite();
		}
		if (x >= 800 - 32 - 10) {
			isTurnLeft = true;
			isLeft = true;
			ToggleRight();
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
		//System.out.println(getX());
		//System.out.println(getY());
		upDateImageView();
	}

	public void upDateImageView() {
		Main.removeFromPane(imageView);
		imageView.relocate(getX(), getY());
		Main.addToPane(imageView);
	}

	private void createFirstSprite() {
		// GetImageView Of each Fish
		imageView = imageViewFish();
	}

	public void upDateSprite() {
		// Hook (Dead)
		if (isKeep) {
			imageView.setImage(null);
			animation.stop();
			imageView.setImage(GameLogic.getInstance().emptySprite);
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
