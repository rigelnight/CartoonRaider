package ru.miroshn.cartoon_raider.helpers;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by miroshn on 14.05.15.
 * Тут собраны все имена файлов и ресурсов и превращены в валидируемый enum
 */
public enum Res {

    SHOT_SOUND("shot.ogg", Types.SOUND),
    EXPLOSIVE_SOUND("explosive.ogg", Types.SOUND),
    ROCKET_SOUND("rocket.ogg", Types.SOUND),
    ALRAM_SOUND("alram.ogg", Types.SOUND),

    BACKGROUND("background", Types.TEXTURE_REGION),
    BULLET("bullet", Types.TEXTURE_REGION),
    EXPLOSIVE1("explosive1", Types.TEXTURE_REGION),
    EXPLOSIVE2("explosive2", Types.TEXTURE_REGION),
    EXPLOSIVE3("explosive3", Types.TEXTURE_REGION),
    GRAPHIC_PACK("CartoonRaider.pack", Types.TEXTURE_REGION),
    I_16("i", Types.TEXTURE_REGION),
    IL_2("il-2", Types.TEXTURE_REGION),
    ISTREBITEL("istrebitel1", Types.TEXTURE_REGION),
    ROCKET("rocket", Types.TEXTURE_REGION),
    ROCKET_FLAME("rocket_flame", Types.TEXTURE_REGION),
    STAR("stars", Types.TEXTURE_REGION),
    BOSS1("cb-sh-2", Types.TEXTURE_REGION),
    CHECK_BOX("cb", Types.TEXTURE_REGION),
    CHECK_BOX_CHECKED("cbCheked", Types.TEXTURE_REGION),
    SLIDER("Slider", Types.TEXTURE_REGION),
    //    EXIT DIALOG
    EXIT_DIALOG("dialog", Types.TEXTURE_REGION),
    EXIT_DIALOG_OK("ok", Types.TEXTURE_REGION),
    EXIT_DIALOG_OK_PRESSED("ok_pressed", Types.TEXTURE_REGION),
    EXIT_DIALOG_CANCEL("cancel", Types.TEXTURE_REGION),
    EXIT_DIALOG_CANCEL_PRESSED("cancel_pressed", Types.TEXTURE_REGION),
    EXIT_DIALOG_TRANSPARENT_BG("transparent_bg", Types.TEXTURE_REGION),
    //    FONT
    FONT("comic_sans.fnt", Types.BITMAP_FONT),
    FONT_16("comic_sans_16.fnt", Types.BITMAP_FONT),
    //    HUD
    BORDER_BAR("border_bar", Types.TEXTURE_REGION),
    BAR("bar", Types.TEXTURE_REGION),
    HP("hp", Types.TEXTURE_REGION),
    ROF("rof", Types.TEXTURE_REGION),
    PAUSE_BUTTON("pause", Types.TEXTURE_REGION),
    //    TITLES
    WELCOME_TITLE("title", Types.TEXTURE_REGION),
    GAME_OVER_TITLE("gameover", Types.TEXTURE_REGION),
    GAME_PAUSED_TITLE("pause_title", Types.TEXTURE_REGION),
    //    Progress bar
    PROGRESS_BAR("progress_bar.png", Types.TEXTURE),
    PROGRESS_BAR_BASE("progress_bar_base.png", Types.TEXTURE);


    private final String name;
    private final Types type;

    Res(String name, Types type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Types getType() {
        return type;
    }

    public enum Types {
        SOUND(Sound.class),
        TEXTURE(Texture.class),
        TEXTURE_REGION(TextureRegion.class),
        BITMAP_FONT(BitmapFont.class);


        private final Class type;

        Types(Class type) {
            this.type = type;
        }

        public Class getType() {
            return type;
        }
    }

}
