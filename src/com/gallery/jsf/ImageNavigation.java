package com.gallery.jsf;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "imageNav")
public class ImageNavigation {
	
	int i = 0;
	
    public void next() {
    	System.out.println("next " + ImagesView.currentImageIndex);
    	
    	if (ImagesView.currentImageIndex < ImagesView.imagesCount - 1) {
    		ImagesView.currentImageIndex = ImagesView.currentImageIndex + 1;
    	}
    }
    
    public void prev() {
    	System.out.println("prev " + ImagesView.currentImageIndex);
    	if (ImagesView.currentImageIndex > 0) {
    		ImagesView.currentImageIndex = ImagesView.currentImageIndex - 1;
    	}
    }
    
    public void first() {
    	ImagesView.currentImageIndex = 0;
    }
    
    public void last() {
    	ImagesView.currentImageIndex = ImagesView.imagesCount - 1;
    }

}
