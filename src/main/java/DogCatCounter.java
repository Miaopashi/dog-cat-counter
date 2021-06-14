import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class DogCatCounter extends JFrame {
    // 定义属性
    private final JFrame frame = new JFrame();
    private final JPanel panel1 = new JPanel();
    private final JPanel panel2 = new JPanel();
    private final JPanel panel3 = new JPanel();
    private final JPanel panel4 = new JPanel();
    private final JPanel panelAll = new JPanel(new GridLayout(4, 0));
    private final JTextField text1 = new JTextField(10);
    private final JTextField text2 = new JTextField(10);
    private final JButton start1 = new JButton("Start");
    private final JButton start2 = new JButton("Start");
    private final JButton stop1 = new JButton("stop");
    private final JButton stop2 = new JButton("stop");
    private int count1 = 0;
    private int count2 = 0;
    private Boolean Flag1 = false;
    private Boolean Flag2 = false;

    private void AppearanceSetting() {
        frame.setSize(1200, 310);
        frame.setLayout(new FlowLayout());
    }


    private void FrameSetting() {
        text1.setEditable(false);
        panel1.add(text1);
        panel1.add(start1);
        panel1.add(stop1);
        panel1.setLayout(new FlowLayout());
        text2.setEditable(false);
        panel2.add(text2);
        panel2.add(start2);
        panel2.add(stop2);
        panel2.setLayout(new FlowLayout());
        panelAll.add(panel3);
        panelAll.add(panel1);
        panelAll.add(panel4);
        panelAll.add(panel2);
        frame.add(panelAll);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    private void Jb1Listener1() {
        start1.addActionListener(e -> {

            Flag1 = true;

            new Thread(() -> {
                while (true) {

                    if (Flag1) {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        count1++;
                        SwingUtilities.invokeLater(() -> {
                            // TODO Auto-generated method stub
                            if (count1 % 10 == 0) {
                                panel3.add(new JLabel(new ImageIcon(Objects.requireNonNull(getClass()
                                        .getResource("/img/cat.png")))));
                                frame.validate();
                                frame.repaint();
                            }
                            text1.setText(String.valueOf(count1));
                        });
                    }
                }

            }).start();
        });
    }


    private void Jb2Listener1() {
        stop1.addActionListener(e -> Flag1 = false);
    }


    private void Jb1Listener2() {
        start2.addActionListener(e -> {

            Flag2 = true;

            new Thread(() -> {
                while (true) {

                    if (Flag2) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        count2++;
                        SwingUtilities.invokeLater(() -> {
                            // TODO Auto-generated method stub
                            if (count2 % 10 == 0) {
                                panel4.add(new JLabel(new ImageIcon(Objects.requireNonNull(getClass()
                                        .getResource("/img/dog.png")))));
                                frame.validate();
                                frame.repaint();
                            }
                            text2.setText(String.valueOf(count2));
                        });
                    }
                }

            }).start();
        });
    }


    private void Jb2Listener2() {
        stop2.addActionListener(e -> Flag2 = false);
    }


    public void Demo() {
        AppearanceSetting();
        FrameSetting();
        Jb1Listener1();
        Jb2Listener1();
        Jb1Listener2();
        Jb2Listener2();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new DogCatCounter().Demo());

    }
}
