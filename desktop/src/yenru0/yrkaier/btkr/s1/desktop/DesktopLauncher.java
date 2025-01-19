package yenru0.yrkaier.btkr.s1.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Input;
import yenru0.yrkaier.btkr.s1.MainGame;

public class DesktopLauncher {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("BTKR-S1");
        config.setWindowedMode(800, 600);

        new Lwjgl3Application(new MainGame(), config);
        Gdx.app.setLogLevel(Lwjgl3Application.LOG_DEBUG);
    }
}
