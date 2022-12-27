package se.sundsvall.supportcenter.api.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Asset model")
public class Asset {

	@Schema(example = "123456", description = "Id of asset")
	private String id;

	@Schema(example = "4VVRN2", description = "Serial-number")
	private String serialNumber;

	@Schema(example = "WB12345NY", description = "Hardware-name")
	private String hardwareName;

	@Schema(example = "Computer", description = "Description of asset")
	private String description;

	@Schema(example = "00:00:0a:bb:28:fc", description = "MAC address for the unit")
	private String macAddress;

	@Schema(description = "Supplier status")
	private String supplierStatus;

	public static Asset create() {
		return new Asset();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Asset withId(String id) {
		this.id = id;
		return this;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Asset withSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
		return this;
	}

	public String getHardwareName() {
		return hardwareName;
	}

	public void setHardwareName(String hardwareName) {
		this.hardwareName = hardwareName;
	}

	public Asset withHardwareName(String hardwareName) {
		this.hardwareName = hardwareName;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Asset withDescription(String description) {
		this.description = description;
		return this;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public Asset withMacAddress(String macAddress) {
		this.macAddress = macAddress;
		return this;
	}

	public String getSupplierStatus() {
		return supplierStatus;
	}

	public void setSupplierStatus(String supplierStatus) {
		this.supplierStatus = supplierStatus;
	}

	public Asset withSupplierStatus(String supplierStatus) {
		this.supplierStatus = supplierStatus;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, hardwareName, id, macAddress, serialNumber, supplierStatus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Asset other = (Asset) obj;
		return Objects.equals(description, other.description) && Objects.equals(hardwareName, other.hardwareName)
				&& Objects.equals(id, other.id) && Objects.equals(macAddress, other.macAddress)
				&& Objects.equals(serialNumber, other.serialNumber)
				&& Objects.equals(supplierStatus, other.supplierStatus);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Asset [id=").append(id).append(", serialNumber=").append(serialNumber).append(", hardwareName=")
				.append(hardwareName).append(", description=").append(description).append(", macAddress=")
				.append(macAddress).append(", supplierStatus=").append(supplierStatus).append("]");
		return builder.toString();
	}
}
