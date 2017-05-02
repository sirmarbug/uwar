package pl.bugajsky;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

/**
 * Created by mariuszbugajski on 02.03.2017.
 */

public class Monster extends Rectangle{

    private int hp;
    private int score;
    private double speed;
    private Texture texture;
    private Pixmap pixmap;
    private int moveDirection; //Kierunek
    private int moveQuantity;  //ilosc
    private Random r;
    private boolean boss;

    public Monster(int x, int y){
        super(x, y,20,20);
        r = new Random();
        hp = 5;
        speed = 5;
        score = 1;
        boss = false;
        moveDirection = r.nextInt(4);
        moveQuantity = r.nextInt(5)+1;
        pixmap = new Pixmap(30, 30, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLUE);
        pixmap.fillRectangle(0,0,20,20);
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    public Monster(int x, int y, int hp, int speed, int score){
        super(x, y,20,20);
        r = new Random();
        this.hp = hp;
        this.speed = speed;
        this.score = score;
        boss = true;
        moveDirection = r.nextInt(4);
        moveQuantity = r.nextInt(5)+1;
        pixmap = new Pixmap(50, 50, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLUE);
        pixmap.fillRectangle(0,0,50,50);
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    public Texture getTexture() {
        return texture;
    }

    public int getHp() {
        return hp;
    }

    public double getSpeed() {
        return speed;
    }

    public int getMoveDirection() {
        return moveDirection;
    }

    public int getMoveQuantity() {
        return moveQuantity;
    }

    public Pixmap getPixmap() {
        return pixmap;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMoveDirection(int moveDirection) {
        this.moveDirection = moveDirection;
    }

    public void setMoveQuantity(int moveQuantity) {
        this.moveQuantity = moveQuantity;
    }

    public void setPixmap(Pixmap pixmap) {
        this.pixmap = pixmap;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean isBoss() {
        return boss;
    }

    public void setBoss(boolean boss) {
        this.boss = boss;
    }

    //    ruch monstera

    public void moveToLeft(){
        if(moveDirection == 0){
            x -= speed;
        }
    }

    public void moveToRight(){
        if(moveDirection == 2){
            x += speed;
        }
    }

    public void moveToTop(){
        if(moveDirection == 1){
            y += speed;
        }
    }

    public void moveToBottom(){
        if(moveDirection == 3){
            y -= speed;
        }
    }

//    generowanie nowych ruchów
    public void generateMove(){
        if(moveQuantity == 0){
            moveDirection = r.nextInt(4);
            moveQuantity = r.nextInt(5)+1;
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
