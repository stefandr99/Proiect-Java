package main;

import ui.MainClientFrame;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        new MainClientFrame().setVisible(true);
    }
}
