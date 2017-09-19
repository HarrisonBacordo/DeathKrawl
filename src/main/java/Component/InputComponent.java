package Component;

import Entity.Entity;
import Entity.KeyInput;
import Entity.NinjaEntity;

/**
 * Component that handles all keyboard input
 *
 * Created by krishna on 2/09/2017.
 */
public class InputComponent extends Component{
    private KeyInput keyInput;

    public InputComponent(Entity entity, KeyInput keyInput){
        super(entity, ComponentType.INPUT);
        this.keyInput = keyInput;
    }

    @Override
    public void execute() {

        //Movement
        if(keyInput.isUp()) entity.setyVelocity(-5);
        else if(!keyInput.isDown()) entity.setyVelocity(0);

        if(keyInput.isDown()) entity.setyVelocity(5);
        else if(!keyInput.isUp()) entity.setyVelocity(0);

        if(keyInput.isLeft()) entity.setxVelocity(-5);
        else if(!keyInput.isRight()) entity.setxVelocity(0);

        if(keyInput.isRight()) entity.setxVelocity(5);
        else if (!keyInput.isLeft()) entity.setxVelocity(0);

        if(keyInput.isSpace()) ((NinjaEntity) entity).jumping = true;

    }
}
