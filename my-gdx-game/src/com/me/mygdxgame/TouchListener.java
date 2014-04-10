package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.me.mygdxgame.Button.ButtonID;

public class TouchListener implements InputProcessor {
	private DrawCapture drawCapture;
	private GUI gui;
	private ModeManager modeMgr;
	private GameMode mMode = GameMode.drawing;
	private enum ButtonType
	{
		start_draw,
		end_draw,
		switch_mode,
		edit_draw,
		nothing
	}
	private String buttonToString(ButtonType btnTyp)
	{
		switch(btnTyp)
		{
		case start_draw: return "start_draw";
		case end_draw: return "end_draw";
		case switch_mode: return "switch_mode";
		case nothing: return "nothing";
		case edit_draw: return "edit_draw";
		default: return "";
		}
	}
	public enum GameMode
	{
		drawing,
		listening
	}
	public GameMode getCurrentMode()
	{
		return mMode;
	}
	
	public void registerAll(DrawCapture drawCapture, GUI gui, ModeManager modeMgr)
	{
		this.drawCapture = drawCapture;
		this.gui = gui;
		this.modeMgr = modeMgr;
	}
	private ButtonType stateFromButtonID(ButtonID id)
	{
		switch(id)
		{
		case topLeft: return ButtonType.start_draw;
		case topRight: return ButtonType.end_draw;
		case bottomRight: return ButtonType.switch_mode;
		case bottomLeft: return ButtonType.edit_draw;
		default: return ButtonType.nothing;
		}
	}
	private void doButton(ButtonType btn)
	{
		switch(btn)
		{
		case start_draw: mMode = GameMode.drawing; drawCapture.clearPoints(); break;
		case end_draw:  mMode = GameMode.listening; modeMgr.setArray(drawCapture.getFreshLinePoints()); break; 
		case switch_mode: modeMgr.newMode(drawCapture.getOldLinePoints()); gui.updateMode(modeMgr.getMode()); break;
		case edit_draw: mMode = GameMode.drawing; break;
		default: break;
		}
	}
	;
		
//--------- callbacks
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		switch(mMode)
		{
		case drawing: drawCapture.insertPoints(screenX, screenY);
		default: break;
		}
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		ButtonID buttonHit = gui.hitTest(new IntVec2(screenX, Gdx.graphics.getHeight() - screenY));
		if(buttonHit != ButtonID.None)
		{
			ButtonType btnHit = stateFromButtonID(buttonHit);
            Gdx.app.log("btn:", buttonToString(btnHit));
			doButton(btnHit);
		}
		else
            Gdx.app.log("btn:", "None");
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		drawCapture.finishLine();
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
//--------- /callbacks\
}
