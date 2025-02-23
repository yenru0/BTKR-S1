package yenru0.yrkaier.btkr.s1;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class DefaultGround {
    private Body body;

    public DefaultGround(World world, Vector2 pos, float width) {

        var bodydef = new BodyDef();
        bodydef.type = BodyDef.BodyType.StaticBody;
        this.body = world.createBody(bodydef);


        var fixdef = new FixtureDef();

        var groundBox = new PolygonShape();
        groundBox.setAsBox(width, 1f);
        fixdef.shape = groundBox;
        var filter = new Filter();
        filter.categoryBits = MainGame.CATEGORY_GROUND_BIT;
        fixdef.filter.set(filter);
        fixdef.density = 0.0f;
        fixdef.restitution = 0.0f;

        body.createFixture(fixdef);

        groundBox.dispose();

    }

}
