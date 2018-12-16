/**
 * A square wave is at its lowest outside of its wave duty cycle,
 * when in the wave duty cycle, it is at its highest.
 * This wave duty cycle will last for a proportion of the period defined by pulse width.
 * 
 * If a square wave has pulse width set to 50%,
 * then half of the period is spent LOW and the other half spent HIGH
 */
public class Square extends Signal {
    float pulseWidth; // 50% wave duty by default
    /**
     * Creates a square wave with a pulse width 50% that of the period
     */
    public Square(){
		pulseWidth = 0.5f;
    }
    /**
     * Creates a square wave with custom pulse with as a percentage of the period
     * @param pw desired pulse width
     */
    public Square(float pw) {
		setPulseWidth(pw);
    }
	/**
	 * Changes the pulse width on this waveform.
	 * @param pw the new pulse width to use
	 */
	public void setPulseWidth(float pw) {
		pulseWidth = pw;
	}


	@Override
	public float getSample(long us) {
		us = (us%Inherit.MICROSECONDS_IN_A_SECOND);
		int tus = Inherit.MICROSECONDS_IN_A_SECOND;
		float tf = (1.0f-pulseWidth);
		tus = (int)(tus*tf);
		if(us < tus)
			return Signal.LOW;
		return Signal.HIGH;
	}
}
