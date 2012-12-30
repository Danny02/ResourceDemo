/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.darwin.resourcedemo;

import java.awt.image.BufferedImage;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class ImageWrapper {

    private BufferedImage img;

    public ImageWrapper(BufferedImage img) {
        this.img = img;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        if (img != null) {
            this.img = img;
        }
    }
}
