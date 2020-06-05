package client;

import entity.Chestionar;
import server.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Player extends JFrame implements Runnable {
    String nume;
    int maxJucatori;
    PrintWriter outSocket;
    BufferedReader in;
    String serverAddress = "127.0.0.1";
    int PORT = 8100;
    Socket socket;
    String raspuns;
    int rand;
    boolean inJoc = false;
    int puncte = 0;

    JLabel logo;

    /* MENIU PRINCIPAL + DESCRIERE*/
    JLabel fundal;
    JLabel titlu;
    JButton creeazaJoc;
    JButton alaturare;
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

    /* CREEAZA JOC */
    JLabel numeLabel;
    JTextField numeText;
    JLabel nrJucatori;
    JTextField nrJucatoriText;
    JButton creeaza;

    /* ALATURA-TE*/
    JButton alatura;
    JLabel idCameraLabel;
    JTextField idCameraText;

    /* REZULTAT */
    JLabel mesajFinal1;
    JLabel mesajFinal2;

    public Player() throws IOException, InterruptedException {
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

        while(true) {
            while(Server.x != rand || !inJoc) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                genereazaRunda();
                Thread.sleep(300);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        }
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
        this.creeazaJoc = new JButton("Creeaza joc");
        this.creeazaJoc.setFont(new Font("Serif", Font.BOLD, 30));
        this.creeazaJoc.setBounds(225, 130, 350, 55);
        this.creeazaJoc.setBackground(new Color(255, 204, 153));
        this.creeazaJoc.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.creeazaJoc.setMargin(new Insets(5, 5, 5, 5));
        add(creeazaJoc);

        repaint();
        this.alaturare = new JButton("Alatura-te unui joc");
        this.alaturare.setFont(new Font("Serif", Font.BOLD, 30));
        this.alaturare.setBounds(225, 195, 350, 55);
        this.alaturare.setBackground(new Color(255, 204, 153));
        this.alaturare.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.alaturare.setMargin(new Insets(5, 5, 5, 5));
        add(alaturare);

        repaint();
        this.despreJoc = new JButton("Despre joc");
        this.despreJoc.setFont(new Font("Serif", Font.BOLD, 30));
        this.despreJoc.setBounds(225, 260, 350, 55);
        this.despreJoc.setBackground(new Color(255, 204, 153));
        this.despreJoc.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.despreJoc.setMargin(new Insets(5, 5, 5, 5));
        add(despreJoc);

        repaint();
        this.inchide = new JButton("Iesire");
        this.inchide.setFont(new Font("Serif", Font.BOLD, 30));
        this.inchide.setBounds(225, 325, 350, 55);
        this.inchide.setBackground(new Color(255, 204, 153));
        this.inchide.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.inchide.setMargin(new Insets(5, 5, 5, 5));
        add(inchide);


        creeazaJoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(titlu);
                remove(creeazaJoc);
                remove(despreJoc);
                remove(inchide);
                remove(fundal);
                revalidate();
                repaint();

                creeazaJoc();
            }
        });

        alaturare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(titlu);
                remove(creeazaJoc);
                remove(despreJoc);
                remove(inchide);
                remove(fundal);
                revalidate();
                repaint();

                outSocket.println("show");
                outSocket.flush();
                try {
                    alaturaTe();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        despreJoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(titlu);
                remove(creeazaJoc);
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
                System.exit(0);
            }
        });
    }

    private void alaturaTe() throws IOException {
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
        this.inapoi.setBackground(new Color(255, 204, 153));
        this.inapoi.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.inapoi.setMargin(new Insets(5, 5, 5, 5));
        add(inapoi);


        String rasp = in.readLine();
        //rasp = rasp.substring(0, rasp.length() - 19);
        JLabel jocuriDisp = new JLabel(rasp);
        jocuriDisp.setFont(new Font("Serif", Font.BOLD, 20));
        jocuriDisp.setBounds(100, 90, 600, 250);
        jocuriDisp.setForeground(new Color(255, 128, 0));
        jocuriDisp.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        add(jocuriDisp);

        if(!rasp.startsWith("Nu exista")) {
            repaint();
            this.numeLabel = new JLabel("Introduceti numele:");
            this.numeLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
            this.numeLabel.setBounds(60, 320, 200, 20);
            this.numeLabel.setForeground(new Color(255, 128, 0));
            this.numeLabel.setCursor(new Cursor(Cursor.TEXT_CURSOR));
            add(numeLabel);

            repaint();
            this.numeText = new JTextField(100);
            this.numeText.setFont(new Font("SansSerif", Font.BOLD, 20));
            this.numeText.setBounds(250, 320, 150, 25);
            this.numeText.setCursor(new Cursor(Cursor.TEXT_CURSOR));
            add(numeText);

            repaint();
            this.idCameraLabel = new JLabel("Introduceti id-ul camerei:");
            this.idCameraLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
            this.idCameraLabel.setBounds(420, 320, 500, 20);
            this.idCameraLabel.setForeground(new Color(255, 128, 0));
            this.idCameraLabel.setCursor(new Cursor(Cursor.TEXT_CURSOR));
            add(idCameraLabel);

            repaint();
            this.idCameraText = new JTextField(100);
            this.idCameraText.setFont(new Font("SansSerif", Font.BOLD, 20));
            this.idCameraText.setBounds(660, 320, 50, 25);
            this.idCameraText.setCursor(new Cursor(Cursor.TEXT_CURSOR));
            add(idCameraText);

            repaint();
            this.alatura = new JButton("Alatura-te!");
            this.alatura.setFont(new Font("Serif", Font.BOLD, 30));
            this.alatura.setBounds(320, 355, 180, 40);
            this.alatura.setBackground(new Color(255, 204, 153));
            this.alatura.setCursor(new Cursor(Cursor.HAND_CURSOR));
            this.alatura.setMargin(new Insets(5, 5, 5, 5));
            add(alatura);

            alatura.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nume = numeText.getText();
                    String nr = idCameraText.getText();
                    int idCam = Integer.parseInt(nr);

                    outSocket.println("alatura " + nume + " " + idCam);
                    outSocket.flush();
                    String randulMeu;
                    try {
                        randulMeu = in.readLine();
                        rand = Integer.parseInt(randulMeu);
                        inJoc = true;

                        remove(fundal);
                        remove(titlu);
                        remove(inapoi);
                        remove(jocuriDisp);
                        remove(numeLabel);
                        remove(numeText);
                        remove(idCameraLabel);
                        remove(idCameraText);
                        remove(alatura);
                        revalidate();
                        repaint();

                        asteapta();
                    } catch (IOException | InterruptedException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
        }

        inapoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(fundal);
                remove(titlu);
                remove(inapoi);
                remove(jocuriDisp);
                if(numeLabel != null) {
                    remove(fundal);
                    remove(titlu);
                    remove(inapoi);
                    remove(jocuriDisp);
                }
                revalidate();
                repaint();

                meniuPrincipal();
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

    private void creeazaJoc() {
        this.fundal2 = new JLabel(new ImageIcon("./cameraDeJoc.jpg"));
        setContentPane(this.fundal2);
        revalidate();

        this.titlu = new JLabel("FII Java");
        this.titlu.setFont(new Font("Serif", Font.BOLD, 70));
        this.titlu.setBounds(280, 20, 500, 70);
        this.titlu.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        add(titlu);

        this.inapoi = new JButton("Inapoi la meniu");
        this.inapoi.setFont(new Font("Serif", Font.BOLD, 15));
        this.inapoi.setBounds(30, 30, 120, 30);
        this.inapoi.setBackground(new Color(255, 204, 153));
        this.inapoi.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.inapoi.setMargin(new Insets(5, 5, 5, 5));
        add(inapoi);

        repaint();
        this.numeLabel = new JLabel("Introduceti numele:");
        this.numeLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
        this.numeLabel.setBounds(150, 130, 500, 30);
        this.numeLabel.setForeground(new Color(255, 128, 0));
        this.numeLabel.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        add(numeLabel);

        repaint();
        this.numeText = new JTextField(100);
        this.numeText.setFont(new Font("SansSerif", Font.BOLD, 25));
        this.numeText.setBounds(390, 130, 200, 30);
        this.numeText.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        add(numeText);

        repaint();
        this.nrJucatori = new JLabel("Introduceti numarul de jucatori:");
        this.nrJucatori.setFont(new Font("SansSerif", Font.BOLD, 25));
        this.nrJucatori.setBounds(150, 200, 500, 30);
        this.nrJucatori.setForeground(new Color(255, 128, 0));
        this.nrJucatori.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        add(nrJucatori);

        repaint();
        this.nrJucatoriText = new JTextField(100);
        this.nrJucatoriText.setFont(new Font("SansSerif", Font.BOLD, 25));
        this.nrJucatoriText.setBounds(530, 200, 50, 30);
        this.nrJucatoriText.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        add(nrJucatoriText);

        repaint();
        this.creeaza = new JButton("Creeaza!");
        this.creeaza.setFont(new Font("Serif", Font.BOLD, 30));
        this.creeaza.setBounds(320, 300, 180, 40);
        this.creeaza.setBackground(new Color(255, 204, 153));
        this.creeaza.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.creeaza.setMargin(new Insets(5, 5, 5, 5));
        add(creeaza);

        creeaza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nume = numeText.getText();
                String nr = nrJucatoriText.getText();
                maxJucatori = Integer.parseInt(nr);

                outSocket.println("creare " + nume + " " + maxJucatori);
                outSocket.flush();
                String randulMeu;
                try {
                    randulMeu = in.readLine();
                    rand = Integer.parseInt(randulMeu);
                    inJoc = true;

                    remove(fundal2);
                    remove(titlu);
                    remove(numeLabel);
                    remove(numeText);
                    remove(nrJucatori);
                    remove(nrJucatoriText);
                    remove(creeaza);
                    revalidate();
                    repaint();

                    asteapta();
                } catch (IOException | InterruptedException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        inapoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(titlu);
                remove(inapoi);
                remove(numeLabel);
                remove(fundal2);
                remove(numeText);
                remove(nrJucatori);
                remove(nrJucatoriText);
                remove(creeaza);
                revalidate();
                repaint();

                meniuPrincipal();
            }
        });
    }

    private void asteapta() throws IOException, InterruptedException {
        this.fundal2 = new JLabel(new ImageIcon("./cameraDeJoc.jpg"));
        setContentPane(this.fundal2);
        revalidate();

        this.titlu = new JLabel("FII Java");
        this.titlu.setFont(new Font("Serif", Font.BOLD, 70));
        this.titlu.setBounds(280, 20, 500, 70);
        this.titlu.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        add(titlu);

        repaint();
        this.numeLabel = new JLabel("Va rugam asteptati!");
        this.numeLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
        this.numeLabel.setBounds(300, 180, 600, 30);
        this.numeLabel.setForeground(new Color(255, 128, 0));
        this.numeLabel.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        add(numeLabel);
        revalidate();
        repaint();

        repaint();
        this.creeaza = new JButton("Activeaza!");
        this.creeaza.setFont(new Font("Serif", Font.BOLD, 30));
        this.creeaza.setBounds(320, 300, 180, 40);
        this.creeaza.setBackground(new Color(255, 204, 153));
        this.creeaza.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.creeaza.setMargin(new Insets(5, 5, 5, 5));
        add(creeaza);

        creeaza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    raspuns = in.readLine();
                    String[] splits = raspuns.split("#");
                    cameraDeJoc(splits[0], splits[1], splits[2], splits[3], splits[4], splits[5]);
                } catch (IOException | InterruptedException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    public void genereazaRunda() throws IOException, InterruptedException {
        Server.x = 0;
        raspuns = in.readLine();
        if(raspuns.startsWith("pierdut")) {
            String[] splits = raspuns.split(" ");
            rezultat(false, splits[1]);
        }
        else {
            String[] splits = raspuns.split("#");
            cameraDeJoc(splits[0], splits[1], splits[2], splits[3], splits[4], splits[5]);
        }
    }

    private void cameraDeJoc(String intr, String v1, String v2, String v3, String v4, String corect) throws IOException, InterruptedException {

        this.fundal2 = new JLabel(new ImageIcon("./cameraDeJoc.jpg"));
        setContentPane(this.fundal2);
        revalidate();

        repaint();
        this.timp = new JLabel("Puncte: ");
        this.timp.setFont(new Font("SansSerif", Font.BOLD, 15));
        this.timp.setBounds(30, 30, 100, 30);
        this.timp.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        add(timp);

        repaint();
        this.logo = new JLabel(new ImageIcon("./Java.jpg"));
        this.logo.setBounds(240, 0, 320, 125);
        add(logo);

        repaint();
        this.secunde = new JLabel(String.valueOf(puncte));
        this.secunde.setFont(new Font("SansSerif", Font.BOLD, 15));
        this.secunde.setBounds(100, 30, 100, 30);
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
                if(corect.compareTo(v1) == 0) {
                    varianta1.setBackground(new Color(153, 255, 51));
                    puncte++;
                    secunde.setText(String.valueOf(puncte));
                }
                else {
                    varianta1.setBackground(new Color(255, 0, 0));

                    if(v1.compareTo(corect) == 0)
                        varianta2.setBackground(new Color(153, 255, 51));
                    else if(v3.compareTo(corect) == 0)
                        varianta3.setBackground(new Color(153, 255, 51));
                    else if(v4.compareTo(corect) == 0)
                        varianta4.setBackground(new Color(153, 255, 51));
                }
                if(puncte == 5) {
                    outSocket.println("victorie");
                    outSocket.flush();

                    rezultat(true, null);
                }
                else
                    outSocket.println("teminat");
                outSocket.flush();
                Server.x = rand;
                //genereazaRunda();
            }
        });


        varianta2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(corect.compareTo(v2) == 0) {
                    varianta2.setBackground(new Color(153, 255, 51));
                    puncte++;
                    secunde.setText(String.valueOf(puncte));
                }
                else {
                    varianta2.setBackground(new Color(255, 0, 0));

                    if(v1.compareTo(corect) == 0)
                        varianta1.setBackground(new Color(153, 255, 51));
                    else if(v3.compareTo(corect) == 0)
                        varianta3.setBackground(new Color(153, 255, 51));
                    else if(v4.compareTo(corect) == 0)
                        varianta4.setBackground(new Color(153, 255, 51));
                }
                if(puncte == 5) {
                    outSocket.println("victorie");
                    outSocket.flush();
                    rezultat(true, null);
                }
                else
                    outSocket.println("teminat");
                outSocket.flush();
                Server.x = rand;
                //genereazaRunda();
            }
        });
        varianta3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(corect.compareTo(v3) == 0) {
                    varianta3.setBackground(new Color(153, 255, 51));
                    puncte++;
                    secunde.setText(String.valueOf(puncte));
                }
                else {
                    varianta3.setBackground(new Color(255, 0, 0));

                    if(v2.compareTo(corect) == 0)
                        varianta2.setBackground(new Color(153, 255, 51));
                    else if(v1.compareTo(corect) == 0)
                        varianta1.setBackground(new Color(153, 255, 51));
                    else if(v4.compareTo(corect) == 0)
                        varianta4.setBackground(new Color(153, 255, 51));
                }
                if(puncte == 5) {
                    outSocket.println("victorie");
                    outSocket.flush();
                    rezultat(true, null);
                }
                else
                    outSocket.println("teminat");
                outSocket.flush();
                Server.x = rand;
                //genereazaRunda();
            }
        });
        varianta4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(corect.compareTo(v4) == 0) {
                    varianta4.setBackground(new Color(153, 255, 51));
                    puncte++;
                    secunde.setText(String.valueOf(puncte));
                }
                else {
                    varianta4.setBackground(new Color(255, 0, 0));

                    if(v2.compareTo(corect) == 0)
                        varianta2.setBackground(new Color(153, 255, 51));
                    else if(v3.compareTo(corect) == 0)
                        varianta3.setBackground(new Color(153, 255, 51));
                    else if(v1.compareTo(corect) == 0)
                        varianta1.setBackground(new Color(153, 255, 51));
                }
                if(puncte == 5) {
                    outSocket.println("victorie");
                    outSocket.flush();
                    rezultat(true, null);
                }
                else
                    outSocket.println("teminat");
                outSocket.flush();
                Server.x = rand;
            }
        });


    }

    private void rezultat(boolean winner, String castigator) {

        if(winner) {
            this.fundal2 = new JLabel(new ImageIcon("./confetti.jpg"));
        }
        else this.fundal2 = new JLabel(new ImageIcon("./keep_going.jpg"));
        setContentPane(this.fundal2);
        revalidate();

        repaint();
        this.titlu = new JLabel("FII Java");
        this.titlu.setFont(new Font("Serif", Font.BOLD, 70));
        this.titlu.setBounds(280, 20, 500, 70);
        this.titlu.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        add(titlu);
        revalidate();

        repaint();
        if(winner) {
            mesajFinal1 = new JLabel("Felicitari!");
            mesajFinal2 = new JLabel("Esti castigatorul FII Java!");
        }
        else {
            mesajFinal1 = new JLabel("Ne pare rau!");
            mesajFinal2 = new JLabel("<html>Din pacate castigatorul este <br>" + castigator + "</html>");
        }
        this.mesajFinal1.setFont(new Font("Serif", Font.BOLD, 70));
        this.mesajFinal1.setBounds(250, 150, 800, 70);
        if(!winner) this.mesajFinal1.setBounds(220, 150, 800, 70);
        this.mesajFinal1.setForeground(new Color(255, 128, 0));
        this.mesajFinal1.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        add(mesajFinal1);
        repaint();
        revalidate();
        this.mesajFinal2.setFont(new Font("Serif", Font.BOLD, 60));
        this.mesajFinal2.setBounds(70, 250, 800, 70);
        if(!winner) this.mesajFinal2.setBounds(60, 250, 800, 150);
        this.mesajFinal2.setForeground(new Color(255, 128, 0));
        this.mesajFinal2.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        add(mesajFinal2);
        repaint();
        revalidate();
    }


    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
}
