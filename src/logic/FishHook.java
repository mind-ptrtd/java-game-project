package logic;

import animation.ImageViewable;
import input.InputUtility;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import main.Main;

public class FishHook extends Entity implements Updateable, ImageViewable {
	private ImageView imageView;
	private boolean isNearMe, isFishing;
	private float speedY;

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
		double willyY = FishingSystem.getInstance().getGlobalWillyY(); // Update Willy Pos
		speedY = 2 * ShopSystem.getHookSpeedFactor(); // Set Speed Rely on GlobalSpeed ()
		// Push local to global
		FishingSystem.setGlobalFishHookXY(getX(), getY());
		FishingSystem.setNearMe(isNearMe);

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

	// For Cut ImageView (Do not Have Animation)
	private static final int offsetX = 0;
	private static final int offsetY = 0;
	private static final int width = 32;
	private static final int height = 32;

	public void createFirstSprite() {
		GameObject.getInstance();
		imageView = new ImageView(GameObject.emptySprite);
	}

	public void upDateSprite() {
		if (!isFishing) { // Hide Hook
			imageView.setImage(null);
			GameObject.getInstance();
			imageView.setImage(GameObject.emptySprite);
		}
		if (isFishing) { // Show Hook
			imageView.setImage(null);
			GameObject.getInstance();
			imageView.setImage(GameObject.fishHook);
		}
		// Cut ImageView
		imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

}
