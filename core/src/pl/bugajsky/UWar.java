package pl.bugajsky;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Random;

public class UWar extends Game{
	private SpriteBatch batch;
	private Player player;
	private Monster monster;
	private OrthographicCamera camera;
	private Texture texture;
	private Pixmap pixmap;
	private Random r;
//	Zmienna wyświetlająca obecną pozycję
 	private BitmapFont font;
 	private Stage stage;
 	private Interface myinterface;

 	class Staty extends Actor{
		BitmapFont font = new BitmapFont();
		Texture texture = new Texture(Gdx.files.internal("player.png"));

		@Override
		public void draw(Batch batch, float parentAlpha){
			batch.draw(texture, 0,0);
			font.setColor(Color.WHITE);
			font.draw(batch, "Tekst", 100, 100);
		}
	}


	@Override
	public void create () {
 		stage = new Stage(new ScreenViewport());

		batch = new SpriteBatch();

//		Random
		r = new Random();

//		Kamera i jej ustawienia
		camera = new OrthographicCamera(5000,5000);
		camera.zoom = (float) 0.2;

//		Utworzenie gracza
		player = new Player(camera.viewportWidth / 2f, camera.viewportHeight / 2f);

		myinterface = new Interface();
		stage.addActor(myinterface);
		Gdx.input.setInputProcessor(stage);

//		Utworzenie potwora
		monster = new Monster(r.nextInt(5000),r.nextInt(5000));
//		monster = new Monster(2400,2400);

//		Rysowanie prostokąta
		pixmap = new Pixmap(5000, 5000, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.RED);
		pixmap.drawRectangle(0,0, 5000, 5000);
		texture = new Texture(pixmap);
		pixmap.dispose();

//		napis
		font = new BitmapFont();
		font.setColor(Color.WHITE);
	}

	@Override
	public void render () {

		super.render();

		update();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		batch.begin();

//		rysowanie obszaru ruchu
		batch.draw(texture,0,0);

//		rysowanie postaci gracza
		batch.draw(player.getTexture(),player.x, player.y);

//		rysowanie potwora
		batch.draw(monster.getTexture(), monster.x + monster.width/2, monster.y + monster.height/2);

//		wypisanie współrzędnych gracza
//		font.draw(batch, "x: " + player.x + "y: " + player.y, camera.position.x - 490, camera.position.y + 490);

//		wypsiywanie współrzędnych potwora
//		font.draw(batch, "x: " + monster.x + "y: " + monster.y, camera.position.x - 490, camera.position.y + 470);

		batch.end();
	}

	private void update() {

		camera.update();
		batch.setProjectionMatrix(camera.combined);

//		ustawienie współrzędnych playera
		myinterface.setPlayer("x: " + player.x + " y: " + player.y);

//		ustawienie współrzędnych monstera
		myinterface.setMonster("x: " + monster.x + " y: " + monster.y);

//		ustawienie życia bohatera
		myinterface.setLife("Life: " + player.getHp());

//		ustawienie kamery tak aby mapa była maksymalnie do krańców ekranu
//		ustawienie kamery z lewej strony i prawej strony
//		operacje dla środka ekranu
		if(player.x > 500 && player.x < 4500) {
			if(player.y > 500 && player.y < 4500) {
				camera.position.set(player.x, player.y, 0);
			}else{
				if(player.y < 500){
					camera.position.set(player.x, 500, 0);
				}else{
					camera.position.set(player.x, 4500, 0);
				}
			}
		}

//		operacje dla krańców ekranu
		if(player.x < 500 || player.x > 4500) {
			if(player.y < 500 || player.y > 4500){
				if(player.x < 500 && player.y < 500){
					camera.position.set(500, 500, 0);
//				}else if(player.x < 500 && player.y > 4500){
//					camera.position.set(500, 5000, 0);
				}
			}else {
				if (player.x < 500) {
					camera.position.set(500, player.y, 0);
				} else {
					camera.position.set(4500, player.y, 0);
				}
			}
		}

//		ruch potwora
		if(monster.getMoveQuantity() > 0){
			monster.moveToBottom();
			monster.moveToLeft();
			monster.moveToRight();
			monster.moveToTop();
			monster.setMoveQuantity(monster.getMoveQuantity()-1);
		}else{
			monster.generateMove();
			monster.moveToBottom();
			monster.moveToLeft();
			monster.moveToRight();
			monster.moveToTop();
			monster.setMoveQuantity(monster.getMoveQuantity()-1);
		}

//		sterowanie
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
		}

		if(Gdx.input.isKeyPressed(Input.Keys.W) && player.y < 5000-2*player.radius){
			player.y += 5;
//			System.out.println(player.y);
		}

		if(Gdx.input.isKeyPressed(Input.Keys.S) && player.y > 0){
			player.y -= 5;
//			System.out.println(player.y);
		}

		if(Gdx.input.isKeyPressed(Input.Keys.A) && player.x > 0){
			player.x -= 5;
//			System.out.println(player.x);
		}

		if(Gdx.input.isKeyPressed(Input.Keys.D) && player.x < 5000-2*player.radius) {
			player.x += 5;
//			System.out.println(player.x);
		}

		if(Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
			player.x += 20;
		}


//		Zoom+
		if(Gdx.input.isKeyPressed(Input.Keys.X)){
			camera.zoom +=0.2;
		}

//		Zoom-
		if(Gdx.input.isKeyPressed(Input.Keys.C)){
			camera.zoom -=0.2;
		}

	}

	@Override
	public void dispose () {
		batch.dispose();
		player.getTexture().dispose();
		font.dispose();
		monster.getTexture().dispose();
		stage.dispose();
	}
}
