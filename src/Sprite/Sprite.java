package Sprite;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Sprite {
	protected int currentIndex = 0;
	protected int maxIndex, nbx, nby, width, height, frameRate;
	protected double nextUpdateFrame;
	protected Image image;

	public Sprite(String path, int nbx, int nby, int width, int height, int frameRate) {
		try {
			this.image = ImageIO.read(new File(path));
			this.maxIndex = nbx*nby;
			this.nbx = nbx;
			this.nby = nby;
			this.width = width;
			this.height = height;
			this.frameRate = frameRate;
			this.nextUpdateFrame = frameRate;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void update(double delta) {
		this.nextUpdateFrame--;
		if(this.nextUpdateFrame == 0) {
			this.nextUpdateFrame = this.frameRate;
			this.currentIndex++;
			if(this.currentIndex > this.maxIndex) {
				this.currentIndex = 0;
			}
		}
	}
	
	public void draw(JPanel panel, Graphics g, int x, int y) {
		if(!this.isFinish()) {
			int imgx = this.currentIndex%this.nbx;
			int imgy = (this.currentIndex - imgx)/this.nbx;
			g.drawImage(this.image,
				       x, y, x+20, y+20, this.width*imgx, this.height*imgy, this.width*(imgx+1), this.height*(imgy+1), panel);
		}
	}
	
	public boolean isFinish() {
		return false;
		//return this.currentIndex == this.maxIndex;
	}

}
