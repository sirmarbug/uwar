package pl.bugajsky;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
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
    private String info;
    private String gift;
    private BitmapFont levelFont;
    private BitmapFont scoreFont;
    private BitmapFont lifeFont;
    private BitmapFont bazaFont;
    private BitmapFont infoFont;
    private BitmapFont giftFont;
    private Texture statTexture;
    private Texture infoTexture;

    public Interface(){
        statTexture = new Texture("ui/panel.png");
        infoTexture = new Texture("ui/info.png");
        levelFont = new BitmapFont();
        scoreFont = new BitmapFont();
        lifeFont = new BitmapFont();
        bazaFont = new BitmapFont();
        infoFont = new BitmapFont();
        giftFont = new BitmapFont();
//        infoTexture = new Texture("shoot.png");
        levelFont.setColor(Color.WHITE);
        scoreFont.setColor(Color.WHITE);
        lifeFont.setColor(Color.WHITE);
        bazaFont.setColor(Color.WHITE);
        infoFont.setColor(Color.WHITE);
        giftFont.setColor(Color.WHITE);
        info = "";
        gift = "";
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.draw(statTexture, Gdx.graphics.getWidth() * 0.92f - statTexture.getWidth() / 2, Gdx.graphics.getHeight() * 0.89f - statTexture.getHeight() / 2);
        batch.draw(statTexture, Gdx.graphics.getWidth() * 0.08f - statTexture.getWidth() / 2, Gdx.graphics.getHeight() * 0.89f - statTexture.getHeight() / 2);
        levelFont.draw(batch, player, Gdx.graphics.getWidth() * 0.03f - levelFont.getSpaceWidth() / 2, Gdx.graphics.getHeight() * 0.98f - levelFont.getLineHeight() / 2);
        scoreFont.draw(batch, score, Gdx.graphics.getWidth() * 0.035f - levelFont.getSpaceWidth() / 2, Gdx.graphics.getHeight() * 0.93f - levelFont.getLineHeight() / 2);
        lifeFont.draw(batch, life, Gdx.graphics.getWidth() * 0.89f - lifeFont.getSpaceWidth() / 2, Gdx.graphics.getHeight() * 0.98f - lifeFont.getLineHeight() / 2);
        bazaFont.draw(batch, baza, Gdx.graphics.getWidth() * 0.88f - bazaFont.getSpaceWidth() / 2, Gdx.graphics.getHeight() * 0.93f - bazaFont.getLineHeight() / 2);
        infoFont.draw(batch, info, Gdx.graphics.getWidth() * 0.3f - infoFont.getSpaceWidth(), Gdx.graphics.getHeight() * 0.08f - infoFont.getLineHeight() / 2);
        giftFont.draw(batch, gift, Gdx.graphics.getWidth() * 0.88f - giftFont.getSpaceWidth() / 2, Gdx.graphics.getHeight() * 0.90f - giftFont.getLineHeight() / 2);
        if (info != "") {
            batch.draw(infoTexture, Gdx.graphics.getWidth() / 2 - infoTexture.getWidth() / 2, Gdx.graphics.getHeight() * 0.05f - infoTexture.getHeight() / 2);
        }
    }

}
