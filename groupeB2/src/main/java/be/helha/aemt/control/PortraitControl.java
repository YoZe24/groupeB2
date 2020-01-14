package be.helha.aemt.control;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

import be.helha.aemt.ejb.PortraitGestionEJB;
import be.helha.aemt.entities.Portrait;

@Named
@SessionScoped
public class PortraitControl implements Serializable {
	
	@EJB
	private PortraitGestionEJB bean;
	
	private Part img;
	
	public void uploadImg() {
		try (InputStream input = img.getInputStream();){
			String path = new File("").getAbsolutePath()+"\\files\\imgPortrait";
			System.out.println(path);
			Files.copy(input, new File(path,"test.png").toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
}
