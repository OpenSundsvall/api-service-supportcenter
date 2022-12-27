package se.sundsvall.supportcenter.service;

import static org.assertj.core.api.Assertions.assertThat;
import static se.sundsvall.supportcenter.service.SupportCenterStatus.ASSIGN_BACK;
import static se.sundsvall.supportcenter.service.SupportCenterStatus.AWAITING_INFO;
import static se.sundsvall.supportcenter.service.SupportCenterStatus.CANCELLED;
import static se.sundsvall.supportcenter.service.SupportCenterStatus.DELIVERED;
import static se.sundsvall.supportcenter.service.SupportCenterStatus.DESPATCHED;
import static se.sundsvall.supportcenter.service.SupportCenterStatus.OPEN;
import static se.sundsvall.supportcenter.service.SupportCenterStatus.PARTIALLY_DESPATCHED;
import static se.sundsvall.supportcenter.service.SupportCenterStatus.PICKING;
import static se.sundsvall.supportcenter.service.SupportCenterStatus.PROCESSED;
import static se.sundsvall.supportcenter.service.SupportCenterStatus.RESERVED;
import static se.sundsvall.supportcenter.service.SupportCenterStatus.RESOLVED;

import org.junit.jupiter.api.Test;

class SupportCenterStatusTest {

	@Test
	void testEnumValues() {
		assertThat(ASSIGN_BACK.getValue()).isEqualTo("AssignBack");
		assertThat(AWAITING_INFO.getValue()).isEqualTo("Awaiting info");
		assertThat(CANCELLED.getValue()).isEqualTo("Cancelled");
		assertThat(DELIVERED.getValue()).isEqualTo("Delivered");
		assertThat(DESPATCHED.getValue()).isEqualTo("Despatched");
		assertThat(OPEN.getValue()).isEqualTo("Open");
		assertThat(PARTIALLY_DESPATCHED.getValue()).isEqualTo("Partially Despatched");
		assertThat(PICKING.getValue()).isEqualTo("Picking");
		assertThat(PROCESSED.getValue()).isEqualTo("Processed");
		assertThat(RESERVED.getValue()).isEqualTo("Reserved");
		assertThat(RESOLVED.getValue()).isEqualTo("Resolved");
	}
}