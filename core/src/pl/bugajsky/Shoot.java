package pl.bugajsky;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by mariuszbugajski on 20.03.2017.
 */
public class Shoot extends Rectangle {
    private int strong;
    private Texture texture;
    private Pixmap pixmap;

    public Shoot(float x, float y, int strong){
        this.x = x;
        this.y = y;
        this.strong = strong;
        pixmap = new Pixmap(10, 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fillRectangle(0,0,10,20);
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    public int getStrong() {
        return strong;
    }

    public void setStrong(int strong) {
        this.strong = strong;
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
}
