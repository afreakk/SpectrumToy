package com.me.mygdxgame;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public interface IGraphicMode {
	public void refreshGraphics(ShapeRenderer shapeRenderer, short[] spectrumData);
	public void setArray(Vector2[] xPArr);
}
	
