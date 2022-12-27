package se.sundsvall.supportcenter.api.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

class UpdateAssetRequestTest {

	@Test
	void isValidBean() {
		assertThat(UpdateAssetRequest.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void hasValidBuilderMethods() {
		final var note = Note.create();
		final var description = "description";
		final var hardwareName = "hardwareName";
		final var macAddress = "macAddress";
		final var supplierStatus = "supplierStatus";
		
		final var updateCaseRequest = UpdateAssetRequest.create()
			.withNote(note)
			.withDescription(description)
			.withHardwareName(hardwareName)
			.withMacAddress(macAddress)
			.withSupplierStatus(supplierStatus);

		assertThat(updateCaseRequest).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(updateCaseRequest.getNote()).isEqualTo(note);
		assertThat(updateCaseRequest.getDescription()).isEqualTo(description);
		assertThat(updateCaseRequest.getHardwareName()).isEqualTo(hardwareName);
		assertThat(updateCaseRequest.getMacAddress()).isEqualTo(macAddress);
		assertThat(updateCaseRequest.getSupplierStatus()).isEqualTo(supplierStatus);
	}

	@Test
	void hasNoDirtOnCreatedBean() {
		assertThat(UpdateCaseRequest.create()).hasAllNullFieldsOrProperties();
	}
}
