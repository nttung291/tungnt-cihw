package game.bases.physics;

import java.util.Vector;

/**
 * Created by Nttung PC on 7/27/2017.
 */
public class Physics {

    private static Vector<PhysicBody> bodies = new Vector<>();

    public static void add(PhysicBody body) {
        bodies.add(body);
    }

    public static <T extends PhysicBody> T bodyInRect (BoxCollider boxCollider, Class<T> classz){
        for (PhysicBody body : bodies){
            if (body.isActive() && body.getBoxCollider().collideWith(boxCollider)){
                if (body.getClass() == classz)
                    return (T) body;
            }
        }
        return null;
    }

}
