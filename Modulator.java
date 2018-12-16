//This class will accept any signal and extends Signal. It will have a method "setPeriod(int hz)". It will be added to Inherit

public class Modulator extends Signal{

    Signal modWave;
    int period;
	
    public Modulator(Signal wave){
	modWave = wave;
    }

    public void setPeriod(int hz){
	period = hz;
    }

    public float getSample(long us){
	us = us * period;
	return modWave.getSample(us);
    }
}
