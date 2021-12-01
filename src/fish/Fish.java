package fish;

import java.util.Random;

import javafx.animation.Animation;
import javafx.scene.image.ImageView;
import logic.Entity;
public abstract class Fish extends Entity {
	public static Random random = new Random();
	protected String name;
	protected int speedX,price;
	protected boolean isLeft,isRight;
	private float timer,runTime;
	private ImageView imageView;
	private Animation animation;
	
	public Fish(){
		this.z = 30;
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

}
