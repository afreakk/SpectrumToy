package com.me.mygdxgame;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ButtonGraphics {
	private Texture btnTexture;
	private Sprite btnSprite;
	private IntRect rect;
	public ButtonGraphics(IntRect rect)
	{
		this.rect = rect;
		Pixmap pixmap = new Pixmap( 64, 64, Format.RGBA8888 );
		pixmap.setColor( 1, 1, 1, 0.15f );
		pixmap.fill();
		setupSprite(pixmap, this.rect);
		pixmap.dispose();
	}
	private void setupSprite(Pixmap pixmap, IntRect rect)
	{
		IntVec2 pos = rect.position();
		IntVec2 dim = rect.dimensions();
		btnTexture = new Texture( pixmap );
		btnSprite = new Sprite(btnTexture);
		btnSprite.setSize(dim.x, dim.y);
		btnSprite.setPosition(pos.x, pos.y-dim.y);
	}
	public void draw(SpriteBatch batch)
	{
		btnSprite.draw(batch);
	}

}
