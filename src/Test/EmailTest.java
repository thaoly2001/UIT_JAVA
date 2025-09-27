package Test;

import MODEL.Users;
import Utils.EmailUtil;

public class EmailTest {

    public static void main(String[] args) {
        Users testUser = new Users();
        testUser.setUsername("testuser");
        testUser.setPassword("testpassword");
        testUser.setEmail("thientran10201@gmail.com");

        String subject = "Kiểm tra chức năng gửi email";
        String content = "Xin chào " + testUser.getUsername() + ",\n\n" +
                         "Đây là một email kiểm tra từ hệ thống của bạn.\n" +
                         "Mật khẩu của bạn là: " + testUser.getPassword() + "\n\n" +
                         "Trân trọng,\n" +
                         "Hệ thống quản lý";

        System.out.println("Đang cố gắng gửi email đến: " + testUser.getEmail());
        try {
            EmailUtil.sendEmail(testUser.getEmail(), subject, content);
            System.out.println("Email đã được gửi thành công.");
        } catch (Exception e) {
            System.err.println("Lỗi khi gửi email: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Đã hoàn tất việc gửi email (kiểm tra console để biết lỗi).");
    }
}