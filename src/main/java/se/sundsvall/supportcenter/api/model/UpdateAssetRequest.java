package se.sundsvall.supportcenter.api.model;

import java.util.Objects;

import javax.validation.Valid;

import io.swagger.v3.oas.annotations.media.Schema;
import se.sundsvall.supportcenter.api.validation.ValidMacAddress;

@Schema(description = "UpdateAssetRequest model")
public class UpdateAssetRequest {

	@Valid
	private Note note;

	@Schema(description = "User friendly name for hardware")
	private String description;
	
	@Schema(description = "Hardware name")
	private String hardwareName;
	
	@Schema(example = "00:00:0a:bb:28:fc", description = "MAC address for the unit")
	@ValidMacAddress
	private String macAddress;

	@Schema(description = "Supplier status")
	private String supplierStatus;
	
	public static UpdateAssetRequest create() {
		return new UpdateAssetRequest();
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	public UpdateAssetRequest withNote(Note note) {
		this.note = note;
		return this;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public UpdateAssetRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	
	public String getHardwareName() {
		return hardwareName;
	}

	public void setHardwareName(String hardwareName) {
		this.hardwareName = hardwareName;
	}

	public UpdateAssetRequest withHardwareName(String hardwareName) {
		this.hardwareName = hardwareName;
		return this;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public UpdateAssetRequest withMacAddress(String macAddress) {
		this.macAddress = macAddress;
		return this;
	}
	
	public String getSupplierStatus() {
		return supplierStatus;
	}

	public void setSupplierStatus(String supplierStatus) {
		this.supplierStatus = supplierStatus;
	}

	public UpdateAssetRequest withSupplierStatus(String supplierStatus) {
		this.supplierStatus = supplierStatus;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, hardwareName, macAddress, note, supplierStatus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpdateAssetRequest other = (UpdateAssetRequest) obj;
		return Objects.equals(description, other.description) && Objects.equals(hardwareName, other.hardwareName)
			&& Objects.equals(macAddress, other.macAddress) && Objects.equals(note, other.note)
			&& Objects.equals(supplierStatus, other.supplierStatus);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateAssetRequest [note=").append(note).append(", description=").append(description)
			.append(", hardwareName=").append(hardwareName).append(", macAddress=").append(macAddress)
			.append(", supplierStatus=").append(supplierStatus).append("]");
		return builder.toString();
	}
}
