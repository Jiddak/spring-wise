package com.jid.springwise.balance.model.balance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveBalanceFundsRequestTest {

    @Test
    void builder_setsAllFields() {
        WiseBalanceMovement movement = WiseBalanceMovement.builder().sourceBalanceId(1L).build();
        MoveBalanceFundsRequest request = MoveBalanceFundsRequest.builder()
            .profileId(123L)
            .idempotenceUuid("custom-uuid")
            .balanceMovement(movement)
            .build();

        assertEquals(123L, request.getProfileId());
        assertEquals("custom-uuid", request.getIdempotenceUuid());
        assertEquals(movement, request.getBalanceMovement());
    }

    @Test
    void builder_defaultIdempotenceUuid_isNotNull() {
        MoveBalanceFundsRequest request = MoveBalanceFundsRequest.builder().profileId(1L).build();
        assertNotNull(request.getIdempotenceUuid());
        assertFalse(request.getIdempotenceUuid().isEmpty());
    }

    @Test
    void builder_differentInstances_haveDifferentUuids() {
        MoveBalanceFundsRequest a = MoveBalanceFundsRequest.builder().profileId(1L).build();
        MoveBalanceFundsRequest b = MoveBalanceFundsRequest.builder().profileId(1L).build();
        assertNotEquals(a.getIdempotenceUuid(), b.getIdempotenceUuid());
    }

    @Test
    void noArgConstructor_allFieldsNull() {
        MoveBalanceFundsRequest request = new MoveBalanceFundsRequest();
        assertEquals(0L, request.getProfileId());
        assertNull(request.getIdempotenceUuid());
        assertNull(request.getBalanceMovement());
    }

    @Test
    void setters_updateFields() {
        MoveBalanceFundsRequest request = new MoveBalanceFundsRequest();
        request.setProfileId(77L);
        request.setIdempotenceUuid("my-uuid");

        assertEquals(77L, request.getProfileId());
        assertEquals("my-uuid", request.getIdempotenceUuid());
    }

    @Test
    void equality_basedOnFields() {
        MoveBalanceFundsRequest a = MoveBalanceFundsRequest.builder()
            .profileId(1L).idempotenceUuid("uuid-1").build();
        MoveBalanceFundsRequest b = MoveBalanceFundsRequest.builder()
            .profileId(1L).idempotenceUuid("uuid-1").build();
        MoveBalanceFundsRequest c = MoveBalanceFundsRequest.builder()
            .profileId(2L).idempotenceUuid("uuid-2").build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

}
