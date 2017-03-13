package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.user.TrainUserImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class TrainSensorTest {
    TrainController controller = mock(TrainController.class);
    TrainUser user = new TrainUserImpl(controller);
    TrainSensor sensor = new TrainSensorImpl(controller, user);


    @Before
    public void before() {
        sensor.overrideSpeedLimit(50);
    }

    @Test
    public void Unit_SpeedTest_AlarmTrue1() {
        sensor.overrideSpeedLimit(100);
        sensor.overrideSpeedLimit(25);

        Assert.assertEquals(true, user.getAlarmState());
    }

    @Test
    public void Unit_SpeedTest_AlarmTrue2() {
        sensor.overrideSpeedLimit(-1);
        Assert.assertEquals(true, user.getAlarmState());
    }

    @Test
    public void Unit_SpeedTest_AlarmTrue3() {
        sensor.overrideSpeedLimit(501);
        Assert.assertEquals(true, user.getAlarmState());
    }

    @Test
    public void Unit_SpeedTest_AlarmFalse() {
        sensor.overrideSpeedLimit(100);
        sensor.overrideSpeedLimit((int)(100*0.53));
        Assert.assertEquals(false, user.getAlarmState());
    }
}
