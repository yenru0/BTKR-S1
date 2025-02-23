package yenru0.yrkaier.btkr.s1;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Enemy {
    public Body body;
    public Fixture fix;
    public World world;
    public float hp = 1f;
    public boolean isDestroyed = false;

    public Enemy(World world, Vector2 pos_init) {
        this.world = world;
        var bodydef = new BodyDef();
        bodydef.type = BodyDef.BodyType.DynamicBody;
        bodydef.position.set(pos_init);
        bodydef.angularDamping = 1f;

        this.body = world.createBody(bodydef);

        var circleShape = new CircleShape();
        circleShape.setRadius(6f);

        var fixdef = new FixtureDef();
        var fixfilter = new Filter();
        fixfilter.categoryBits = MainGame.CATEGORY_ENEMY_BIT;

        fixdef.filter.set(fixfilter);
        fixdef.shape = circleShape;
        fixdef.density = 0.5f;
        fixdef.friction = 0.5f;
        fixdef.restitution = 0.0f;

        this.fix = this.body.createFixture(fixdef);
        fix.setUserData(this);
        circleShape.dispose();
        
        body.setAwake(true);
    }

    public void damaged(float dmg) {
        hp -= dmg;
        if(hp <= 0) {
            isDestroyed = true;
        }

    }

    public void dispose() {
        world.destroyBody(body);
        body = null;

    }

}
