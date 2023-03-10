package se.sundsvall.supportcenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import se.sundsvall.supportcenter.api.model.Asset;
import se.sundsvall.supportcenter.api.model.CreateAssetRequest;
import se.sundsvall.supportcenter.api.model.UpdateAssetRequest;
import se.sundsvall.supportcenter.integration.pob.POBClient;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static se.sundsvall.supportcenter.service.mapper.ConfigurationMapper.toAssetList;
import static se.sundsvall.supportcenter.service.mapper.ConfigurationMapper.toPobPayload;
import static se.sundsvall.supportcenter.service.mapper.CreateAssetMapper.toItemId;
import static se.sundsvall.supportcenter.service.mapper.CreateAssetMapper.toItemIdList;
import static se.sundsvall.supportcenter.service.mapper.CreateAssetMapper.toPobPayloadForCreatingConfigurationItem;
import static se.sundsvall.supportcenter.service.mapper.CreateAssetMapper.toPobPayloadForCreatingItem;
import static se.sundsvall.supportcenter.service.mapper.constant.ConfigurationMapperConstants.KEY_CONFIGURATION_ITEM;
import static se.sundsvall.supportcenter.service.mapper.constant.ConfigurationMapperConstants.TYPE_CONFIGURATION_ITEM;
import static se.sundsvall.supportcenter.service.mapper.constant.ConfigurationMapperConstants.TYPE_ITEM;

@Service
public class AssetService {

	private static final String CI_ID_ERROR_TEMPLATE = "No ID was found in POB-configurationitems";

	@Autowired
	private POBClient pobClient;


	public String createAsset(String pobKey, CreateAssetRequest createAssetRequest) {

		var id = "";
		final var itemIds = getItemsByModelName(pobKey, createAssetRequest.getModelName());

		if (Optional.ofNullable(itemIds).orElse(emptyList()).isEmpty()) {
			id = createItem(pobKey, createAssetRequest);
		} else {
			id = itemIds.stream().findFirst().orElseThrow(() -> Problem.valueOf(Status.BAD_GATEWAY, CI_ID_ERROR_TEMPLATE));
		}
		return createConfigurationItem(pobKey, id, createAssetRequest);
	}

	public void updateConfigurationItem(String pobKey, String id, UpdateAssetRequest request) {

		pobClient.updateConfigurationItem(pobKey, toPobPayload(id, request));
	}

	public List<Asset> getConfigurationItemsBySerialNumber(String pobKey, String serialNumber) {

		return toAssetList(pobClient.getConfigurationItemsBySerialNumber(pobKey, serialNumber));
	}

	private String createItem(String pobKey, CreateAssetRequest request) {

		return toItemId(pobClient.createItem(pobKey, toPobPayloadForCreatingItem(request)), TYPE_ITEM, KEY_CONFIGURATION_ITEM);
	}

	private String createConfigurationItem(String pobKey, String id, CreateAssetRequest request) {

		return toItemId(pobClient.createConfigurationItem(pobKey, toPobPayloadForCreatingConfigurationItem(id, request)), TYPE_CONFIGURATION_ITEM, KEY_CONFIGURATION_ITEM);
	}

	private List<String> getItemsByModelName(String pobKey, String modelName) {
		return toItemIdList(pobClient.getItemsByModelName(pobKey, modelName));
	}
}
