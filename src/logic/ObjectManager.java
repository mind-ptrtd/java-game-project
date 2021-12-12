package logic;

import java.util.ArrayList;
import java.util.List;

import fish.*;
import main.Main;
import player.Willy;
import ui.Map;

public class ObjectManager {
	private static final List<Entity> gameObjectContainer = new ArrayList<Entity>(); 
	
	public ObjectManager(){
		
		Map bgMap = new Map();
		GameLogic.getInstance().add(bgMap);

		for (int i=0;i<5;i++) {
			this.addNewObject(new BlueFish());
		}
		
		//new change
		Willy player1 = new Willy();
		this.addNewObject(player1);
		
		/*
		Background bg = new Background();
		GameLogic.getInstance().add(background);
		tank = new Tank(320,240);
		mine = new Mine(100,100);
		addNewObject(tank);
		addNewObject(mine);
		*/
	}
	
	protected void addNewObject(Entity entity){
		gameObjectContainer.add(entity);
		GameLogic.getInstance().add(entity);
	}
	

	public void update(){
		for(Entity obj : gameObjectContainer) {
			if(obj instanceof Updateable) {
				((Updateable) obj).update();
			}
		}
	}


}
