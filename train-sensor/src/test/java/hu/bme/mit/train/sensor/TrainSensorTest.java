package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainController mockTC;
    TrainUser mockUser;
    TrainSensorImpl mockSensorImpl;

    @Before
    public void before() {
        mockTC = mock(TrainController.class);
        mockUser = mock(TrainUser.class);
        mockSensorImpl = new TrainSensorImpl(mockTC, mockUser);
    }


    @Test
    public void lowerAbsMarginTest() {
        // Cannot be smaller than 0;
       mockSensorImpl.overrideSpeedLimit(-100);
       verify(mockUser, times(1)).setAlarmState(true);

    }

    @Test
    public void higerAbsMarginTest() {
        // Cannot be greater than 500;
        mockSensorImpl.overrideSpeedLimit(1000);
        verify(mockUser, times(1)).setAlarmState(true);

    }

    @Test
    public void normalAbsMarginTest() {
        // Should be between 0 and 500
        mockSensorImpl.overrideSpeedLimit(50);
        verify(mockUser, times(1)).setAlarmState(false);

    }
    @Test
    public void failRelativeMarinTest() {
        // If the new speed limit is more than 50% slower than the actual reference
        //speed (e.g., 150 to 50 is an alarming situation, because 50 is more than 50% less than 150)
        when(mockTC.getReferenceSpeed()).thenReturn(150);
        mockSensorImpl.overrideSpeedLimit(50);
        verify(mockUser, times(1)).setAlarmState(true);

    }
}
