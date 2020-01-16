package be.helha.aemt.control;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;

import be.helha.aemt.ejb.PortraitGestionEJB;
import be.helha.aemt.entities.Portrait;
import be.helha.aemt.entities.User;

@Named
@SessionScoped
public class PortraitControl implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2483812624471684713L;

	@EJB
	private PortraitGestionEJB bean;
	
	private Part img;
	
	private Portrait portrait;
	
	public PortraitControl() {
		this.portrait = new Portrait();
	}
	
	public void uploadImg() {
		System.out.println("upload "+img.getSize());
		try (InputStream input = img.getInputStream();){
			File upload = new File("/uploads");
			//Files.copy(input, new File(upload,img.getSubmittedFileName()).toPath(),StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String addPortrait(User user) {
		byte[] picBytes = new byte[(int) img.getSize()];
		try {
			img.getInputStream().read(picBytes);
			this.portrait.setImg(picBytes);
		}catch (Exception e) {
		}
		
		this.portrait.setAuthor(user);
		
		post(portrait);
		
		return "/index.xhtml";
	}
	
	public Part getImg() {
		return img;
	}

	public void setImg(Part img) {
		this.img = img;
	}

	public List<Portrait> query(){
		return bean.query();
	}
	
	public Portrait get(Portrait p) {
		return bean.get(p);
	}
	
	public Portrait post(Portrait p) {
		return bean.post(p);
	}
	
	public Portrait delete(Portrait p) {
		return bean.delete(p);
	}
	
	public Portrait update(Portrait p) {
		return bean.update(p);
	}

	public Portrait getPortrait() {
		return portrait;
	}

	public void setPortrait(Portrait portrait) {
		this.portrait = portrait;
	}
	
	
}
