package hust.thubm.game2048.screens;

import hust.thubm.game2048.controllers.Controller;
import hust.thubm.game2048.views.GameRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen, InputProcessor {
	public static final float WIDTH = 40f;
	public static final float HEIGHT = 64f;
	
	public static int width = Gdx.graphics.getWidth();
	public static int height = Gdx.graphics.getHeight();
	public static float ppuX = (float)width / WIDTH;
	public static float ppuY = (float)height / HEIGHT;
	
	private Controller controller;
	private GameRenderer renderer;
	
	public GameScreen(Controller controller, GameRenderer renderer) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
		this.renderer = renderer;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public GameRenderer getRenderer() {
		return renderer;
	}

	public void setRenderer(GameRenderer renderer) {
		this.renderer = renderer;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stubg
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
