package com.jid.springwise.balance.model.statement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetBalanceStatementResponseTest {

    @Test
    void builder_setsField() {
        WiseBalanceStatement statement = WiseBalanceStatement.builder()
            .query(WiseStatementQuery.builder().currency("GBP").build())
            .build();
        GetBalanceStatementResponse response = GetBalanceStatementResponse.builder()
            .statement(statement)
            .build();
        assertEquals(statement, response.getStatement());
    }

    @Test
    void noArgConstructor_fieldIsNull() {
        GetBalanceStatementResponse response = new GetBalanceStatementResponse();
        assertNull(response.getStatement());
    }

    @Test
    void setters_updateField() {
        GetBalanceStatementResponse response = new GetBalanceStatementResponse();
        WiseBalanceStatement statement = WiseBalanceStatement.builder().build();
        response.setStatement(statement);
        assertNotNull(response.getStatement());
    }

    @Test
    void equality_basedOnFields() {
        WiseBalanceStatement statement = WiseBalanceStatement.builder().build();
        GetBalanceStatementResponse a = GetBalanceStatementResponse.builder().statement(statement).build();
        GetBalanceStatementResponse b = GetBalanceStatementResponse.builder().statement(statement).build();
        GetBalanceStatementResponse c = GetBalanceStatementResponse.builder().build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

}
