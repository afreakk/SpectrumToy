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
	    	if(i>2 && pos != null && lastPos != null)
	    		shapeRenderer.line(lastPos.x, lastPos.y, pos.x, pos.y);	
    		lastPos = pos;
    		i++;
		}
		shapeRenderer.end();
	}
	public void insertPoints(int x, int y)
	{
		xPoints.add(new IntVec2(x,y));
	}
	public void finishLine()
	{
		if(xPoints.get(xPoints.size()-1) != null)
            xPoints.add(null);
	}
	public Vector2[] getFreshLinePoints()
	{
		xPArr = Utils.intToFloatVec(xPoints);
		return xPArr;
	}
	public Vector2[] getOldLinePoints()
	{
		return xPArr;
	}
	public void clearPoints()
	{
		xPoints.clear();
	}
}
