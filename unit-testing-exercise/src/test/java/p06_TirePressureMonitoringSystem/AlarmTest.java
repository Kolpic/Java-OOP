package p06_TirePressureMonitoringSystem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AlarmTest {

    private Sensor sensor;
    private Alarm alarm;

    @Before
    public void prepare() {
         sensor = mock(Sensor.class);

         alarm = new Alarm(sensor);
    }

    @Test
    public void testCheckTrueIfPressureIsUnder17() {

        when(sensor.popNextPressurePsiValue()).thenReturn(16.0);

        alarm.check();

        Assert.assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void testCheckFalseIfPressureIsHigherThan17() {

        when(sensor.popNextPressurePsiValue()).thenReturn(20.0);

        alarm.check();

        Assert.assertFalse(alarm.getAlarmOn());
    }

}