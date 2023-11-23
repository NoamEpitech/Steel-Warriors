package com.stg13.steelwarriors.ressources;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class Assets {

    public static final AssetManager manager= new AssetManager();

    // Définition de l'énumération pour les animations du chevalier
    public enum KnightAnimation {
        READY("Knight/ready_1.png", "Knight/ready_2.png", "Knight/ready_3.png"),
        JUMP("Knight/jump_1.png", "Knight/jump_2.png", "Knight/jump_3.png", "Knight/jump_4.png", "Knight/jump_5.png"),
        HIT("Knight/hit_1.png", "Knight/hit_2.png", "Knight/hit_3.png"),
        WALK("Knight/walk_1.png", "Knight/walk_2.png", "Knight/walk_3.png", "Knight/walk_4.png", "Knight/walk_5.png", "Knight/walk_6.png"),
        WALKBACK("Knight/walk_1_back.png", "Knight/walk_2_back.png", "Knight/walk_3_back.png", "Knight/walk_4_back.png", "Knight/walk_5_back.png", "Knight/walk_6_back.png"),
        ATTACK("Knight/attack1_1.png", "Knight/attack1_2.png", "Knight/attack1_3.png", "Knight/attack1_4.png", "Knight/attack1_5.png", "Knight/attack1_6.png"),
        ATTACK2("Knight/attack4_1.png", "Knight/attack4_2.png", "Knight/attack4_3.png", "Knight/attack4_4.png", "Knight/attack4_5.png", "Knight/attack4_6.png"),
        FALL("Knight/fall_back_1.png", "Knight/fall_back_2.png", "Knight/fall_back_3.png", "Knight/fall_back_4.png", "Knight/fall_back_5.png"),
        RUN("Knight/run_1.png", "Knight/run_2.png", "Knight/run_3.png", "Knight/run_4.png", "Knight/run_5.png", "Knight/run_6.png"),
        RUNBACK("Knight/runback_1_flipped.png", "Knight/runback_2_flipped.png", "Knight/runback_3_flipped.png", "Knight/runback_4_flipped.png", "Knight/runback_5_flipped.png", "Knight/runback_6_flipped.png"),
        STAND("Knight/stand_up_1.png", "Knight/stand_up_2.png", "Knight/stand_up_3.png", "Knight/stand_up_4.png", "Knight/stand_up_5.png");

        private final String[] paths;

        KnightAnimation(String... paths) {
            this.paths = paths;
        }

        public String[] getPaths() {
            return paths;
        }
    }

    public enum BanditAnimation {
        READY("Bandit/LightBandit_Combat_Idle_0.png", "Bandit/LightBandit_Combat_Idle_1.png", "Bandit/LightBandit_Combat_Idle_2.png", "Bandit/LightBandit_Combat_Idle_3.png"),
        JUMP("Bandit/LightBandit_Jump_0.png"),
        HIT("Bandit/LightBandit_Recover_3.png", "Bandit/LightBandit_Recover_4.png", "Bandit/LightBandit_Recover_5.png", "Bandit/LightBandit_Recover_6.png", "Bandit/LightBandit_Recover_7.png"),
        WALK("Bandit/LightBandit_Run_0.png", "Bandit/LightBandit_Run_1.png", "Bandit/LightBandit_Run_2.png", "Bandit/LightBandit_Run_3.png", "Bandit/LightBandit_Run_4.png", "Bandit/LightBandit_Run_5.png", "Bandit/LightBandit_Run_6.png", "Bandit/LightBandit_Run_7.png"),
        RUNBACK("Bandit/LightBandit_Run_0.png", "Bandit/LightBandit_Run_1.png", "Bandit/LightBandit_Run_2.png", "Bandit/LightBandit_Run_3.png", "Bandit/LightBandit_Run_4.png", "Bandit/LightBandit_Run_5.png", "Bandit/LightBandit_Run_6.png", "Bandit/LightBandit_Run_7.png"),
        RUN("Bandit/LightBandit_Runback_0_flipped.png", "Bandit/LightBandit_Runback_1_flipped.png", "Bandit/LightBandit_Runback_2_flipped.png", "Bandit/LightBandit_Runback_3_flipped.png", "Bandit/LightBandit_Runback_4_flipped.png", "Bandit/LightBandit_Runback_5_flipped.png", "Bandit/LightBandit_Runback_6_flipped.png", "Bandit/LightBandit_Runback_7_flipped.png"),
        ATTACK("Bandit/LightBandit_Combat_Idle_0.png", "Bandit/LightBandit_Combat_Idle_1.png", "Bandit/LightBandit_Combat_Idle_2.png", "Bandit/LightBandit_Combat_Idle_3.png", "Bandit/LightBandit_Attack_0.png", "Bandit/LightBandit_Attack_1.png", "Bandit/LightBandit_Attack_2.png", "Bandit/LightBandit_Attack_3.png", "Bandit/LightBandit_Attack_4.png", "Bandit/LightBandit_Attack_5.png", "Bandit/LightBandit_Attack_6.png", "Bandit/LightBandit_Attack_7.png"),
        HURT("Bandit/LightBandit_Hurt_0.png", "Bandit/LightBandit_Hurt_1.png", "Bandit/LightBandit_Combat_Idle_0.png"),
        DEAD("Bandit/LightBandit_Recover_7.png", "Bandit/LightBandit_Recover_6.png", "Bandit/LightBandit_Recover_5.png", "Bandit/LightBandit_Recover_4.png", "Bandit/LightBandit_Recover_3.png", "Bandit/LightBandit_Recover_2.png", "Bandit/LightBandit_Recover_1.png", "Bandit/LightBandit_Recover_0.png", "Bandit/LightBandit_Death_0.png");
        private final String[] paths;

        BanditAnimation(String... paths) {
            this.paths = paths;
        }

        public String[] getPaths() {
            return paths;
        }
    }



    //Classe interne pour les ressources musicales
    public static class MusicAssets {
        public static final String FIGHT_MUSIC = "Sounds/duringfight.wav";
        // Ajoutez d'autres pistes musicales ici si nécessaire
    }

    // Classe interne pour les ressources du menu
    public static class MenuAssets {
        public static final String BACKGROUND = "Interfaces/bg_bandit.png";
        public static final String BUTTON_BEIGE = "Interfaces/PLAY.png";
        public static final String BUTTON_BEIGE_PRESSED = "Interfaces/PLAY.png";
        public static final String BUTTON_BROWN = "Interfaces/EXIT.png";
        public static final String BUTTON_BROWN_PRESSED = "Interfaces/buttonLong_brown_pressed.png";
        public static final String PANEL_BEIGE = "Interfaces/panel_beige.png";
        public static final String PANEL_BEIGE_PRESSED = "Interfaces/panelpressed_beige.png";
        public static final String PANEL_BROWN = "Interfaces/panel_brown.png";
        public static final String PANEL_BROWN_PRESSED = "Interfaces/panelpressed_brown.png";
        public static final String ICONE = "Interfaces/steelwarrior.jpg";
    }

    // Classe interne pour les cartes
    public static class MapAssets {
        public static final String MAP1 = "Maps/map1.png";
        // Ajoutez d'autres cartes ici si nécessaire
    }

        public void load(){
            loadGameplayAssets();
            loadMusicAssets();
            loadMenuAssets();
            loadMapAssets();
        }

    private void loadGameplayAssets() {
        for (KnightAnimation animation : KnightAnimation.values()) {
            for (String path : animation.getPaths()) {
                manager.load(path, Texture.class);
            }
        }
        for (BanditAnimation animation : BanditAnimation.values()) {
            for (String path : animation.getPaths()) {
                manager.load(path, Texture.class);
            }
        }
    }

    private void loadMusicAssets() {
        manager.load(MusicAssets.FIGHT_MUSIC, Music.class);
        // Chargez d'autres pistes musicales ici si nécessaire
    }
        private void loadMenuAssets() {
            manager.load(MenuAssets.BACKGROUND, Texture.class);
            manager.load(MenuAssets.BUTTON_BEIGE, Texture.class);
            manager.load(MenuAssets.BUTTON_BEIGE_PRESSED, Texture.class);
            manager.load(MenuAssets.BUTTON_BROWN, Texture.class);
            manager.load(MenuAssets.BUTTON_BROWN_PRESSED, Texture.class);
            manager.load(MenuAssets.PANEL_BEIGE, Texture.class);
            manager.load(MenuAssets.PANEL_BEIGE_PRESSED, Texture.class);
            manager.load(MenuAssets.PANEL_BROWN, Texture.class);
            manager.load(MenuAssets.PANEL_BROWN_PRESSED, Texture.class);
            manager.load(MenuAssets.ICONE, Texture.class);
        }

        private void loadMapAssets() {
            manager.load(MapAssets.MAP1, Texture.class);
            // Chargez d'autres cartes ici si nécessaire
        }

        public void dispose(){
            manager.dispose();
        }
    }