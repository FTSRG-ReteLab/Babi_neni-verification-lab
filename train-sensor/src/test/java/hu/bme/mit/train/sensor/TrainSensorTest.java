package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;

public class TrainSensorTest {

    //TrainController controller;
    TrainSensor sensor;
    TrainUser user;

    TrainController controller = mock(TrainController.class);

    @Before
    public void before() {


        sensor.overrideSpeedLimit(50);
    }

    @Test
    public void Unit_SpeedTest_AlarmTrue1() {
        sensor.overrideSpeedLimit(100);
        sensor.overrideSpeedLimit(25);

        Assert.assertEquals(true, user.getAlarmFlag());
    }

    @Test
    public void Unit_SpeedTest_AlarmTrue2() {
        sensor.overrideSpeedLimit(-1);
        Assert.assertEquals(true, user.getAlarmFlag());
    }

    @Test
    public void Unit_SpeedTest_AlarmTrue3() {
        sensor.overrideSpeedLimit(501);
        Assert.assertEquals(true, user.getAlarmFlag());
    }

    @Test
    public void Unit_SpeedTest_AlarmFalse() {
        sensor.overrideSpeedLimit(100);
        sensor.overrideSpeedLimit((int)(100*0.51));

        Assert.assertEquals(false, user.getAlarmFlag());
    }



}
