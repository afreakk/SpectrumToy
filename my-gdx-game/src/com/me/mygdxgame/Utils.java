package com.me.mygdxgame;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Utils {

	public static Vector2 normalVector(Vector2 lV, Vector2 nV)
	{
		Vector2 vec = new Vector2(lV.y-nV.y, lV.x-nV.x );
		//Vector2 vec = new Vector2(-(lV.x-nV.x), (lV.y-nV.y)); 
		//vec.nor();
		vec.nor();
		return vec;
	}
	public static float scaleFromSpectrum(float spectrum)
	{
		float scale = spectrum;
    	scale *= 0.000002;
    	return scale;
	}
	public static Vector2 resolutionToGL(IntVec2 pos)
	{
		Vector2 vec = new Vector2((pos.x/(float)Gdx.graphics.getWidth())-0.5f, (pos.y/(float)Gdx.graphics.getHeight())-0.5f);
		vec.y *= -0.65f;
		return vec;
	}
	public static Vector2[] intToFloatVec(ArrayList<IntVec2> poses)
	{
		Vector2[] vectors = new Vector2[poses.size()];
		int i=0;
		for(IntVec2 it : poses)
		{
			vectors[i] = resolutionToGL(it);
			i++;
		}
		return vectors;
	}
}
