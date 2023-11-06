package com.insta.instagram.Services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendOtpEmail(String email, String otp)  {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // Set the email recipient
            helper.setTo(email);

            // Set the email subject
            helper.setSubject("OTP Verification");

            // Create the HTML content for the email
            StringBuilder body = new StringBuilder();
            body.append("<html>");
            body.append("<body>");
            body.append("<div style='text-align: center;'>");
            body.append("<h1 style='color: #007BFF;'>OTP Verification</h1>");
            body.append("<p>Hello there,</p>");
            body.append("<p>Your OTP (One-Time Password) for login is:</p>");
            body.append("<div style='background-color: #007BFF; color: #fff; padding: 10px 15px; border-radius: 5px; font-size: 18px; margin-top: 10px;'>");
            body.append(otp); // Insert the OTP dynamically
            body.append("</div>");
            body.append("<p style='font-size: 14px; color: #777;'>Please use this OTP to complete your login process. This OTP is valid for a short duration for security purposes.</p>");
            body.append("<p>If you did not request this OTP, please ignore this email.</p>");
            body.append("</div>");
            body.append("</body>");
            body.append("</html>");

            // Set the email content as HTML
            helper.setText(body.toString(), true);

            // Send the email
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }
}