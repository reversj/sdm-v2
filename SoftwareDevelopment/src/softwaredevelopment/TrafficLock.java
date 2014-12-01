/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaredevelopment;

/**
 *
 * @author Jan
 */
public class TrafficLock {

    public boolean locked = false;

    public void lock() {
        if (locked == false) {
            locked = true;
        }
    }

    public void release() {
        locked = false;
    }
}
