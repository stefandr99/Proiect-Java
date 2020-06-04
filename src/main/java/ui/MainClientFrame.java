package ui;

import client.Player;
import entity.Chestionar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import javax.swing.border.Border;

public class MainClientFrame extends JFrame implements Runnable {
    String nume;
    Chestionar chestionar;
    PrintWriter outSocket;
    BufferedReader in;
    String serverAddress = "127.0.0.1";
    int PORT = 8100;
    Socket socket;
    String raspuns;
    String ales;
    String varAles;

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



        try {
            socket = new Socket(serverAddress, PORT);
            outSocket = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println("No server listening... " + e);
        }
    }

    @Override
    public void run() {
        meniuPrincipal();


    }

    private void meniuPrincipal() {
        this.fundal = new JLabel(new ImageIcon("./meniuPrincipal.jpg"));
        setContentPane(this.fundal);
        revalidate();
        repaint();

        this.titlu = new JLabel("FII Java");
        this.titlu.setFont(new Font("Serif", Font.BOLD, 70));
        this.titlu.setBounds(280, 50, 500, 70);
        this.titlu.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        this.titlu.setForeground(new Color(255, 128, 0));
        add(titlu);

        repaint();
        this.startJoc = new JButton("Start joc");
        this.startJoc.setFont(new Font("Serif", Font.BOLD, 30));
        this.startJoc.setBounds(225, 150, 350, 70);
        this.startJoc.setBackground(new Color(255, 204, 153));
        this.startJoc.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.startJoc.setMargin(new Insets(5, 5, 5, 5));
        add(startJoc);

        repaint();
        this.despreJoc = new JButton("Despre joc");
        this.despreJoc.setFont(new Font("Serif", Font.BOLD, 30));
        this.despreJoc.setBounds(225, 230, 350, 70);
        this.despreJoc.setBackground(new Color(255, 204, 153));
        this.despreJoc.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.despreJoc.setMargin(new Insets(5, 5, 5, 5));
        add(despreJoc);

        repaint();
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
                    outSocket.println("creare " + nume);
                    outSocket.flush();
                    raspuns = in.readLine();
                    String[] splits = raspuns.split("#");
                    cameraDeJoc(splits[0], splits[1], splits[2], splits[3], splits[4], splits[5]);
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


                despreJoc();
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

    private void cameraDeJoc(String intr, String v1, String v2, String v3, String v4, String corect) throws IOException, InterruptedException {

        this.fundal2 = new JLabel(new ImageIcon("./cameraDeJoc.jpg"));
        setContentPane(this.fundal2);
        revalidate();

        repaint();
        this.timp = new JLabel("Timp: ");
        this.timp.setFont(new Font("SansSerif", Font.BOLD, 15));
        this.timp.setBounds(30, 30, 100, 30);
        this.timp.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        add(timp);

        repaint();
        this.logo = new JLabel(new ImageIcon("./Java.jpg"));
        this.logo.setBounds(240, 0, 320, 125);
        add(logo);

        repaint();
        this.secunde = new JLabel("20");
        this.secunde.setFont(new Font("SansSerif", Font.BOLD, 15));
        this.secunde.setBounds(70, 30, 100, 30);
        this.secunde.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        add(secunde);

        repaint();
        this.intrebare = new JLabel("<html><div style='text-align: center; '>" + intr + "</div></html>");
        this.intrebare.setFont(new Font("Serif", Font.BOLD, 30));
        this.intrebare.setBounds(150, 120, 500, 80);
        this.intrebare.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        add(intrebare);

        repaint();
        this.varianta1 = new JButton("A. " + v1);
        this.varianta1.setFont(new Font("Serif", Font.BOLD, 30));
        this.varianta1.setBounds(30, 250, 350, 40);
        this.varianta1.setBackground(new Color(255, 204, 128));
        this.varianta1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.varianta1.setMargin(new Insets(5, 5, 5, 5));
        add(varianta1);

        repaint();
        this.varianta2 = new JButton("B. " + v2);
        this.varianta2.setFont(new Font("Serif", Font.BOLD, 30));
        this.varianta2.setBounds(420, 250, 350, 40);
        this.varianta2.setBackground(new Color(255, 204, 128));
        this.varianta2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(varianta2);

        repaint();
        this.varianta3 = new JButton("C. " + v3);
        this.varianta3.setFont(new Font("Serif", Font.BOLD, 30));
        this.varianta3.setBounds(30, 320, 350, 40);
        this.varianta3.setBackground(new Color(255, 204, 128));
        this.varianta3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(varianta3);

        repaint();
        this.varianta4 = new JButton("D. " + v4);
        this.varianta4.setFont(new Font("Serif", Font.BOLD, 30));
        this.varianta4.setBounds(420, 320, 350, 40);
        this.varianta4.setBackground(new Color(255, 204, 128));
        this.varianta4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(varianta4);

        varianta1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(corect.compareTo(v1) == 0)
                    varianta1.setBackground(new Color(153, 255, 51));
                else {
                    varianta1.setBackground(new Color(255, 0, 0));

                    if(v2.compareTo(corect) == 0)
                        varianta2.setBackground(new Color(153, 255, 51));
                    else if(v3.compareTo(corect) == 0)
                        varianta3.setBackground(new Color(153, 255, 51));
                    else if(v4.compareTo(corect) == 0)
                        varianta4.setBackground(new Color(153, 255, 51));
                }
            }
        });
        varianta2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(corect.compareTo(v2) == 0)
                    varianta2.setBackground(new Color(153, 255, 51));
                else {
                    varianta2.setBackground(new Color(255, 0, 0));

                    if(v1.compareTo(corect) == 0)
                        varianta1.setBackground(new Color(153, 255, 51));
                    else if(v3.compareTo(corect) == 0)
                        varianta3.setBackground(new Color(153, 255, 51));
                    else if(v4.compareTo(corect) == 0)
                        varianta4.setBackground(new Color(153, 255, 51));
                }
            }
        });
        varianta3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(corect.compareTo(v3) == 0)
                    varianta3.setBackground(new Color(153, 255, 51));
                else {
                    varianta3.setBackground(new Color(255, 0, 0));

                    if(v2.compareTo(corect) == 0)
                        varianta2.setBackground(new Color(153, 255, 51));
                    else if(v1.compareTo(corect) == 0)
                        varianta1.setBackground(new Color(153, 255, 51));
                    else if(v4.compareTo(corect) == 0)
                        varianta4.setBackground(new Color(153, 255, 51));
                }
            }
        });
        varianta4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(corect.compareTo(v4) == 0)
                    varianta4.setBackground(new Color(153, 255, 51));
                else {
                    varianta4.setBackground(new Color(255, 0, 0));

                    if(v2.compareTo(corect) == 0)
                        varianta2.setBackground(new Color(153, 255, 51));
                    else if(v3.compareTo(corect) == 0)
                        varianta3.setBackground(new Color(153, 255, 51));
                    else if(v1.compareTo(corect) == 0)
                        varianta1.setBackground(new Color(153, 255, 51));
                }
            }
        });

    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
}
