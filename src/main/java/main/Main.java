package main;

import ui.MainClientFrame;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        MainClientFrame frame = new MainClientFrame();
        frame.setVisible(true);
        new Thread(frame).start();
    }
}
