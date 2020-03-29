package hu.bme.mit.train.sensor;

import hu.bme.mit.train.user.*;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class TrainSensorTest {

    TrainController trainControllerMock;
    TrainUser trainUserMock;

    @Before
    public void before() {
        trainControllerMock = mock(TrainController.class, "");
        trainUserMock = mock(TrainUserImpl.class, "");
        when(trainControllerMock.getReferenceSpeed()).thenReturn(10);
    }

    @Test
    public void lowerAbsoluteMarginTest() {
        TrainSensorImpl sensor = new TrainSensorImpl(trainControllerMock, trainUserMock);
        sensor.overrideSpeedLimit(-1);
        verify(trainControllerMock).setSpeedLimit(-1);
        assertTrue(sensor.getAlarmState() == true);
    }

    @Test
    public void higherAbsoluteMarginTest() {
        TrainSensorImpl sensor = new TrainSensorImpl(trainControllerMock, trainUserMock);
        sensor.overrideSpeedLimit(501);
        verify(trainControllerMock).setSpeedLimit(501);
        assertTrue(sensor.getAlarmState() == true);
    }

    @Test
    public void relativeMarginTest() {
        TrainSensorImpl sensor = new TrainSensorImpl(trainControllerMock, trainUserMock);
        when(trainControllerMock.getReferenceSpeed()).thenReturn(150);
        sensor.overrideSpeedLimit(50);
        verify(trainControllerMock).setSpeedLimit(50);
        assertTrue(sensor.getAlarmState() == true);
    }

    @Test
    public void noAlarmTest() {
        TrainSensorImpl sensor = new TrainSensorImpl(trainControllerMock, trainUserMock);
        sensor.overrideSpeedLimit(6);
        verify(trainControllerMock).setSpeedLimit(6);
        assertTrue(sensor.getAlarmState() == false);
    }
}
