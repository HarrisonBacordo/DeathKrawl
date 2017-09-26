package Component;

import Entity.Entity;
import Entity.KeyInput;
import Entity.NinjaEntity;

/**
 * Component that handles all keyboard input
 *
 * Created by krishna on 2/09/2017.
 *
 * CONTRIBUTION: Harrison Bacordo (bacordharr)
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
        if(keyInput.isUp()) entity.setYVelocity(-5);
        else if(!keyInput.isDown()) entity.setYVelocity(0);

        if(keyInput.isDown()) entity.setYVelocity(5);
        else if(!keyInput.isUp()) entity.setYVelocity(0);

        if(keyInput.isLeft()) entity.setXVelocity(-5);
        else if(!keyInput.isRight()) entity.setXVelocity(0);

        if(keyInput.isRight()) entity.setXVelocity(5);
        else if (!keyInput.isLeft()) entity.setXVelocity(0);

        if(keyInput.isSpace()) ((NinjaEntity) entity).jumping = true;

//        Handles shooting input
        if(keyInput.isShootUp()) ((NinjaEntity) entity).shootingDirection = ShootComponent.ShootingDirection.SHOOT_UP;
        else if(keyInput.isShootDown()) ((NinjaEntity) entity).shootingDirection = ShootComponent.ShootingDirection.SHOOT_DOWN;
        else if(keyInput.isShootLeft()) ((NinjaEntity) entity).shootingDirection = ShootComponent.ShootingDirection.SHOOT_LEFT;
        else if(keyInput.isShootRight()) ((NinjaEntity) entity).shootingDirection = ShootComponent.ShootingDirection.SHOOT_RIGHT;
        else ((NinjaEntity) entity).shootingDirection = ShootComponent.ShootingDirection.NOT_SHOOTING;

//        Handles gun switches
        if(keyInput.isPreviousGun()) {
            ((NinjaEntity) entity).switchPreviousGun();
            keyInput.setPreviousGun(false);
        }
        if(keyInput.isNextGun()) {
            ((NinjaEntity) entity).switchNextGun();
            keyInput.setNextGun(false);
        }

    }
}
