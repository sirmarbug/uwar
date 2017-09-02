package pl.bugajsky;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by mariuszbugajski on 07.04.2017.
 */
public class End implements Screen {

    final UWar game;
    private String wynik;
    private OrthographicCamera camera;
    private Stage stage;
    private Skin skin;
    private Texture texture;
    private Label label;
    private Image image;
    private Drawable drawable;
    private ImageButton againButton;
    private ImageButton exitButton;

    public End(final UWar game, final Player player) {
        this.game = game;
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("uiFile/uiskin.json"));

        texture = new Texture("ui/UWar.png");
        image = new Image(texture);
        image.setHeight(25f);
        image.setWidth(100f);
        image.setPosition(Gdx.graphics.getWidth() / 2 - image.getWidth() / 2, Gdx.graphics.getHeight() * 0.8f);

        label = new Label(player.getScore() + "", skin);
        label.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

        texture = new Texture("ui/again.png");
        drawable = new TextureRegionDrawable(new TextureRegion(texture));
        againButton = new ImageButton(drawable);
        againButton.setWidth(100f);
        againButton.setHeight(25f);
        againButton.setPosition(Gdx.graphics.getWidth() * 0.2f, Gdx.graphics.getHeight() * 0.3f);

        texture = new Texture("ui/exit.png");
        drawable = new TextureRegionDrawable(new TextureRegion(texture));
        exitButton = new ImageButton(drawable);
        exitButton.setWidth(100f);
        exitButton.setHeight(25f);
        exitButton.setPosition(Gdx.graphics.getWidth() * 0.65f, Gdx.graphics.getHeight() * 0.3f);

        againButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                dispose();
                game.setScreen(new Game(game));
            }
        });

        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                dispose();
                Gdx.app.exit();
            }
        });

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage.addActor(label);
        stage.addActor(image);
        stage.addActor(againButton);
        stage.addActor(exitButton);
        Gdx.input.setInputProcessor(stage);

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        stage.draw();
//        game.font.draw(game.batch, "Koniec", 360, 500);
//        game.font.draw(game.batch, wynik, 360, 450);
        game.batch.end();

//        if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
//            game.setScreen(new Game(game));
//            dispose();
//        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            dispose();
            Gdx.app.exit();
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
