package pl.bugajsky;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UWar extends ApplicationAdapter {
	private SpriteBatch batch;
	private Player player;
	private OrthographicCamera camera;
	private Texture texture;
	private Pixmap pixmap;

	@Override
	public void create () {
		batch = new SpriteBatch();

//		Kamera i jej ustawienia
		camera = new OrthographicCamera(5000,5000);
		camera.zoom = (float) 0.2;

		player = new Player(camera.viewportWidth / 2f, camera.viewportHeight / 2f);

//		Rysowanie prostokąta
		pixmap = new Pixmap(5000, 5000, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.RED);
		pixmap.drawRectangle(0,0, 5000, 5000);
		texture = new Texture(pixmap);
		pixmap.dispose();
	}

	@Override
	public void render () {

		update();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

//		rysowanie obszaru ruchu
		batch.draw(texture,0,0);

//		rysowanie postaci gracza
		batch.draw(player.getTexture(),player.x, player.y);
		batch.end();
	}

	private void update() {

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		camera.position.set(player.x, player.y, 0);

		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
		}

		if(Gdx.input.isKeyPressed(Input.Keys.W) && player.y < 5000-2*player.radius){
			player.y += 15;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.S) && player.y > 0){
			player.y -= 15;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.A) && player.x > 0){
			player.x -= 15;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.D) && player.x < 5000-2*player.radius) {
			player.x += 15;
		}


//		Zoom+
//		if(Gdx.input.isKeyPressed(Input.Keys.X)){
//			System.out.println(camera.zoom);
//			camera.zoom -=0.2;
//		}

	}

	@Override
	public void dispose () {
		batch.dispose();
		player.getTexture().dispose();
	}
}
