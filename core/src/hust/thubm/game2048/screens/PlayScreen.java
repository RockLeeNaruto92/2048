package hust.thubm.game2048.screens;

import hust.thubm.game2048.controllers.PlayController;
import hust.thubm.game2048.views.PlayRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

public class PlayScreen extends GameScreen {
	
	private InputMultiplexer inputMultiplexer;

	public PlayScreen(PlayController controller, PlayRenderer renderer) {
		super(controller, renderer);
	}
	
	@Override
	public void render(float delta){
		getController().update(delta);
		getRenderer().render(delta);
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stubg
		((PlayController)getController()).onTouchDragger(screenX, screenY);
		return false;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		((PlayController)getController()).onTouchDown(screenX, screenY);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		((PlayController)getController()).onTouchUp(screenX, screenY);
		return false;
	}
	
	@Override
	public void show(){
		inputMultiplexer = new InputMultiplexer(this, ((PlayRenderer)getRenderer()).getStage());
		Gdx.input.setInputProcessor(inputMultiplexer);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		((PlayController)getController()).onKeyDown(keycode);
		return false;
	}
}
