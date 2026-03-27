package com.jid.springwise.balance.model.balance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateBalanceRequestTest {

    @Test
    void builder_setsAllFields() {
        WiseBalanceCreate balance = WiseBalanceCreate.builder().currency("GBP").build();
        CreateBalanceRequest request = CreateBalanceRequest.builder()
            .profileId(123L)
            .idempotenceUuid("custom-uuid")
            .balance(balance)
            .build();

        assertEquals(123L, request.getProfileId());
        assertEquals("custom-uuid", request.getIdempotenceUuid());
        assertEquals(balance, request.getBalance());
    }

    @Test
    void builder_defaultIdempotenceUuid_isNotNull() {
        CreateBalanceRequest request = CreateBalanceRequest.builder().profileId(1L).build();
        assertNotNull(request.getIdempotenceUuid());
        assertFalse(request.getIdempotenceUuid().isEmpty());
    }

    @Test
    void builder_differentInstances_haveDifferentUuids() {
        CreateBalanceRequest a = CreateBalanceRequest.builder().profileId(1L).build();
        CreateBalanceRequest b = CreateBalanceRequest.builder().profileId(1L).build();
        assertNotEquals(a.getIdempotenceUuid(), b.getIdempotenceUuid());
    }

    @Test
    void noArgConstructor_allFieldsNull() {
        CreateBalanceRequest request = new CreateBalanceRequest();
        assertEquals(0L, request.getProfileId());
        assertNull(request.getIdempotenceUuid());
        assertNull(request.getBalance());
    }

    @Test
    void setters_updateFields() {
        CreateBalanceRequest request = new CreateBalanceRequest();
        request.setProfileId(99L);
        request.setIdempotenceUuid("my-uuid");
        request.setBalance(WiseBalanceCreate.builder().currency("USD").build());

        assertEquals(99L, request.getProfileId());
        assertEquals("my-uuid", request.getIdempotenceUuid());
        assertEquals("USD", request.getBalance().getCurrency());
    }

    @Test
    void equality_basedOnFields() {
        WiseBalanceCreate balance = WiseBalanceCreate.builder().currency("GBP").build();
        CreateBalanceRequest a = CreateBalanceRequest.builder()
            .profileId(1L).idempotenceUuid("uuid-1").balance(balance).build();
        CreateBalanceRequest b = CreateBalanceRequest.builder()
            .profileId(1L).idempotenceUuid("uuid-1").balance(balance).build();
        CreateBalanceRequest c = CreateBalanceRequest.builder()
            .profileId(2L).idempotenceUuid("uuid-2").build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

}
