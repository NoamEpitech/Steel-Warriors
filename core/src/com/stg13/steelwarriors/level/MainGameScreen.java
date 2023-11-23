package com.stg13.steelwarriors.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stg13.steelwarriors.CharacterSelection;
import com.stg13.steelwarriors.SteelWarriorGame;
import com.stg13.steelwarriors.characters.Bandit;
import com.stg13.steelwarriors.menuScreen;
import com.stg13.steelwarriors.ressources.Assets;
import com.stg13.steelwarriors.characters.Knight;
import com.stg13.steelwarriors.characters.Obstacle;

import java.util.ArrayList;
import java.util.List;

public class MainGameScreen implements Screen {
    SteelWarriorGame game;
    float playerX = 10;
    float playerY = 10;
    float Speed = 1000.0f;

    float tableX = 410; // Remplacez par la position x réelle de la table
    float tableY = 70; // Remplacez par la position y réelle de la table
    float tableWidth = 1050; // Remplacez par la largeur réelle de la table
    float tableHeight = 50; // Remplacez par la hauteur réelle de la table

    Obstacle table;
    Texture img;
    Texture img2;
    Texture map;
    Knight knight;
    Bandit bandit;
    Bandit bandit2;


    Music backgroundMusic;

    public MainGameScreen(SteelWarriorGame game){
        this.game = game;

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(Assets.MusicAssets.FIGHT_MUSIC));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.5f);
        backgroundMusic.play();
    }

    @Override
    public void show() {
        map = game.assets.manager.get(Assets.MapAssets.MAP1, Texture.class);
        table = new Obstacle(tableX, tableY, tableWidth, tableHeight);
        img = game.assets.manager.get(Assets.KnightAnimation.READY.getPaths()[0]);
        img2 = game.assets.manager.get(Assets.BanditAnimation.READY.getPaths()[0]);

        knight = new Knight(game, -30 , 85, 100, img);
        bandit = new Bandit(game, 1200, 85, 100, img2);
        bandit2 = new Bandit(game, 600, 85, 100, img2);
        knight.setBandit(bandit);
        bandit.setKnight(knight);
        bandit2.setKnight(knight);

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);

        // Créez une liste d'obstacles
        List<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(new Obstacle(0, 85, 1980, 0));
        obstacles.add(table);
//      obstacles.add(new Obstacle(0, 85, 1980, 200));
//      Mettez à jour le chevalier pour chaque obstacle dans la liste
        for (Obstacle obstacle : obstacles) {
            knight.update(obstacle,table);
            bandit.update(obstacle,table);
            bandit2.update(obstacle,table);
        }

        knight.nextFrame();
        bandit.nextFrame(bandit.getAnimationFromAI());
        bandit2.nextFrame(bandit2.getAnimationFromAI());
        game.batch.begin();
        game.batch.draw(map, 0, 0, 1920, 1080);
        game.batch.draw(knight.getTexture(), knight.getX(), knight.getY(),100,100);
        game.batch.draw(bandit.getTexture(), bandit.getX(), bandit.getY(),100,100);
        game.batch.draw(bandit2.getTexture(), bandit2.getX(), bandit2.getY(),100,100);
        game.batch.end();
    }
    @Override
    public void resize(int width, int height) {
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        camera.update();
        camera.position.set(width / 2f, height / 2f, 0);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
        //backgroundMusic.play();
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.5f);
        backgroundMusic.play();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.batch.dispose();
        img.dispose();
        img2.dispose();
        map.dispose();
        backgroundMusic.dispose();
    }
}