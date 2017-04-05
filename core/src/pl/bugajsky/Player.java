package pl.bugajsky;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
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

    public Player(float x, float y){
        super(x,y,10);
        hp = 90;
        score = 0;
        speed = 170;
        direction = 0;
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

    public double getSpeed() {
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

    public void goMove(Vector2 nowaPozycja, int direction, float dt){
        wektor.set(nowaPozycja).sub(pozycja).nor();
        szybkosc.set(wektor).scl(speed);
        ruch.set(szybkosc).scl(dt);
        if(pozycja.dst2(nowaPozycja) > ruch.len2()){
            pozycja.add(ruch);
//            System.out.println(pozycja);
        }else{
            pozycja.set(nowaPozycja);
        }
//        this.direction = direction;
        setX(pozycja.x);
        setY(pozycja.y);
    }

    public void goMoveToLeft(float dt){
        nowaPozycja.set(pozycja.x - 100, pozycja.y);
        goMove(nowaPozycja, 0,dt);
    }

    public void goMoveToRight(float dt){
        nowaPozycja.set(pozycja.x + 100, pozycja.y);
        goMove(nowaPozycja, 2, dt);
    }

    public void goMoveToTop(float dt){
        nowaPozycja.set(pozycja.x, pozycja.y + 100);
        goMove(nowaPozycja, 1, dt);
    }

    public void goMoveToBottom(float dt){
        nowaPozycja.set(pozycja.x, pozycja.y - 100);
        goMove(nowaPozycja, 3, dt);
    }
}
