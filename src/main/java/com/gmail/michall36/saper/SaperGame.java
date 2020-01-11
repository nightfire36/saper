package com.gmail.michall36.saper;

import java.awt.Color;
import java.awt.Label;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SaperGame {

	public static final int HEIGHT = 5;
	public static final int WIDTH = 5;
	public static final int BOMBS_NUMBER = 5;
	
	private List<SaperButton> buttons;
	
	private int[] poolsWithBombs = new int[BOMBS_NUMBER];
	private boolean isGameOver = false;
	private int poolsCheckedNumber = 0;
	private Label label;

	public SaperGame(Label label, List<SaperButton> buttons) {
		this.label = label;
		this.buttons = buttons;
	}

	public void initialize() {
		Random rand = new Random();
		for (int i = 0; i < BOMBS_NUMBER; i++) {
			int randomNumber = rand.nextInt(HEIGHT * WIDTH - i);
			while (contains(randomNumber)) {
				randomNumber = rand.nextInt(HEIGHT * WIDTH - i);
			}
			poolsWithBombs[i] = randomNumber;
		}
	}

	public boolean contains(int number) {
		for (int i = 0; i < BOMBS_NUMBER; i++) {
			if (poolsWithBombs[i] == number)
				return true;
		}
		return false;
	}

	public int getBombsAround(int number) {
		int bombsAround = 0;
		if (number % WIDTH != 0) {
			if (contains(number - 1)) {
				bombsAround++;
			}
			if (number - WIDTH - 1 >= 0) {
				if (contains(number - WIDTH - 1)) {
					bombsAround++;
				}
			}
			if (number + WIDTH - 1 < WIDTH * HEIGHT) {
				if (contains(number + WIDTH - 1)) {
					bombsAround++;
				}
			}
		}
		if (number % WIDTH != WIDTH - 1) {
			if (contains(number + 1)) {
				bombsAround++;
			}
			if (number - WIDTH + 1 >= 0) {
				if (contains(number - WIDTH + 1)) {
					bombsAround++;
				}
			}
			if (number + WIDTH + 1 < WIDTH * HEIGHT) {
				if (contains(number + WIDTH + 1)) {
					bombsAround++;
				}
			}
		}
		if (number - WIDTH >= 0) {
			if (contains(number - WIDTH)) {
				bombsAround++;
			}
		}
		if (number + WIDTH < WIDTH * HEIGHT) {
			if (contains(number + WIDTH)) {
				bombsAround++;
			}
		}
		return bombsAround;
	}
	
	public void revealBombsAround(int poolId) {
		if (poolId % WIDTH != 0) {
			revealPool(poolId - 1);
			if (poolId - WIDTH - 1 >= 0) {
				revealPool(poolId - WIDTH - 1);
			}
			if (poolId + WIDTH - 1 < WIDTH * WIDTH) {
				revealPool(poolId + WIDTH - 1);
			}
		}
		if (poolId % WIDTH != WIDTH - 1) {
			revealPool(poolId + 1);
			if (poolId - WIDTH + 1 >= 0) {
				revealPool(poolId - WIDTH + 1);
			}
			if (poolId + WIDTH + 1 < WIDTH * WIDTH) {
				revealPool(poolId + WIDTH + 1);
			}
		}
		if (poolId - WIDTH >= 0) {
			revealPool(poolId - WIDTH);
		}
		if (poolId + WIDTH < WIDTH * WIDTH) {
			revealPool(poolId + WIDTH);
		}
	}
	
	public void revealPool(int poolId)
	{
		SaperButton button = this.buttons.get(poolId);
		if(!button.isChcecked())
		{
			if (contains(poolId)) {
				button.setButtonIcon();
				gameOver();
			} else {
				button.setEnabled(false);
				button.setChcecked(true);
				int bombsAround = getBombsAround(poolId);
				if(bombsAround > 0) {
					button.setText(String.valueOf(bombsAround));
				}
				else {
					revealBombsAround(poolId);
				}
				this.poolsCheckedNumber++;
				if (this.poolsCheckedNumber + SaperGame.BOMBS_NUMBER == SaperGame.HEIGHT * SaperGame.WIDTH) {
					victory();
				}
			}
		}
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void gameOver() {
		this.isGameOver = true;
		label.setForeground(Color.RED);
		label.setText("GAME OVER!");
	}

	public void victory() {
		this.isGameOver = true;
		label.setForeground(Color.GREEN);
		label.setText("YOU WON!");
	}
}
