package pl.bugajsky;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
    private float moveTime;
    private int step;
    private Sprite sprite;

    public Monster(int x, int y, int lvl){
        super(x, y,20,20);
        r = new Random();
        hp = r.nextInt(lvl) + 1;
        speed = r.nextInt(lvl*5) + 1;
        score = r.nextInt(lvl) + 1;
        boss = false;
        moveDirection = r.nextInt(4);
        moveQuantity = r.nextInt(5)+1;
        pixmap = new Pixmap(30, 30, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLUE);
        pixmap.fillRectangle(0,0,20,20);
        texture = new Texture(pixmap);
        pixmap.dispose();
        moveTime = 0;
        step = 1;
        sprite = new Sprite();
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
        moveTime = 0;
        step = 1;
        sprite = new Sprite();
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
            if(x > 0)
                x -= speed;
        }
    }

    public void moveToRight(){
        if(moveDirection == 2){
            if(x < 5000)
                x += speed;
        }
    }

    public void moveToTop(){
        if(moveDirection == 1){
            if(y < 5000)
                y += speed;
        }
    }

    public void moveToBottom(){
        if(moveDirection == 3){
            if(y > 0)
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

//    generowanie nowych ruchów na bazie położenia bohatera
    public void generateMove(Player player){
        if(moveQuantity == 0){
            boolean kierunek = r.nextBoolean();
            if(kierunek == false){
                if(x > player.getPozycja().x){
                    moveDirection = 0;
                }else{
                    moveDirection = 2;
                }
            }else{
                if(y > player.getPozycja().y){
                    moveDirection = 3;
                }else{
                    moveDirection = 1;
                }
            }
            moveQuantity = r.nextInt(5)+1;
        }
    }

//    Generowanie kierunku strzału na podstawie położenia gracza
public int generateDirectionShoot(Player player){
        boolean kierunek = r.nextBoolean();
        if(kierunek == false){
            if(x > player.getPozycja().x){
                return 0;
            }else{
                return 2;
            }
        }else{
            if(y > player.getPozycja().y){
                return 3;
            }else{
                return 1;
            }
        }
}

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public float getMoveTime() {
        return moveTime;
    }

    public void setMoveTime(float moveTime) {
        this.moveTime = moveTime;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public void stepAnimation(float time){
        setMoveTime(getMoveTime() + time);
        if(getMoveTime() > 0.15) {
            setStep(getStep() + 1);
            setMoveTime(0);

            if(getStep() > 2)
                setStep(0);
        }
    }

    public void draw (SpriteBatch batch, TextureAtlas region, float angle) {
        sprite.set(region.createSprite("" + getStep()));
        sprite.setPosition(x, y);
        sprite.rotate(angle);
        sprite.draw(batch);
    }
}
