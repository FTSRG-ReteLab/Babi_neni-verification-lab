package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class TrainSensorTest {
    TrainController controller = mock(TrainController.class);
    TrainUser user =  mock(TrainUser.class);
    TrainSensor sensor = new TrainSensorImpl(controller, user);


    @Before
    public void before() {
        sensor.overrideSpeedLimit(100);
    }

    @Test
    public void Unit_SpeedTest_AlarmTrue1() {
        when(user.getAlarmState()).thenReturn(true);
        sensor.overrideSpeedLimit(25);

        Assert.assertEquals(true, user.getAlarmState());
        verify(user, times(1)).setAlarmState(true);
    }

    @Test
    public void Unit_SpeedTest_AlarmTrue2() {
        when(user.getAlarmState()).thenReturn(true);
        sensor.overrideSpeedLimit(-1);
        Assert.assertEquals(true, user.getAlarmState());
        verify(user, times(2)).setAlarmState(true);
    }

    @Test
    public void Unit_SpeedTest_AlarmTrue3() {
        when(user.getAlarmState()).thenReturn(true);
        sensor.overrideSpeedLimit(501);
        Assert.assertEquals(true, user.getAlarmState());
        verify(user, times(1)).setAlarmState(true);
    }

    @Test
    public void Unit_SpeedTest_AlarmFalse() {
        when(user.getAlarmState()).thenReturn(false);
        sensor.overrideSpeedLimit((int)(100*0.53));
        Assert.assertEquals(false, user.getAlarmState());
        verify(user, times(0)).setAlarmState(false);
    }
}
