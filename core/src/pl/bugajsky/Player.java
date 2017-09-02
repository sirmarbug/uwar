package pl.bugajsky;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by mariuszbugajski on 25.02.2017.
 */

public class Player extends Circle{

    private int hp;
    private float speed;
    private Vector2 pozycja;
    private Vector2 nowaPozycja;
    private Vector2 wektor;
    private Vector2 szybkosc;
    private Vector2 ruch;
    private int score;
    private int direction;
    private Texture texture;
    private Pixmap pixmap;
    private float giftTime;
    private int runTime;
    private int giftType;
    private float speedRun;
    private float moveTime;
    private int step;
    private Sprite sprite;

    public Player(float x, float y){
        super(x,y,10);
        hp = 1;
//        hp = 50;
        score = 0;
        speed = 250;
        speedRun = 250 + 50;
        direction = 0;
        giftTime = 1;
        runTime = 5;
        giftType = -1;
        pozycja = new Vector2(x, y);
        nowaPozycja = new Vector2();
        wektor = new Vector2();
        szybkosc = new Vector2();
        ruch = new Vector2();
        pixmap = new Pixmap(20, 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.YELLOW);
        pixmap.fillCircle(10, 10, 10);
        texture = new Texture(pixmap);
        pixmap.dispose();
        moveTime = 0;
        step = 1;
        sprite = new Sprite();
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Pixmap getPixmap() {
        return pixmap;
    }

    public void setPixmap(Pixmap pixmap) {
        this.pixmap = pixmap;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Vector2 getPozycja() {
        return pozycja;
    }

    public void setPozycja(Vector2 pozycja) {
        this.pozycja = pozycja;
    }

    public Vector2 getNowaPozycja() {
        return nowaPozycja;
    }

    public void setNowaPozycja(Vector2 nowaPozycja) {
        this.nowaPozycja = nowaPozycja;
    }

    public Vector2 getSzybkosc() {
        return szybkosc;
    }

    public void setSzybkosc(Vector2 szybkosc) {
        this.szybkosc = szybkosc;
    }

    public Vector2 getRuch() {
        return ruch;
    }

    public void setRuch(Vector2 ruch) {
        this.ruch = ruch;
    }

    public float getGiftTime() {
        return giftTime;
    }

    public void setGiftTime(float giftTime) {
        this.giftTime = giftTime;
    }

    public int getGiftType() {
        return giftType;
    }

    public void setGiftType(int giftType) {
        this.giftType = giftType;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public float getSpeedRun() {
        return speedRun;
    }

    public void setSpeedRun(float speedRun) {
        this.speedRun = speedRun;
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

    public void goMove(Vector2 nowaPozycja, int direction, float dt){
        wektor.set(nowaPozycja).sub(pozycja).nor();
        szybkosc.set(wektor).scl(speed);
        ruch.set(szybkosc).scl(dt);
        if(pozycja.dst2(nowaPozycja) > ruch.len2()){
            pozycja.add(ruch);
        }else{
            pozycja.set(nowaPozycja);
        }
        setX(pozycja.x);
        setY(pozycja.y);
    }

    public void runMove(Vector2 nowaPozycja, int direction, float dt){
        wektor.set(nowaPozycja).sub(pozycja).nor();
        szybkosc.set(wektor).scl(speedRun);
        ruch.set(szybkosc).scl(dt);
        if(pozycja.dst2(nowaPozycja) > ruch.len2()){
            pozycja.add(ruch);
        }else{
            pozycja.set(nowaPozycja);
        }
        setX(pozycja.x);
        setY(pozycja.y);
    }

    public void goMoveToLeft(float dt){
        nowaPozycja.set(pozycja.x - 100, pozycja.y);
        goMove(nowaPozycja, 0,dt);
        setDirection(0);
    }

    public void goMoveToRight(float dt){
        nowaPozycja.set(pozycja.x + 100, pozycja.y);
        goMove(nowaPozycja, 2, dt);
        setDirection(2);
    }

    public void goMoveToTop(float dt){
        nowaPozycja.set(pozycja.x, pozycja.y + 100);
        goMove(nowaPozycja, 1, dt);
        setDirection(1);
    }

    public void goMoveToBottom(float dt){
        nowaPozycja.set(pozycja.x, pozycja.y - 100);
        goMove(nowaPozycja, 3, dt);
        setDirection(3);
    }

    public void goMoveToTopRight(float dt){
        nowaPozycja.set(pozycja.x - 100, pozycja.y - 100);
        goMove(nowaPozycja, 3, dt);
        setDirection(3);
    }

    public void runMoveToLeft(float dt){
        nowaPozycja.set(pozycja.x - 100, pozycja.y);
        runMove(nowaPozycja, 0,dt);
    }

    public void runMoveToRight(float dt){
        nowaPozycja.set(pozycja.x + 100, pozycja.y);
        runMove(nowaPozycja, 2, dt);
    }

    public void runMoveToTop(float dt){
        nowaPozycja.set(pozycja.x, pozycja.y + 100);
        runMove(nowaPozycja, 1, dt);
    }

    public void runMoveToBottom(float dt){
        nowaPozycja.set(pozycja.x, pozycja.y - 100);
        runMove(nowaPozycja, 3, dt);
    }

    public void draw (SpriteBatch batch, TextureAtlas region, float angle) {
        sprite.set(region.createSprite("" + getStep()));
        sprite.setPosition(getPozycja().x, getPozycja().y);
        sprite.rotate(angle);
        sprite.draw(batch);
    }
}
