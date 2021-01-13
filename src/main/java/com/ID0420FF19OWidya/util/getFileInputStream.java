package com.ID0420FF19OWidya.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import com.ID0420FF19OWidya.models.User;

public class getFileInputStream
{
    public static InputStream getImageInputStream(final User user) {
        System.out.println("getFile");
        System.out.println(user);
        InputStream inputStream = null;
        final File sourceimage = new File("c:/Users/62812/Pictures/" + user.getUserID() + "/" + user.getProfilePic());
        if (sourceimage.exists()) {
            final ByteArrayOutputStream os = new ByteArrayOutputStream();
            try {
                final BufferedImage img = ImageIO.read(sourceimage);
                ImageIO.write(img, "jpg", os);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            inputStream = new ByteArrayInputStream(os.toByteArray());
        }
        return inputStream;
    }
}