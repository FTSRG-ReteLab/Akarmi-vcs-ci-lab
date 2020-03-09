package hu.bme.mit.train.system;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.system.TrainSystem;
import hu.bme.mit.train.system.Tachograph;

public class Tachographtest {

    TrainController controller;
    TrainSensor sensor;
    TrainUser user;

    @Before
    public void before() {
        TrainSystem system = new TrainSystem();
        controller = system.getController();
        sensor = system.getSensor();
        user = system.getUser();

        sensor.overrideSpeedLimit(50);
    }

    @Test
    public void testTachograph() {
        Tachograph t = new Tachograph();
        Assert.assertEquals(t.get().isEmpty(), true);
    }

}
