package se.sundsvall.supportcenter.service;

import generated.client.pob.PobPayload;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.supportcenter.api.model.Asset;
import se.sundsvall.supportcenter.integration.pob.POBClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAssetsServiceTest {

	@Mock
	private POBClient pobClientMock;

	@InjectMocks
	private AssetService assetService;


	@Test
	void getConfigurationItemsBySerialNumber() {

		// Parameter values
		final var pobKey = "pobKey";
		final var serialNumber = "serialNumber";
		final var id1 = "111";
		final var id2 = "222";

		// Mock
		when(pobClientMock.getConfigurationItemsBySerialNumber(pobKey, serialNumber)).thenReturn(setupListOfPobPayload("ConfigurationItem", "Id", id1, id2));

		// Call
		final var result = assetService.getConfigurationItemsBySerialNumber(pobKey, serialNumber);

		// Verification
		verify(pobClientMock).getConfigurationItemsBySerialNumber(pobKey, serialNumber);

		assertThat(result).extracting(Asset::getId).containsExactly(id1, id2);
	}

	private List<PobPayload> setupListOfPobPayload(String type, String key, String... values) {
		final var result = new ArrayList<PobPayload>();
		for (String value : values) {
			result.add(new PobPayload()
				.type(type)
				.data(Map.of(key, value)));
		}
		return result;
	}
}
