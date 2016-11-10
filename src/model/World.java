package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;

import java.awt.*;
public class World extends JPanel{

	private int w, h;
	Ship ship;
	
	/**
	 * @param w
	 * @param h
	 */
	public World(int w, int h) {
		this.w = w;
		this.h = h;
		this.ship = new Ship(w / 2, h - 30);
		this.addKeyListener(new ToucheAppuie());
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		int h = getHeight();
		int w = getWidth();
		g.setColor(Color.red);
		g.fillRect((int)this.ship.getPosition().x, (int)this.ship.getPosition().y, 10, 10);
	}

	/**
	 * @return the w
	 */
	public int getW() {
		return w;
	}

	/**
	 * @param w the w to set
	 */
	public void setW(int w) {
		this.w = w;
	}

	/**
	 * @return the h
	 */
	public int getH() {
		return h;
	}

	/**
	 * @param h the h to set
	 */
	public void setH(int h) {
		this.h = h;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int e) {
		if((e < 0 && this.ship.getPosition().x > 0) || (e > 0 && this.ship.getPosition().x < this.w - 10))
		{
			System.out.println(e);
			switch (e) {
			case 1:
				this.ship.turnRight();
			break;
			case -1:
				this.ship.turnLeft();
				break;
			}
			this.ship.update(1);
		}
		repaint();
	}
	
}
