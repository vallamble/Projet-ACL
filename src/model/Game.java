package model;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame {

	public static void main(String[] args) {
		JFrame fenetre = new JFrame("Space Ship");
	    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    World world = new World(400, 600);
	    world.setPreferredSize(new Dimension(400,600));
	    fenetre.setContentPane(world);
	    fenetre.pack();
	    fenetre.setVisible(true);
	    world.requestFocus();
	}

}