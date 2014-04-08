package com.me.mygdxgame;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class SoftMode implements IGraphicMode {
	private int storageSize;
	private Vector2[] xPArr;
	public SoftMode(int storageSize, Vector2[] xPArr)
	{
		this.storageSize=storageSize;
		this.xPArr = xPArr;
	}
	
	@Override
	public void refreshGraphics(ShapeRenderer shapeRenderer, short[] spectrumData) {
		Vector2 currentPos = new Vector2(xPArr[0]);
		Vector2 lastPos = new Vector2();
		shapeRenderer.begin(ShapeType.Line);
		for (int i = 0; i < xPArr.length; i++) 
		{
	    	shapeRenderer.setColor((float)i/storageSize, 0, 1, 1);
	    	Vector2 pos = new Vector2(xPArr[i].x, xPArr[i].y);
	    	
	    	if(i < spectrumData.length)
	    	{
	    		Vector2 normal = Utils.normalVector(lastPos, pos);
	    		float scale = Utils.scaleFromSpectrum(spectrumData[i]);
	    		normal.scl(scale);
	    		pos.add(normal);
	    	}
	    	Vector2 temp = new Vector2();
	    	temp.x = pos.x - currentPos.x;
	    	temp.y = pos.y - currentPos.y;
	    	temp.nor();
	    	temp.scl(0.01f);
	    	currentPos.add(temp);
	    	if(i>2)
	    		shapeRenderer.line(lastPos,currentPos);
	    	lastPos = new Vector2(currentPos);
		}
		shapeRenderer.end();
	}
	@Override
	public void setArray(Vector2[] xPArr) {
		this.xPArr = xPArr;
	}

}
