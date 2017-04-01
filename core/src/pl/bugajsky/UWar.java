package pl.bugajsky;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.*;

public class UWar extends Game{
	private SpriteBatch batch;
	private Player player;
	private OrthographicCamera camera;
	private Texture texture;
	private Texture home;
	private float timeHome;
	private float timeShoot;
	private float timerMonster;
	private Pixmap pixmap;
	private Random r;
//	lista strzałów
	private LinkedList<Shoot> strzaly; //lista strzałów
	private LinkedList<Monster> potwory; //lista potworów
//	Zmienna wyświetlająca obecną pozycję
 	private BitmapFont font;
 	private Stage stage;
 	private Interface myinterface;

	@Override
	public void create () {
 		stage = new Stage(new ScreenViewport());

		batch = new SpriteBatch();

//		Inicjalizacja strzałów
		strzaly = new LinkedList<Shoot>();

//		Inicjalizacja potworów
		potwory = new LinkedList<Monster>();

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

//		Rysowanie prostokąta
		pixmap = new Pixmap(5000, 5000, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.RED);
		pixmap.drawRectangle(0,0, 5000, 5000);
		texture = new Texture(pixmap);
		pixmap.dispose();

//		Utworzenie wyglądu bazy
		pixmap = new Pixmap(200,200, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.GREEN);
		pixmap.fillRectangle(0,0,200,200);
		home = new Texture(pixmap);
		pixmap.dispose();

//		TimerHome
		timeHome = 0;

//		TimerShoot
		timeShoot = 1;

//		napis
		font = new BitmapFont();
		font.setColor(Color.WHITE);
	}

	@Override
	public void render () {

		super.render();

		update(Gdx.graphics.getDeltaTime());

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

		batch.begin();

//		rysowanie obszaru ruchu
		batch.draw(texture,0,0);

//		obszar bazy
		batch.draw(home,2400,2400, home.getWidth(),home.getHeight());

//		rysowanie postaci gracza
		batch.draw(player.getTexture(),player.getPozycja().x, player.getPozycja().y);

//		Rysowanie strzałów
		for (Shoot s : strzaly) {
			batch.draw(s.getTexture(), s.x, s.y);
		}

//		Rysowanie potworów z listy
		for (Monster m : potwory) {
			batch.draw(m.getTexture(), m.x, m.y);
		}

//		Wartość zooma
//		font.draw(batch,camera.zoom + "",2500,2700);

		batch.end();
	}

	private void update(float dt) {

		camera.update();
		batch.setProjectionMatrix(camera.combined);

//		ustawienie współrzędnych playera
		myinterface.setPlayer("x: " + player.x + " y: " + player.y);

//		ustawienie życia bohatera
		myinterface.setLife("Life: " + player.getHp());

//		ustawienie obecnego wyniku
		myinterface.setScore(player.getScore() + "");

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

//		dodawanie życia w bazie
		if(player.x > 2400 && player.x < 2600){
			if(player.y > 2400 && player.y < 2600){
				if(player.getHp() < 100){
					timeHome += Gdx.graphics.getDeltaTime();
					if(timeHome > 1){
						player.setHp(player.getHp() + 1);
						timeHome = 0;
					}
				}
			}else{
				timeHome = 0;
			}
		}else{
			timeHome = 0;
		}

//		STRZAŁY
//		dodanie strzałów
		timeShoot += Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
			if(timeShoot > 0.2){
				strzaly.add(new Shoot(player.x + player.getTexture().getWidth() / 2 - 5, player.y + player.getTexture().getHeight() / 2 - 5, 1, player.getDirection()));
				timeShoot = 0;
			}
		}

		for (Shoot s : strzaly) {
			if(s.getDirection() == 0){
				s.x -= 300 * Gdx.graphics.getDeltaTime();
			}
			if(s.getDirection() == 1){
				s.y += 300 * Gdx.graphics.getDeltaTime();
			}
			if(s.getDirection() == 2){
				s.x += 300 * Gdx.graphics.getDeltaTime();
			}
			if(s.getDirection() == 3){
				s.y -= 300 * Gdx.graphics.getDeltaTime();
			}
		}

//		ograniczenie pola strzałów do mapy gry
		for(Iterator<Shoot> it = strzaly.iterator(); it.hasNext();) {
			Shoot shoot = it.next();
			if (shoot.y > 5000 || shoot.y < 0 || shoot.x > 5000 || shoot.x < 0) {
				it.remove();
			}
		}

//		POTWORY
		timerMonster += Gdx.graphics.getDeltaTime();
			if(timerMonster > 1){
				potwory.add(new Monster(r.nextInt(5000),r.nextInt(5000)));
				timerMonster = 0;
			}

		for (Monster m : potwory) {
			if (m.getMoveQuantity() > 0) {
				m.moveToBottom();
				m.moveToLeft();
				m.moveToRight();
				m.moveToTop();
				m.setMoveQuantity(m.getMoveQuantity() - 1);
			} else {
				m.generateMove();
				m.moveToBottom();
				m.moveToLeft();
				m.moveToRight();
				m.moveToTop();
				m.setMoveQuantity(m.getMoveQuantity() - 1);
			}
		}

//		KOLIZJE
//		Strzał - potwór
		for(Iterator<Shoot> it = strzaly.iterator(); it.hasNext();) {
			Shoot shoot = it.next();
			for(Iterator<Monster> pot = potwory.iterator(); pot.hasNext();) {
				Monster p = pot.next();
				if (shoot.overlaps(p)) {
					p.setHp(p.getHp() - shoot.getStrong());
//					System.out.println("Życie: " + p.getHp());
					if(p.getHp() < 1){
//						System.out.println("weszło");
						player.setScore(player.getScore() + p.getScore());
						System.out.println();
						pot.remove();
					}
					it.remove();
				}
			}
		}

//		sterowanie
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
		}

		if(Gdx.input.isKeyPressed(Input.Keys.W) && player.y < 5000-2*player.radius){
			player.goMoveToTop(Gdx.graphics.getDeltaTime());
//			player.y += 250 * Gdx.graphics.getDeltaTime();
//			player.setDirection(1);
		}

		if(Gdx.input.isKeyPressed(Input.Keys.S) && player.y > 0){
			player.goMoveToBottom(Gdx.graphics.getDeltaTime());
//			player.y -= 250 * Gdx.graphics.getDeltaTime();
//			player.setDirection(3);
		}

		if(Gdx.input.isKeyPressed(Input.Keys.A) && player.x > 0){
			player.goMoveToLeft(Gdx.graphics.getDeltaTime());
//			player.x -= 250 * Gdx.graphics.getDeltaTime();
//			player.setDirection(0);
		}

		if(Gdx.input.isKeyPressed(Input.Keys.D) && player.x < 5000-2*player.radius) {
			player.goMoveToRight(Gdx.graphics.getDeltaTime());
//			player.x += 250 * Gdx.graphics.getDeltaTime();
//			player.setDirection(2);
		}

		if(Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
			player.x += 20;
		}

//		Zoom-
		if(Gdx.input.isKeyPressed(Input.Keys.X)){
			if(camera.zoom >= 0 && camera.zoom <= 0.95){
				camera.zoom += 0.01;
//				System.out.println(camera.zoom);
			}
		}

//		Zoom+
		if(Gdx.input.isKeyPressed(Input.Keys.C)){
			if(camera.zoom >= 0.1 && camera.zoom <= 1){
				camera.zoom -= 0.01;
//				System.out.println(camera.zoom);
			}
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
		player.getTexture().dispose();
		font.dispose();
		texture.dispose();
		home.dispose();
		stage.dispose();
	}
}
