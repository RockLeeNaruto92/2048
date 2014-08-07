package hust.thubm.game2048.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

import hust.thubm.game2048.controllers.StartController;
import hust.thubm.game2048.views.StartRenderer;

public class StartScreen extends GameScreen {

	private InputMultiplexer inputMultiplexer;
	
	public StartScreen(StartController controller, StartRenderer renderer) {
		super(controller, renderer);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		super.render(delta);
		getController().update(delta);
		getRenderer().render(delta);
	}
	
	@Override
	public void show(){
		inputMultiplexer = new InputMultiplexer(this, ((StartRenderer)getRenderer()).getStage());
		Gdx.input.setInputProcessor(inputMultiplexer);
	}
}
