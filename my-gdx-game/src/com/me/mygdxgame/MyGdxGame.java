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
	private ArrayList<IntVec2> xPoints = new ArrayList<IntVec2>();
	private Vector2[] xPArr = null;
	private IGraphicMode currentMode;
	
	@Override
	public void create() 
	{
		Gdx.gl.glLineWidth(1);
		shapeRenderer = new ShapeRenderer();
		recorder  = Gdx.audio.newAudioRecorder(samples, isMono);
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		Gdx.input.setInputProcessor(this);
		currentMode = new ZMode(storageSize,null);
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
	
	

	private float scaleFromHalfSpectrum(int start, int end)
	{
		int result = 0; 
		for(int i=start; i< end; i++)
		{
			result += spectrumData[i];
		}
		return Utils.scaleFromSpectrum(result)/(float)(end-start);
	}
	private float getBgColor()
	{
		float val = scaleFromHalfSpectrum(0,spectrumData.length)*10.0f;
		val *= -1.0f;
		val = Math.min(0.25f,val);
		val = Math.max(val, 0.0f);
		return val;//Math.min(val,0.01f);
	}
	@Override
	public void render() 
	{
		record();	
		Gdx.gl.glClearColor(0, getBgColor(), 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		 shapeRenderer.setProjectionMatrix(camera.combined);
		 if(xPArr != null)
			 currentMode.refreshGraphics(shapeRenderer, spectrumData);
		 else
			 drawLine();
	}
	private void drawLine()
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
		if(tap)
		{
			tap = false;
			xPArr = null;
		}
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
	private boolean tap = false;
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		tap =  true;
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(!tap)
		{
			xPArr = Utils.intToFloatVec(xPoints);
			currentMode.setArray(xPArr);
			xPoints.clear();
		}
		else
		{
			switchMode();
			setMode();
		}
		return false;
	}
	private enum GMode
	{
		ZMode, 
		SoftMode {
	        @Override
	        public GMode next() {
	            return ZMode; 
	        };
	    };

	    public GMode next() {
	        return values()[ordinal() + 1];
	    }
	}
	private void switchMode()
	{
		mode = mode.next();
	}
	GMode mode = GMode.ZMode;
	private void setMode()
	{
		Gdx.app.log("mode", String.valueOf(mode));
		switch(mode)
		{
		case ZMode: currentMode = new ZMode(storageSize, xPArr); break;
		case SoftMode: currentMode = new SoftMode(storageSize, xPArr); break;
		}
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
