package com.gallery.jsf;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@RequestScoped
@ManagedBean(name = "imagesView")
public class ImagesView {

	public static int currentImageIndex = 0;
	public static int imagesCount = 0;

	private ArrayList<String> images;

	@PostConstruct
	public void init() {
		System.out.println("init");

	}

	public String getCurrentImage() {
		System.out.println("getFirstImage");
		ArrayList<String> images = getImages();
		if (images.isEmpty()) {
			return "";
		} else {
			return images.get(currentImageIndex);
		}
	}

	public String getNextImage() {
		return getImages().get(0);
	}

	public ArrayList<String> getImages() {
		images = new ArrayList<>();

		File imagesDir = new File(getImagesPath());
		if (imagesDir.exists() && imagesDir.isDirectory()) {
			for (File image : imagesDir.listFiles()) {
				images.add("/images/" + image.getName());
				/*
				 * try { images.add("/images/" + image.getName()); } catch (IOException e) { //
				 * TODO Auto-generated catch block e.printStackTrace(); }
				 */
			}
		}
		imagesCount = images.size();
		return images;

	}

	public String gallery() {
		return "gallery_view";
	}

	public static String getImagesPath() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		String input = "";
		try {
			input = ec.getResource("/images/").getPath();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return input;
	}

}
