package com.gallery.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/data")
public class JSONService {

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createDataInJSON(String data) {
		System.out.println("rest!");
		String result = "Data post: " + data;

		return Response.status(201).entity(result).build();
	}

	@POST
	@Path("/upload")
	// @Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@Context HttpServletRequest request, @Context HttpServletResponse response, @FormDataParam("upfile") InputStream uploadedInputStream,
	        @FormDataParam("upfile") FormDataContentDisposition fileDetail) throws IOException {
		System.out.println("rest! image");

		String fileName = fileDetail.getFileName();
		if (fileDetail.getFileName().contains(" ")) {
			fileName = fileName.substring(fileName.indexOf(" ") + 1);
		}
		String imageDir = this.getClass().getClassLoader().getResource("../../").getPath();
		System.out.println(imageDir);
		File doc = new File(imageDir, "/images/" + fileName);
    	System.out.println(doc.getAbsolutePath());
    	
    	
    	writeToFile(uploadedInputStream, doc.getPath());

    	String myJsfPage = "/single_view.xhtml";
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + myJsfPage);
        return Response.status(200).build();
	}
	
	// save uploaded file to new location
    private void writeToFile(InputStream uploadedInputStream,
        String uploadedFileLocation) {

        try {
            OutputStream out = new FileOutputStream(new File(
                    uploadedFileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}
