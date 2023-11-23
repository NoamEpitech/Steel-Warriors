package com.stg13.steelwarriors;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stg13.steelwarriors.ressources.Assets;

public class SteelWarriorGame extends Game {
	public SpriteBatch batch;
	public Assets assets;
	public static int width=1920;
	public static int height=1080;
	public BitmapFont font;

	@Override
	public void create () {
		batch = new SpriteBatch();
		assets= new Assets();

		assets.load();
		assets.manager.finishLoading();
		font= new BitmapFont();
		this.setScreen(new menuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose(){
		batch.dispose();
		font.dispose();
		assets.dispose();
	}
}
