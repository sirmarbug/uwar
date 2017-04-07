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
    private String score;
    private String life;
    private String baza;
    BitmapFont playerFont = new BitmapFont();
    BitmapFont scoreFont = new BitmapFont();
    BitmapFont lifeFont = new BitmapFont();
    BitmapFont bazaFont = new BitmapFont();

    public Interface(){
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String monster) {
        this.score = monster;
    }

    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }

    public String getBaza() {
        return baza;
    }

    public void setBaza(String baza) {
        this.baza = baza;
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        playerFont.setColor(Color.WHITE);
        playerFont.draw(batch, player, 5, 475);
        scoreFont.setColor(Color.WHITE);
        scoreFont.draw(batch, score, 300, 475);
        lifeFont.setColor(Color.WHITE);
        lifeFont.draw(batch, life, 570, 475);
        bazaFont.setColor(Color.WHITE);
        bazaFont.draw(batch, baza, 570, 450);
    }

}
