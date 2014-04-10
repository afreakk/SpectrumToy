package com.me.mygdxgame;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class ZMode implements IGraphicMode{

	private int storageSize;
	private Vector2[] xPArr;
	public ZMode(int storageSize, Vector2[] xPArr)
	{
		this.storageSize=storageSize;
		this.xPArr = xPArr;
	}
	@Override
	public void refreshGraphics(ShapeRenderer shapeRenderer, short[] spectrumData) {
		Vector2 lastPos = new Vector2();
		shapeRenderer.begin(ShapeType.Line);
		for (int i = 0; i < xPArr.length; i++) 
		{
			if(xPArr[i] == null)
			{
				if(i+2 > xPArr.length)
					break;
				i++;
				lastPos = xPArr[i];
				continue;
			}
            shapeRenderer.setColor((float)i/storageSize, 0, 1, 1);
            Vector2 pos = new Vector2(xPArr[i]);
            if(i < spectrumData.length)
            {
                Vector2 normal = Utils.normalVector(lastPos, pos);
                float scale = Utils.scaleFromSpectrum(spectrumData[i]);
                normal.scl(scale);
                pos.add(normal);
            }
            
            if(i>2)
                shapeRenderer.line(lastPos.x, lastPos.y, pos.x, pos.y);
            lastPos = pos;
		}
		shapeRenderer.end();
	}
	@Override
	public void setArray(Vector2[] xPArr) {
		this.xPArr = xPArr;
		
	}

}
