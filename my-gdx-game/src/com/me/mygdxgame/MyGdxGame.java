package com.me.mygdxgame;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
 

public class MyGdxGame implements ApplicationListener {
	private OrthographicCamera camera;

	private ShapeRenderer shapeRenderer= null;
	private DrawCapture drawCapture;
	private Recorder recordMgr;
	private ModeManager modeMgr;
	private GUI gui;
	private TouchListener touchListener = new TouchListener();
	@Override
	public void create() 
	{
		Gdx.input.setInputProcessor(touchListener);
		Gdx.gl.glLineWidth(1);
		drawCapture = new DrawCapture();
		recordMgr = new Recorder();
		shapeRenderer = new ShapeRenderer();
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(1, h/w);
		modeMgr = new ModeManager(recordMgr.getStorageSize());
		gui = new GUI(modeMgr.getMode());
		//-- last -.- touchListener.registerAll
		touchListener.registerAll(drawCapture, gui, modeMgr);
	}

	@Override
	public void dispose() {
		gui.dispose();
		recordMgr.disposeRecorder();
	}
	@Override
	public void render() 
	{
		recordMgr.record();	
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		shapeRenderer.setProjectionMatrix(camera.combined);
		drawLine();
		gui.draw();
	}
	private void drawLine()
	{
		switch(touchListener.getCurrentMode())
		{
		case drawing: drawCapture.drawLine(shapeRenderer, recordMgr.getStorageSize()); break;
		case listening: modeMgr.refreshGraphics(shapeRenderer, recordMgr.getSpectrumData()); break;
		}
			 
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

}
