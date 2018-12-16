public class VickiMartinez extends Signal{
    public float getSample(long us){
	us = us % MICROSECONDS_IN_A_SECOND;
	float usf = (float)us;
	float slope = 0.0f;
	float m = slope/250000.0f;
	float b = 0.0f;
	float equation = 0.0f;
	if(usf < 500000.0f){
	    slope = LOW;
	    m = slope/125000.0f;
	    b = 1.0f;
	    equation = m * usf + b;
	    if(equation < -1.0f){
		slope = HIGH;
		m = slope/125000.0f;
		b = -3.0f;
	    }
	    equation = m * usf + b;
	}else{
	    slope = LOW;
	    m = slope/125000.0f;
	    b = 5.0f;
	    equation = m * usf + b;
	    if(equation < 0.0f){
	    	slope = HIGH;
	    	m = slope/125000.0f;
	    	b = -5.0f;
	    }
	    equation = m * usf + b;
	    if(equation > 1.0f){
		slope = LOW;
		m = slope/125000.0f;
		b = 7.0f;
	    }
	    equation = m * usf + b;
	}
	return equation;
    }
}
