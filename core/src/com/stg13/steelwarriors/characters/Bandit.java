package com.stg13.steelwarriors.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.stg13.steelwarriors.SteelWarriorGame;
import com.stg13.steelwarriors.ressources.Assets;

public class Bandit {
    private static final float HAUTEUR_DU_SOL = 85;
    private SteelWarriorGame game;
    private float x;
    private float y;
    private int health;
    private Texture banditTexture;
    private Array<Texture> animationFrames;
    private int currentFrameIndex;
    private Array<Texture> readyFrames, jumpFrames, hitFrames, attackFrames, runFrames, runbackFrames, hurtFrames, deadFrames, walkFrames;
    private int frameDelay = 15;
    private int frameCounter = 0;
    private Knight knight;
    float stateTime = 100;
    private boolean isAttacking = false;

    public Bandit(SteelWarriorGame game, float x, float y, int health, Texture texture) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.health = health;
        this.banditTexture = texture;
        animationFrames = new Array<>();
        currentFrameIndex = 0;
        getBanditImage();
        currentAnimation = BanditState.READY;
        animationFrames = readyFrames;
    }

    public void setKnight(Knight knight) {
        this.knight = knight;
    }

    private void getBanditImage() {
        walkFrames = loadTextures(Assets.BanditAnimation.WALK);
        readyFrames = loadTextures(Assets.BanditAnimation.READY);
        jumpFrames = loadTextures(Assets.BanditAnimation.JUMP);
        hitFrames = loadTextures(Assets.BanditAnimation.HIT);
        attackFrames = loadTextures(Assets.BanditAnimation.ATTACK);
        runFrames = loadTextures(Assets.BanditAnimation.RUN);
        runbackFrames = loadTextures(Assets.BanditAnimation.RUNBACK);
        hurtFrames = loadTextures(Assets.BanditAnimation.HURT);
        deadFrames = loadTextures(Assets.BanditAnimation.DEAD);
    }

    private Array<Texture> loadTextures(Assets.BanditAnimation animation) {
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
        if (banditTexture == null) {
            banditTexture = new Texture("Bandit/ready_1.png");
        }
        return banditTexture;
    }

    public enum BanditState {
        READY,
        JUMPING,
        HITTING,
        ATTACKING,
        HURT,
        DEAD,
        RUNNING,
        WALKING,
        RUNNINGBACK
    }

    private BanditState currentAnimation;

    public void nextFrame(BanditState newAnimation) {
        if (newAnimation != currentAnimation) {
            currentAnimation = newAnimation;
            switch (currentAnimation) {
                case READY:
                    animationFrames = readyFrames;
                    break;
                case JUMPING:
                    animationFrames = jumpFrames;
                    break;
                case HITTING:
                    animationFrames = hitFrames;
                    break;
                case ATTACKING:
                    animationFrames = attackFrames;
                    break;
                case HURT:
                    animationFrames = hurtFrames;
                    break;
                case DEAD:
                    animationFrames = deadFrames;
                    break;
                case RUNNING:
                    animationFrames = runFrames;
                    break;
                case RUNNINGBACK:
                    animationFrames = runbackFrames;
                    break;
                case WALKING:
                    animationFrames = walkFrames;
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

    public BanditState getAnimationFromAI() {
        if (health <= 0) {
            return BanditState.DEAD;
        }

        if (isPlayerClose()) {
            if (health < 50) {
                return BanditState.HURT;
            } else {
                isAttacking = true;
                return BanditState.ATTACKING;
            }
        } else if (isPlayerAtMediumRange()) {
            return BanditState.HITTING;
        } else {
            isAttacking = false;
            if (goingRight) {
                return BanditState.RUNNING;
            } else {
                return BanditState.RUNNINGBACK;
            }
        }
    }

    private boolean isPlayerAtMediumRange() {
        float distance = (float) Math.sqrt(Math.pow(knight.getX() - x, 2) + Math.pow(knight.getY() - y, 2));
        return distance >= 50 && distance < 100;
    }

    public void attack() {
        if (isPlayerClose()) {
            knight.takeDamage(17);
            System.out.println("Bandit attacks Knight");
        }
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            System.out.println("Bandit has been defeated!");
        } else {
            System.out.println("Bandit took damage. Remaining health: " + health);
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean collidesWithObstacle(Obstacle obstacle) {
        return x < obstacle.getX() + obstacle.getWidth() && x + getTexture().getWidth() > obstacle.getX() &&
                y < obstacle.getY() + obstacle.getHeight() && y + getTexture().getHeight() > obstacle.getY();
    }

    private boolean isPlayerClose() {
        float distance = (float) Math.sqrt(Math.pow(knight.getX() - x, 2) + Math.pow(knight.getY() - y, 2));
        return distance < 100000;
    }

    public void move(float deltaX, float deltaY, Obstacle obstacle) {
        float prevX = getX();
        float prevY = getY();

        setX(getX() + deltaX);
        if (collidesWithObstacle(obstacle)) {
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
        if (collidesWithObstacle(obstacle)) {
            setY(prevY);
        }
    }

    private float jumpVelocity = 100;
    private float gravity = -10;
    private boolean isJumping = false;

    public void jump() {
        if (!isJumping) {
            isJumping = true;
            y += jumpVelocity;
        }
    }

    public void updateJump(float deltaTime) {
        if (isJumping) {
            jumpVelocity += gravity * deltaTime;
            y += jumpVelocity * deltaTime;

            if (y <= HAUTEUR_DU_SOL) {
                y = HAUTEUR_DU_SOL;
                isJumping = false;
                jumpVelocity = 100;
            }
        }
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    private float maxDistance = 200;
    private float distanceTraveled = 100;
    private float speed = 0.5f;
    private boolean goingRight = true;

    public void update(Obstacle obstacle, Obstacle table) {
        if (currentAnimation == BanditState.DEAD) {
            return;
        }

        float deltaX;
            deltaX = (knight.getX() - x) > 0 ? speed : -speed;

            // Ajoutez cette partie pour que le Bandit suive directement le chevalier
            if (Math.abs(knight.getX() - x) > speed) {
                if (knight.getX() > x) {
                    deltaX = speed;
                } else {
                    deltaX = -speed;
                }
            }

        BanditState newState = getAnimationFromAI();
        nextFrame(newState);

        if (newState != BanditState.DEAD) {
            move(deltaX, 0, obstacle);

            updateJump(Gdx.graphics.getDeltaTime());

            if (!isJumping && !collidesWithObstacle(table)) {
                y -= speed;
                if (y <= HAUTEUR_DU_SOL) {
                    y = HAUTEUR_DU_SOL;
                }
            }

            if (obstacle == table && collidesWithObstacle(obstacle)) {
                setY(table.getY() + table.getHeight());
            } else {
                move(deltaX, 0, obstacle);
            }
        }
    }


    public float getWidth() {
        return getTexture().getWidth();
    }

    public float getHeight() {
        return getTexture().getHeight();
    }
}
