package hust.thubm.game2048.views;

import hust.thubm.game2048.controllers.Controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameRenderer {
	private SpriteBatch spriteBatch;
	private Controller controller;

	public GameRenderer(Controller controller) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
		this.spriteBatch = new SpriteBatch();
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}

	public void setSpriteBatch(SpriteBatch spriteBatch) {
		this.spriteBatch = spriteBatch;
	}
	
	public void render(float delta){
		
	}
	
}
