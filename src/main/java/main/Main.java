package main;

import client.Player;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Player frame = new Player();
        frame.setVisible(true);
        new Thread(frame).start();
    }
}
