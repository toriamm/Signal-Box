public class Beep{
    public static void main(String[] args){
        Triangle tr;
        try {
            String waveform = args[0];
            int pitch = Integer.parseInt(args[1]);
            double dr = Double.parseDouble(args[2]);
            dr *= 1000000;
            long duration = (long)dr;
            Signal sig;

            if (waveform.equalsIgnoreCase("square"))
                sig = new Square();
            else if (waveform.equalsIgnoreCase("triangle"))
                tr = new Triangle();
            else if (waveform.equalsIgnoreCase("sawtooth"))
                sig = new Sawtooth();
            else if (waveform.equalsIgnoreCase("vicki"))
                sig = new VickiMartinez();
            else
                sig = new VickiMartinez();

            Modulator mod = new Modulator(tr);
            Envelope env = new Envelope(mod, 2000000, 100000, 0.6f, 200000);
            mod.setPeriod(pitch);
            env.setDuration(duration);
            WavePlay.play(env, duration);
        } catch(Exception e){
            System.out.println("You messed up lol");
            e.printStackTrace();
        }
    }
}