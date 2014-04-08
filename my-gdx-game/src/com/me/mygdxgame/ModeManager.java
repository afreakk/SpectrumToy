package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class ModeManager {
	private IGraphicMode currentMode;
	private int storageSize;
	private SpriteBatch batch;
	private BitmapFont modeText;
	ModeManager(int storageSize)
	{
		this.storageSize = storageSize;
		currentMode = new ZMode(this.storageSize,null);
		batch = new SpriteBatch();
		modeText = new BitmapFont();
	    modeText.setColor(1, 1, 1, 1);
	}
	public void refreshGraphics(ShapeRenderer shapeRenderer, short[] spectrumData)
	{
		currentMode.refreshGraphics(shapeRenderer, spectrumData);
	}
	public void touchUp(Vector2[] xPArr)
	{

		switchMode();
		setMode(xPArr);
	}
	public void dispose()
	{
		batch.dispose();
		modeText.dispose();
	}
	public void setArray(Vector2[] xPArr)
	{
		currentMode.setArray(xPArr);
	}
	private enum GMode
	{
		ZMode, 
		SoftMode {
	        @Override
	        public GMode next() {
	            return ZMode; 
	        };
	    };

	    public GMode next() {
	        return values()[ordinal() + 1];
	    }
	}
	public void newMode(Vector2[] xPArr)
	{
		switchMode();
		setMode(xPArr);
	}
	private void switchMode()
	{
		mode = mode.next();
	}
	GMode mode = GMode.ZMode;
	private void setMode(Vector2[] xPArr)
	{
		Gdx.app.log("mode", String.valueOf(mode));
		switch(mode)
		{
		case ZMode: currentMode = new ZMode(storageSize, xPArr); break;
		case SoftMode: currentMode = new SoftMode(storageSize, xPArr); break;
		}
		setBgColor();
	}
	private void setBgColor()
	{
		switch (mode)
		{
		case ZMode:Gdx.gl.glClearColor(0,0,0,1); break;
		case SoftMode: Gdx.gl.glClearColor(0,0,0,1); break;
		}
	}
	public void drawModeText()
	{
		batch.begin();
		modeText.draw(batch, "Mode: "+modeToString(), Gdx.graphics.getWidth()/16.0f, Gdx.graphics.getHeight()/16.0f);
		batch.end();
	}
	private String modeToString()
	{
		switch(mode)
		{
		case ZMode: return "ZMode";
		case SoftMode: return "SoftMode";
		}
		return "None";
	}

}
