package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;

public class IntRect {

	private IntVec2 pos;
	private IntVec2 dimensions;
	public IntRect(IntVec2 pos, IntVec2 dimensions)
	{
		this.pos = pos;
		this.dimensions = dimensions;
	}
	public IntVec2 position()
	{
		return pos;
	}
	public IntVec2 dimensions()
	{
		return dimensions;
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
