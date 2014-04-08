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
		Vector2 lastPos = new Vector2();
		shapeRenderer.begin(ShapeType.Line);
		int i=0;
		for(Vector2 pos:xPArr)
		{
			shapeRenderer.setColor((float)i/(float)storageSize, 0, 1, 1);
			Vector2 modPos = new Vector2(pos);
			if(i < spectrumData.length)
				modPos.scl((float)spectrumData[i]/50000.0f);
			modPos.add(pos);
	    	if(i>2)
	    		shapeRenderer.line(lastPos.x, lastPos.y, modPos.x, modPos.y);
    		lastPos = modPos;
    		i++;
    		//Gdx.app.log("line:", "v0:"+String.valueOf(lastPos)+", v1:"+String.valueOf(pos));
		}
		shapeRenderer.end();
	}
	@Override
	public void setArray(Vector2[] xPArr) {
		this.xPArr = xPArr;
	}

}
