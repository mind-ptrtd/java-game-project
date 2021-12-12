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
import logic.GameLogic;
import logic.Updateable;
import main.Main;

public class Willy extends Entity implements Updateable, Animateable {
	private ImageView imageView;
	private Animation animation;
	private boolean isWalkLeft, isWalkRight, isFishing, isDead, isFront;
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
	public void move(direction dir) {
		if(dir==direction.RIGHT) {
			if(x<=800-32-10) {
				x+=speedX;				
			}
		} else {
			if(x>=0+10) {
				x-=speedX;				
			}
		}
	}
	public void update() {
		if (InputUtility.getKeyPressed(KeyCode.W)) { // reset
			isFishing = isWalkLeft = isWalkRight = false;
			isFront = true;
			upDateSprite();
		}
		if (InputUtility.getKeyPressed(KeyCode.S)) {
			isFishing = true;
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
		if(!InputUtility.getKeyPressed(KeyCode.A) && !InputUtility.getKeyPressed(KeyCode.D) && !isFishing) {
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

	private void createFirstSprite() {
		// GetImageView Of each Fish
		imageView = new ImageView(GameLogic.getInstance().playerPic);
		setSpriteProporty(1, 1, 0, 0, 32, 2 * 32);
		imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
		startAnimation();
	}

	public void upDateSprite() {
		if (isDead) {
			imageView.setImage(null);
			animation.stop();
			imageView.setImage(GameLogic.getInstance().emptySprite);
			setSpriteProporty(0, 0, 0, 0, 0, 0);
		} else if (isFishing) {
			imageView.setImage(null);
			animation.stop();
			imageView.setImage(GameLogic.getInstance().playerPic);
			setSpriteProporty(4, 4, 0, 8 * 32, 32, 4 * 32);
		} else if (isWalkLeft) {
			imageView.setImage(null);
			animation.stop();
			imageView.setImage(GameLogic.getInstance().playerPic);
			setSpriteProporty(4, 4, 0 * 32, 6 * 32, 32, 2 * 32);
		} else if (isWalkRight) {
			imageView.setImage(null);
			animation.stop();
			imageView.setImage(GameLogic.getInstance().playerPic);
			setSpriteProporty(4, 4, 0 * 32, 2 * 32, 32, 2 * 32);
		} else if (isFront) { // STAND STILL
			imageView.setImage(null);
			animation.stop();
			imageView.setImage(GameLogic.getInstance().playerPic);
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

}
