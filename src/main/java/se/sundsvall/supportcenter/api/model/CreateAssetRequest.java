package se.sundsvall.supportcenter.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import se.sundsvall.supportcenter.api.validation.ValidMacAddress;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;

@Schema(description = "CreateAssetRequest model")
public class CreateAssetRequest {

	@Schema(example = "x_dell", description = "Manufacturer", required = true)
	private String manufacturer;

	@Schema(example = "Latitude 9000", description = "Model name", required = true)
	@NotBlank(message = "must be provided")
	private String modelName;

	@Schema(example = "Latitude 9000", description = "Description of asset", required = true)
	private String description;

	@Schema(description = "Serial number", required = true)
	@NotBlank(message = "must be provided")
	private String serialNumber;

	@Schema(example = "00:00:0a:bb:28:fc", description = "MAC address for the unit", required = true)
	@ValidMacAddress
	private String macAddress;

	@Schema(example = "2022-01-01", description = "Warranty end date", required = true)
	private LocalDate warrantyEndDate;

	@Schema(example = "0", description = "Status", required = true)
	private String supplierStatus;

	@Schema(example = "2022-01-01", description = "Delivery date", required = true)
	private LocalDate deliveryDate;

	public static CreateAssetRequest create() {
		return new CreateAssetRequest();
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public CreateAssetRequest withManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
		return this;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public CreateAssetRequest withModelName(String modelName) {
		this.modelName = modelName;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CreateAssetRequest withDescription(String description) {
		this.description = description;
		return this;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public CreateAssetRequest withSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
		return this;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public CreateAssetRequest withMacAddress(String macAddress) {
		this.macAddress = macAddress;
		return this;
	}

	public LocalDate getWarrantyEndDate() {
		return warrantyEndDate;
	}

	public void setWarrantyEndDate(LocalDate warrantyEndDate) {
		this.warrantyEndDate = warrantyEndDate;
	}

	public CreateAssetRequest withWarrantyEndDate(LocalDate warrantyEndDate) {
		this.warrantyEndDate = warrantyEndDate;
		return this;
	}

	public String getSupplierStatus() {
		return supplierStatus;
	}

	public void setSupplierStatus(String supplierStatus) {
		this.supplierStatus = supplierStatus;
	}

	public CreateAssetRequest withSupplierStatus(String supplierStatus) {
		this.supplierStatus = supplierStatus;
		return this;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public CreateAssetRequest withDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CreateAssetRequest that = (CreateAssetRequest) o;
		return Objects.equals(manufacturer, that.manufacturer) && Objects.equals(modelName, that.modelName) &&
				   Objects.equals(description, that.description) && Objects.equals(serialNumber, that.serialNumber) &&
				   Objects.equals(macAddress, that.macAddress) && Objects.equals(warrantyEndDate, that.warrantyEndDate) &&
				   Objects.equals(supplierStatus, that.supplierStatus) && Objects.equals(deliveryDate, that.deliveryDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(manufacturer, modelName, description, serialNumber, macAddress, warrantyEndDate, supplierStatus, deliveryDate);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CreateAssetRequest [manufacturer=").append(manufacturer).append(", modelName=").append(modelName).append(", description=").append(description)
			.append(", serialNumber=").append(serialNumber).append(", macAddress=").append(macAddress).append(", warrantyEndDate=").append(warrantyEndDate).append(", supplierStatus=")
			.append(supplierStatus).append(", deliveryDate=").append(deliveryDate).append("]");
		return builder.toString();
	}
}
