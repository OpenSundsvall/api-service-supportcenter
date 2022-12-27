package se.sundsvall.supportcenter.service.mapper.constant;

import static se.sundsvall.supportcenter.service.SupportCenterStatus.ASSIGN_BACK;
import static se.sundsvall.supportcenter.service.SupportCenterStatus.CANCELLED;
import static se.sundsvall.supportcenter.service.SupportCenterStatus.DELIVERED;
import static se.sundsvall.supportcenter.service.SupportCenterStatus.DESPATCHED;
import static se.sundsvall.supportcenter.service.SupportCenterStatus.OPEN;
import static se.sundsvall.supportcenter.service.SupportCenterStatus.PICKING;
import static se.sundsvall.supportcenter.service.SupportCenterStatus.PROCESSED;
import static se.sundsvall.supportcenter.service.SupportCenterStatus.RESERVED;
import static se.sundsvall.supportcenter.service.SupportCenterStatus.RESOLVED;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.sundsvall.supportcenter.api.model.enums.NoteType;
import se.sundsvall.supportcenter.service.mapper.model.CustomStatusMapping;

public class CaseMapperConstants {

	private CaseMapperConstants() {}

	// Data-keys
	public static final String KEY_ID = "Id";
	public static final String KEY_CASE_CATEGORY = "CaseCategory";
	public static final String KEY_CASE_STATUS = "CaseStatus";
	public static final String KEY_CLOSURE_CODE = "ClosureCode";
	public static final String KEY_SHOP_CI_NAME = "Virtual.Shop_CI_Name";
	public static final String KEY_EXTERNAL_CASE_ID = "Virtual.ExternalCaseId";
	public static final String KEY_RESPONSIBLE = "Responsible";
	public static final String KEY_RESPONSIBLE_GROUP = "ResponsibleGroup";
	public static final String KEY_CI_INFO = "CIInfo.Ci";
	public static final String KEY_CI_INFO2 = "CIInfo2.Ci";
	public static final String KEY_DESCRIPTION = "Description";
	public static final String KEY_CASE_TYPE = "CaseType";
	public static final String KEY_CUSTOMER_CONTACT = "Contact.Customer";
	public static final String KEY_SUSPENSION = "Suspension";
	public static final String KEY_PRIORITY = "PriorityInfo.Priority";
	public static final String KEY_EXTERNAL_SERVICE_ID = "Virtual.Shop_ServiceIdExternalSync";
	public static final String KEY_EXTERNAL_ARTICLE_NUMBER = "Virtual.Shop_ExterntArtikelnummer";
	public static final String KEY_MANAGEMENT_COMPANY = "Virtual.Shop_ForvaltningBolag";
	public static final String KEY_PERSONAL = "Virtual.Shop_PersonligGemensam";
	public static final String KEY_OFFICE = "Virtual.Shop_Office";
	public static final String KEY_FREE_TEXT = "Virtual.Shop_Fritext";
	public static final String KEY_JOIN_CONTACT = "Virtual.Shop_JoinContact";
	public static final String KEY_RESPONSIBLE_NUMBER = "Virtual.Shop_Kst_Ansvarsnummer";
	public static final String KEY_SUBACCOUNT = "Virtual.Shop_Kst_Underkonto";
	public static final String KEY_BUSINESS_NUMBER = "Virtual.Shop_Kst_Verksamhetsnummer";
	public static final String KEY_ACTIVITY_NUMBER = "Virtual.Shop_Kst_Aktivitetsnummer";
	public static final String KEY_PROJECT_NUMBER = "Virtual.Shop_Kst_Projektnummer";
	public static final String KEY_OBJECT_NUMBER = "Virtual.Shop_Kst_Objektnummer";
	public static final String KEY_COUNTERPART = "Virtual.Shop_Kst_Motpart";
	public static final String KEY_CI_DESCRIPTION = "Virtual.Shop_CI_Description";
	public static final String KEY_STREET = "Virtual.Shop_Adr_Gatuadress";
	public static final String KEY_POSTAL_CODE = "Virtual.Shop_Adr_Postnr";
	public static final String KEY_CITY = "Virtual.Shop_Adr_Postort";
	public static final String KEY_CONTACT_PERSON = "Virtual.Shop_Kontaktperson";
	public static final String KEY_PHONE_NUMBER = "Virtual.Shop_Telefonnummer";
	public static final String KEY_EMAIL = "Virtual.Shop_Epost";
	public static final String KEY_ACTION_NEEDED = "Virtual.ActionNeeded";
	public static final String KEY_ACTION_NEEDED_DESCRIPTION = "Virtual.ActionNeededDescription";
	
	// Default-values
	public static final String DEFAULT_TYPE = "Case";

	// Format
	public static final String NOTE_STATUS_PART = "Status: '%s'";

	// Custom status mapping (I.e. custom logic for different statuses)
	public static final String STATUS_SOLVED = "Solved";
	public static final String STATUS_IN_PROCESS = "In Process";
	public static final String STATUS_CLOSED = "Closed";
	
	public static final String CLOSURE_CODE_CHANGE_OF_HARDWARE = "Byte av hårdvara";
	public static final String CLOSURE_CODE_ADVANIA_DEFAULT_SOLUTION_TEXT = "Advania - Övriga lösningar Incident";
	private static final String SEE_INTERNAL_NOTE_FOR_ACTION = "Åtgärdsbeskrivning i interna anteckningar";
	private static final String SECOND_LINE_IT = "Second Line IT";
	
	public static final Map<String, List<CustomStatusMapping>> CUSTOM_STATUS_MAP = Map.of(
		/**
		 * NETSET statuses (Order flow):
		 */
		PROCESSED.getValue(), List.of(
			CustomStatusMapping.create()
				.withAttributes(Map.of(KEY_CASE_STATUS, STATUS_IN_PROCESS))
				.withStatusNoteType(NoteType.WORKNOTE)),
		RESERVED.getValue(), List.of(
			CustomStatusMapping.create()
				.withAttributes(Map.of(KEY_CASE_STATUS, STATUS_IN_PROCESS))
				.withStatusNoteType(NoteType.WORKNOTE)),
		PICKING.getValue(), List.of(
			CustomStatusMapping.create()
				.withAttributes(Map.of(KEY_CASE_STATUS, STATUS_IN_PROCESS))
				.withStatusNoteType(NoteType.WORKNOTE)),
		DESPATCHED.getValue(), List.of(
			CustomStatusMapping.create()
				.withAttributes(Map.of(KEY_CASE_STATUS, STATUS_IN_PROCESS))
				.withStatusNoteType(NoteType.PROBLEM)),
		DELIVERED.getValue(), List.of(
			CustomStatusMapping.create()
				.withAttributes(Map.of(KEY_CASE_STATUS, STATUS_SOLVED, KEY_CLOSURE_CODE, CLOSURE_CODE_CHANGE_OF_HARDWARE))
				.withStatusNoteType(NoteType.WORKNOTE),
			CustomStatusMapping.create()
				.withAttributes(Map.of(KEY_CASE_STATUS, STATUS_SOLVED))
				.withStatusNoteType(NoteType.WORKNOTE)),
		/**
		 * CUBE statuses (Support flow):
		 */
		OPEN.getValue(), List.of(
			CustomStatusMapping.create()
				.withAttributes(Map.of(KEY_CASE_STATUS, STATUS_IN_PROCESS))
				.withStatusNoteType(NoteType.WORKNOTE)),
		CANCELLED.getValue(), List.of(
			CustomStatusMapping.create()
				.withAttributes(createAttributesForCancelledStatus())
				.withStatusNoteType(NoteType.WORKNOTE)),
		ASSIGN_BACK.getValue(), List.of(
			CustomStatusMapping.create()
				.withAttributes(createAtributesForAssignBackStatus())),
		RESOLVED.getValue(), List.of(
			CustomStatusMapping.create()
				.withAttributes(Map.of(KEY_CASE_STATUS, STATUS_SOLVED, KEY_CLOSURE_CODE, CLOSURE_CODE_ADVANIA_DEFAULT_SOLUTION_TEXT, KEY_EXTERNAL_CASE_ID, ""))
				.withStatusNoteType(NoteType.WORKNOTE),
			CustomStatusMapping.create()
				.withAttributes(Map.of(KEY_CASE_STATUS, STATUS_CLOSED, KEY_EXTERNAL_CASE_ID, ""))));
	
		/**
		 * Method used for the CANCELLED status, as this status need external case id value to be set to null, which Map.of() doesn't allow
		 * @return
		 */
		private static Map<String, Object> createAttributesForCancelledStatus() {
			Map<String, Object> map = from(Map.of(KEY_CASE_STATUS, STATUS_IN_PROCESS, KEY_RESPONSIBLE_GROUP, SECOND_LINE_IT));
			map.put(KEY_EXTERNAL_CASE_ID, null);
			return map;
		}
		
		/**
		 * Method used for the ASSIGN_BACK status, as this status need key responsible value to be set to null when programmatically 
		 * adding key responsible group, which Map.of() doesn't allow
		 * @return
		 */
		private static Map<String, Object> createAtributesForAssignBackStatus() {
			Map<String, Object> map = from(Map.of(KEY_ACTION_NEEDED, true, KEY_ACTION_NEEDED_DESCRIPTION, SEE_INTERNAL_NOTE_FOR_ACTION, KEY_RESPONSIBLE_GROUP, SECOND_LINE_IT));
			map.put(KEY_RESPONSIBLE, null);
			return map;
		}
		
		private static Map<String, Object> from(Map<String, Object> map) {
			Map<String, Object> hashMap = new HashMap<>();
			hashMap.putAll(map);
			return hashMap;
		}
	}