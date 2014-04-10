package com.me.mygdxgame;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Button {

    public enum ButtonID
	{
		topLeft, 
		topRight,
		bottomRight,
		bottomLeft,
		bottomMid,
		None
	}
	private String caption = "";
	private IntVec2 pos;
	private IntVec2 dimensions;
	private ButtonGraphics buttonGraphic;
	public Button(IntVec2 pos, int width, int height)
	{
		this.pos = pos;
		this.dimensions = new IntVec2(width,height);
		buttonGraphic = new ButtonGraphics(this.pos, this.dimensions);
	}
	public void SetButtonName(String caption)
	{
		this.caption = caption;
	}
	public void draw(SpriteBatch batch, BitmapFont modeText)
	{
		buttonGraphic.draw(batch);
		modeText.draw(batch, caption, pos.x, pos.y);
	}
	public boolean hitTest(IntVec2 intersect)
	{
		if(intersect.x > pos.x)
		{
			if(intersect.x < pos.x + dimensions.x)
			{
				if(intersect.y < pos.y)
				{
					if(intersect.y > pos.y - dimensions.y)
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
}
