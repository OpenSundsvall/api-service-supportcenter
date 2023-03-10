package se.sundsvall.supportcenter.integration.pob;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static se.sundsvall.supportcenter.integration.pob.configuration.POBConfiguration.CLIENT_ID;

import java.util.List;

import generated.client.pob.PobPayloadWithTriggerResults;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import generated.client.pob.PobPayload;
import generated.client.pob.SuspensionInfo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import se.sundsvall.supportcenter.integration.pob.configuration.POBConfiguration;

@FeignClient(name = CLIENT_ID, url = "${integration.pob.url}", configuration = POBConfiguration.class)
@CircuitBreaker(name = CLIENT_ID)
@Retry(name = CLIENT_ID)
public interface POBClient {

	/**
	 * Updates an existing case in POB.
	 * 
	 * @param pobKey
	 * @param payload the object with the updated case-attributes.
	 * @return
	 */
	@PostMapping(path = "cases", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	Void updateCase(@RequestHeader(AUTHORIZATION) String pobKey, @RequestBody PobPayload payload);

	/**
	 * Creates a case in POB.
	 *
	 * @param pobKey
	 * @param payload payload the object with the item to create.
	 * @return
	 */
	@PutMapping(path = "cases", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	List<PobPayloadWithTriggerResults> createCase(@RequestHeader(AUTHORIZATION) String pobKey, @RequestBody PobPayload payload);


	/**
	 * Get an existing case in POB.
	 * 
	 * @param pobKey
	 * @param caseId the ID of the case.
	 * @return The case
	 */
	@GetMapping(path = "cases/{caseId}", produces = APPLICATION_JSON_VALUE)
	PobPayload getCase(@RequestHeader(AUTHORIZATION) String pobKey, @PathVariable("caseId") String caseId);

	/**
	 * Returns a list of all available case-categories.
	 * 
	 * @param pobKey
	 * @return a list of available case-categories (max 10000)
	 */
	@Cacheable("case-categories")
	@GetMapping(path = "casecategories?Fields=Id&limit=10000", produces = APPLICATION_JSON_VALUE)
	List<PobPayload> getCaseCategories(@RequestHeader(AUTHORIZATION) String pobKey);

	/**
	 * Returns a list of of all available closure-codes.
	 * 
	 * @param pobKey
	 * @return a list of available closure-codes (max 10000)
	 */
	@Cacheable("closure-codes")
	@GetMapping(path = "closurecodes?Fields=Id&limit=10000", produces = APPLICATION_JSON_VALUE)
	List<PobPayload> getClosureCodes(@RequestHeader(AUTHORIZATION) String pobKey);

	/**
	 * Returns a list of configuration-items by serialNumber. The returned objects only contains the ID-attribute.
	 * 
	 * @param pobKey
	 * @param serialNumber
	 * @return a list of configuration-items
	 */
	@GetMapping(path = "configurationitems?Filter=SerialNumber={serialNumber}", produces = APPLICATION_JSON_VALUE)
	List<PobPayload> getConfigurationItemsBySerialNumber(@RequestHeader(AUTHORIZATION) String pobKey, @PathVariable("serialNumber") String serialNumber);

	/**
	 * Returns a list of items by model-name. The returned objects only contains the ID-attribute.
	 *
	 * @param pobKey
	 * @param modelName
	 * @return a list of configuration-items
	 */
	@GetMapping(path = "items?Filter=IdOption={modelName}", produces = APPLICATION_JSON_VALUE)
	List<PobPayload> getItemsByModelName(@RequestHeader(AUTHORIZATION) String pobKey, @PathVariable("modelName") String modelName);

	/**
	 * Creates an item
	 *
	 * @param pobKey
	 * @param payload the object with the updated configuration attributes
	 * @return list of pob-payloads with triggered results
	 */
	@PutMapping(path = "items", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	List<PobPayloadWithTriggerResults> createItem(@RequestHeader(AUTHORIZATION) String pobKey, @RequestBody PobPayload payload);

	/**
	 * Updates a configuration-item
	 *
	 * @param pobKey
	 * @param payload the object with configuration-items to create
	 * @return list of pob-payloads with triggered results
	 */
	@PutMapping(path = "configurationitems", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	List<PobPayloadWithTriggerResults> createConfigurationItem(@RequestHeader(AUTHORIZATION) String pobKey, @RequestBody PobPayload payload);

	/**
	 * Updates a configuration-item
	 *
	 * @param pobKey
	 * @param payload the object with the updated configuration attributes
	 * @return
	 */
	@PostMapping(path = "configurationitems", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	Void updateConfigurationItem(@RequestHeader(AUTHORIZATION) String pobKey, @RequestBody PobPayload payload);
	
	/**
	 * Suspend an existing case in POB.
	 * 
	 * @param pobKey
	 * @param caseId the ID of the case
	 * @param payload object with suspension information
	 * @return
	 */
	@PostMapping(path = "cases/{caseId}/suspension", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	Void suspendCase(@RequestHeader(AUTHORIZATION) String pobKey, @PathVariable("caseId") String caseId, @RequestBody SuspensionInfo payload);

	/**
	 * Get suspension information for a suspended case in POB, if case is not suspended a 404 will be thrown.
	 * 
	 * @param pobKey
	 * @param caseId the ID of the case
	 * @return Information about the suspension
	 */
	@GetMapping(path = "cases/{caseId}/suspension", produces = APPLICATION_JSON_VALUE)
	SuspensionInfo getSuspension(@RequestHeader(AUTHORIZATION) String pobKey, @PathVariable("caseId") String caseId);
	
	/**
	 * Unsuspend a suspended case in POB.
	 * 
	 * @param pobKey
	 * @param caseId the ID of the case
	 * @return
	 */
	@DeleteMapping(path = "cases/{caseId}/suspension", produces = APPLICATION_JSON_VALUE)
	Void deleteSuspension(@RequestHeader(AUTHORIZATION) String pobKey, @PathVariable("caseId") String caseId);
}
