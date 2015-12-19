package com.grs.product.smartflat.database;

import android.database.Cursor;
import com.grs.product.smartflat.models.ContactDetails;
import com.grs.product.smartflat.models.FamilyDetails;
import com.grs.product.smartflat.models.FlatOwnerDetails;
import com.grs.product.smartflat.models.NoticeDetails;
import com.grs.product.smartflat.models.QueryDetails;
import com.grs.product.smartflat.models.RequestDetails;
import com.grs.product.smartflat.models.RequestMessages;
import com.grs.product.smartflat.models.SocietyDetails;
import com.grs.product.smartflat.models.VehicleDetails;
import com.grs.product.smartflat.models.VisitorDetails;

public class SmartFlatDBManager {

	public boolean saveSocietyDetails(SocietyDetails details){
		boolean isAdded = false;
		SmartFlatDatabase.getInstance().open();
		isAdded = SmartFlatDatabase.getInstance().saveSocietyDetails(details);
		SmartFlatDatabase.getInstance().close();
		return isAdded;
	}
	
	public Cursor getSocietyDetails(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getSocietyDetails();
		SmartFlatDatabase.getInstance().close();
		return details;	
	}
	
	public boolean saveFlatOwnerDeatils(FlatOwnerDetails details){
		boolean isAdded = false;
		SmartFlatDatabase.getInstance().open();
		isAdded = SmartFlatDatabase.getInstance().saveFlatOwnerDeatils(details);
		SmartFlatDatabase.getInstance().close();
		return isAdded;
	}
	
	public Cursor getFlatOwnerDetails(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getFlatOwnerDetails();
		SmartFlatDatabase.getInstance().close();
		return details;
	}
	
	public boolean saveFamilyDetails(FamilyDetails details){
		boolean isAdded = false;
		SmartFlatDatabase.getInstance().open();
		isAdded = SmartFlatDatabase.getInstance().saveFamilyDetails(details);
		SmartFlatDatabase.getInstance().close();
		return isAdded;
	}
	
	public Cursor getFamilyDetails(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getFamilyDetails();
		SmartFlatDatabase.getInstance().close();
		return details;
	}
	
	public boolean saveVehicleDetails(VehicleDetails details){
		boolean isAdded = false;
		SmartFlatDatabase.getInstance().open();
		isAdded = SmartFlatDatabase.getInstance().saveVehicleDetails(details);
		SmartFlatDatabase.getInstance().close();
		return isAdded;
	}
	
	public Cursor getVehicleDetails(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getVehicleDetails();
		SmartFlatDatabase.getInstance().close();
		return details;
	}
	
/*	public boolean saveComplaintDetails(ComplaintDetails details){
		boolean isAdded = false;
		SmartFlatDatabase.getInstance().open();
		isAdded = SmartFlatDatabase.getInstance().saveComplaintDetails(details);
		SmartFlatDatabase.getInstance().close();
		return isAdded;
	}*/
	
	public Cursor getAllComplaintDetails(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getAllComplaintDetails();
		SmartFlatDatabase.getInstance().close();
		return details;
	}
	
	public boolean saveRequestDetails(RequestDetails details){
		boolean isAdded = false;
		SmartFlatDatabase.getInstance().open();
		isAdded = SmartFlatDatabase.getInstance().saveRequestDetails(details);
		SmartFlatDatabase.getInstance().close();
		return isAdded;
	}
	
	public Cursor getAllRequestDetails(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getAllRequestDetails();
		SmartFlatDatabase.getInstance().close();
		return details;
	}
	
	public boolean saveQueryDetails(QueryDetails details){
		boolean isAdded = false;
		SmartFlatDatabase.getInstance().open();
		isAdded = SmartFlatDatabase.getInstance().saveQueryDetails(details);
		SmartFlatDatabase.getInstance().close();
		return isAdded;
	}
	
	public Cursor getAllQueryDetails(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getAllQueryDetails();
		SmartFlatDatabase.getInstance().close();
		return details;
	}
	
	public boolean saveSocietyNoticeDetails(NoticeDetails details){
		boolean isAdded = false;
		SmartFlatDatabase.getInstance().open();
		isAdded = SmartFlatDatabase.getInstance().saveSocietyNoticeDetails(details);
		SmartFlatDatabase.getInstance().close();
		return isAdded;
	}
	
	public Cursor getAllSocietyNoticeDetails(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getAllSocietyNoticeDetails();
		SmartFlatDatabase.getInstance().close();
		return details;
	}
	
/*	public Cursor getRaisedComplaintDetails(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getRaisedComplaintDetails();
		SmartFlatDatabase.getInstance().close();
		return details;
	}
	
	public Cursor getClosedComplaintDetails(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getClosedComplaintDetails();
		SmartFlatDatabase.getInstance().close();
		return details;
	}*/
		
	public Cursor getRaisedRequestDetails(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getRaisedRequestDetails();
		SmartFlatDatabase.getInstance().close();
		return details;
	}
	
	public Cursor getRaisedRequestDetailsByType(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getRaisedRequestDetailsByType();
		SmartFlatDatabase.getInstance().close();
		return details;
	}
	
	public Cursor getRaisedRequestDetailsByCategory(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getRaisedRequestDetailsByCategory();
		SmartFlatDatabase.getInstance().close();
		return details;
	}
	
	public Cursor getRaisedRequestDetailsByPriorityHtoL(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getRaisedRequestDetailsByPriorityHtoL();
		SmartFlatDatabase.getInstance().close();
		return details;
	}
	
	public Cursor getRaisedRequestDetailsByPriorityLtoH(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getRaisedRequestDetailsByPriorityLtoH();
		SmartFlatDatabase.getInstance().close();
		return details;
	}
	
	public Cursor getClosedRequestDetails(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getClosedRequestDetails();
		SmartFlatDatabase.getInstance().close();
		return details;
	}
	
	public Cursor getRaisedQueryDetails(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getRaisedQueryDetails();
		SmartFlatDatabase.getInstance().close();
		return details;
	}
	
	public Cursor getClosedQueryDetails(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getClosedQueryDetails();
		SmartFlatDatabase.getInstance().close();
		return details;
	}
	
	public Cursor getSinbleRequestDetails(String requestNumber){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getSinbleRequestDetails(requestNumber);
		SmartFlatDatabase.getInstance().close();
		return details;
	}
	
	public boolean saveMessage(RequestMessages messages){
		boolean isAdded = false;
		SmartFlatDatabase.getInstance().open();
		isAdded = SmartFlatDatabase.getInstance().saveMessage(messages);
		SmartFlatDatabase.getInstance().close();
		return isAdded;
	} 
	
	public Cursor getMessages(String requestNumber){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getMessages(requestNumber);
		SmartFlatDatabase.getInstance().close();
		return details;	
	}
	
	public boolean saveContact(ContactDetails details){
		boolean isAdded = false;
		SmartFlatDatabase.getInstance().open();
		isAdded = SmartFlatDatabase.getInstance().saveContact(details);
		SmartFlatDatabase.getInstance().close();
		return isAdded;
	}
	
	public Cursor getAllContacts(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getAllContacts();
		SmartFlatDatabase.getInstance().close();
		return details;	
	}
	
	public boolean saveVisitor(VisitorDetails details){
		boolean isAdded = false;
		SmartFlatDatabase.getInstance().open();
		isAdded = SmartFlatDatabase.getInstance().saveVisitor(details);
		SmartFlatDatabase.getInstance().close();
		return isAdded;
	}
	
	public Cursor getVisitors(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getVisitors();
		SmartFlatDatabase.getInstance().close();
		return details;	
	}
	
	public void deleteDataFromAllTables(){
		SmartFlatDatabase.getInstance().open();
		SmartFlatDatabase.getInstance().deleteDataFromAllTables();
		SmartFlatDatabase.getInstance().close();
	}
	
	public void deleteDataFromSocietyDetails(){
		SmartFlatDatabase.getInstance().open();
		SmartFlatDatabase.getInstance().deleteDataFromSocietyDetails();
		SmartFlatDatabase.getInstance().close();
	}
	
	public Cursor getUnreadMessageCountForRequest(String requestNumber){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getUnreadMessageCountForRequest(requestNumber);
		SmartFlatDatabase.getInstance().close();
		return details;
	}
	
	public void setMessagesRead(String requestNumber)
	{
		SmartFlatDatabase.getInstance().open();
		SmartFlatDatabase.getInstance().setMessagesRead(requestNumber);
		SmartFlatDatabase.getInstance().close();
	}
	
	public Cursor getNoticeDetails(String noticeNumber){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getNoticeDetails(noticeNumber);
		SmartFlatDatabase.getInstance().close();
		return details;
	}
}
