package com.me.mygdxgame;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class ModeManager {
	private IGraphicMode currentMode;
	private int storageSize;
	GMode mode = GMode.ZMode;
	ModeManager(int storageSize)
	{
		this.storageSize = storageSize;
		currentMode = new ZMode(this.storageSize,null);
	}
	public void refreshGraphics(ShapeRenderer shapeRenderer, short[] spectrumData)
	{
		currentMode.refreshGraphics(shapeRenderer, spectrumData);
	}
	public void setArray(Vector2[] xPArr)
	{
		currentMode.setArray(xPArr);
	}
	public GMode getMode()
	{
		return mode;
	}
	public enum GMode
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
	private void setMode(Vector2[] xPArr)
	{
		switch(mode)
		{
		case ZMode: currentMode = new ZMode(storageSize, xPArr); break;
		case SoftMode: currentMode = new SoftMode(storageSize, xPArr); break;
		}
	}
}
