/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.darwin.resourcedemo;

import java.awt.image.BufferedImage;
import java.io.*;

import darwin.annotations.ServiceProvider;
import darwin.resourcehandling.factory.*;
import darwin.resourcehandling.handle.ResourceHandle;

import javax.imageio.ImageIO;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
@ServiceProvider(ResourceFromHandleProvider.class)
public class ImageLoader extends ResourceFromHandleProvider<ImageWrapper> implements ResourceFromHandle<ImageWrapper> {

    public ImageLoader() {
        super(ImageWrapper.class);
    }

    @Override
    public ImageWrapper create(ResourceHandle handle) throws IOException {
        return new ImageWrapper(createImage(handle));
    }
    
    private BufferedImage createImage(ResourceHandle handle) throws IOException
    {
		    try(InputStream in = handle.getStream()) {
        	return ImageIO.read(in);
				}
    }

    @Override
    public void update(ResourceHandle changed, ImageWrapper wrapper) {
        try {
            wrapper.setImg(createImage(changed));
        } catch (IOException ex) {
        }
    }

    @Override
    public ImageWrapper getFallBack() {
        return new ImageWrapper(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));
    }

    @Override
    public ResourceFromHandle<ImageWrapper> get(String[] options) {
        return new ImageLoader();
    }
}
