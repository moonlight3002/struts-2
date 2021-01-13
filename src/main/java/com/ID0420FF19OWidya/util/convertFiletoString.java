package com.ID0420FF19OWidya.util;

import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.util.Base64;
import java.io.OutputStream;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import com.ID0420FF19OWidya.models.User;

public class convertFiletoString
{
    public static String getBase64(final User user, final String directory) throws IOException {
        System.out.println("getBase64");
        String base64 = "";
        final InputStream inputStream = null;
        final File f = new File(String.valueOf(directory) + user.getUserID() + "/" + user.getProfilePic());
        if (f.exists()) {
            try {
                final BufferedImage img = ImageIO.read(f);
                final int scaleX = (int)(img.getHeight() * 0.25);
                final int scaleY = (int)(img.getWidth() * 0.25);
                final Image tempimg = img.getScaledInstance(scaleX, scaleY, 4);
                final BufferedImage resized = new BufferedImage(scaleX, scaleY, 1);
                final Graphics2D g2d = resized.createGraphics();
                g2d.drawImage(tempimg, 0, 0, new Color(0, 0, 0), null);
                final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                ImageIO.write(resized, "jpg", buffer);
                final byte[] outputBytes = buffer.toByteArray();
                base64 = "data:image/jpg;base64," + Base64.getEncoder().encodeToString(outputBytes);
                System.out.println(base64);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return base64;
    }
}