package ru.miroshn.cartoon_raider.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import ru.miroshn.cartoon_raider.CartoonRaider;

/**
 * Created by miroshn on 06.04.15.
 * Показывается экран с названием..
 */
public class WelcomeScreen implements Screen {
    private CartoonRaider game;
    private Texture backgroundTexture;
    private Texture titleTexture;
    private SpriteBatch batch;
    private Title title;
    private Stage stage;

    public WelcomeScreen(CartoonRaider game) {
        this.game = game;
        backgroundTexture = new Texture("background.jpg");
        titleTexture = new Texture("title.png");
        batch = new SpriteBatch();
        batch.getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        title = new Title();

        title.setScale(Gdx.graphics.getWidth() * 3.0f / 5.0f / (float) titleTexture.getWidth());
        title.setPosition((Gdx.graphics.getWidth() - title.getWidth() * title.getScaleX()) / 2.0f,
                (Gdx.graphics.getHeight() - title.getHeight() * title.getScaleY()) / 2.0f);
        stage = new Stage();
        stage.addActor(title);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        for (int x = 0; x < Gdx.graphics.getWidth(); x += backgroundTexture.getWidth()) {
            for (int y = 0; y < Gdx.graphics.getHeight(); y += backgroundTexture.getHeight()) {
                batch.draw(backgroundTexture, x, y);
            }
        }
        batch.end();

        stage.act(delta);
        stage.draw();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        titleTexture.dispose();
        batch.dispose();
        stage.dispose();
    }


    class Title extends Actor {
        TextureRegion region;

        public Title() {
            setSize(titleTexture.getWidth(), titleTexture.getHeight());
            region = new TextureRegion(titleTexture);
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            batch.draw(region, getX(), getY(),
                    getOriginX(), getOriginY(),
                    getWidth(), getHeight(),
                    getScaleX(), getScaleY(), getRotation());
        }
    }
}