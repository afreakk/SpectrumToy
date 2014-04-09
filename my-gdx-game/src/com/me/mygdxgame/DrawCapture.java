package com.me.mygdxgame;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class DrawCapture {
	private Vector2[] xPArr = null;
	private ArrayList<IntVec2> xPoints = new ArrayList<IntVec2>();
	
	public void drawLine(ShapeRenderer shapeRenderer, int storageSize)
	{
		Vector2 lastPos = new Vector2();
		shapeRenderer.begin(ShapeType.Line);
		int i=0;
		for(IntVec2 vec:xPoints)
		{
			shapeRenderer.setColor((float)i/(float)storageSize, 0, 1, 1);
	    	Vector2 pos = Utils.resolutionToGL(vec);
	    	if(i>2)
	    		shapeRenderer.line(lastPos.x, lastPos.y, pos.x, pos.y);	
    		lastPos = pos;
    		i++;
		}
		shapeRenderer.end();
	}
	private int pPoints = 0;
	private boolean tap = false;
	public void touchDragged(int x, int y)
	{
		if(tap)
		{	
			if(pPoints  > 10)
			{
				tap = false;
				xPArr = null;
				pPoints = 0;
			}
			pPoints ++;
		}
		xPoints.add(new IntVec2(x,y));
	}
	public void touchDown()
	{
		tap =  true;
	}
	public boolean switchMode()
	{
		return tap;
	}
	public Vector2[] getFreshLinePoints()
	{
		xPArr = Utils.intToFloatVec(xPoints);
		xPoints.clear();
		return xPArr;
	}
	public Vector2[] getOldLinePoints()
	{
		return xPArr;
	}
}
