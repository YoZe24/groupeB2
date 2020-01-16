package be.helha.aemt.control;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

import be.helha.aemt.ejb.PortraitGestionEJB;
import be.helha.aemt.entities.Portrait;
import be.helha.aemt.entities.User;

@Named
@ApplicationScoped
public class PortraitControl implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2483812624471684713L;

	@EJB
	private PortraitGestionEJB bean;
	
	private Part img;
	
	private Portrait portrait;
	
	private List<Portrait> listPortraitToDiplay = new ArrayList<>(); 
	
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
	
	public Portrait addPortrait(User user) {
		byte[] picBytes = new byte[(int) img.getSize()];
		try {
			img.getInputStream().read(picBytes);
			this.portrait.setImg(picBytes);
		}catch (Exception e) {
		}
		
		this.portrait.setAuthor(user);
		
		return post(portrait);
	}
	
	public byte[] getImgByte(int id) {
		Portrait port = get(id);
		return port.getImg();
	}
	
	
	public Part getImg() {
		return img;
	}

	public void setImg(Part img) {
		this.img = img;
	}

	public List<Portrait> query(){
		this.listPortraitToDiplay = bean.query();
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
	
	public Portrait get(int id) {
		return bean.get(id);
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

	public List<Portrait> getListPortraitToDiplay() {
		return listPortraitToDiplay;
	}

	public void setListPortraitToDiplay(List<Portrait> listPortraitToDiplay) {
		this.listPortraitToDiplay = listPortraitToDiplay;
	}
	
	public int getIndexOf(Portrait port) {
		return listPortraitToDiplay.indexOf(port);
	}
	
	
	
}
