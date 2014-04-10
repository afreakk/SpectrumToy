package com.me.mygdxgame;
import com.badlogic.gdx.Gdx;
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
	private ButtonGraphics buttonGraphic;
	private TextDisplay caption;
	private IntRect rect;
	public Button(IntVec2 pos, int width, int height, BitmapFont modeText)
	{
		this.rect = new IntRect(pos, new IntVec2(width,height));
		buttonGraphic = new ButtonGraphics(this.rect);
		caption = new TextDisplay(this.rect, modeText);
	}
	public void draw(SpriteBatch batch)
	{
		WhiteBlink.update();
		buttonGraphic.draw(batch);
		caption.centerTextDraw(batch);
	}
	public void setCaption(String caption)
	{
		this.caption.setCaption(caption);
	}
	public boolean hitTest(IntVec2 intersect)
	{
		if(rect.hitTest(intersect))
		{
			onButtonPress();
            return true;
		}
		return false;
	}
	private void onButtonPress()
	{
        Gdx.input.vibrate(50);
        WhiteBlink.goWhite();
	}
	
}
