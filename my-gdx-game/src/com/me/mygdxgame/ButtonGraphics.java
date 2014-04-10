package com.me.mygdxgame;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ButtonGraphics {
	private Texture btnTexture;
	private Sprite btnSprite;
	private IntVec2 pos;
	private IntVec2 dimensions;
	public ButtonGraphics(IntVec2 pos, IntVec2 dimensions)
	{
		this.dimensions = dimensions;
		this.pos = pos;
		Pixmap pixmap = new Pixmap( 64, 64, Format.RGBA8888 );
		pixmap.setColor( 0, 1, 0, 0.75f );
		pixmap.fill();
		btnTexture = new Texture( pixmap );
		pixmap.dispose();
		btnSprite = new Sprite(btnTexture);
		btnSprite.setSize(dimensions.x, dimensions.y);
		btnSprite.setPosition(pos.x, pos.y-dimensions.y);
	}
	public void draw(SpriteBatch batch)
	{
		btnSprite.draw(batch);
	}

}
