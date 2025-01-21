package yenru0.yrkaier.btkr.s1;

import com.badlogic.gdx.Game;

public class MainGame extends Game {

    public static final int V_WIDTH = 800;
    public static final int V_HEIGHT = 600;
    public static final float PPM = 100;

    @Override
    public void create() {
        setScreen(new MainScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
    }
}
