package se.sundsvall.supportcenter.service;

import generated.client.pob.PobPayload;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.supportcenter.api.model.CreateAssetRequest;
import se.sundsvall.supportcenter.integration.pob.POBClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static se.sundsvall.supportcenter.service.mapper.constant.ConfigurationMapperConstants.KEY_CI_STATUS;
import static se.sundsvall.supportcenter.service.mapper.constant.ConfigurationMapperConstants.KEY_DELIVERY_DATE;
import static se.sundsvall.supportcenter.service.mapper.constant.ConfigurationMapperConstants.KEY_DESCRIPTION;
import static se.sundsvall.supportcenter.service.mapper.constant.ConfigurationMapperConstants.KEY_END_WARRANTY_DATE;
import static se.sundsvall.supportcenter.service.mapper.constant.ConfigurationMapperConstants.KEY_ITEM;
import static se.sundsvall.supportcenter.service.mapper.constant.ConfigurationMapperConstants.KEY_ITEM_ID;
import static se.sundsvall.supportcenter.service.mapper.constant.ConfigurationMapperConstants.KEY_MAC_ADDRESS;
import static se.sundsvall.supportcenter.service.mapper.constant.ConfigurationMapperConstants.KEY_MANUFACTURER;
import static se.sundsvall.supportcenter.service.mapper.constant.ConfigurationMapperConstants.KEY_SERIAL_NUMBER;
import static se.sundsvall.supportcenter.service.mapper.constant.ConfigurationMapperConstants.TYPE_CONFIGURATION_ITEM;
import static se.sundsvall.supportcenter.service.mapper.constant.ConfigurationMapperConstants.TYPE_ITEM;

@ExtendWith(MockitoExtension.class)
class AssetServiceTest {

	@Mock
	private POBClient pobClientMock;

	@InjectMocks
	private AssetService assetService;

	@Captor
	private ArgumentCaptor<PobPayload> pobPayloadCaptorConfigurationItem;

	@Captor
	private ArgumentCaptor<PobPayload> pobPayloadCaptorItem;


	@Test
	void createAssetItemExist() {

		// Parameter values
		final var pobKey = "pobKey";
		final var modelName = "modelName";
		final var description = "description";
		final var manufacturer = "manufacturer";
		final var serialNumber = "serialNumber";
		final var macAddress = "macAddress";
		final var supplierStatus = "supplierStatus";
		final var itemId = "itemId";

		final var createAssetRequest = CreateAssetRequest.create()
			.withManufacturer(manufacturer)
			.withModelName(modelName)
			.withDescription(description)
			.withSerialNumber(serialNumber)
			.withMacAddress(macAddress)
			.withWarrantyEndDate(LocalDate.now().plusDays(360))
			.withSupplierStatus(supplierStatus)
			.withDeliveryDate(LocalDate.now().plusDays(5));

		// Mock
		when(pobClientMock.getItemsByModelName(pobKey, modelName)).thenReturn(setupListOfPobPayload("Item", List.of("Id", "IdOption", "Virtual.Manufacturer", "StartDate"),
			List.of(itemId, modelName, manufacturer, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))));

		// Call

		assetService.createAsset(pobKey, createAssetRequest);

		// Verification
		verify(pobClientMock).getItemsByModelName(pobKey, modelName);
		verify(pobClientMock).createConfigurationItem(eq(pobKey), pobPayloadCaptorConfigurationItem.capture());
		verifyNoMoreInteractions(pobClientMock);

		final var pobPayloadValueConfigurationItem = pobPayloadCaptorConfigurationItem.getValue();
		assertThat(pobPayloadValueConfigurationItem).isNotNull();
		assertThat(pobPayloadValueConfigurationItem.getType()).isEqualTo(TYPE_CONFIGURATION_ITEM);
		assertThat(pobPayloadValueConfigurationItem.getData()).isNotNull()
			.containsEntry(KEY_SERIAL_NUMBER, serialNumber)
			.containsEntry(KEY_MAC_ADDRESS, macAddress)
			.containsEntry(KEY_CI_STATUS, supplierStatus)
			.containsEntry(KEY_ITEM, itemId)
			.containsEntry(KEY_DELIVERY_DATE, createAssetRequest.getDeliveryDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
			.containsEntry(KEY_END_WARRANTY_DATE, createAssetRequest.getWarrantyEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	}

	@Test
	void createAssetItemDoesNotExist() {

		// Parameter values
		final var pobKey = "pobKey";
		final var modelName = "modelName";
		final var description = "description";
		final var manufacturer = "manufacturer";
		final var serialNumber = "serialNumber";
		final var macAddress = "macAddress";
		final var supplierStatus = "supplierStatus";

		final var createAssetRequest = CreateAssetRequest.create()
			.withManufacturer(manufacturer)
			.withModelName(modelName)
			.withDescription(description)
			.withSerialNumber(serialNumber)
			.withMacAddress(macAddress)
			.withWarrantyEndDate(LocalDate.now().plusDays(360))
			.withSupplierStatus(supplierStatus)
			.withDeliveryDate(LocalDate.now().plusDays(5));

		// Mock
		when(pobClientMock.getItemsByModelName(pobKey, modelName)).thenReturn(emptyList());

		// Call
		assetService.createAsset(pobKey, createAssetRequest);

		// Verification
		verify(pobClientMock).getItemsByModelName(pobKey, modelName);
		verify(pobClientMock).createItem(eq(pobKey), pobPayloadCaptorItem.capture());

		final var pobPayloadValueItem = pobPayloadCaptorItem.getValue();
		assertThat(pobPayloadValueItem).isNotNull();
		assertThat(pobPayloadValueItem.getType()).isEqualTo(TYPE_ITEM);
		assertThat(pobPayloadValueItem.getData()).isNotNull()
			.containsEntry(KEY_DESCRIPTION, description)
			.containsEntry(KEY_ITEM_ID, modelName)
			.containsEntry(KEY_MANUFACTURER, manufacturer);

		verify(pobClientMock).createConfigurationItem(eq(pobKey), pobPayloadCaptorConfigurationItem.capture());
		verifyNoMoreInteractions(pobClientMock);

		final var pobPayloadValueConfigurationItem = pobPayloadCaptorConfigurationItem.getValue();

		assertThat(pobPayloadValueConfigurationItem).isNotNull();
		assertThat(pobPayloadValueConfigurationItem.getType()).isEqualTo(TYPE_CONFIGURATION_ITEM);
		assertThat(pobPayloadValueConfigurationItem.getData()).isNotNull()
			.containsEntry(KEY_SERIAL_NUMBER, serialNumber)
			.containsEntry(KEY_MAC_ADDRESS, macAddress)
			.containsEntry(KEY_CI_STATUS, supplierStatus)
			.containsEntry(KEY_DELIVERY_DATE, createAssetRequest.getDeliveryDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
			.containsEntry(KEY_END_WARRANTY_DATE, createAssetRequest.getWarrantyEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

	}

	private List<PobPayload> setupListOfPobPayload(String type, List<String> keys, List<String> values) {
		final var result = new ArrayList<PobPayload>();
		int i = 0;
		for (String value : values) {
			result.add(new PobPayload()
				.type(type)
				.data(Map.of(keys.get(i), value)));
			i++;
		}
		return result;
	}
}
