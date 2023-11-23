package com.stg13.steelwarriors.characters;
import com.badlogic.gdx.graphics.Texture;

public class Archer {
    private float x;
    private float y;
    private int health;
    private Texture knight;

    public Archer(int x, int y, int health) {
        this.x = x;
        this.y = y;
        this.health = health;
        knight = new Texture("badlogic.jpg");
    }

    public void attack() {
        System.out.println("Archer attacks with damage: ");
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            System.out.println("Archer has been defeated!");
        } else {
            System.out.println("Archer took damage. Remaining health: " + health);
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void move(float deltaX, float deltaY) {
        float prevX = getX();
        float prevY = getY();
        float newX = getX() + deltaX;
        float newY = getY() + deltaY;
        setX(newX);
        setY(newY);
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

    public Texture getTexture() {
        return knight;
    }
}