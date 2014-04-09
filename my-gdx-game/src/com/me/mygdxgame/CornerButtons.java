package com.me.mygdxgame;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.mygdxgame.Button.ButtonID;

public class CornerButtons {
	//formatting
    int leftCoord = (int)((float)Gdx.graphics.getWidth()/16.0f);
    int topCoord = Gdx.graphics.getHeight()-(int)((float)Gdx.graphics.getHeight()/16.0f);
    int rightCoord = Gdx.graphics.getWidth()-(int)((float)Gdx.graphics.getWidth()/5.0f);
    int bottomCoord =(int)((float)Gdx.graphics.getHeight()/16.0f);
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
        buttons.put(id, new Button(posFromId(id)));
	}
	private void initAll()
	{
        createButton(ButtonID.topLeft);
        createButton(ButtonID.topRight);
        createButton(ButtonID.bottomRight);
        createButton(ButtonID.bottomLeft);
	}
	private IntVec2 posFromId(ButtonID id)
	{
		switch (id)
		{
		case topLeft: return new IntVec2(leftCoord, topCoord);
		case topRight: return new IntVec2(rightCoord, topCoord);
		case bottomRight: return new IntVec2(rightCoord, bottomCoord);
		case bottomLeft: return new IntVec2(leftCoord, bottomCoord);
		}
		Gdx.app.error("error:", "CornerButtons.posFromId(ButtonID id)");
		return new IntVec2();
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
