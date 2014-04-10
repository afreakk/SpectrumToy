package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;



public class WhiteBlink {
	private static Color clr = new Color(0,0,0,1);
	private static float decrement = 0.5f;
	public static void goWhite()
	{
		setMax();
	}
	public static void update()
	{
		decrementColor(clr);
		Gdx.gl.glClearColor(clr.r, clr.g, clr.b, clr.a);
	}

	private static void setMax()
	{
		clr.r = 1;
		clr.g = 1;
		clr.b = 1;
	}
	private static void decrementColor(Color c)
	{
		if(c.r > 0.0f)
		{
            c.r = c.r -0.1f;
            c.g = c.g -0.1f;
            c.b = c.b -0.1f;
		}
	}
	private static void decrementValue(float value)
	{
		value -= decrement;
		if(value < 0)
			value += decrement;
	}
}
