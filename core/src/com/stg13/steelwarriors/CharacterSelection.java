package com.stg13.steelwarriors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stg13.steelwarriors.level.MainGameScreen;
import com.stg13.steelwarriors.ressources.Assets;
import com.stg13.steelwarriors.menuScreen;

public class CharacterSelection implements Screen {
    SteelWarriorGame game;

    BitmapFont font;

    Texture panelbrown;

    Texture panelbeige;

    Texture panelbeigeinset;

    Texture panelbrowninset;

    Texture back;
    Texture backp;

    Texture title;

    int width_height =300;

    int title_XY=800;

    int button_Y= 400;

    int backX=1920/2-50;
    int backY=100;

    OrthographicCamera camera;

    public CharacterSelection(SteelWarriorGame game){
        this.game=game;

    }
    @Override
    public void show() {
        panelbeige = game.assets.manager.get(Assets.MenuAssets.PANEL_BEIGE, Texture.class);
        panelbrown = game.assets.manager.get(Assets.MenuAssets.PANEL_BROWN, Texture.class);
        panelbeigeinset = game.assets.manager.get(Assets.MenuAssets.PANEL_BEIGE_PRESSED, Texture.class);
        panelbrowninset = game.assets.manager.get(Assets.MenuAssets.PANEL_BROWN_PRESSED, Texture.class);
        backp = game.assets.manager.get(Assets.MenuAssets.BUTTON_BROWN_PRESSED, Texture.class);
        back = game.assets.manager.get(Assets.MenuAssets.BUTTON_BROWN, Texture.class);
        title = game.assets.manager.get(Assets.MenuAssets.ICONE, Texture.class);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        ScreenUtils.clear(0, 0, 0, 0);

        game.batch.begin();


        //buttons
        if (Gdx.input.getX()<200 + width_height && Gdx.input.getX() > 200 && SteelWarriorGame.height - Gdx.input.getY() < button_Y + width_height && SteelWarriorGame.height - Gdx.input.getY() > button_Y) {

            game.batch.draw(panelbeige,200,button_Y,300,300);
            if (Gdx.input.isTouched()) {
                game.setScreen(new MainGameScreen(game));
            }
        } else {
            game.batch.draw(panelbeigeinset,200,button_Y,300,300);
        }

        if (Gdx.input.getX()<800 + width_height && Gdx.input.getX() > 800 && SteelWarriorGame.height - Gdx.input.getY() < button_Y + width_height && SteelWarriorGame.height - Gdx.input.getY() > button_Y) {

            game.batch.draw(panelbrown,800,button_Y,width_height,width_height);
            if (Gdx.input.isTouched()) {
                game.setScreen(new MainGameScreen(game));
            }
        } else {
            game.batch.draw(panelbrowninset,800,button_Y,width_height,width_height);
        }

        if (Gdx.input.getX()<1400 + width_height && Gdx.input.getX() > 1400 && SteelWarriorGame.height - Gdx.input.getY() < button_Y + width_height && SteelWarriorGame.height - Gdx.input.getY() > button_Y) {

            game.batch.draw(panelbeige,1400,button_Y,width_height,width_height);
            if (Gdx.input.isTouched()) {
                game.setScreen(new MainGameScreen(game));
            }
        } else {
            game.batch.draw(panelbeigeinset,1400,button_Y,width_height,width_height);
        }

        //title
        game.batch.draw(title,title_XY, title_XY,width_height,width_height);

        //back
        if (Gdx.input.getX()<backX + width_height && Gdx.input.getX() > backX && SteelWarriorGame.height - Gdx.input.getY() < backY + width_height && SteelWarriorGame.height - Gdx.input.getY() > backY) {
            game.batch.draw(backp, backX, backY, 100, 100);
            if (Gdx.input.isTouched()) {
                game.setScreen(new menuScreen(game)); // menuScreen est maintenant importé, donc cette ligne ne devrait plus causer d'erreur
            }
        } else {
            game.batch.draw(back, backX, backY, 100, 100);
        }
        game.batch.end();
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
    public void hide() {

    }

    @Override
    public void dispose() {
        // Libérez les ressources chargées
        panelbeige.dispose();
        panelbrown.dispose();
        panelbeigeinset.dispose();
        panelbrowninset.dispose();
        backp.dispose();
        back.dispose();
        title.dispose();
    }
}
