package pl.bugajsky;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by mariuszbugajski on 16.04.2017.
 */
public class Gift extends Rectangle{
    private int type;
    private Pixmap pixmap;
    private Texture texture;
    private Sprite sprite;
    private float time;

    public Gift(int x, int y) {
        //		Rysowanie prezentu
        super(x,y,32,32);
        pixmap = new Pixmap(32, 32, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.PURPLE);
        pixmap.fillRectangle(0,0, 32, 32);
        texture = new Texture(pixmap);
        sprite = new Sprite(texture);
        pixmap.dispose();
        Random r = new Random();
//        type = 3;
        type = r.nextInt(7);
        time = 20;
//        type = 11;
        sprite.setX(x);
        sprite.setY(y);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Pixmap getPixmap() {
        return pixmap;
    }

    public void setPixmap(Pixmap pixmap) {
        this.pixmap = pixmap;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public void animation(){
        getSprite().rotate(-2);
    }

    public void getGift(Player player, LinkedList<Monster> monsters, LinkedList<Shoot> shootsPlayer, LinkedList<Shoot> shootsMonster, Interface myInterface){
        if(getType() == 0){
            player.setHp(player.getHp() + 10);
            myInterface.setInfo("Dodano 10pkt zycia");
        }else if(getType() == 1){
            player.setHp(player.getHp() - 10);
            myInterface.setInfo("Odjeto 10pkt zycia");
        }else if(getType() == 2){
            player.setSpeed(player.getSpeed() + 50);
            myInterface.setInfo("Zwiekszono szybkosc gracza");
        }else if(getType() == 3){
            player.setSpeed(player.getSpeed() - 50);
            myInterface.setInfo("Zmniejszono szybkosc gracza");
        }else if(getType() == 4){
            for(Iterator<Shoot> it = shootsMonster.iterator(); it.hasNext();) {
                Shoot shoot = it.next();
                it.remove();
                myInterface.setInfo("Usunieto wszystkie strzaly wrogow");
            }
        }else if(getType() == 5){
            for (Monster monster : monsters) {
                monster.setSpeed(monster.getSpeed() + 1);
                myInterface.setInfo("Zwiekszono szybkosc wrogow");
            }
        }else if(getType() == 6){
            for (Monster monster : monsters) {
                monster.setSpeed(monster.getSpeed() - 1);
                myInterface.setInfo("Zmniejszono szybkosc wroga");
            }
        }
    }
}
