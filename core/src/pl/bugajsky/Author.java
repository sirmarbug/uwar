package pl.bugajsky;

import com.badlogic.gdx.Gdx;
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
 * Created by mariuszbugajski on 02.09.2017.
 */
public class Author implements Screen{
    final UWar game;

    private String wynik;
    private OrthographicCamera camera;
    private Stage stage;
    private Skin skin;
    private Texture texture;
    private Label author;
    private Label me;
//    private Image image;
    private Drawable drawable;
    private ImageButton backButton;
//    private ImageButton exitButton;

    public Author(final UWar game) {
        this.game = game;
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("uiFile/uiskin.json"));

        author = new Label("Author", skin);
        author.setPosition(Gdx.graphics.getWidth() / 2 - author.getWidth() / 2, Gdx.graphics.getHeight() * 0.7f);

        me = new Label("Mariusz Bugajski", skin);
        me.setPosition(Gdx.graphics.getWidth() / 2 - me.getWidth() / 2, Gdx.graphics.getHeight() * 0.5f);

        texture = new Texture("ui/exit.png");
        drawable = new TextureRegionDrawable(new TextureRegion(texture));
        backButton = new ImageButton(drawable);
        backButton.setWidth(100f);
        backButton.setHeight(25f);
        backButton.setPosition(Gdx.graphics.getWidth() / 2 - backButton.getWidth() / 2, Gdx.graphics.getHeight() * 0.3f);

        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                dispose();
                game.setScreen(new Menu(game));
            }
        });

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage.addActor(author);
        stage.addActor(me);
        stage.addActor(backButton);
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
