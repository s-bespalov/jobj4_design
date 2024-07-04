package ru.job4j.ood.isp.example3;

public class TapeRecorder implements MediaPlayer {
    @Override
    public void playVideo() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void playAudio() {
        System.out.println("Playing audio");
    }
}
