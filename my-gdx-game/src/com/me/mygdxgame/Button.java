package com.me.mygdxgame;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Button {

    public enum ButtonID
	{
		topLeft, 
		topRight,
		bottomRight,
		bottomLeft
	}
	private String caption = "";
	private IntVec2 pos;
	public Button(IntVec2 pos)
	{
		this.pos = pos;
	}
	public void SetButtonName(String caption)
	{
		this.caption = caption;
	}
	public void draw(SpriteBatch batch, BitmapFont modeText)
	{
		modeText.draw(batch, caption, pos.x, pos.y);
	}
	
}
