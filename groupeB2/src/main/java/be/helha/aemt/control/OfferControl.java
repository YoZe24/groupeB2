package be.helha.aemt.control;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import be.helha.aemt.ejb.OfferGestionEJB;
import be.helha.aemt.entities.Address;
import be.helha.aemt.entities.Offer;
import be.helha.aemt.entities.User;
import be.helha.aemt.enums.EnumOfferType;
import be.helha.aemt.enums.EnumRole;
import be.helha.aemt.enums.EnumSection;

@Named
@SessionScoped
public class OfferControl implements Serializable {

	@EJB
	private OfferGestionEJB bean;
	private Offer offer;
	private String name = "OfferEJB";
	private EnumOfferType typeOfferChoose = null;
	private List<Offer> listOfferLoad = new ArrayList<>();

	private Part pdf;

	private String startDateStr = "";
	private String endDateStr = "";

	private boolean offerTypeIsOk;
	private Offer singleOffer;

	private boolean valid;

	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public OfferControl() {
		offer = new Offer();
		offer.setOfferType(EnumOfferType.CDD);
	}
	public EnumOfferType[] getOfferTypes() {
		return EnumOfferType.values();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Offer> getListOffers(){
		return bean.query();
	}
	public Offer getOffer(Offer o) {
		return bean.get(o);
	}
	public Offer postOffer(Offer o) {
		return bean.post(o);
	}

	public void download(Offer offer) throws IOException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		externalContext.responseReset();
		externalContext.setResponseContentType("application/pdf");
		externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + offer.getId()+"uploaded_offre.pdf" + "\"");
		OutputStream outputStream = externalContext.getResponseOutputStream();
		outputStream.write(offer.getImg());
		
		facesContext.responseComplete();
	}

	public void createOfferInPdf(Offer offer) throws DocumentException, IOException {
		Document document = new Document();
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		externalContext.responseReset();
		externalContext.setResponseContentType("application/pdf");
		externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + offer.getId()+"_offre.pdf" + "\"");
		PdfWriter.getInstance(document, externalContext.getResponseOutputStream());
		
		document.open();
		document.addTitle(offer.getOfferType().getType() + " - " +offer.getFunctionOffer());
		document.addAuthor(offer.getAuthor().getName()+" "+offer.getAuthor().getFirstname());

		PdfPTable table = new PdfPTable(5);
		addTableHeader(table);
		addRows(table,offer);
		
		document.add(table);
		document.close();
		facesContext.responseComplete();
	}
	
	private void addRows(PdfPTable table,Offer o) {
		table.addCell("Nom : ");
		table.addCell(o.getSocietyName());
		table.addCell("   ");
		table.addCell("Fonction : ");
		table.addCell(o.getFunctionOffer());
		
		table.addCell("Email : ");
		table.addCell(o.getSocietyMail());
		table.addCell("   ");
		table.addCell("Sujet : ");
		table.addCell(o.getSubject());

		table.addCell("Numéro de tél : ");
		table.addCell(o.getSocietyNum());
		table.addCell("   ");
		table.addCell("Compétences requises : ");
		table.addCell(o.getSkillsNeeded());
		
		table.addCell("Secteur : ");
		table.addCell(o.getSocietySector());
		table.addCell("   ");
		table.addCell("Informations supp. : ");
		table.addCell(o.getNoteSupp());

		table.addCell("Addresse : ");
		table.addCell(o.getSocietyAddress().toString());
		table.addCell("   ");
		table.addCell("Date : ");
		table.addCell(o.getStartDate() +" au " +o.getEndDate());

		table.addCell("Statut : ");
		table.addCell("Disponible");
		table.addCell("   ");
		table.addCell("Rémunération : ");
		table.addCell(o.getAmount()+"€/mois");

	}
	private void addTableHeader(PdfPTable table) {
		Stream.of("\t", "Entreprise","", "\t","Offre")
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(2);
	        header.setPhrase(new Phrase(columnTitle));
	        table.addCell(header);
	    });
	}
	
	private byte[] getPdfBytes(Offer offer) {

		return getOffer(offer).getImg();
	}

	public String addOffer(User u) {
		if(this.offer.getOfferType() != EnumOfferType.CDD) {
			this.offer.setStartDate(convertDateStrToLocalDateTime(startDateStr));
			this.offer.setEndDate(convertDateStrToLocalDateTime(endDateStr));
		}

		byte[] pdfBytes = new byte[(int) pdf.getSize()];
		try {
			pdf.getInputStream().read(pdfBytes);
			this.offer.setImg(pdfBytes);
		}catch (Exception e) {
		}

		this.offer.setAuthor(u);

		postOffer(offer);

		return "/listOffer.xhtml";
	}

	public Offer submitOffer() {
		List<String> skills = new ArrayList<>();
		Address a = new Address("testOffer", "offerNum", "offerCity", "offerCp");
		Address aUser = new Address("testOfferUser", "userNum", "userCity", "userCp");
		User u = new User("testOffer", "testOffer", "testOffer@gmail.com", "testoffer", "testOffer", "testOffer","testOffer",EnumSection.COMPTABILITE, aUser, EnumRole.ANCIENT);
		this.offer = new Offer(u, LocalDateTime.now(), "pathFile","SocietyTest", "societyMail","societySector","03",a,"functionOffer",true, "Java,Mysql","noteSupp","subject",EnumOfferType.CDD,LocalDateTime.now(),LocalDateTime.now(),200.0);
		return bean.post(offer);
	}

	public void removeOffer() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();
		int offerId = Integer.parseInt((String) map.get("idRemoved"));

		Offer offerToRemove = getOfferById(offerId);
		removeOffer(offerToRemove);
		listOfferLoad.remove(offerToRemove);
	}

	public Offer removeOffer(Offer offer) {
		listOfferLoad.remove(offer);
		return bean.delete(offer);
	}


	public void confirmOffer() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();
		int offerId = Integer.parseInt((String) map.get("idConfirmed"));
		Offer offerUpdated = updateOffer(offerId);
		System.out.println(offerUpdated);
		bean.update(offerUpdated);
		listOfferLoad.set(listOfferLoad.indexOf(offerUpdated), offerUpdated);
		//System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++"+listOfferLoad.get(offerId));
	}

	public Offer updateOffer(int id) {
		Offer offerToUpdate = bean.getById(id);
		offerToUpdate.setConfirmed(true);
		return offerToUpdate;
	}

	public boolean renderDate() {
		return offer.getOfferType() != EnumOfferType.CDD;
	}

	public Offer deleteOffer(Offer o) {
		return bean.delete(o);
	}
	public Offer updateOffer(Offer o) {
		return bean.update(o);
	}
	public Offer updateOfferAvailable(Offer o) {
		return bean.updateStatut(o);
	}

	public void loadListOffer() {
		this.listOfferLoad = getAllByOffer();
	}

	public void loadListOffer (EnumOfferType type) {
		System.out.println("Type choisis: " + typeOfferChoose);
		this.listOfferLoad = getAllByOfferType(type);
	}
	public List<Offer> getAllByOfferType(EnumOfferType type){
		return bean.getAllByOfferType(type);
	}

	public List<Offer> getAllByOffer(){
		return bean.query();
	}

	public boolean checkConfirmed(int id) {
		Offer o = getOfferById(id);
		return o.isConfirmed();
	}
	public Offer getOfferById(int id) {
		return bean.getById(id);
	}
	public Offer getOffer() {
		return offer;
	}
	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	public EnumOfferType getTypeOfferChoose() {
		return typeOfferChoose;
	}
	public void setTypeOfferChoose(EnumOfferType typeOfferChoose) {
		this.typeOfferChoose = typeOfferChoose;
	}
	public List<Offer> getListOfferLoad() {
		return listOfferLoad;
	}
	public void setListOfferLoad(List<Offer> listOfferLoad) {
		this.listOfferLoad = listOfferLoad;
	}

	public boolean isOfferTypeIsOk() {
		return renderDate();
	}
	public void setOfferTypeIsOk(boolean offerTypeIsOk) {
		this.offerTypeIsOk = offerTypeIsOk;
	}

	private LocalDateTime convertDateStrToLocalDateTime(String dateStr) {
		if(dateStr == null || dateStr.length() == 0) return LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
		return dateTime;
	}
	public String getStartDateStr() {
		return startDateStr;
	}
	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}
	public String getEndDateStr() {
		return endDateStr;
	}
	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
	public Part getPdf() {
		return pdf;
	}
	public void setPdf(Part pdf) {
		this.pdf = pdf;
	}

	public String convertBoolToString(boolean bool) {
		return bool == false? "Non validï¿½" : "Validï¿½";
	}

	public void seeNotConfirmedUsers() {
		List<Offer> listOfferNotConfirmed = new ArrayList<Offer>();
		for (Offer offer : listOfferLoad) {
			if(!offer.isConfirmed()) listOfferNotConfirmed.add(offer);
		}
		setListOfferLoad(listOfferNotConfirmed);
	}

	public void seeAllOffers() {
		loadListOffer();
	}

	public void singleOfferDetails() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();
		int userId = Integer.parseInt((String) map.get("idClicked"));
		setSingleOffer(bean.getById(userId));
	}

	public void setSingleOffer(Offer singleOffer) {
		this.singleOffer = singleOffer;
	}
	public Offer getSingleOffer() {
		return singleOffer;
	}






}
