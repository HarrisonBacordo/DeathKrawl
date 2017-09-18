package Components;

import Entities.Entity;
import Entities.KeyInput;
import Entities.NinjaEntity;

/**
 * Component that handles all keyboard input
 *
 * Created by krishna on 2/09/2017.
 */
public class InputComponent extends Component{
    private KeyInput keyInput;

    public InputComponent(Entity entity, KeyInput keyInput){
        super(entity);
        this.keyInput = keyInput;
    }

    @Override
    public void execute() {

        //Movement
        if(keyInput.isUp()) entity.setyVel(-5);
        else if(!keyInput.isDown()) entity.setyVel(0);

        if(keyInput.isDown()) entity.setyVel(5);
        else if(!keyInput.isUp()) entity.setyVel(0);

        if(keyInput.isLeft()) entity.setxVel(-5);
        else if(!keyInput.isRight()) entity.setxVel(0);

        if(keyInput.isRight()) entity.setxVel(5);
        else if (!keyInput.isLeft()) entity.setxVel(0);

        if(keyInput.isSpace()) ((NinjaEntity) entity).jumping = true;

    }
}
