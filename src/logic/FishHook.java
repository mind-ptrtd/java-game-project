package logic;

import java.util.ArrayList;

import animation.Animateable;
import animation.ImageViewable;
import animation.SpriteAnimation;
import fish.Fish;
import input.InputUtility;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import main.Main;

public class FishHook extends Entity implements Updateable, ImageViewable {
	private ImageView imageView;
	private Animation animation;
	private boolean isNearMe, isFishing;
	private double willyX, willyY;
	private float speedX, speedY;

	public FishHook() {
		super();
		x = FishingSystem.getInstance().getGlobalWillyX();
		y = FishingSystem.getInstance().getGlobalWillyY(); // under his Willy's Feet
		z = 30;
		speedY = 2;
		createFirstSprite();
		upDateSprite();
	}

	public void move(Direction dir) {
		if (dir == Direction.DOWN) {
			if (y <= 550) {
				y += speedY;
			}
		} else {
			if (y >= 120 + 3 * 32) { // Will Fish Height is 3 block of 32 bits
				y -= speedY;
			}
		}
	}
	public void updateNear() {
		if (isFishing && Math.abs(FishingSystem.getInstance().getGlobalWillyY() + 3 * 32 - getY()) <= 10) { // Near
			isNearMe = true;
		} else {
			isNearMe = false;
		}
	}
	// Update Logic
	public void logicUpdate() {
		// Pull Global to local
		double willyX = FishingSystem.getInstance().getGlobalWillyX();
		double willyY = FishingSystem.getInstance().getGlobalWillyY(); // under his Willy's Feet
		speedY = 2 * MarketSystem.getHookSpeedFactor();
		// Push local to global
		FishingSystem.getInstance().setGlobalFishHookXY(getX(), getY());
		FishingSystem.getInstance().setNearMe(isNearMe);

		if (!isFishing) {
			x = willyX;
			y = willyY;
		}
		updateNear();
		// CONTROL
		if (!isFishing && InputUtility.getKeyPressed(KeyCode.SPACE)) { // Fishing Show Hook
			x = willyX;
			y = willyY + 3 * 32; // Willy sprites Height 3 block of 32 bits
			isFishing = true;
			upDateSprite();
		} else if (isNearMe && InputUtility.getKeyPressed(KeyCode.E)) { // Keep Hook (Hide Hook)
			x = willyX;
			y = willyY;
			isFishing = false;
			upDateSprite();
		} else { // MOVEMENT
			if (isFishing && InputUtility.getKeyPressed(KeyCode.S)) { // Go Down
				move(Direction.DOWN);
			}
			if (isFishing && InputUtility.getKeyPressed(KeyCode.W)) { // Go Up
				move(Direction.UP);
			}
		}
	}

	// Draw On Screen
	public void draw(GraphicsContext gc) {
		upDateImageView();
	}

	public void upDateImageView() {
		Main.removeFromPane(imageView);
		imageView.relocate(getX(), getY());
		Main.addToPane(imageView);
	}

	// For CUT IMAGE (Do not Have Animation)
	private static int offsetX;
	private static int offsetY;
	private static int width;
	private static int height;
	
	public void setSpriteProporty(int offsetX, int offsetY, int width, int height) {
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.width = width;
		this.height = height;
	}
	
	public void createFirstSprite() {
		imageView = new ImageView(GameObject.getInstance().emptySprite);
		setSpriteProporty(0,0,32,32);
	}

	public void upDateSprite() {
		if (!isFishing) { // Hide Hook
			imageView.setImage(null);
			imageView.setImage(GameObject.getInstance().emptySprite);
			setSpriteProporty(0,0,32,32);
		}
		if (isFishing) { // Show Hook
			imageView.setImage(null);
			imageView.setImage(GameObject.getInstance().fishHook);
			setSpriteProporty(0,0,32,32);
		}
		imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
	}

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

}
