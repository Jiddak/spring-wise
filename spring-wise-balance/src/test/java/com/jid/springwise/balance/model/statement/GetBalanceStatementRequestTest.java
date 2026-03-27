package com.jid.springwise.balance.model.statement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetBalanceStatementRequestTest {

    @Test
    void builder_setsAllFields() {
        GetBalanceStatementRequest request = GetBalanceStatementRequest.builder()
            .profileId(123L)
            .balanceId(456L)
            .currency("GBP")
            .intervalStart("2024-01-01T00:00:00Z")
            .intervalEnd("2024-01-31T23:59:59Z")
            .type(WiseStatementType.FLAT)
            .statementLocale("en-GB")
            .build();

        assertEquals(123L, request.getProfileId());
        assertEquals(456L, request.getBalanceId());
        assertEquals("GBP", request.getCurrency());
        assertEquals("2024-01-01T00:00:00Z", request.getIntervalStart());
        assertEquals("2024-01-31T23:59:59Z", request.getIntervalEnd());
        assertEquals(WiseStatementType.FLAT, request.getType());
        assertEquals("en-GB", request.getStatementLocale());
    }

    @Test
    void noArgConstructor_fieldsAreDefaulted() {
        GetBalanceStatementRequest request = new GetBalanceStatementRequest();
        assertEquals(0L, request.getProfileId());
        assertEquals(0L, request.getBalanceId());
        assertNull(request.getCurrency());
        assertNull(request.getIntervalStart());
        assertNull(request.getIntervalEnd());
        assertNull(request.getType());
        assertNull(request.getStatementLocale());
    }

    @Test
    void setters_updateFields() {
        GetBalanceStatementRequest request = new GetBalanceStatementRequest();
        request.setProfileId(99L);
        request.setBalanceId(88L);
        request.setCurrency("EUR");
        request.setType(WiseStatementType.COMPACT);
        request.setStatementLocale("de-DE");

        assertEquals(99L, request.getProfileId());
        assertEquals(88L, request.getBalanceId());
        assertEquals("EUR", request.getCurrency());
        assertEquals(WiseStatementType.COMPACT, request.getType());
        assertEquals("de-DE", request.getStatementLocale());
    }

    @Test
    void optionalFields_canBeNull() {
        GetBalanceStatementRequest request = GetBalanceStatementRequest.builder()
            .profileId(1L)
            .balanceId(2L)
            .currency("GBP")
            .intervalStart("2024-01-01T00:00:00Z")
            .intervalEnd("2024-01-31T23:59:59Z")
            .build();

        assertNull(request.getType());
        assertNull(request.getStatementLocale());
    }

    @Test
    void equality_basedOnFields() {
        GetBalanceStatementRequest a = GetBalanceStatementRequest.builder()
            .profileId(1L).balanceId(2L).currency("GBP").build();
        GetBalanceStatementRequest b = GetBalanceStatementRequest.builder()
            .profileId(1L).balanceId(2L).currency("GBP").build();
        GetBalanceStatementRequest c = GetBalanceStatementRequest.builder()
            .profileId(3L).balanceId(4L).currency("USD").build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

}
