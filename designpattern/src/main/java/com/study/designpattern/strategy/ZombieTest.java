/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.designpattern.strategy;

/**
 * @author study
 * @version : ZombieTest.java, v 0.1 2020年06月16日 7:24 study Exp $
 */
public class ZombieTest {

}
interface Moveable{
    void move();
}
interface Attackable{
    void attack();
}

abstract class AbstractZombie{
    public abstract void display();
    Moveable moveable;
    Attackable attackable;

    abstract void move();
    abstract void attack();

    public AbstractZombie(Moveable moveable, Attackable attackable) {
        this.moveable = moveable;
        this.attackable = attackable;
    }

    /**
     * Getter method for property <tt>moveable</tt>.
     *
     * @return property value of moveable
     */
    public Moveable getMoveable() {
        return moveable;
    }

    /**
     * Setter method for property <tt>moveable</tt>.
     *
     * @param moveable value to be assigned to property moveable
     */
    public void setMoveable(Moveable moveable) {
        this.moveable = moveable;
    }

    /**
     * Getter method for property <tt>attackable</tt>.
     *
     * @return property value of attackable
     */
    public Attackable getAttackable() {
        return attackable;
    }

    /**
     * Setter method for property <tt>attackable</tt>.
     *
     * @param attackable value to be assigned to property attackable
     */
    public void setAttackable(Attackable attackable) {
        this.attackable = attackable;
    }
}

class StepByStep implements Moveable{

    @Override
    public void move() {
        System.out.println("一步一步移动");
    }
}