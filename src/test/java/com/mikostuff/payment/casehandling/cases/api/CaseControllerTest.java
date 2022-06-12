package com.mikostuff.payment.casehandling.cases.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.util.UriComponentsBuilder;

import com.mikostuff.payment.casehandling.cases.fixture.CaseResponseFixture;
import com.mikostuff.payment.casehandling.mother.CaseRequestMother;

@IntegrationTest
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, // AFTER EACH because initial database setup is done by
															// Flyway
		scripts = { "classpath:db/migration/h2/clear_database.sql",
				"classpath:db/migration/h2/V1.0__create_case_tables.sql" })
class CaseControllerTest {

	private static final String BASE_URL = "/api/cases/";

	@Autowired
	private TestRestTemplate restTemplate;

	@ParameterizedTest
	@ValueSource(strings = { "PCH_1", "PCH_2", "PCH_3", "PCH_4", "PCH_5", "PCH_6", "PCH_7", "PCH_8", "PCH_9",
			"PCH_10" })
	final void findCase_givenExistingCaseId_thenReturnThatCase(String caseId) {
		ResponseEntity<CaseResponse> response = restTemplate.getForEntity(BASE_URL + caseId, CaseResponse.class);
		CaseResponse actualCase = response.getBody();

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(actualCase).isIn(CaseResponseFixture.expectedCases());
	}

	@ParameterizedTest
	@ValueSource(strings = { "PCH_11", "PCH_9001", " PCH_1 ", "MEMES_ARE_GOOD", "C O O K I E S", "P" })
	final void findCase_givenNonExistingCaseId_thenReturn404(String caseId) {
		ResponseEntity<CaseResponse> response = restTemplate.getForEntity(BASE_URL + caseId, CaseResponse.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@ParameterizedTest
	@MethodSource("provideCasesByResolutionState")
	final void findAllCases_givenResolutionState_thenReturnCasesWithThatResolution(CaseResolutionState caseResolution,
			List<CaseResponse> expectedCases) {
		ResponseEntity<CaseResponse[]> response = restTemplate
				.getForEntity(BASE_URL + "resolution/" + caseResolution.name(), CaseResponse[].class);
		List<CaseResponse> actualCases = List.of(response.getBody());

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(actualCases).containsExactlyInAnyOrderElementsOf(expectedCases);
	}

	private static Stream<Arguments> provideCasesByResolutionState() {
		return Stream.of(Arguments.of(CaseResolutionState.UNRESOLVED, CaseResponseFixture.expectedUnresolvedCases()),
				Arguments.of(CaseResolutionState.ACCEPTED, CaseResponseFixture.expectedAcceptedCases()),
				Arguments.of(CaseResolutionState.REJECTED, CaseResponseFixture.expectedRejectedCases()));
	}

	@Test
	final void createCase_givenValidCase_thenReturnCreatedCaseId() {
		ResponseEntity<String> response = restTemplate.postForEntity(BASE_URL, CaseRequestMother.validFinnishCase(),
				String.class);
		String actualCaseId = response.getBody();

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(actualCaseId).isEqualTo("PCH_11");
	}

	@ParameterizedTest
	@ValueSource(longs = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 })
	final void createCase_givenCaseWithExistingPaymentId_thenReturn400(Long existingPaymentId) {
		CaseRequest existingPaymentIdCase = new CaseRequest(new BigDecimal("777.77"), "USD", existingPaymentId,
				CountryCode.FI);
		ResponseEntity<String> response = restTemplate.postForEntity(BASE_URL, existingPaymentIdCase, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@ParameterizedTest
	@ValueSource(strings = { "CURRENCY", "DOLLAR", " USD ", "E U R", "EU", "E" })
	final void createCase_givenCaseWithWrongLengthCurrencyCode_thenReturn400(String wrongLengthCurrencyCode) {
		CaseRequest badCase = new CaseRequest(new BigDecimal("777.77"), wrongLengthCurrencyCode, 9001L, CountryCode.FI);
		ResponseEntity<String> response = restTemplate.postForEntity(BASE_URL, badCase, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@ParameterizedTest
	@EnumSource(CaseResolution.class)
	final void resolveCase_givenValidCaseId_thenReturnResolvedCaseId(CaseResolution caseResolution) {
		String unresolvedCaseId = "PCH_2";
		ResponseEntity<String> response = resolve(unresolvedCaseId, caseResolution);
		String actualCaseId = response.getBody();

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(actualCaseId).isEqualTo("PCH_2");
	}

	@ParameterizedTest
	@ValueSource(strings = { "PCH_11", "PCH_9001", " PCH_1 ", "MEMES_ARE_GOOD", "C O O K I E S", "P" })
	final void resolveCase_givenNonExistingCaseId_thenReturn404(String nonExistingCaseId) {
		CaseResolution resolution = CaseResolution.ACCEPTED; // doesn't matter when case doesn't exist
		ResponseEntity<String> response = resolve(nonExistingCaseId, resolution);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@ParameterizedTest
	@MethodSource("provideResolvedCases")
	final void resolveCase_givenAlreadyResolvedCaseId_thenReturn400(CaseResolution caseResolution,
			List<CaseResponse> resolvedCases) {
		resolvedCases.forEach(resolvedCase -> {
			ResponseEntity<String> response = resolve(resolvedCase.getId(), caseResolution);

			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		});
	}

	private static Stream<Arguments> provideResolvedCases() {
		// Try to accept/reject already accepted/rejected cases.
		return Stream.of(Arguments.of(CaseResolution.ACCEPTED, CaseResponseFixture.expectedAcceptedCases()),
				Arguments.of(CaseResolution.REJECTED, CaseResponseFixture.expectedAcceptedCases()),
				Arguments.of(CaseResolution.REJECTED, CaseResponseFixture.expectedRejectedCases()),
				Arguments.of(CaseResolution.ACCEPTED, CaseResponseFixture.expectedRejectedCases()));
	}

	private ResponseEntity<String> resolve(String caseId, CaseResolution caseResolution) {
		String url = UriComponentsBuilder.fromUriString(BASE_URL + "resolve/").path(caseId)
				.queryParam("caseResolution", caseResolution.name()).build().toString();
		return restTemplate.postForEntity(url, null, String.class);
	}
}
