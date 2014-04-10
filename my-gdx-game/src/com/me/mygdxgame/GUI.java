package com.me.mygdxgame;

import com.me.mygdxgame.Button.ButtonID;
import com.me.mygdxgame.ModeManager.GMode;

public class GUI {
	private CornerButtons buttons = new CornerButtons(); 
	public GUI(GMode initMode)
	{
		buttons.setButtonName(ButtonID.topLeft, "Draw(Clear)");
		buttons.setButtonName(ButtonID.topRight, "Listen");
		buttons.setButtonName(ButtonID.bottomRight, "Switch Mode");
		buttons.setButtonName(ButtonID.bottomLeft, "Draw(Edit)");
		buttons.setButtonName(ButtonID.bottomMid, modeToString(initMode));
	}	
	public void dispose()
	{
		buttons.dispose();
	}
	public void updateMode(GMode mode)
	{
		buttons.setButtonName(ButtonID.bottomMid, modeToString(mode));
	}	
	public ButtonID hitTest(IntVec2 intersect)
	{
		return buttons.hitTest(intersect);
	}
	private String modeToString(GMode mode)
	{
		switch(mode)
		{
		case ZMode: return "ZMode";
		case SoftMode: return "SoftMode";
		}
		return "None";
	}
	public void draw()
	{
		buttons.draw();
	}
}
