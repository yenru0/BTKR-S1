package yenru0.yrkaier.btkr.s1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainScreen implements Screen {
    private MainGame game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private Vector2 playerPos = new Vector2(0, 0);
    private Vector2 playerVel = new Vector2(0, 0);
    private Vector2 playerAcc = new Vector2(0, 0);
    private boolean isJumpable = false;

    private Vector2 gravity = new Vector2(0, -50);
    private ShapeRenderer shapeRenderer;

    public MainScreen(MainGame game) {
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(MainGame.V_WIDTH / MainGame.PPM, MainGame.V_HEIGHT / MainGame.PPM, gameCam);
        shapeRenderer = new ShapeRenderer();

        playerPos.x = (float) MainGame.V_WIDTH / 2;
        playerPos.y = 200;
    }

    public void handleInput(float dt) {
        if (isJumpable && Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            playerVel.add(new Vector2(0, 100));
            isJumpable = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playerPos.add(new Vector2(-100f * dt, 0));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            playerPos.add(new Vector2(100f * dt, 0));
        }
    }

    public void playerPhysicsUpdate(float dt) {
        playerVel.mulAdd(playerAcc, dt);
        playerPos.mulAdd(playerVel, dt);
        if (playerPos.y < 66) {
            playerPos.y = 66;
            playerVel.y = 0;
            playerAcc.y = 0;
            isJumpable = true;
        } else {

        }
    }

    public void update(float dt) {
        playerAcc.set(gravity);
        handleInput(dt);
        playerPhysicsUpdate(dt);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float dt) {
        update(dt);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(playerPos.x, playerPos.y, 16);

        shapeRenderer.end();
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(0, 0, MainGame.V_WIDTH, 50);
        shapeRenderer.end();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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

    }
}
