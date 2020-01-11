package com.gmail.michall36.saper;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SaperButton extends JButton implements ActionListener {

	private int buttonId;
	private SaperGame bombs;
	
	private boolean checked = false;

	public SaperButton(int buttonId, SaperGame bombs) {
		super("");
		this.buttonId = buttonId;
		this.bombs = bombs;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		if (!bombs.isGameOver()) {
			bombs.revealPool(this.buttonId);
		}
	}

	public void setButtonIcon() {
		try {
			Image icon = ImageIO.read(getClass().getResource("/bomb.bmp"));
			setIcon(new ImageIcon(icon));
		} catch (Exception e) {
			System.out.println("Set icon exception: " + e);
		}
	}
	
	public void setChcecked(boolean checked)
	{
		this.checked = checked;
	}
	
	public boolean isChcecked()
	{
		return checked;
	}

}
