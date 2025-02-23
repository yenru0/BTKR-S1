package yenru0.yrkaier.btkr.s1;

import com.badlogic.gdx.physics.box2d.*;

public class WorldContactListener implements ContactListener {


    WorldContactListener(World world) {

    }

    @Override
    public void beginContact(Contact contact) {
        var fixA = contact.getFixtureA();
        var fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
        switch (cDef) {
            case MainGame.CATEGORY_GROUND_BIT | MainGame.CATEGORY_PLAYER_BIT:
                if (fixA.getFilterData().categoryBits == MainGame.CATEGORY_PLAYER_BIT) {
                    ((Player) fixA.getUserData()).enableJump();
                } else {
                    ((Player) fixB.getUserData()).enableJump();
                }
                break;
            case MainGame.CATEGORY_ENEMY_BIT:
                if (fixA.getUserData() != null) {
                    ((Enemy) fixA.getUserData()).damaged(0.001f);
                }
                if (fixB.getUserData() != null) {
                    ((Enemy) fixB.getUserData()).damaged(0.001f);
                }
                break;
            case MainGame.CATEGORY_PLAYER_BIT | MainGame.CATEGORY_ENEMY_BIT:
                if(fixA.getFilterData().categoryBits == MainGame.CATEGORY_PLAYER_BIT) {
                    ((Player) fixA.getUserData()).damaged(0.1f);
                    ((Player) fixA.getUserData()).enableJump();
                    ((Enemy) fixB.getUserData()).damaged(0.1f);

                } else {
                    ((Player) fixB.getUserData()).damaged(0.1f);
                    ((Player) fixB.getUserData()).enableJump();
                    ((Enemy) fixA.getUserData()).damaged(0.1f);
                }
                break;
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
