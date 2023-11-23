package com.stg13.steelwarriors.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.stg13.steelwarriors.SteelWarriorGame;
import com.stg13.steelwarriors.ressources.Assets;

public class Knight {
    private static final float HAUTEUR_DU_SOL = 85;
    private boolean canJump = true;
    private boolean isJumpCooldown = false;
    private float jumpCooldownTime = 3.0f; // Temps de recharge en secondes
    private float currentJumpCooldown = 0;

    SteelWarriorGame game;
    private float x;
    private float y;
    private int health;
    private Texture knight;
    private Array<Texture> animationFrames;
    private int currentFrameIndex;
    private Array<Texture> readyframe, walkFrames, jumpFrames, hitFrames, attackFrames, attack2Frames, fallFrames, runFrames, standFrames, walkbackFrames, runbackFrames;
    private int frameDelay = 25;
    private int frameCounter = 0;
    private float walkSpeed = 1;
    private float runSpeed = 1.5f;
    private Bandit bandit;
    private float maxJumpHeight = 150;
    private float currentJumpHeight = 0;
    private float jumpVelocity = 100;
    private float gravity = -10;
    private boolean isJumping = false;

    float stateTime = 0;

    public Knight(SteelWarriorGame game, float x, float y, int health, Texture knightTexture) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.health = health;
        this.knight = knightTexture;
        knight = game.assets.manager.get(Assets.KnightAnimation.READY.getPaths()[0]);

        animationFrames = new Array<>();
        currentFrameIndex = 0;
        getKnightImage();
        currentAnimation = KnightState.READY;
        animationFrames = readyframe;
    }

    public void setBandit(Bandit bandit) {
        this.bandit = bandit;
    }

    private void getKnightImage() {
        readyframe = loadTextures(Assets.KnightAnimation.READY);
        walkFrames = loadTextures(Assets.KnightAnimation.WALK);
        jumpFrames = loadTextures(Assets.KnightAnimation.JUMP);
        hitFrames = loadTextures(Assets.KnightAnimation.HIT);
        attackFrames = loadTextures(Assets.KnightAnimation.ATTACK);
        attack2Frames = loadTextures(Assets.KnightAnimation.ATTACK2);
        fallFrames = loadTextures(Assets.KnightAnimation.FALL);
        runFrames = loadTextures(Assets.KnightAnimation.RUN);
        standFrames = loadTextures(Assets.KnightAnimation.STAND);
        walkbackFrames = loadTextures(Assets.KnightAnimation.WALKBACK);
        runbackFrames = loadTextures(Assets.KnightAnimation.RUNBACK);
    }

    private Array<Texture> loadTextures(Assets.KnightAnimation animation) {
        Array<Texture> frames = new Array<>();
        for (String path : animation.getPaths()) {
            frames.add(game.assets.manager.get(path, Texture.class));
        }
        return frames;
    }

    public Texture getTexture() {
        if (animationFrames.size == 0) {
            return defaultTexture();
        }
        return animationFrames.get(currentFrameIndex);
    }

    private Texture defaultTexture() {
        if (knight == null) {
            knight = new Texture("Knight/ready_1.png");
        }
        return knight;
    }

    public enum KnightState {
        READY,
        WALKING,
        WALKINGBACK,
        JUMPING,
        ATTACKING,
        ATTACKING2,
        FALLING,
        RUNNING,
        RUNNINGBACK,
        STANDING,
        HITTING
    }

    private KnightState currentAnimation;

    public void nextFrame() {
        KnightState newAnimation = getAnimationFromInput();

        if (newAnimation != currentAnimation) {
            currentAnimation = newAnimation;
            switch (currentAnimation) {
                case READY:
                    animationFrames = readyframe;
                    break;
                case WALKING:
                    animationFrames = walkFrames;
                    break;
                case WALKINGBACK:
                    animationFrames = walkbackFrames;
                    break;
                case JUMPING:
                    animationFrames = jumpFrames;
                    break;
                case ATTACKING:
                    animationFrames = attackFrames;
                    break;
                case ATTACKING2:
                    animationFrames = attack2Frames;
                    break;
                case FALLING:
                    animationFrames = fallFrames;
                    break;
                case RUNNING:
                    animationFrames = runFrames;
                    break;
                case RUNNINGBACK:
                    animationFrames = runbackFrames;
                    break;
                case STANDING:
                    animationFrames = standFrames;
                    break;
                case HITTING:
                    animationFrames = hitFrames;
                    break;
            }
            currentFrameIndex = 0;
            frameCounter = 0;
        }

        frameCounter++;
        if (frameCounter >= frameDelay) {
            if (!animationFrames.isEmpty()) {
                currentFrameIndex = (currentFrameIndex + 1) % animationFrames.size;
            }
            frameCounter = 0;
        }
    }

    private KnightState getAnimationFromInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            return KnightState.WALKING;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            return KnightState.WALKINGBACK;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            return KnightState.JUMPING;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            return KnightState.FALLING;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            return KnightState.ATTACKING;
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            return KnightState.ATTACKING2;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            return KnightState.HITTING;
        } else if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            return KnightState.RUNNING;
        } else if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            return KnightState.RUNNINGBACK;
        } else {
            return KnightState.READY;
        }
    }

    public void attack() {
        if (collidesWithBandit()) {
            bandit.takeDamage(10);
            System.out.println("Knight attacks Bandit");
        }
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            System.out.println("Knight has been defeated!");
        } else {
            System.out.println("Knight took damage. Remaining health: " + health);
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void move(float deltaX, float deltaY, Obstacle obstacle) {
        float prevX = getX();
        float prevY = getY();

        setX(getX() + deltaX);
        if (collidesWithObstacle(obstacle) || collidesWithBandit()) {
            setX(prevX);
        }

        if (getX() < 0) {
            setX(0);
        } else if (getX() > 1920 - getTexture().getWidth()) {
            setX(1920 - getTexture().getWidth());
        }

        if (getY() < 0) {
            setY(0);
        } else if (getY() > SteelWarriorGame.height - getTexture().getHeight()) {
            setY(SteelWarriorGame.height - getTexture().getHeight());
        }

        setY(getY() + deltaY);
        if (collidesWithObstacle(obstacle) || collidesWithBandit()) {
            setY(prevY);
        }
    }

    public void jump() {
        if (canJump && !isJumpCooldown) {
            isJumping = true;
            canJump = false;
            currentJumpHeight = y;
            jumpVelocity = 100;
            isJumpCooldown = true; // Active le cooldown de saut
        }
    }

    private void updateJumpCooldown(float deltaTime) {
        if (isJumpCooldown) {
            currentJumpCooldown += deltaTime;
            if (currentJumpCooldown >= jumpCooldownTime) {
                currentJumpCooldown = 0;
                isJumpCooldown = false;
            }
        }
    }

    public void updateJump(float deltaTime) {
        if (isJumping) {
            jumpVelocity += gravity * deltaTime;
            y += jumpVelocity * deltaTime;
            currentJumpHeight += jumpVelocity * deltaTime;

            if (currentJumpHeight - y >= maxJumpHeight || jumpVelocity <= 0) {
                jumpVelocity = 0;
            }

            if (y <= HAUTEUR_DU_SOL) {
                y = HAUTEUR_DU_SOL;
                isJumping = false;
                canJump = true;
                currentJumpHeight = 0;
            }
        }

        updateJumpCooldown(deltaTime);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getHealth() {
        return health;
    }

    public void update(Obstacle obstacle, Obstacle table) {
        float deltaX = 0, deltaY = 0;
        float speed;

        if (currentAnimation == KnightState.RUNNING || currentAnimation == KnightState.RUNNINGBACK) {
            speed = runSpeed;
        } else {
            speed = walkSpeed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.E)) {
            deltaX = speed;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.Q)) {
            deltaX = -speed;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            deltaY = speed;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            deltaY = -speed;

            if (y < HAUTEUR_DU_SOL) {
                y = HAUTEUR_DU_SOL;
            }
        }

        move(deltaX, deltaY, obstacle);

        updateJump(Gdx.graphics.getDeltaTime());

        if (!isJumping && !collidesWithObstacle(table)) {
            y -= speed;
            if (y <= HAUTEUR_DU_SOL) {
                y = HAUTEUR_DU_SOL;
                canJump = true;
            }
        }

        if (obstacle == table && collidesWithObstacle(obstacle)) {
            setY(table.getY() + table.getHeight());
        } else {
            move(deltaX, deltaY, obstacle);
        }

        nextFrame();

        if (currentAnimation == KnightState.ATTACKING || currentAnimation == KnightState.ATTACKING2) {
            attack();
        }
    }

    public boolean collidesWithBandit() {
        Rectangle knightRect = new Rectangle(x, y, getTexture().getWidth(), getTexture().getHeight());
        Rectangle banditRect = new Rectangle(bandit.getX(), bandit.getY(), bandit.getTexture().getWidth(), bandit.getTexture().getHeight());
        return knightRect.overlaps(banditRect);
    }

    public boolean collidesWithObstacle(Obstacle obstacle) {
        // Vérifiez si les coordonnées du chevalier sont à l'intérieur de l'obstacle
        return x < obstacle.getX() + obstacle.getWidth() && x + getTexture().getWidth() > obstacle.getX() &&
                y < obstacle.getY() + obstacle.getHeight() && y + getTexture().getHeight() > obstacle.getY();
    }
}
