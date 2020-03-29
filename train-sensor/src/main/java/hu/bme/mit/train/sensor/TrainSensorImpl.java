package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private TrainUser user;
	private int speedLimit = 5;
	private boolean alarmState = false;

	public TrainSensorImpl(TrainController controller, TrainUser user) {
		this.controller = controller;
		this.user = user;
	}

	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

	@Override
	public void overrideSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;

		if (speedLimit < 0 || speedLimit > 500 || speedLimit < (this.controller.getReferenceSpeed() * 0.5)) {
			this.alarmState = true;
		} else {
			this.alarmState = false;
		}
		controller.setSpeedLimit(speedLimit);
	}

	public boolean getAlarmState() {
		return this.alarmState;
	}

	public void setAlarmState(boolean alarmState) {
		this.alarmState = alarmState;
	}

}
