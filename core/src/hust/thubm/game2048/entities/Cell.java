package hust.thubm.game2048.entities;

import hust.thubm.game2048.screens.GameScreen;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Cell {
	public enum CellState {
		STAND, MOVE_UP, MOVE_DOWN, MOVE_RIGHT, MOVE_LEFT
	} 
	private static final float SPEED = 10f;

	private Vector2 position;
	private Vector2 destination;
	private Rectangle bound;
	private Vector2 speed;
	private int value = 0;
	private CellState state = CellState.STAND;

	public Cell() {
		// TODO Auto-generated constructor stub
		this.position = new Vector2();
		this.bound = new Rectangle();
		this.destination = new Vector2();
		this.speed = new Vector2(SPEED * GameScreen.ppuX, SPEED * GameScreen.ppuY);
	}

	public Cell(int x, int y, float posx, float posy, float width, float height) {
		this.position = new Vector2(x, y);
		this.bound = new Rectangle(posx, posy, width, height);
		this.destination = new Vector2();
		this.speed = new Vector2(SPEED * GameScreen.ppuX, SPEED * GameScreen.ppuY);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public void setPosition(int x, int y) {
		this.position.x = x;
		this.position.y = y;
	}

	public Vector2 getDestination() {
		return destination;
	}

	public void setDestination(Vector2 destination) {
		this.destination = destination;
	}

	public void setDestination(float x, float y) {
		this.destination.x = x;
		this.destination.y = y;
	}

	public CellState getState() {
		return state;
	}

	public void setState(CellState state) {
		this.state = state;
	}

	public Rectangle getBound() {
		return bound;
	}

	public void setBound(Rectangle bound) {
		this.bound = bound;
	}

	public void setBound(float posx, float posy, float width, float height) {
		this.bound.x = posx;
		this.bound.y = posy;
		this.bound.width = width;
		this.bound.height = height;
	}

	public Vector2 getSpeed() {
		return speed;
	}

	public void setSpeed(Vector2 speed) {
		this.speed = speed;
	}

	public void moveLeft() {
		this.bound.x -= this.speed.x;
	}

	public void moveRight() {
		this.bound.x += this.speed.x;
	}

	public void moveUp() {
		this.bound.y += this.speed.y;
	}

	public void moveDown() {
		this.bound.y -= this.speed.y;
	}

	
	public void update(float delta) {
		if (state == CellState.STAND)
			return;

		if (state == CellState.MOVE_LEFT) {
			// Trang thai cell dang di chuyen sang trai
			moveLeft();

			if (destination.x >= bound.x) {
				bound.x = destination.x;
				state = CellState.STAND;
			}
		} else if (state == CellState.MOVE_RIGHT) {
			// Trang thai cell dang di chuyen sang phai
			moveRight();

			if (destination.x <= bound.x) {
				bound.x = destination.x;
				state = CellState.STAND;
			}
		} else if (state == CellState.MOVE_UP) {
			// Trang thai cell dang di chuyen len tren
			moveUp();

			if (destination.y <= bound.y) {
				bound.y = destination.y;
				state = CellState.STAND;
			}
		} else {
			// Trang thai cell dang di chuyen xuong duoi
			moveDown();

			if (destination.y >= bound.y) {
				bound.y = destination.y;
				state = CellState.STAND;
			}
		}

	}

}
