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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by mariuszbugajski on 07.04.2017.
 */
public class Menu implements Screen {

    final UWar game;

    private OrthographicCamera camera;
    private Stage stage;
    private Skin skin;
    private Texture playTexture;
    private ImageButton playButton;
    private ImageButton exitButton;
    private Image image;

    public Menu(final UWar game) {
        this.game = game;

        stage = new Stage();

        skin = new Skin(Gdx.files.internal("uiFile/uiskin.json"));

        playTexture = new Texture("ui/play.png");
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(playTexture));
        playButton = new ImageButton(drawable);
        playButton.setWidth(100f);
        playButton.setHeight(25f);
        playButton.setPosition(Gdx.graphics.getWidth() / 2 - playButton.getWidth() / 2, Gdx.graphics.getHeight() * 0.5f);

        playTexture = new Texture("ui/exit.png");
        drawable = new TextureRegionDrawable(new TextureRegion(playTexture));
        exitButton = new ImageButton(drawable);
        exitButton.setWidth(100f);
        exitButton.setHeight(25f);

        exitButton.setPosition(Gdx.graphics.getWidth() / 2 - exitButton.getWidth() / 2, Gdx.graphics.getHeight() * 0.4f);

        playTexture = new Texture("ui/UWar.png");
        image = new Image(playTexture);
        image.setHeight(25f);
        image.setWidth(100f);
        image.setPosition(Gdx.graphics.getWidth() / 2 - image.getWidth() / 2, Gdx.graphics.getHeight() * 0.8f);

        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                dispose();
                Gdx.app.exit();
            }
        });

        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                dispose();
                game.setScreen(new Game(game));

            }
        });

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage.addActor(playButton);
        stage.addActor(exitButton);
        stage.addActor(image);

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
        game.batch.end();
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
