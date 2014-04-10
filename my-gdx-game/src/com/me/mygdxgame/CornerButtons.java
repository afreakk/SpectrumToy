package com.me.mygdxgame;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.mygdxgame.Button.ButtonID;

public class CornerButtons {
	int height = 20;
	int width = 85;
	//formatting
    int leftCoord = 0;
    int topCoord = Gdx.graphics.getHeight();
    int bottomCoord = height;
    int rightCoord = Gdx.graphics.getWidth()-width;
    int midCoord = (int)((float)Gdx.graphics.getWidth()/2.0f);
    //---==
    
	private BitmapFont modeText;
	private SpriteBatch batch;
	HashMap<ButtonID, Button> buttons = new HashMap<ButtonID, Button>();

	public CornerButtons()
	{
		modeText = new BitmapFont();
	    modeText.setColor(1, 1, 1, 1);
		batch = new SpriteBatch();
		initAll();
	}
	private void createButton(ButtonID id)
	{
        buttons.put(id, new Button(posFromId(id), width, height));
	}
	private void initAll()
	{
        createButton(ButtonID.topLeft);
        createButton(ButtonID.topRight);
        createButton(ButtonID.bottomRight);
        createButton(ButtonID.bottomLeft);
        createButton(ButtonID.bottomMid);
	}
	public ButtonID hitTest(IntVec2 intersect)
	{
		Iterator it = buttons.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry elem = (Map.Entry)it.next();
			if(((Button)elem.getValue()).hitTest(intersect))
				return (ButtonID) elem.getKey();
		}
		return ButtonID.None;
	}
	private IntVec2 posFromId(ButtonID id)
	{
		switch (id)
		{
		case topLeft: return new IntVec2(leftCoord, topCoord);
		case topRight: return new IntVec2(rightCoord, topCoord);
		case bottomRight: return new IntVec2(rightCoord, bottomCoord);
		case bottomLeft: return new IntVec2(leftCoord, bottomCoord);
		case bottomMid: return new IntVec2(midCoord, bottomCoord);
		default:Gdx.app.error("error:", "CornerButtons.posFromId(ButtonID id)"); return new IntVec2();
		}
	}
	public void setButtonName(ButtonID id, String name)
	{
		buttons.get(id).SetButtonName(name);
	}
	public void dispose()
	{
		batch.dispose();
		modeText.dispose();
	}
	public void draw()
	{
		batch.begin();
		Iterator it = buttons.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry elem = (Map.Entry)it.next();
			((Button)elem.getValue()).draw(batch, modeText);
		}
		batch.end();
	}
}
