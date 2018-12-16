import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

final class WavePlay{

    public WavePlay(){}

    public static void play(Signal mySound, long us){
        try{
        SourceDataLine sdl = AudioSystem.getSourceDataLine(WaveIO.FORMAT);
        sdl.open();
        byte[] myByteArr = WaveIO.toByteArray(mySound, us);
        sdl.start();
        WaveIO.writeFully(sdl, myByteArr);
        sdl.close();
        }catch(LineUnavailableException e){
            JFrame jframe = new JFrame();
            JOptionPane.showMessageDialog(jframe, "Couldn’t open audio output.", "Attention!", JOptionPane.WARNING_MESSAGE);
        }
    }
}