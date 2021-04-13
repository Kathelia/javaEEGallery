package com.gallery.jsf;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileUtils;

@ManagedBean(name = "imageManager")
public class ManageImages {

	private Part file;

	public void save() {
		String fileName = Paths.get(file.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

		try (InputStream input = file.getInputStream()) {
			File doc = new File(ImagesView.getImagesPath(), fileName);
			System.out.println(doc.getAbsolutePath());
			Files.copy(input, new File(ImagesView.getImagesPath(), fileName).toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void delete(String fileName) {
		File image = new File(ImagesView.getImagesPath(), fileName.replace("/images/", ""));
		try {
			FileUtils.forceDelete(image);
			System.out.println("Deleted image " + fileName);
			if (ImagesView.currentImageIndex > 0) {
				ImagesView.currentImageIndex = ImagesView.currentImageIndex - 1;
			}
		} catch (IOException e) {
			System.out.println("Can not delete image " + fileName);
			e.printStackTrace();
		}
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

}
