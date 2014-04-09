package com.me.mygdxgame;

import com.me.mygdxgame.Button.ButtonID;
import com.me.mygdxgame.ModeManager.GMode;

public class GUI {
	private CornerButtons buttons = new CornerButtons(); 
	public GUI(GMode initMode)
	{
		buttons.setButtonName(ButtonID.topLeft, "Begin Draw");
		buttons.setButtonName(ButtonID.topRight, "End Draw");
		buttons.setButtonName(ButtonID.bottomRight, "Switch Mode");
		buttons.setButtonName(ButtonID.bottomLeft, modeToString(initMode));
	}	

	public void dispose()
	{
		buttons.dispose();
	}
	public void updateMode(GMode mode)
	{
		buttons.setButtonName(ButtonID.bottomLeft, modeToString(mode));
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
