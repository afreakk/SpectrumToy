package com.me.mygdxgame;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextDisplay {
	private String caption = "";
	private IntVec2 end_pos = new IntVec2();
	private IntRect rect;
	private BitmapFont modeText;
	TextDisplay(IntRect rect, BitmapFont modeText)
	{
		this.modeText = modeText;
		this.rect = rect;
	}
	public void centerTextDraw(SpriteBatch batch)
	{
		modeText.draw(batch, caption, end_pos.x, end_pos.y);
	}
	private int half(int value)
	{
		return (int)((float)value/2.0f );
	}
	private int half(float value)
	{
		return (int)(value/2.0f );
	}
	private void refreshPos()
	{
		IntVec2 pos = rect.position();
		IntVec2 dimensions = rect.dimensions();
		TextBounds d = modeText.getBounds(caption);
		end_pos.x = pos.x+half(dimensions.x)-half(d.width);
		end_pos.y = pos.y-half(dimensions.y)+half(d.height);
	}
	public void setCaption(String caption)
	{
		this.caption = caption;
		refreshPos();
	}
}
