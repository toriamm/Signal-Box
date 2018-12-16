import java.util.ArrayList;
import java.util.LinkedList;

public class SequenceTrack extends Signal implements Tracked {

    private LinkedList<Note> track = new LinkedList<Note>();
    private Instrument ins;
    private long trackL;

    public SequenceTrack(Instrument instrument) {
        ins = instrument;
    }

    public float getSample(long us) {
        Note[] array = getAllAt(us);
        float amplitude = 0.0f;
        for (Note n : array) {
            n.configure(ins);
            amplitude += n.getSample(ins, us);
        }
        return amplitude;
    }

    @Override
    public void add(long p, long d, int hz) {
        Note note = new Note(p, d, hz);
        track.add(note);
        if (trackL < (p + d)) {
            trackL = p + d;
        }
    }

    @Override
    public Note[] getAllAt(long us) {
        ArrayList<Note> noteErAList = new ArrayList<Note>();
        for (Note n : track) {
            if(n.isInside(us)) {
                noteErAList.add(n);
            }
        }
        Note[] noteErA = new Note[noteErAList.size()];
        int index = 0;
        for (Note note : noteErA) {
            noteErA[index] = note;
            index++;
        }
        if (noteErA[0] == null) {
            noteErA = new Note[0];
        }
        return noteErA;
    }

    @Override
    public long getLength() {
        return trackL;
    }

    @Override
    public void remove (Note n) {
        if(track.contains(n)) {
            track.remove(n);
        }
    }

    @Override
    public void removeAllAt(long us) {
        Note[] array = getAllAt(us);
        for (Note n : array) {
            if (n.isInside(us)) {
                track.remove(n);
            }
        }
    }

    @Override
    public Instrument getInstrument() {
        return ins;
    }
}
