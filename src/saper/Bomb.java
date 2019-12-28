package saper;

import java.awt.Color;
import java.awt.Label;
import java.util.Arrays;
import java.util.Random;

public class Bomb {
	
	public static final int HEIGHT = 5;
	public static final int WIDTH = 5;
	public static final int BOMBS_NUMBER = 5;
	
	int[] poolsWithBombs = new int[BOMBS_NUMBER];
	private boolean isGameOver = false;
	private int poolsChecked = 0;
	Label label;
	
	public Bomb(Label label)
	{
		this.label = label;
	}
	
	public void initialize()
	{
		Random rand = new Random();
		for(int i = 0; i < BOMBS_NUMBER; i++)
		{
			int randomNumber = rand.nextInt(HEIGHT * WIDTH - i);
			while(contains(randomNumber))
			{
				randomNumber = rand.nextInt(HEIGHT * WIDTH - i);
			}
			poolsWithBombs[i] = randomNumber;
		}
	}
	
	public boolean contains(int number)
	{
		for(int i = 0; i < BOMBS_NUMBER; i++)
		{
			if(poolsWithBombs[i] == number) return true;
		}
		return false;
	}
	
	public int getBombsAround(int number)
	{
		int bombsAround = 0;
		if(number % WIDTH != 0)
		{
			if(contains(number - 1))
			{
				bombsAround++;
			}
			if(number - WIDTH - 1 > 0)
			{
				if(contains(number - WIDTH - 1))
				{
					bombsAround++;
				}
			}
			if(number + WIDTH - 1 < WIDTH*HEIGHT)
			{
				if(contains(number + WIDTH - 1))
				{
					bombsAround++;
				}
			}
		}
		if(number % WIDTH != WIDTH-1)
		{
			if(contains(number + 1))
			{
				bombsAround++;
			}
			if(number - WIDTH + 1 > 0)
			{
				if(contains(number - WIDTH + 1))
				{
					bombsAround++;
				}
			}
			if(number + WIDTH + 1 < WIDTH*HEIGHT)
			{
				if(contains(number + WIDTH + 1))
				{
					bombsAround++;
				}
			}
		}
		if(number - WIDTH > 0)
		{
			if(contains(number - WIDTH))
			{
				bombsAround++;
			}
		}
		if(number + WIDTH < WIDTH*HEIGHT)
		{
			if(contains(number + WIDTH))
			{
				bombsAround++;
			}
		}
		return bombsAround;
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

	public int getPoolsChecked() {
		return poolsChecked;
	}

	public void incrementPoolsChecked() {
		this.poolsChecked++;
	}
}
