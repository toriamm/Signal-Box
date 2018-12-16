import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Assumes you've completed the assignment and sends me signal tests to grade
 */
public class Test {

    public static void main(String[] args) throws IOException {

        // Create signals
        Signal signal = new Square();
        Envelope envelope = new Envelope(signal, 2000000, 1000000, 0.6f, 2000000);
        envelope.setDuration(11000000);
        System.out.println("Signal and Envelope created.");

        // Plot
        PrintStream ps = new PrintStream(new FileOutputStream(new File("testEnvelope.csv")));
        for(int i = 0; i < (Inherit.MICROSECONDS_IN_A_SECOND*15); i += 100000) {

            // Sample
            float ex = envelope.getSample(i);

            // What time is this in seconds?
            float sec = (float)i;
            sec /= ((float)Inherit.MICROSECONDS_IN_A_SECOND);

            ps.println(sec+","+ex+";");
        }

        // Finish
        ps.close();
    }
}