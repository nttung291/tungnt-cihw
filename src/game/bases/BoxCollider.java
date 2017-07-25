package game.bases;

/**
 * Created by Nttung PC on 7/23/2017.
 */
public class BoxCollider extends GameObject{
    public float width;
    public float height;

    public BoxCollider(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public BoxCollider() {
        this(0,0);
    }

    public float left(){
        return this.position.x-width/2;
    }
    public float right(){
        return this.position.x+width/2;
    }
    public float top(){
        return this.position.y-height/2;
    }
    public float bottom(){
        return this.position.y+height/2;
    }

    public boolean collideWith(BoxCollider other){
//        if (this.left() < other.right() && this.right() > other.left() &&
//                this.bottom() > other.top() && this.top() < other.bottom() ){
//            return true;
//        }
//        return false;
//        boolean xOverlap = Mathx.inRange(other.left(),this.left(),this.right()) || Mathx.inRange(this.left(),other.left(),other.right());
//        boolean yOverlap = Mathx.inRange(other.top(),this.top(),this.bottom()) || Mathx.inRange(this.top(),other.top(),other.bottom());
//        return (xOverlap && yOverlap);
        return ((Math.abs(this.screenPosition.x - other.screenPosition.x) < (this.width + other.width)/2) &&
                (Math.abs(this.screenPosition.y - other.screenPosition.y) < (this.height + other.height)/2) &&
                other.screenPosition.x > 0 && other.screenPosition.y > 0 && this.screenPosition.x > 0 && this.screenPosition.y >0);
    }

    @Override
    public String toString() {
        return "BoxCollider{" +
                ", position=" + position +
                ", screenPosition=" + screenPosition +
                '}';
    }
}
