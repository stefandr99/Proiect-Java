package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.border.Border;

public class MainClientFrame extends JFrame {
    JLabel logo;

    /* MENIU PRINCIPAL + DESCRIERE*/
    JLabel fundal;
    JLabel titlu;
    JButton startJoc;
    JButton despreJoc;
    JButton inchide;
    JLabel descriere;
    JButton inapoi;

    /* CAMERA DE JOC */
    JLabel fundal2;
    JLabel timp;
    JLabel secunde;
    JLabel intrebare;
    JButton varianta1;
    JButton varianta2;
    JButton varianta3;
    JButton varianta4;

    public MainClientFrame() throws IOException, InterruptedException {
        super("FII Java");
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(820, 440);
        meniuPrincipal();
    }

    private void meniuPrincipal() {
        this.fundal = new JLabel(new ImageIcon("./meniuPrincipal.jpg"));
        setContentPane(this.fundal);
        revalidate();

        this.titlu = new JLabel("FII Java");
        this.titlu.setFont(new Font("Serif", Font.BOLD, 70));
        this.titlu.setBounds(280, 50, 500, 70);
        this.titlu.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        this.titlu.setForeground(new Color(255, 128, 0));
        add(titlu);

        this.startJoc = new JButton("Start joc");
        this.startJoc.setFont(new Font("Serif", Font.BOLD, 30));
        this.startJoc.setBounds(225, 150, 350, 70);
        this.startJoc.setBackground(new Color(255, 204, 153));
        this.startJoc.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.startJoc.setMargin(new Insets(5, 5, 5, 5));
        add(startJoc);

        this.despreJoc = new JButton("Despre joc");
        this.despreJoc.setFont(new Font("Serif", Font.BOLD, 30));
        this.despreJoc.setBounds(225, 230, 350, 70);
        this.despreJoc.setBackground(new Color(255, 204, 153));
        this.despreJoc.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.despreJoc.setMargin(new Insets(5, 5, 5, 5));
        add(despreJoc);

        this.inchide = new JButton("Iesire");
        this.inchide.setFont(new Font("Serif", Font.BOLD, 30));
        this.inchide.setBounds(225, 310, 350, 70);
        this.inchide.setBackground(new Color(255, 204, 153));
        this.inchide.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.inchide.setMargin(new Insets(5, 5, 5, 5));
        add(inchide);


        startJoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(titlu);
                remove(startJoc);
                remove(despreJoc);
                remove(inchide);
                remove(fundal);
                revalidate();
                repaint();


                try {
                    cameraDeJoc();
                } catch (InterruptedException | IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        despreJoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(titlu);
                remove(startJoc);
                remove(despreJoc);
                remove(inchide);
                remove(fundal);
                revalidate();
                repaint();


                try {
                    Thread.sleep(50);
                    despreJoc();
                } catch (InterruptedException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        inchide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainClientFrame.super.dispose();
            }
        });
    }



    private void despreJoc() {
        this.fundal = new JLabel(new ImageIcon("./cameraDeJoc.jpg"));
        setContentPane(this.fundal);
        revalidate();

        this.titlu = new JLabel("FII Java");
        this.titlu.setFont(new Font("Serif", Font.BOLD, 70));
        this.titlu.setBounds(280, 20, 500, 70);
        this.titlu.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        add(titlu);

        this.inapoi = new JButton("Inapoi la meniu");
        this.inapoi.setFont(new Font("Serif", Font.BOLD, 15));
        this.inapoi.setBounds(30, 30, 120, 30);
        this.inapoi.setBackground(new Color(255, 128, 0));
        this.inapoi.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.inapoi.setMargin(new Insets(5, 5, 5, 5));
        add(inapoi);

        this.descriere = new JLabel("<html><div style='text-align: center; '>Vrei sa iti testezi abilitatile in Java sau esti dornic sa cunosti mai multe despre " +
                "aceasta platforma? FII Java este jocul potrivit pentru tine. Aceasta aplicatie are ca scop atat evaluarea " +
                "cunostintelor in Java, cat si aprofundarea acestora. Regulile jocului sunt simple: " +
                "<ul><li>Fiecare jucator va avea de raspuns la 10 intrebari;</li>" +
                "<li>Jocul se va desfasura secvential, adica fiecare trebuie sa isi astepte randul;</li>" +
                "<li>Cine ajunge primul la 10 raspunsuri corecte castiga.</li></ul>" +
                "Succes!</div></html>");
        this.descriere.setFont(new Font("Serif", Font.BOLD, 20));
        this.descriere.setBounds(100, 100, 600, 300);
        this.descriere.setForeground(new Color(255, 128, 0));
        this.descriere.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        add(descriere);

        inapoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(titlu);
                remove(inapoi);
                remove(descriere);
                remove(fundal);
                revalidate();
                repaint();

                meniuPrincipal();
            }
        });
    }

    private void cameraDeJoc() throws IOException, InterruptedException {
        this.fundal2 = new JLabel(new ImageIcon("./cameraDeJoc.jpg"));
        setContentPane(this.fundal2);
        revalidate();

        this.timp = new JLabel("Timp: ");
        this.timp.setFont(new Font("SansSerif", Font.BOLD, 15));
        this.timp.setBounds(30, 30, 100, 30);
        this.timp.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        add(timp);

        this.logo = new JLabel(new ImageIcon("./Java.jpg"));
        this.logo.setBounds(240, 0, 320, 125);
        add(logo);

        this.secunde = new JLabel("20");
        this.secunde.setFont(new Font("SansSerif", Font.BOLD, 15));
        this.secunde.setBounds(70, 30, 100, 30);
        this.secunde.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        add(secunde);

        this.intrebare = new JLabel("<html><div style='text-align: center; '>Ce limbaj de programare invatam?</div></html>");
        this.intrebare.setFont(new Font("Serif", Font.BOLD, 30));
        this.intrebare.setBounds(150, 120, 500, 80);
        this.intrebare.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        add(intrebare);

        this.varianta1 = new JButton("A. C");
        this.varianta1.setFont(new Font("Serif", Font.BOLD, 30));
        this.varianta1.setBounds(30, 250, 350, 40);
        this.varianta1.setBackground(new Color(230, 92, 0));
        this.varianta1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.varianta1.setMargin(new Insets(5, 5, 5, 5));
        add(varianta1);

        this.varianta2 = new JButton("B. Java");
        this.varianta2.setFont(new Font("Serif", Font.BOLD, 30));
        this.varianta2.setBounds(420, 250, 350, 40);
        this.varianta2.setBackground(new Color(230, 92, 0));
        this.varianta2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(varianta2);

        this.varianta3 = new JButton("C. C++");
        this.varianta3.setFont(new Font("Serif", Font.BOLD, 30));
        this.varianta3.setBounds(30, 320, 350, 40);
        this.varianta3.setBackground(new Color(230, 92, 0));
        this.varianta3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(varianta3);

        this.varianta4 = new JButton("D. Javascript");
        this.varianta4.setFont(new Font("Serif", Font.BOLD, 30));
        this.varianta4.setBounds(420, 320, 350, 40);
        this.varianta4.setBackground(new Color(230, 92, 0));
        this.varianta4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(varianta4);


    }
}