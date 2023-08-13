package com.example.iaimagemaker.services;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Service;

@Service
public class ImageTextService {

    public byte[] generateImageWithText(String text, String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        BufferedImage image = ImageIO.read(url);

        Graphics2D g = image.createGraphics();
        Font font = new Font("Arial", Font.BOLD, 24);

        g.setFont(font);
        g.setColor(Color.WHITE);

        int x = 50;
        int y = 50;

        g.drawString(text, x, y);
        g.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        baos.flush();
        byte[] imageBytes = baos.toByteArray();
        baos.close();

        return imageBytes;
    }
}