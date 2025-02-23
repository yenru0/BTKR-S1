package yenru0.yrkaier.btkr.s1;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Player {
    public Body body;
    public Fixture fixture;

    public boolean canJump = false;

    public float MAX_VEL = 10f;

    private float hp = 100f;

    public boolean isDestroyed = false;

    public Player(World world, Vector2 pos_init) {
        BodyDef bodydef = new BodyDef();
        bodydef.type = BodyDef.BodyType.DynamicBody;
        bodydef.position.set(pos_init);
        bodydef.fixedRotation = true;
        this.body = world.createBody(bodydef);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(6f);

        body.setAwake(true);
        FixtureDef fixdef = new FixtureDef();
        var ff = new Filter();
        ff.categoryBits = MainGame.CATEGORY_PLAYER_BIT;
        fixdef.filter.set(ff);
        fixdef.shape = circleShape;
        fixdef.density = 0.5f;
        fixdef.friction = 0.5f;
        fixdef.restitution = 0.0f;

        this.fixture = body.createFixture(fixdef);
        fixture.setUserData(this);
        circleShape.dispose();
    }

    public void damaged(float dmg) {
        setHp(getHp() - dmg);
        if(getHp() <= 0) {
            isDestroyed = true;
        }

    }

    public void enableJump() {
        canJump = true;
    }

    public void tryJump() {
        var pos = this.body.getPosition();

        if(canJump) {
            this.body.applyLinearImpulse(new Vector2(0, 1500f), pos, true);
            canJump = false;
        }
    }

    public void moveLeft() {
        var vel = this.body.getLinearVelocity();
        if(vel.x > -MAX_VEL)
            this.body.applyLinearImpulse(new Vector2(-2, 0), this.body.getWorldCenter(), true);
    }

    public void moveRight() {
        var vel = this.body.getLinearVelocity();
        if (vel.x < MAX_VEL)
            this.body.applyLinearImpulse(new Vector2(2, 0), this.body.getWorldCenter(), true);
    }

    public float getHp() {
        return hp;
    }

    private void setHp(float hp) {
        this.hp = hp;
    }
}
