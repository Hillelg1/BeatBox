import java.awt.*;
import javax.swing.*;
import javax.sound.midi.*;
import java.awt.event.*;
import java.util.*;

public class BeatBox {
    private static final int START_EVENT = 176;
    private static final int NOTE_ON_EVENT = 144;
    private static final int NOTE_OFF_EVENT = 128;

    private final JPanel mainPanel;
    private final ArrayList<JCheckBox> checkBoxList;
    private Sequencer sequencer;
    private Sequence sequence;
    private Track track;
    private final JFrame theFrame;

    private static final String[] INSTRUMENT_NAMES = {
            "Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal", "Hand Clap", "High Tom",
            "High Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap", "Low-Mid Tom", "High Agogo",
            "Open Hi Conga"
    };
    private static final int[] INSTRUMENTS = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};

    public static void main(String[] args) {
        new BeatBox().buildGUI();
    }

    public BeatBox() {
        mainPanel = new JPanel();
        checkBoxList = new ArrayList<>();
        theFrame = new JFrame("Cyber BeatBox");
    }

    public void buildGUI() {
        // GUI initialization code remains similar, but you may want to migrate to JavaFX for a more modern look.
        // ...

        JButton start = new JButton("Start");
        start.addActionListener(new MyStartListener());
        buttonBox.add(start);

        // ... (Other buttons)

        for (int i = 0; i < 256; i++) {
            JCheckBox c = new JCheckBox();
            c.setSelected(false);
            checkBoxList.add(c);
            mainPanel.add(c);
        }
        setUpMidi();

        theFrame.setBounds(50, 50, 300, 300);
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.pack();
        theFrame.setVisible(true);
    }

    // Other methods remain similar...

    public class MyStartListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            buildTrackAndStart();
        }
    }

    // Other inner classes...

    private void makeTracks(int[] list) {
        for (int i = 0; i < 16; i++) {
            int key = list[i];

            if (key != 0) {
                track.add(makeEvent(NOTE_ON_EVENT, 9, key, 100, i));
                track.add(makeEvent(NOTE_OFF_EVENT, 9, key, 100, i + 1));
            }
        }
    }

    private MidiEvent makeEvent(int command, int channel, int one, int two, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(command, channel, one, two);
            event = new MidiEvent(a, tick);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        return event;
    }
}
