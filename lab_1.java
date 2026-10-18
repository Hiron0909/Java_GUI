import javax.swing.*;

public class img {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Display Image");

        ImageIcon imj = new ImageIcon("cateimage.jpg"); //image name here
        JLabel lebel = new JLabel(imj);

        frame.add(lebel);

        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }}
