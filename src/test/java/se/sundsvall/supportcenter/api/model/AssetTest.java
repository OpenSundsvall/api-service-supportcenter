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

class AssetTest {

	@Test
	void isValidBean() {
		assertThat(Asset.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void hasValidBuilderMethods() {

		final var id = "id";
		final var serialNumber = "serialNumber";
		final var hardwareName = "hardwareName";
		final var description = "description";
		final var macAddress = "macAddress";
		final var supplierStatus = "supplierStatus";
		
		final var asset = Asset.create()
			.withId(id)
			.withSerialNumber(serialNumber)
			.withHardwareName(hardwareName)
			.withDescription(description)
			.withMacAddress(macAddress)
			.withSupplierStatus(supplierStatus);

		assertThat(asset).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(asset.getId()).isEqualTo(id);
		assertThat(asset.getSerialNumber()).isEqualTo(serialNumber);
		assertThat(asset.getHardwareName()).isEqualTo(hardwareName);
		assertThat(asset.getDescription()).isEqualTo(description);
		assertThat(asset.getMacAddress()).isEqualTo(macAddress);
		assertThat(asset.getSupplierStatus()).isEqualTo(supplierStatus);
	}

	@Test
	void hasNoDirtOnCreatedBean() {
		assertThat(Asset.create()).hasAllNullFieldsOrProperties();
	}
}
