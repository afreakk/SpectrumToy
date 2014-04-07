package com.me.mygdxgame;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.AudioRecorder;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
 

public class MyGdxGame implements ApplicationListener, InputProcessor {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private int samples = 44100;
	private int sampleSize = samples/30;
	private AudioRecorder recorder;
	private short[] temp = null;
	private int storageSize = samples/30;
	private short[] spectrumData = new short[storageSize];
	private boolean isMono = true;
	private ShapeRenderer shapeRenderer= null;
	ArrayList<IntVec2> xPoints = new ArrayList<IntVec2>();
	Vector2[] xPArr = null;
	
	@Override
	public void create() 
	{
		Gdx.gl.glLineWidth(5);
		shapeRenderer = new ShapeRenderer();
		recorder  = Gdx.audio.newAudioRecorder(samples, isMono);
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		Gdx.input.setInputProcessor(this);	
	}

	@Override
	public void dispose() {
		batch.dispose();
		recorder.dispose();
	}
	private int placingPos = 0;
	
	private void record()
	{
		temp = new short[sampleSize];
        recorder.read(temp, 0, temp.length);
        System.arraycopy(temp, 0, spectrumData, Math.min(placingPos, spectrumData.length-temp.length), temp.length);
        placingPos += sampleSize;
        if(placingPos >= storageSize)
        	placingPos = 0;    
	}
	
	private void refreshSpectrum()
	{
		Vector2 lastPos = new Vector2();
		shapeRenderer.begin(ShapeType.Line);
    	shapeRenderer.setColor(1, 0, 0, 1);
		for (int i = 0; i < xPArr.length; i++) 
		{
	    	shapeRenderer.setColor((float)i/(float)xPArr.length, 0, 1, 1);
	    	Vector2 pos = new Vector2(xPArr[i].x, xPArr[i].y);
	    	
	    	if(i < spectrumData.length)
	    	{
	    		Vector2 normal = Utils.normalVector(lastPos, pos);
	    		float scale = scaleFromSpectrum(spectrumData[i]);
	    		normal.scl(scale);
	    		pos.add(normal);
	    	}
	    	
	    	if(i>2)
	    		shapeRenderer.line(lastPos.x, lastPos.y, pos.x, pos.y);
	    	lastPos = pos;
		}
		shapeRenderer.end();
	}
	
	private float scaleFromSpectrum(float spectrum)
	{
		float scale = spectrum;
    	scale *= 0.000002;
    	return scale;
	}
	private float scaleFromHalfSpectrum(int start, int end)
	{
		int result = 0; 
		for(int i=start; i< end; i++)
		{
			result += spectrumData[i];
		}
		return scaleFromSpectrum(result)/(float)(end-start);
	}
	@Override
	public void render() 
	{
		record();	
		Gdx.gl.glClearColor(0, scaleFromHalfSpectrum(0,spectrumData.length), 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		 shapeRenderer.setProjectionMatrix(camera.combined);
		 if(xPArr != null)
			 refreshSpectrum();
		 else
			 drawLine();
	}
	private void drawLine()
	{
		Vector2 lastPos = new Vector2();
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(1, 0, 0, 1);
		int i=0;
		for(IntVec2 vec:xPoints)
		{
			shapeRenderer.setColor((float)i/(float)xPoints.size(), 0, 1, 1);
	    	Vector2 pos = Utils.resolutionToGL(vec);
	    	if(i>2)
	    		shapeRenderer.line(lastPos.x, lastPos.y, pos.x, pos.y);	
    		lastPos = pos;
    		i++;
		}
		shapeRenderer.end();
	}
	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		xPoints.add(new IntVec2(screenX,screenY));
		return false;
	}
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		xPArr = null;
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		xPArr = Utils.intToFloatVec(xPoints);
		xPoints.clear();
		return false;
	}
	
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
