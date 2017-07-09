package pl.bugajsky;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by mariuszbugajski on 26.05.2017.
 */
public class GameInterface extends Actor {
    private Texture texture;

    public GameInterface(){
        texture = new Texture("mapa.png");
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.draw(texture,0,0);
    }
}
