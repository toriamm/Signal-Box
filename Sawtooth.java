/**
 * A sawtooth wave rises linearly from its lowest point to its highest during one period.
 */
public class Sawtooth extends Signal {
	@Override
	public float getSample(long us) {
	    // Implement this for a sawtooth wave that has a period of 1 second
	    // Parameter us is given to you in microseconds
	    us = us % MICROSECONDS_IN_A_SECOND;
	    float usf = (float)us;
	    float slope = HIGH;
	    float b = LOW;
	    float equation = (slope/500000.f) * usf + b;
	    return equation;
	}
}
