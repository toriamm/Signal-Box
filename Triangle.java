public class Triangle extends Signal{
    public float getSample(long us){
	us = us % MICROSECONDS_IN_A_SECOND;
	float usf = (float)us;
	float slope = 0.0f;
	float m = slope/250000.0f;
	float b = 0.0f;
	float equation = 0.0f;
	if(usf < 1000000.0f){
	    slope = HIGH;
	    m = slope/250000.0f;
	    b = 0.0f;
	    equation = m * usf + b;
	    if(equation > 1.0f){
		m *= -1.0f;
		b = 2.0f;
	}
	equation = m * usf + b;
	if(equation < -1.0f){
		slope = HIGH;
		m = slope/250000.0f;
		b = -4.0f;
	    }
	    equation = m * usf + b;
	}
	return equation;
    }
}
