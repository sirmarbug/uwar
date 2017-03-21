package pl.bugajsky;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by mariuszbugajski on 12.03.2017.
 */
public class Interface extends Actor{
    private String player;
    private String monster;
    private String life;
    BitmapFont playerFont = new BitmapFont();
    BitmapFont monsterFont = new BitmapFont();
    BitmapFont lifeFont = new BitmapFont();

    public Interface(){
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getMonster() {
        return monster;
    }

    public void setMonster(String monster) {
        this.monster = monster;
    }

    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        playerFont.setColor(Color.WHITE);
        playerFont.draw(batch, player, 5, 475);
//        monsterFont.setColor(Color.WHITE);
//        playerFont.draw(batch, monster, 5, 450);
        lifeFont.setColor(Color.WHITE);
        playerFont.draw(batch, life, 580, 475);
    }

}
