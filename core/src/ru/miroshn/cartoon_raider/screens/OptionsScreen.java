package ru.miroshn.cartoon_raider.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import ru.miroshn.cartoon_raider.CartoonRaider;
import ru.miroshn.cartoon_raider.gameobjects.Background;
import ru.miroshn.cartoon_raider.gameobjects.ui.IstrebitelButton;
import ru.miroshn.cartoon_raider.helpers.CRAssetManager;
import ru.miroshn.cartoon_raider.helpers.Conf;
import ru.miroshn.cartoon_raider.helpers.Res;

/**
 * Экран настроек
 * <br/>Created by miroshn on 25.08.15.
 */
public class OptionsScreen implements Screen {
    private static final String SOUND_VOLUME_TEXT = Conf.SOUND_VOLUME_TEXT;
    private static final String SOUND_ENABLE_TEXT = Conf.SOUND_ENABLE_TEXT;
    private final float BUTTON_SIZE = 1.0f / 10.0f;
    private final Stage stage;
    private final Table table;
    private final IstrebitelButton okButton;
    private final BitmapFont font;
    private final Label lVolume, lEnableSound;
    private final CheckBox cbSoundEnable;
    private final Slider slider;
    private final Sound sound;
    private final Preferences preferences;


    public OptionsScreen() {
        // объект настроек
        preferences = Gdx.app.getPreferences(Conf.OPTIONS_NAME);

        // просто для краткости
        CRAssetManager asset = CRAssetManager.getInstance();
        sound = asset.get(Res.SHOT_SOUND);

        stage = new Stage();
        // Back или ESC сохраняет настройки и возврат в меню
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE) {
                    saveExit();
                }
                return true;
            }
        });

        table = new Table();
        table.setDebug(Conf.DEBUG);
        table.setFillParent(true);

        font = CRAssetManager.getInstance().get(Res.FONT);
        final float width = Gdx.graphics.getWidth();

        lEnableSound = new Label(SOUND_ENABLE_TEXT, CRAssetManager.getInstance().getLabelStyle());
        table.add(lEnableSound).left().center();
        float labelWidth = lEnableSound.getWidth();
        cbSoundEnable = new CheckBox(""
                , new CheckBox.CheckBoxStyle(
                new TextureRegionDrawable(((TextureRegion) asset.get(Res.CHECK_BOX)))
                , new TextureRegionDrawable(((TextureRegion) asset.get(Res.CHECK_BOX_CHECKED)))
                , font, CartoonRaider.NORMAL_COLOR));
        cbSoundEnable.getImage().setColor(CartoonRaider.NORMAL_COLOR);
        cbSoundEnable.setChecked(preferences.getBoolean(Conf.SOUND_ENABLE_PREF_KEY, true));
        table.add(cbSoundEnable).size(width * BUTTON_SIZE /*/ 2.0f*/).pad(width * BUTTON_SIZE / 2.0f);
        table.row();

        lVolume = new Label(SOUND_VOLUME_TEXT, CRAssetManager.getInstance().getLabelStyle());
        table.add(lVolume).left().center();
        slider = new Slider(0.f, 1.0f, 0.05f, false, new Slider.SliderStyle(
                new TextureRegionDrawable(((TextureRegion) asset.get(Res.SLIDER)))
                , new TextureRegionDrawable(((TextureRegion) asset.get(Res.BULLET)))
        ));
        slider.setColor(CartoonRaider.NORMAL_COLOR);
        slider.setValue(preferences.getFloat(Conf.SOUND_VOLUME_PREF_KEY, 0.5f));
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                sound.play(slider.getValue());
            }
        });
        table.add(slider).width(width - labelWidth * 2.0f).pad(width * BUTTON_SIZE / 2.0f);
        table.row();

        okButton = new IstrebitelButton(Conf.OK_STRING);
        okButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                saveExit();
            }
        });
        final float proportion = okButton.getHeight() / okButton.getWidth();
        table.add(okButton).bottom().size(width * BUTTON_SIZE, width * BUTTON_SIZE * proportion).fillY()
                .padTop(Gdx.graphics.getHeight() / 2.0f).colspan(2);

    }

    private void saveExit() {
        preferences.putBoolean(Conf.SOUND_ENABLE_PREF_KEY, cbSoundEnable.isChecked());
        preferences.putFloat(Conf.SOUND_VOLUME_PREF_KEY, slider.getValue());
        preferences.flush();
        ScreenManager.getInstance().show(CustomScreen.MENU_SCREEN);
    }

    @Override
    public void dispose() {
        Gdx.app.debug(getClass().getSimpleName(), "dispose called");
        stage.dispose();
//        font.dispose(); // ??
//        sound.dispose(); // ??
    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
    }

    @Override
    public void show() {
        stage.getActors().clear();
        stage.addActor(Background.getInstance());
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
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
}
