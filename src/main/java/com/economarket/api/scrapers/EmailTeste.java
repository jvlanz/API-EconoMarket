package com.economarket.api.scrapers;

import org.springframework.mail.javamail.JavaMailSenderImpl;

public class EmailTeste {

    public static void main(String[] args) {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("oficialeconomarket@gmail.com");
        mailSender.setPassword("SUA_SENHA_DE_APP");

        mailSender.getJavaMailProperties().put("mail.smtp.auth", "true");
        mailSender.getJavaMailProperties().put("mail.smtp.starttls.enable", "true");

        System.out.println("Testando conexão...");
        System.out.println("Usuário: " + mailSender.getUsername());

        try {

            mailSender.testConnection();

            System.out.println("================================");
            System.out.println("CONEXÃO COM GMAIL FUNCIONOU!");
            System.out.println("================================");

        } catch (Exception e) {

            System.out.println("================================");
            System.out.println("ERRO AO CONECTAR NO GMAIL:");
            System.out.println("================================");

            e.printStackTrace();
        }
    }
}