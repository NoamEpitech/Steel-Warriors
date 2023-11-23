package com.stg13.steelwarriors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stg13.steelwarriors.ressources.Assets;
import org.w3c.dom.Text;

public class menuScreen implements Screen {
    SteelWarriorGame game;
    Texture title;
    Texture background;
    OrthographicCamera camera;

    Texture exitButtonActive;
    Texture exitButtonInactive;
    Texture playButtonActive;
    Texture playButtonInactive;

    int buttonwidth = 300;
    int buttonheight=300;
    int exit_Y=340;
    int exit_X=30;
    int play_Y=750;
    int button_X=30;

    public menuScreen(SteelWarriorGame game){
        this.game=game;

        playButtonActive = game.assets.manager.get(Assets.MenuAssets.BUTTON_BEIGE_PRESSED, Texture.class);
        playButtonInactive = game.assets.manager.get(Assets.MenuAssets.BUTTON_BEIGE, Texture.class);
        exitButtonActive = game.assets.manager.get(Assets.MenuAssets.BUTTON_BROWN_PRESSED, Texture.class);
        exitButtonInactive = game.assets.manager.get(Assets.MenuAssets.BUTTON_BROWN, Texture.class);

        background = game.assets.manager.get(Assets.MenuAssets.BACKGROUND, Texture.class);
        title = game.assets.manager.get(Assets.MenuAssets.ICONE, Texture.class);

        camera= new OrthographicCamera();
        camera.setToOrtho(false, 1920,1080);
    }

    @Override
    public void show() {
    }

    private void setScreen(Screen Screen) {
        // Libérez les ressources chargées par l'écran précédent
        game.setScreen(Screen);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 0);
        ScreenUtils.clear(1, 1, 1, 0);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.batch.draw(background, 0, 0, SteelWarriorGame.width, SteelWarriorGame.height);

        game.batch.draw(title,800, 850,300,300);

        if (Gdx.input.getX() < button_X + buttonwidth && Gdx.input.getX() > button_X && SteelWarriorGame.height - Gdx.input.getY() < play_Y + buttonheight && SteelWarriorGame.height - Gdx.input.getY() > play_Y) {
            game.batch.draw(playButtonActive, button_X, play_Y, buttonwidth, buttonheight);;
            game.batch.draw(playButtonInactive, button_X, play_Y, buttonwidth, buttonheight);

            if (Gdx.input.isTouched()) {
                game.setScreen(new CharacterSelection(game));
            }
        } else {
            game.batch.draw(playButtonInactive, button_X, play_Y, buttonwidth, buttonheight);
            game.batch.draw(playButtonActive, button_X, play_Y, buttonwidth, buttonheight);
        }

        if (Gdx.input.getX() < button_X + buttonwidth && Gdx.input.getX() > button_X && SteelWarriorGame.height - Gdx.input.getY() < exit_Y + buttonheight && SteelWarriorGame.height - Gdx.input.getY() > exit_Y) {
            game.batch.draw(exitButtonActive, button_X, exit_Y, buttonwidth, buttonheight);
            game.batch.draw(exitButtonInactive, button_X, exit_Y, buttonwidth, buttonheight);

            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
    }else {
            game.batch.draw(exitButtonActive, button_X, exit_Y, buttonwidth, buttonheight);
            game.batch.draw(exitButtonInactive, button_X, exit_Y, buttonwidth, buttonheight);

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
        playButtonActive.dispose();
        playButtonInactive.dispose();
        exitButtonActive.dispose();
        exitButtonInactive.dispose();
        background.dispose();
        title.dispose();
    }
}
