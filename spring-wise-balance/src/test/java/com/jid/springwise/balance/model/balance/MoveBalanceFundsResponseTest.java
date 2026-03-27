package com.jid.springwise.balance.model.balance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveBalanceFundsResponseTest {

    @Test
    void builder_setsField() {
        WiseBalanceMovementResponse movement = WiseBalanceMovementResponse.builder().id(1L).state("COMPLETED").build();
        MoveBalanceFundsResponse response = MoveBalanceFundsResponse.builder().movement(movement).build();
        assertEquals(movement, response.getMovement());
    }

    @Test
    void noArgConstructor_fieldIsNull() {
        MoveBalanceFundsResponse response = new MoveBalanceFundsResponse();
        assertNull(response.getMovement());
    }

    @Test
    void setters_updateField() {
        MoveBalanceFundsResponse response = new MoveBalanceFundsResponse();
        WiseBalanceMovementResponse movement = WiseBalanceMovementResponse.builder().id(2L).build();
        response.setMovement(movement);
        assertEquals(2L, response.getMovement().getId());
    }

    @Test
    void equality_basedOnFields() {
        WiseBalanceMovementResponse movement = WiseBalanceMovementResponse.builder().id(1L).build();
        MoveBalanceFundsResponse a = MoveBalanceFundsResponse.builder().movement(movement).build();
        MoveBalanceFundsResponse b = MoveBalanceFundsResponse.builder().movement(movement).build();
        MoveBalanceFundsResponse c = MoveBalanceFundsResponse.builder().build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

}
