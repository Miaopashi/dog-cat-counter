import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class DogCatCounter extends JFrame {
    private final JFrame frame = new JFrame();  // 主窗口画板
    private final JPanel panel1 = new JPanel();  // 窗口组件
    private final JPanel panel2 = new JPanel();
    private final JPanel panel3 = new JPanel();
    private final JPanel panel4 = new JPanel();
    private final JPanel panelAll = new JPanel(new GridLayout(4, 0));  // 控制窗口子组件的布局
    private final JTextField text1 = new JTextField(10);  // 计数文本
    private final JTextField text2 = new JTextField(10);
    private final JButton start1 = new JButton("Start");  // 开始按钮
    private final JButton start2 = new JButton("Start");
    private final JButton stop1 = new JButton("stop");  // 暂停按钮
    private final JButton stop2 = new JButton("stop");
    private int count1 = 0;  // 计数比变量
    private int count2 = 0;
    private Boolean Flag1 = false;  // 用于线程间同步
    private Boolean Flag2 = false;

    private void AppearanceSetting() {
        // 设置主窗口大小
        frame.setSize(1200, 310);
        frame.setLayout(new FlowLayout());
    }


    private void FrameSetting() {
        // 初始化计数器1
        text1.setEditable(false);
        panel1.add(text1);
        panel1.add(start1);
        panel1.add(stop1);
        panel1.setLayout(new FlowLayout());
        // 初始化计数器2
        text2.setEditable(false);
        panel2.add(text2);
        panel2.add(start2);
        panel2.add(stop2);
        panel2.setLayout(new FlowLayout());
        // 根据布局的层次添加子组件到父组件中
        panelAll.add(panel3);
        panelAll.add(panel1);
        panelAll.add(panel4);
        panelAll.add(panel2);
        // 将组件添加到主窗口
        frame.add(panelAll);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    private void Jb1Listener1() {
        // 监听计数器1的start按钮事件
        start1.addActionListener(e -> {

            Flag1 = true;  // 开始计数

            new Thread(() -> {
                while (true) {

                    if (Flag1) {
                        try {
                            Thread.sleep(200);  // 线程暂停200ms
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        count1++;
                        SwingUtilities.invokeLater(() -> {
                            // TODO Auto-generated method stub
                            if (count1 % 10 == 0) {
                                // 计数变量每增加10次，添加一个动物图标并且刷新主窗口
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
        // 监听计数器1的stop按钮事件
        stop1.addActionListener(e -> Flag1 = false);  // 停止计数
    }


    private void Jb1Listener2() {
        // 监听计数器2的start按钮事件
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
        // 监听计数器2的stop按钮事件
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
