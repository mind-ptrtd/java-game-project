package fishing;

import animation.ImageViewable;
import input.InputUtility;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import logic.Direction;
import logic.Entity;
import logic.GameObject;
import logic.Updateable;
import main.Main;
import shop.ShopSystem;

public class Hook extends Entity implements Updateable, ImageViewable, FishingSyncable {
	private ImageView imageView;
	private HookState currentState;
	private boolean isNearMe;
	private float speedY;
	private double willyX, willyY;

	public Hook() {
		super();
		z = 100;
		speedY = 2;
		currentState = HookState.KEEP;
		createFirstSprite();
		updateSprite();
	}

	private void move(Direction dir) {
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

	private void updateNear() {
		if (currentState == HookState.FISHING
				&& Math.abs(FishingSystem.getGlobalWillyY() + 3 * 32 - getY()) <= 10) { // Near
			isNearMe = true;
		} else {
			isNearMe = false;
		}
	}

	public void fishingSync() {
		// Pull Global to local
		willyX = FishingSystem.getGlobalWillyX();
		willyY = FishingSystem.getGlobalWillyY(); // Update Willy Pos
		speedY = 2 * ShopSystem.getHookSpeedFactor(); // Set Speed Rely on GlobalSpeed ()
		// Push local to global
		FishingSystem.setGlobalFishHookXY(getX(), getY());
		if(isNearMe) {
			FishingSystem.setNearMe(true);
		} else {
			FishingSystem.setNearMe(false);
		}
	}

	// Update Logic
	public void logicUpdate() {
		fishingSync();
		updateNear();
		if (currentState == HookState.KEEP) {
			x = willyX; 
			y = willyY;
		}
		updateNear();
		// CONTROL
		if (currentState == HookState.KEEP && InputUtility.getKeyPressed(KeyCode.SPACE)) { // Fishing Show Hook
			x = willyX;
			y = willyY + 3 * 32; // Willy sprites Height 3 block of 32 bits
			currentState = HookState.FISHING;
			isNearMe = true;
			updateSprite();
			//System.out.println("SHOW");
		} else if (currentState == HookState.FISHING && isNearMe && InputUtility.getKeyPressed(KeyCode.E)) { // Keep
			x = willyX;
			y = willyY;
			currentState = HookState.KEEP;
			isNearMe = false;
			//System.out.println("KEEP");
			updateSprite();
		} else { // MOVEMENT
			if (currentState == HookState.FISHING && InputUtility.getKeyPressed(KeyCode.S)) { // Go Down
				move(Direction.DOWN);
			}
			if (currentState == HookState.FISHING && InputUtility.getKeyPressed(KeyCode.W)) { // Go Up
				move(Direction.UP);
			}
		}
	}

	// Draw On Screen
	public void draw(GraphicsContext gc) {
		updateImageView();
	}

	public void updateImageView() {
		Main.removeFromPane(imageView);
		imageView.relocate(getX(), getY());
		Main.addToPane(imageView);
	}

	// For Cut ImageView (Do not Have Animation)
	private final int offsetX = 0;
	private final int offsetY = 0;
	private final int width = 32;
	private final int height = 32;

	public void createFirstSprite() {
		imageView = new ImageView(GameObject.emptySprite);
	}

	public void updateSprite() {
		if (currentState == HookState.KEEP) { // Hide Hook
			imageView.setImage(null);
			imageView = new ImageView(GameObject.emptySprite);
		}
		if (currentState == HookState.FISHING) { // Show Hook
			imageView.setImage(null);
			imageView = new ImageView(GameObject.fishHook);
		}
		// Cut ImageView
		imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
	}

}
