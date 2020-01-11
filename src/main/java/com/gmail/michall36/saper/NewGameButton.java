package com.gmail.michall36.saper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class NewGameButton extends JButton implements ActionListener {

	private JFrame frame;

	public NewGameButton(JFrame frame) {
		super("New game");
		this.frame = frame;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		frame.setVisible(false);
		frame.dispose();
		new Window().initialize();
	}
}
