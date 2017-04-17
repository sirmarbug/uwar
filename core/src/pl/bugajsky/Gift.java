package pl.bugajsky;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

/**
 * Created by mariuszbugajski on 16.04.2017.
 */
public class Gift extends Rectangle{
    private int type;
    private Pixmap pixmap;
    private Texture texture;
    private Sprite sprite;
    private int animacja;

    public Gift(int x, int y) {
        //		Rysowanie prezentu
        super(x,y,32,32);
        pixmap = new Pixmap(32, 32, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.PURPLE);
        pixmap.fillRectangle(0,0, 32, 32);
        texture = new Texture(pixmap);
        sprite = new Sprite(texture);
        pixmap.dispose();
//        animacja = 0;
        Random r = new Random();
        this.type = r.nextInt();
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

    public float getAnimacja() {
        return animacja;
    }

    public void setAnimacja(int animacja) {
        this.animacja = animacja;
    }

    public void animation(){
        getSprite().rotate(-2);
    }
}
