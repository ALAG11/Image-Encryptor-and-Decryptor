import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;


public class Image_Encryption_and_Decryption {

    public static void operate(int key) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();

        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[fis.available()];
            fis.read(data);
            int i = 0;
            for (byte b : data) {
                System.out.println(b);
                data[i] = (byte)(b^key);
                i++;
            }
            FileOutputStream fos=new FileOutputStream(file);
            fos.write(data);
            fos.close();
            fis.close();
            JOptionPane.showMessageDialog(null, "Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("This is testing");

        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame();
            f.setTitle("Image Encryption and Decryption");
            f.setSize(400, 400);
            f.setLocationRelativeTo(null);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Font font = new Font("Arial", Font.BOLD, 20);

            JButton button = new JButton();
            button.setText("Open Image");
            button.setFont(font);

            JTextField textField = new JTextField();
            textField.setPreferredSize(new Dimension(250, 40));
            textField.setFont(font);

            button.addActionListener(e -> {
                System.out.println("Button is clicked");
                String text = textField.getText();
                int temp = Integer.parseInt(text);
                operate(temp);
            });

            f.setLayout(new FlowLayout());

            f.add(button);
            f.add(textField);
            f.setVisible(true);
        });
    }
}
