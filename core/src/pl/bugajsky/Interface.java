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
    private BitmapFont playerFont;
    private BitmapFont scoreFont;
    private BitmapFont lifeFont;
    private BitmapFont bazaFont;

    public Interface(){
        playerFont = new BitmapFont();
        scoreFont = new BitmapFont();
        lifeFont = new BitmapFont();
        bazaFont = new BitmapFont();
        playerFont.setColor(Color.WHITE);
        scoreFont.setColor(Color.WHITE);
        lifeFont.setColor(Color.WHITE);
        bazaFont.setColor(Color.WHITE);
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
        playerFont.draw(batch, player, 5, 475);
        scoreFont.draw(batch, score, 300, 475);
        lifeFont.draw(batch, life, 570, 475);
        bazaFont.draw(batch, baza, 570, 450);
    }

}
