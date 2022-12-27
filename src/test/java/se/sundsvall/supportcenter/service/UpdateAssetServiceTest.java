package se.sundsvall.supportcenter.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.supportcenter.api.model.UpdateAssetRequest;
import se.sundsvall.supportcenter.integration.pob.POBClient;
import se.sundsvall.supportcenter.service.mapper.ConfigurationMapper;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UpdateAssetServiceTest {

	@Mock
	private POBClient pobClientMock;

	@InjectMocks
	private AssetService assetService;

	@Test
	void updateConfigurationItem() {

		// Parameter values
		final var pobKey = "pobKey";
		final var updateAssetRequest = UpdateAssetRequest.create().withHardwareName("hardwareName");
		final var id = "123456";

		// Call
		assetService.updateConfigurationItem(pobKey, id, updateAssetRequest);

		// Verification
		verify(pobClientMock).updateConfigurationItem(pobKey, ConfigurationMapper.toPobPayload(id, updateAssetRequest));
	}
}
