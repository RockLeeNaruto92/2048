package hust.thubm.game2048.views;

import hust.thubm.game2048.constant.Constant;
import hust.thubm.game2048.controllers.StartController;
import hust.thubm.game2048.screens.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class StartRenderer extends GameRenderer {
	private Button btnStart;
	private Button btnSetting;
	private Button btnHighScore;
	private Button btnAbout;
	
	private TextureAtlas atlas;
	private Skin skin;
	private Stage stage;
	
	
	private TextureRegion background;
	
	public StartRenderer(StartController startController) {
		// TODO Auto-generated constructor stub
		super(startController);
		load();
	}
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void load(){
		stage = new Stage();
		
		loadAtlas();
		loadBackground();
		loadButtons();
		loadSound();
	}
	
	private void loadSound(){
		
	}
	
	private void loadAtlas(){
		atlas = new TextureAtlas(Gdx.files.internal("img/startscreen.atlas"));
		skin = new Skin(Gdx.files.internal("img/startscreenskin.json"), atlas);
	}
	
	private void loadBackground(){
		background = atlas.findRegion("background");
	}
	
	private void loadButtons(){
		loadStartBtn();
		loadHighScoreBtn();
		loadSettingBtn();
		loadAboutBtn();
	}
	
	private void loadStartBtn(){
		float posX = Constant.BUTTON_START_X * GameScreen.ppuX;
		float posY = Constant.BUTTON_START_Y * GameScreen.ppuY;
		float width = Constant.BUTTON_START_WIDTH * GameScreen.ppuX;
		float height= Constant.BUTTON_START_HEIGHT * GameScreen.ppuY;
		
		btnStart = new Button(skin, "btn-start");
		btnStart.setBounds(posX, posY, width, height);
		
		btnStart.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((StartController)getController()).onStartBtnClick();
			}
		});
		
		stage.addActor(btnStart);
	}
	
	private void loadHighScoreBtn(){
		float posX = Constant.BUTTON_HIGHSCORE_X * GameScreen.ppuX;
		float posY = Constant.BUTTON_HIGHSCORE_Y * GameScreen.ppuY;
		float width = Constant.BUTTON_HIGHSCORE_WIDTH * GameScreen.ppuX;
		float height = Constant.BUTTON_HIGHSCORE_HEIGHT * GameScreen.ppuY;
		
		btnHighScore = new Button(skin, "btn-highscore");
		btnHighScore.setBounds(posX, posY, width, height);
		
		btnHighScore.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((StartController)getController()).onHighScoreBtnClick();
			}
		});
		
		stage.addActor(btnHighScore);
	}
	
	private void loadSettingBtn(){
		float posX = Constant.BUTTON_SETTING_X * GameScreen.ppuX;
		float posY = Constant.BUTTON_SETTING_Y * GameScreen.ppuY;
		float width = Constant.BUTTON_SETTING_WIDTH * GameScreen.ppuX;
		float height = Constant.BUTTON_SETTING_HEIGHT * GameScreen.ppuY;
		
		btnSetting = new Button(skin, "btn-setting");
		btnSetting.setBounds(posX, posY, width, height);
		
		btnSetting.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((StartController)getController()).onSettingBtnClick();
			}
		});
		
		stage.addActor(btnSetting);
	}
	
	private void loadAboutBtn(){
		float posX = Constant.BUTTON_ABOUT_X * GameScreen.ppuX;
		float posY = Constant.BUTTON_ABOUT_Y * GameScreen.ppuY;
		float width = Constant.BUTTON_ABOUT_WIDTH * GameScreen.ppuX;
		float height = Constant.BUTTON_ABOUT_HEIGHT * GameScreen.ppuY;
		
		btnAbout = new Button(skin, "btn-about");
		btnAbout.setBounds(posX, posY, width, height);
		
		btnAbout.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((StartController)getController()).onAboutBtnClick();
			}
		});
		
		stage.addActor(btnAbout);
	}

	@Override
	public void render(float delta){
		getSpriteBatch().begin();
		
		getSpriteBatch().draw(background, 0, 0, GameScreen.width, GameScreen.height);
		
		getSpriteBatch().end();
	
		stage.act(delta);
		stage.draw();
	}
}

