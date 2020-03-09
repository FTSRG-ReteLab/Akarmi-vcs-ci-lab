package hu.bme.mit.train.system;

import hu.bme.mit.train.controller.TrainControllerImpl;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.sensor.TrainSensorImpl;
import hu.bme.mit.train.user.TrainUserImpl;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class Tachograph {
    Table<Integer, String, Integer> tachograph
            = HashBasedTable.create();
    public Table<Integer, String, Integer> get() {
        return tachograph;
    }
}
