package com.example.iaimagemaker.services;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Service;

@Service
public class ImageTextService {

    public byte[] generateImageWithText(String text, String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        BufferedImage image = ImageIO.read(url);

        Color backgroundColor = getAverageBackgroundColor(image);
        Color textColor = getContrastingTextColor(backgroundColor);

        Graphics2D graphics = image.createGraphics();

        Font font = new Font("Arial", Font.BOLD, 60);

        graphics.setFont(font);
        graphics.setColor(textColor);

        String[] lines = divideTextIntoLines(text, graphics.getFontMetrics(), image.getWidth());

        int y = (image.getHeight() - (lines.length * graphics.getFontMetrics().getHeight())) / 2;

        for (String line : lines) {
            int x = (image.getWidth() - graphics.getFontMetrics().stringWidth(line)) / 2;
            graphics.drawString(line, x, y);
            y += graphics.getFontMetrics().getHeight();
        }

        graphics.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        baos.flush();
        byte[] imageBytes = baos.toByteArray();
        baos.close();

        return imageBytes;
    }

    private static String[] divideTextIntoLines(String text, FontMetrics fontMetrics, int maxWidth) {
        List<String> lines = new ArrayList<>();
        String[] words = text.split(" ");
        StringBuilder currentLine = new StringBuilder(words[0]);

        for (int i = 1; i < words.length; i++) {
            String word = words[i];
            String potentialLine = currentLine + " " + word;

            if (fontMetrics.stringWidth(potentialLine) <= maxWidth) {
                currentLine.append(" ").append(word);
            } else {
                lines.add(currentLine.toString());
                currentLine = new StringBuilder(word);
            }
        }

        lines.add(currentLine.toString());

        return lines.toArray(new String[0]);
    }

    private static Color getContrastingTextColor(Color backgroundColor) {
        int luminance = (int) ((0.299 * backgroundColor.getRed()) + (0.587 * backgroundColor.getGreen()) + (0.114 * backgroundColor.getBlue()));

        if (luminance > 128) {
            Color[] darkColors = {
                    new Color(31, 38, 42),
                    new Color(63, 81, 181),
                    new Color(48, 63, 159),
                    new Color(149, 117, 205),
                    new Color(194, 24, 91)
            };
            return darkColors[(int) (Math.random() * darkColors.length)];
        } else {
            Color[] lightColors = {
                    new Color(239, 247, 255),
                    new Color(255, 193, 7),
                    new Color(156, 204, 101),
                    new Color(255, 138, 101),
                    new Color(244, 67, 54)
            };
            return lightColors[(int) (Math.random() * lightColors.length)];
        }
    }

    private static Color getAverageBackgroundColor(BufferedImage image) {
        int totalRed = 0;
        int totalGreen = 0;
        int totalBlue = 0;
        int pixelCount = 0;

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color pixelColor = new Color(image.getRGB(x, y));
                totalRed += pixelColor.getRed();
                totalGreen += pixelColor.getGreen();
                totalBlue += pixelColor.getBlue();
                pixelCount++;
            }
        }

        int avgRed = totalRed / pixelCount;
        int avgGreen = totalGreen / pixelCount;
        int avgBlue = totalBlue / pixelCount;

        return new Color(avgRed, avgGreen, avgBlue);
    }
}
