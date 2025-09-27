package Utils;

import GUI.admin.popup.TeacherDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import javax.imageio.ImageIO;

public class ImageUtil {

    public static byte[] getBlobFromFile(String filePath) throws IOException {
        return Files.readAllBytes(new File(filePath).toPath());
    }

    public static void saveBlobToFile(byte[] data, String filePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(data);
        }
    }

    public static void showImageOnLabel(JLabel label, String filePath) throws IOException {
        byte[] data = getBlobFromFile(filePath);
        showImageOnLabel(label, data);
    }

    public static void showImageOnLabel(JLabel label, byte[] data) {
        if (data == null) {
            label.setIcon(null);
            return;
        }
        try {
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(data));
            if (img == null) {
                label.setIcon(null);
                return;
            }
            int width = label.getPreferredSize().width;
            int height = label.getPreferredSize().height;
            Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(scaledImg));
            label.setText("");
            label.revalidate();
            label.repaint();
        } catch (IOException e) {
            e.printStackTrace();
            label.setIcon(null);
        }
    }

    public static void showImageOnButton(JButton button, String filePath) throws IOException {
        byte[] data = getBlobFromFile(filePath);
        showImageOnButton(button, data);
    }

    public static void showImageOnButton(JButton button, byte[] data) {
        showImageOnButtonSafe(button, data);
    }

    public static void showImageOnButtonSafe(JButton button, byte[] data) {
        if (data == null || data.length == 0) {
        button.setIcon(null);
        button.setText("Không có ảnh");
        return;
    }
    try {
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(data));
        if (img == null) {
            button.setIcon(null);
            button.setText("Ảnh lỗi");
            return;
        }
        int width = button.getWidth() > 0 ? button.getWidth() : button.getPreferredSize().width;
        int height = button.getHeight() > 0 ? button.getHeight() : button.getPreferredSize().height;
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImg));
        button.setText("");
        button.revalidate();
        button.repaint();
    } catch (IOException e) {
        e.printStackTrace();
        button.setIcon(null);
        button.setText("Lỗi load ảnh");
    }
    }

    public static void chooseImageForLabel(JLabel label) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Chọn ảnh");
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (!validationUtils.isImageFile(file)) {
                JOptionPane.showMessageDialog(null,
                        "File chọn không phải ảnh! Vui lòng chọn file hình (.png, .jpg, .jpeg, .gif).",
                        "Lỗi",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                showImageOnLabel(label, file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi đọc file ảnh!");
            }
        }
    }

    public static byte[] chooseImageForButton(JButton button) {
    JFileChooser chooser = new JFileChooser();
    chooser.setDialogTitle("Chọn ảnh");
    int result = chooser.showOpenDialog(null);

    if (result == JFileChooser.APPROVE_OPTION) {
        File file = chooser.getSelectedFile();

        if (!validationUtils.isImageFile(file)) {
            JOptionPane.showMessageDialog(null,
                    "File chọn không phải ảnh! Vui lòng chọn file hình (.png, .jpg, .jpeg, .gif).",
                    "Lỗi",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }

        try {
            byte[] data = Files.readAllBytes(file.toPath());

            showImageOnButtonSafe(button, data);
            TeacherDialog.imgPath = file.toPath().toString();
            return data;

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi đọc file ảnh!");
            button.setIcon(null);
            button.setText("Ảnh lỗi");
            return null;
        }
    }

    button.setIcon(null);
    button.setText("Không có ảnh");
    return null;
}

}
