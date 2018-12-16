public class Instrument extends Signal implements Musical {

    private String instrumentName;
    private Signal toneSource;
    private long envAttack;
    private long envDecay;
    private float envSustLvl;
    private long envRelease;

    public Instrument(String n, Signal t, long a, long d, float slv, long r) {
        instrumentName = n;
        toneSource = t;
        envAttack = a;
        envDecay = d;
        envSustLvl = slv;
        envRelease = r;
    }

    public float getSample(long us) {
        return 0.0f;
    }

    @Override
    public void setNote(long d, int hz) {

    }

    @Override
    public Instrument getInstrument() {
        return null;
    }
}
