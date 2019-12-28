package saper;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SaperButton extends JButton implements ActionListener {
	
	private int buttonId;
	private Bomb bombs;
	
	public SaperButton(int buttonId, Bomb bombs) {
		super("");
		this.buttonId = buttonId;
		this.bombs = bombs;
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent action)
	{
		if(!bombs.isGameOver())
		{
			if(bombs.contains(buttonId))
			{
				setButtonIcon();
				bombs.gameOver();
			}
			else
			{
				this.setEnabled(false);
				this.setText(String.valueOf(bombs.getBombsAround(buttonId)));
				bombs.incrementPoolsChecked();
				if(bombs.getPoolsChecked() + Bomb.BOMBS_NUMBER == Bomb.HEIGHT*Bomb.WIDTH)
				{
					bombs.victory();
				}
			}
		}
	}
	
	private void setButtonIcon()
	{
		try
		{
			Image icon = ImageIO.read(getClass().getResource("/bomb.bmp"));
			setIcon(new ImageIcon(icon));
		}
		catch(Exception e)
		{
			System.out.println("Set icon exception: " + e);
		}
	}
	
}
