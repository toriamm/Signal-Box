public class Envelope extends Signal {
    Signal signal;
    long attack;
    long decay;
    float sustainLvl;
    long release;
    private long sustainPeriod;
    private long envelopeDuration;

    public Envelope(Signal sig, long A, long D, float S, long R) {
        signal = sig;
        attack = A;
        decay = D;
        sustainLvl = S;
        release = R;
    }

    public void setDuration(long myDur) {
        envelopeDuration = myDur;
        sustainPeriod = envelopeDuration - attack - decay - release;
        if (sustainPeriod < 0) {
            throw new IllegalArgumentException("Invalid duration: " + sustainPeriod);
        }
    }

    public float getSample(long us) {

//        OLD CODE
//        float x = (float) us/1000000.0f;
//        float attackT = (float)attack/1000000.0f;
//        float decayT = (float)decay/1000000.0f;
//        float sustainT = (float)sustainPeriod/1000000.0f;
//        float releaseT = (float)release/1000000.0f;
//        float m = 1.0f/attackT;
//        float b = 0.0f;
//        float y;
//
//        if (x > attackT) {
//            m = - (1.0f - sustain) / decayT;
//            b = m * (x - attackT) + 1.0f;
//        }
//        if (x > (attackT + decayT)) {
//            m = 0.0f;
//            b = m * (x - (attackT - decayT)) + sustain;
//        }
//        if (x > (attackT + decayT + sustainT)) {
//            m = - sustain / releaseT;
//            b = m * (x - (attackT - decayT - sustainT)) + sustain;
//        }
//        y = m * x + b;
//        return y;
//        OLD CODE

        float y;
        float rVal = signal.getSample(us);

        if(us < attack){
            y = (float)us / (float)attack;
            y *= rVal;
            return y;
        } else if(us < (attack + decay)){
            us -= attack;
            y = (float)us / (float)decay;
            y *= sustainLvl;
            y *= -1.0f;
            y += 1.0f;
            y *= rVal;
            return y;
        } else if(us < (attack + decay + sustainPeriod)){
            y = sustainLvl * rVal;
            return y;
        } else if(us < (attack + decay + sustainPeriod + release)){
            us -= (attack + decay + sustainPeriod);
            y = (float)us / (float)release;
            y = (float)(Math.pow((double)y, 2.0));
            y *= -1.0f;
            y += 1.0f;
            y *= rVal;
            return y;
        } else{
            return 0.0f;
        }
    }
}