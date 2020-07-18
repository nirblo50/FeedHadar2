package com.balouka.feedhadar;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.badlogic.gdx.Gdx.graphics;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor
{
	// Hadar
	Hadar hadar;	// the Hadar object
	int posX;		// the Hadar image X position
	int posY;		// the Hadar image Y position

	// the vegan food
	VeganFood food1;
	VeganFood food2;


	private int scoreCounter;	// the score
	private float timePast;

	// game flags
	private String gameStatus = "inGame";



	@Override
	public void create ()
	{
		// setting Hadar Stats
		hadar = new Hadar();
		posY = 0;
		posX = Gdx.graphics.getWidth()/2 - hadar.getWidth() / 2;

		scoreCounter = 0;
		timePast = 0;
		food1 = new VeganFood();
		food2 = new VeganFood();
	}



	@Override
	public void render ()
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.input.setInputProcessor(this);		//o setting the input from the phone (pressing the screen)
		timePast += graphics.getDeltaTime();

		//if (gameStatus.equals("inGame"))
		{
			hadar.drawHadar(posX, timePast);

			// if Hadar eat a vegan food
			if (food1.drawVeganFood(posX).equals("vegan food eaten") || food2.drawVeganFood(posX).equals("vegan food eaten"))
				scoreCounter++;

			// if hadar eat meat
			if (food1.drawVeganFood(posX).equals("meat food eaten"))
				gameStatus = "lost";
		}

		//System.out.println(gameStatus);

	}


















	@Override
	public void dispose ()
	{
		// Hadar
		hadar.getBatch().dispose();
		hadar.getAtlas().dispose();
		food1.getBatch().dispose();
		food1.getVeganFood().dispose();
	}


	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{

		posX = screenX - hadar.getWidth()/2;
		posY = Gdx.graphics.getHeight() - screenY;
		return true;
	}


	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		posX = screenX - hadar.getWidth()/2;
		posY = Gdx.graphics.getHeight() - screenY;
		return true;
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
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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

}
