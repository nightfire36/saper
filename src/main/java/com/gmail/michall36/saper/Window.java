package com.gmail.michall36.saper;

import javax.swing.JFrame;

import java.awt.Font;
import java.awt.Label;
import java.util.ArrayList;
import java.util.List;

public class Window extends JFrame {

	public Window() {
		super("Saper");
	}

	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(430, 300);
		setLayout(null);

		Label label = new Label("");
		List<SaperButton> buttons = new ArrayList();
		SaperGame bombs = new SaperGame(label, buttons);
		bombs.initialize();

		int k = 0;
		for (int i = 0; i < SaperGame.HEIGHT; i++) {
			for (int j = 0; j < SaperGame.WIDTH; j++) {
				SaperButton button = new SaperButton(k, bombs);

				button.setSize(45, 45);

				button.setLocation(160 + j * 50, 10 + i * 50);
				add(button);
				buttons.add(button);
				k++;
			}
		}

		NewGameButton newGameButton = new NewGameButton(this);
		newGameButton.setSize(100, 30);
		newGameButton.setLocation(10, 10);
		add(newGameButton);

		label.setSize(120, 40);
		label.setLocation(20, 120);
		label.setFont(new Font("Serif", Font.BOLD, 16));

		add(label);

		setVisible(true);
	}
}
