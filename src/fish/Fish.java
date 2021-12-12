package fish;

import java.util.Random;

import animation.SpriteAnimation;
import animation.Animateable;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.util.Duration;
import logic.Entity;
import logic.GameLogic;
import logic.Updateable;
import main.Main;
public abstract class Fish extends Entity implements Updateable,Animateable{
	public static Random random = new Random();
	protected String name;
	protected int speedX,price;
	protected boolean isLeft,isRight;
	private float timer,runTime;
	private ImageView imageView;
	private Animation animation;
	private boolean isHook,isKeep;
	
	private static final int COLUMNS  = 4;
	private static final int COUNT    = 4;
	private static final int OFFSET_X =  0;
	private static final int OFFSET_Y =  0;
	private static final int WIDTH    = 32;
	private static final int HEIGHT   = 32;
	
	// Sea Level in range(7,17) Due to Height of GameScreen 600px
	public Fish(){
		this.isLeft = random.nextBoolean();
		ToggleRight();
		this.z = 100;
		this.x = random.nextInt(400);
		this.y = 7*32+random.nextInt(10*32);
		this.isKeep = false;
		this.isHook = false;
		
		this.speedX = 2;
		
		createFirstSprite();
		createSprite();
	}
	public void ToggleRight() {
		this.isRight = !isLeft;
	}
	public void ToggleLeft() {
		this.isLeft = !isRight;
	}
	// Handle Logic
	public void update() {
		if(isHook) {
			//hooked();
		}
		if(isKeep) {
			// KeepInStorage (Despawn)
		}
		if(x<=0) {
			isRight=true;
			ToggleLeft();
		}
		if(x>=800-32) {
			isLeft=true;
			ToggleRight();
		}
		if(!isHook && !isKeep) {
			if(isRight) {
				x+=speedX;
			} else {
				x-=speedX;
			}
		}
	}
	// Handle Display
	
	
	@Override
	public void draw(GraphicsContext gc) {
		/* In Animation We can't use this
		WritableImage crop = new WritableImage(GameLogic.getInstance().fishPic.getPixelReader(),0,0,32,32);
		//gc.drawImage(GameLogic.getInstance().fishPic, 400, 400);
		gc.drawImage(crop, getX(), getY());
		gc.translate(speedX, 0);
		*/
		System.out.println(getX());
		System.out.println(getY());
		Main.removeFromPane(imageView);
		imageView.relocate(getX(), getY());
		Main.addToPane(imageView);
	}
	private void createFirstSprite() {
		imageView = new ImageView(GameLogic.getInstance().fishPic);
	}
	
	private void createSprite() {
		/*
		if(isKeep) {
			imageView.setImage(null);
			animation.stop();
			imageView = new ImageView(GameLogic.getInstance().emptySprite);
		}
		*/
		// imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));
		// imageView.relocate((double)(getX()), (double)(getY()));
		// imageView.relocate(arg0, arg1);
		if(!isKeep) {
			animation = new SpriteAnimation(
		             imageView,
		             Duration.millis(500),
		             COUNT, COLUMNS,
		             OFFSET_X, OFFSET_Y,
		             WIDTH, HEIGHT
			);
			animation.setCycleCount(Animation.INDEFINITE);
			animation.play();
		}
	}
	
	public int getSpeed() {
		return speedX;
	}

	public void setSpeed(int speed) {
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
