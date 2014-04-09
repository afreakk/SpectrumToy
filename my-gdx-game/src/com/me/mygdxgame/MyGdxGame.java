package com.me.mygdxgame;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
 

public class MyGdxGame implements ApplicationListener, InputProcessor {
	private OrthographicCamera camera;

	private ShapeRenderer shapeRenderer= null;
	private DrawCapture drawCapture;
	private Recorder recordMgr;
	private ModeManager modeMgr;
	private GUI gui;
	@Override
	public void create() 
	{
		Gdx.input.setInputProcessor(this);
		Gdx.gl.glLineWidth(1);
		drawCapture = new DrawCapture();
		recordMgr = new Recorder();
		shapeRenderer = new ShapeRenderer();
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(1, h/w);
		modeMgr = new ModeManager(recordMgr.getStorageSize());
		gui = new GUI(modeMgr.getMode());
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
		if(drawCapture.getOldLinePoints() != null)
			 modeMgr.refreshGraphics(shapeRenderer, recordMgr.getSpectrumData());
		 else
			 drawCapture.drawLine(shapeRenderer, recordMgr.getStorageSize());
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		drawCapture.touchDragged(screenX, screenY);
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		drawCapture.touchDown();
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(drawCapture.switchMode())
			modeMgr.newMode(drawCapture.getOldLinePoints());
		else
			modeMgr.setArray(drawCapture.getFreshLinePoints());
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}
	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
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
