package pl.bugajsky;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by mariuszbugajski on 07.04.2017.
 */
public class Game implements Screen {

    final UWar game;

    private SpriteBatch batch;
    private Player player;
    private Baza baza;
    private OrthographicCamera camera;
    private Texture texture;
//    private Texture giftTexture;
//    private Sprite giftSprite;
    //	private Texture home;
    private float timeHome;
    private float timeShoot;
    private float timerMonster;
    private float timerGift;
    private Pixmap pixmap;
    private Random r;
    //	lista strzałów
    private LinkedList<Shoot> strzaly; //lista strzałów
    private LinkedList<Shoot> strzalyPotworow; // lsita strzałów potworów
    private LinkedList<Monster> potwory; //lista potworów
    private LinkedList<Gift> giftLinkedList; //lista potworów
    //	Zmienna wyświetlająca obecną pozycję
    private BitmapFont font;
    private Stage stage;
    private Interface myinterface;

    public Game(final UWar game) {
        this.game = game;

        stage = new Stage(new ScreenViewport());

        batch = new SpriteBatch();

//		Inicjalizacja strzałów
        strzaly = new LinkedList<Shoot>();

//		Inicjalizacja strzałów
        strzalyPotworow = new LinkedList<Shoot>();

//		Inicjalizacja potworów
        potwory = new LinkedList<Monster>();

//      Inicjalizacja prezentów
        giftLinkedList = new LinkedList<Gift>();

//		Random
        r = new Random();

        baza = new Baza();

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
//		pixmap = new Pixmap(200,200, Pixmap.Format.RGBA8888);
//		pixmap.setColor(Color.GREEN);
//		pixmap.fillRectangle(0,0,200,200);
//		home = new Texture(pixmap);
//		pixmap.dispose();

//		TimerHome
        timeHome = 0;

//		TimerShoot
        timeShoot = 1;

        timerMonster = 1;

        timerGift = 0;

//		napis
        font = new BitmapFont();
        font.setColor(Color.WHITE);

    }


    @Override
    public void render(float delta) {
//        super.render();

        update(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        batch.begin();

//		rysowanie obszaru ruchu
        batch.draw(texture,0,0);

//		obszar bazy
        batch.draw(baza.getTexture(), baza.getX(), baza.getY(),baza.getWidth(), baza.getHeight());
//		batch.draw(home,2400,2400, home.getWidth(),home.getHeight());

//		rysowanie postaci gracza
        batch.draw(player.getTexture(),player.x, player.y);

//		Rysowanie strzałów
        for (Shoot s : strzaly) {
            batch.draw(s.getTexture(), s.x, s.y);
        }

//		Rysowanie strzałów potworów
        for (Shoot s : strzalyPotworow) {
            batch.draw(s.getTexture(), s.x, s.y);
        }

//		Rysowanie potworów z listy
        for (Monster m : potwory) {
            batch.draw(m.getTexture(), m.x, m.y);
        }

//      Rysowanie prezentów
        for (Gift g : giftLinkedList) {
            g.getSprite().draw(batch);
        }

//		Wartość zooma
//		font.draw(batch,camera.zoom + "",2500,2700);

        batch.end();
    }

    private void update(float dt) {

//      warunek na koniec gry
        if(player.getHp() < 1 || baza.getHp() < 1){
            game.setScreen(new End(game, player));
        }

        camera.update();
        batch.setProjectionMatrix(camera.combined);

//		ustawienie współrzędnych playera
        myinterface.setPlayer("x: " + player.x + " y: " + player.y);

//		ustawienie życia bohatera
        myinterface.setLife("Life: " + player.getHp());

//		ustawienie obecnego wyniku
        myinterface.setScore(player.getScore() + "");

//		ustawienie życia bazy
        myinterface.setBaza("Baza: " + baza.getHp());

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
//		Dodawanie strzałów potorów
        for (Monster m : potwory) {
            int l1 = r.nextInt(100);
            int l2 = r.nextInt(100);
            if(l1 == l2){
                int kierunek = r.nextInt(4);
                strzalyPotworow.add(new Shoot(m.x + m.getTexture().getWidth() / 2 - 5, m.y + m.getTexture().getHeight() / 2 - 5, 1, kierunek));
            }
        }


//		dodanie strzałów
        timeShoot += Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.DOWN)){

//		Kierowanie strzałem
            if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                player.setDirection(1);
            }

            if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                player.setDirection(3);
            }

            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                player.setDirection(2);
            }

            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                player.setDirection(0);
            }

            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.x < 5000-2*player.radius && Gdx.input.isKeyPressed(Input.Keys.UP)) {
//				player.setDirection(4);
            }

            if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.x < 5000-2*player.radius && Gdx.input.isKeyPressed(Input.Keys.UP)) {
//				player.setDirection(5);
            }

            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.x < 5000-2*player.radius && Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
//				player.setDirection(6);
            }

            if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.x < 5000-2*player.radius && Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
//				player.setDirection(7);
            }

            if(timeShoot > 0.2){
                strzaly.add(new Shoot(player.x + player.getTexture().getWidth() / 2 - 5, player.y + player.getTexture().getHeight() / 2 - 5, 1, player.getDirection()));
                timeShoot = 0;
            }
        }

        for (Shoot s : strzaly) {
            if(s.getDirection() == 0){
                s.x -= 400 * Gdx.graphics.getDeltaTime();
            }
            if(s.getDirection() == 1){
                s.y += 400 * Gdx.graphics.getDeltaTime();
            }
            if(s.getDirection() == 2){
                s.x += 400 * Gdx.graphics.getDeltaTime();
            }
            if(s.getDirection() == 3){
                s.y -= 400 * Gdx.graphics.getDeltaTime();
            }

            if(s.getDirection() == 4){
                s.x += 400 * Gdx.graphics.getDeltaTime();
                s.y += 400 * Gdx.graphics.getDeltaTime();
            }

            if(s.getDirection() == 5){
                s.x -= 400 * Gdx.graphics.getDeltaTime();
                s.y += 400 * Gdx.graphics.getDeltaTime();
            }

            if(s.getDirection() == 6){
                s.x += 400 * Gdx.graphics.getDeltaTime();
                s.y -= 400 * Gdx.graphics.getDeltaTime();
            }

            if(s.getDirection() == 7){
                s.x -= 400 * Gdx.graphics.getDeltaTime();
                s.y -= 400 * Gdx.graphics.getDeltaTime();
            }
        }

        for (Shoot s : strzalyPotworow) {
            if(s.getDirection() == 0){
                s.x -= 400 * Gdx.graphics.getDeltaTime();
            }
            if(s.getDirection() == 1){
                s.y += 400 * Gdx.graphics.getDeltaTime();
            }
            if(s.getDirection() == 2){
                s.x += 400 * Gdx.graphics.getDeltaTime();
            }
            if(s.getDirection() == 3){
                s.y -= 400 * Gdx.graphics.getDeltaTime();
            }

            if(s.getDirection() == 4){
                s.x += 400 * Gdx.graphics.getDeltaTime();
                s.y += 400 * Gdx.graphics.getDeltaTime();
            }

            if(s.getDirection() == 5){
                s.x -= 400 * Gdx.graphics.getDeltaTime();
                s.y += 400 * Gdx.graphics.getDeltaTime();
            }

            if(s.getDirection() == 6){
                s.x += 400 * Gdx.graphics.getDeltaTime();
                s.y -= 400 * Gdx.graphics.getDeltaTime();
            }

            if(s.getDirection() == 7){
                s.x -= 400 * Gdx.graphics.getDeltaTime();
                s.y -= 400 * Gdx.graphics.getDeltaTime();
            }
        }

//		ograniczenie pola strzałów do mapy gry
        for(Iterator<Shoot> it = strzaly.iterator(); it.hasNext();) {
            Shoot shoot = it.next();
            if (shoot.y > 5000 || shoot.y < 0 || shoot.x > 5000 || shoot.x < 0) {
                it.remove();
            }
        }

//		ograniczenie pola strzałów potworów do mapy gry
        for(Iterator<Shoot> it = strzalyPotworow.iterator(); it.hasNext();) {
            Shoot shoot = it.next();
            if (shoot.y > 5000 || shoot.y < 0 || shoot.x > 5000 || shoot.x < 0) {
                it.remove();
            }
        }

//		POTWORY
        timerMonster += Gdx.graphics.getDeltaTime();
        if(timerMonster > 1){
            if(potwory.size() < 10)
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
                    if(p.getHp() < 1){
                        player.setScore(player.getScore() + p.getScore());
                        pot.remove();
                    }
                    it.remove();
                }
            }
        }

//		Potwór bohater/baza
        for(Iterator<Monster> pot = potwory.iterator(); pot.hasNext();) {
            Monster p = pot.next();
            Rectangle rec = new Rectangle(player.x - player.radius, player.y - player.radius, player.radius * 2,player.radius * 2);
            if(p.overlaps(rec)){
                player.setHp(player.getHp() - 10);
                pot.remove();
            }

            if(p.overlaps(baza)){
                baza.setHp(baza.getHp() - 10);
                pot.remove();
            }
        }

//		Strzał potwora - bohatera/bazy
        for(Iterator<Shoot> it = strzalyPotworow.iterator(); it.hasNext();) {
            Shoot shoot = it.next();
            Rectangle rec = new Rectangle(player.x - player.radius, player.y - player.radius, player.radius * 2,player.radius * 2);
            if(shoot.overlaps(rec)){
                it.remove();
                player.setHp(player.getHp() - 1);
            }
            if(shoot.overlaps(baza)){
                it.remove();
                baza.setHp(baza.getHp() - 1);
            }
        }

//		sterowanie
//        chodzenia
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W) && player.y < 5000-2*player.radius){
            player.goMoveToTop(Gdx.graphics.getDeltaTime());
//			player.y += 250 * Gdx.graphics.getDeltaTime();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S) && player.y > 0){
            player.goMoveToBottom(Gdx.graphics.getDeltaTime());
//			player.y -= 250 * Gdx.graphics.getDeltaTime();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A) && player.x > 0){
            player.goMoveToLeft(Gdx.graphics.getDeltaTime());
//			player.x -= 250 * Gdx.graphics.getDeltaTime();
//			player.setDirection(0);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D) && player.x < 5000-2*player.radius) {
            player.goMoveToRight(Gdx.graphics.getDeltaTime());
//			player.x += 250 * Gdx.graphics.getDeltaTime();
        }

//        Bieganie
        if(Gdx.input.isKeyPressed(Input.Keys.W) && player.y > 0 && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
            System.out.println("weszło");
            player.runMoveToTop(Gdx.graphics.getDeltaTime());
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S) && player.y > 0 && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
            System.out.println("weszło");
            player.runMoveToBottom(Gdx.graphics.getDeltaTime());
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A) && player.x > 0 && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
            System.out.println("weszło");
            player.runMoveToLeft(Gdx.graphics.getDeltaTime());
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            System.out.println("weszło");
            player.runMoveToRight(Gdx.graphics.getDeltaTime());
        }


        if(Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
            player.x += 20;
        }

//		Kierowanie strzałem
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            player.setDirection(1);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            player.setDirection(3);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            player.setDirection(2);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            player.setDirection(0);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.x < 5000-2*player.radius && Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.setDirection(4);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.x < 5000-2*player.radius && Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.setDirection(5);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.x < 5000-2*player.radius && Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.setDirection(6);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.x < 5000-2*player.radius && Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.setDirection(7);
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

//      Obsługa prezentu
//      Rotaja
        if(giftLinkedList != null){
            for (Gift g : giftLinkedList) {
                g.animation();
                g.setX(g.getSprite().getX());
                g.setY(g.getSprite().getY());
            }
        }

//        Dodanie czasu od ostatniego prezentu
        timerGift += Gdx.graphics.getDeltaTime();

//      Dodanie prezentu na mapę
        int g1 = r.nextInt(1000);
        int g2 = r.nextInt(1000);
        if(g1 == g2 || timerGift > 100){
            giftLinkedList.add(new Gift(r.nextInt(5000), r.nextInt(5000)));
            timerGift = 0;
        }

//        sprawdzenie czasu obecnego prezentu
        if(player.getGiftTime() < 10 && player.getGiftType() != -1){
            myinterface.setInfo("");
        }

//        Show bonus time
        if(player.getGiftType() != -1){
            player.setGiftTime(player.getGiftTime() - Gdx.graphics.getDeltaTime());
            if(player.getGiftType() == 2 || player.getGiftType() == 3 || player.getGiftType() == 5 || player.getGiftType() == 6){
                myinterface.setGift("Bonus: " + player.getGiftTime());
            }
        }

//        Sprawdzenie czasu obencego prezentu
        if(player.getGiftTime() < 0 && player.getGiftType() != -1){
            if(player.getGiftType() == 2){
                player.setSpeed(player.getSpeed() - 50);
                myinterface.setGift("");
            }else if(player.getGiftType() == 3){
                player.setSpeed(player.getSpeed() + 50);
                myinterface.setGift("");
            }else if(player.getGiftType() == 5){
                for (Monster monster : potwory) {
                    monster.setSpeed(monster.getSpeed() - 1);
                    myinterface.setGift("");
                }
            }else if(player.getGiftType() == 6){
                for (Monster monster : potwory) {
                    monster.setSpeed(monster.getSpeed() + 1);
                    myinterface.setGift("");
                }
            }

            System.out.println("Usuń bonus");
            player.setGiftType(-1);
            player.setGiftTime(1);
        }

//      Kolizje Z PREZENTEM
//      Bohater - prezent
        for(Iterator<Gift> it = giftLinkedList.iterator(); it.hasNext();) {
            Gift gift = it.next();
            Rectangle rec = new Rectangle(player.x - player.radius, player.y - player.radius, player.radius * 2,player.radius * 2);
            if(gift.overlaps(rec)){
                if(player.getGiftType() == -1){
//                    if(gift.getType() == 2 || gift.getType() == 3 || gift.getType() == 5 || gift.getType() == 6){
                        player.setGiftTime(15);
                        player.setGiftType(gift.getType());
//                    }
//                    dfkngkdjs
                    it.remove();
                    gift.getGift(player, potwory, strzaly,strzalyPotworow, myinterface);
                }
            }
        }

//        Kolizja potworek - prezent
        for(Iterator<Gift> giftIterator = giftLinkedList.iterator(); giftIterator.hasNext();) {
            Gift gift = giftIterator.next();
            for(Iterator<Monster> monsterIterator = potwory.iterator(); monsterIterator.hasNext();) {
                Monster monster = monsterIterator.next();
                if(gift.overlaps(monster)){
                    giftIterator.remove();
                    monster.setHp(monster.getHp() - 1);
                }
            }
        }

//        Kolizja strzał Potworów - prezent
        for(Iterator<Gift> giftIterator = giftLinkedList.iterator(); giftIterator.hasNext();) {
            Gift gift = giftIterator.next();
            for(Iterator<Shoot> shootIterator = strzalyPotworow.iterator(); shootIterator.hasNext();) {
                Shoot shoot = shootIterator.next();
                if(gift.overlaps(shoot)){
                    giftIterator.remove();
                    shootIterator.remove();
                }
            }
        }

//        Kolizja strzał - prezent
        for(Iterator<Gift> giftIterator = giftLinkedList.iterator(); giftIterator.hasNext();) {
            Gift gift = giftIterator.next();
            for(Iterator<Shoot> shootIterator = strzaly.iterator(); shootIterator.hasNext();) {
                Shoot shoot = shootIterator.next();
                if(gift.overlaps(shoot)){
                    giftIterator.remove();
                    shootIterator.remove();
                }
            }
        }

//        czas po którym prezent znika
        for(Iterator<Gift> giftIterator = giftLinkedList.iterator(); giftIterator.hasNext();) {
            Gift gift = giftIterator.next();
            gift.setTime(gift.getTime() - Gdx.graphics.getDeltaTime());
            if(gift.getTime() < 0){
                giftIterator.remove();
            }
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
        batch.dispose();
        player.getTexture().dispose();
        font.dispose();
        texture.dispose();
//		home.dispose();
        stage.dispose();
    }
}
