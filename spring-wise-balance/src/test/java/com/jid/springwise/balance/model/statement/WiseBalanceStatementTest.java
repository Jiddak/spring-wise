package com.jid.springwise.balance.model.statement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jid.springwise.core.WiseApiMapper;
import com.jid.springwise.core.model.WiseMoney;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WiseBalanceStatementTest {

    private final ObjectMapper mapper = WiseApiMapper.getMapper();

    @Test
    void builder_setsAllFields() {
        WiseStatementAccountHolder holder = WiseStatementAccountHolder.builder().firstName("John").build();
        WiseStatementIssuer issuer = WiseStatementIssuer.builder().name("Wise").build();
        WiseMoney balance = new WiseMoney(new BigDecimal("500.00"), "GBP");
        WiseStatementQuery query = WiseStatementQuery.builder().currency("GBP").build();

        WiseBalanceStatement statement = WiseBalanceStatement.builder()
            .accountHolder(holder)
            .issuer(issuer)
            .bankDetails("some-details")
            .transactions(List.of())
            .endOfStatementBalance(balance)
            .query(query)
            .build();

        assertEquals(holder, statement.getAccountHolder());
        assertEquals(issuer, statement.getIssuer());
        assertEquals("some-details", statement.getBankDetails());
        assertNotNull(statement.getTransactions());
        assertEquals(balance, statement.getEndOfStatementBalance());
        assertEquals(query, statement.getQuery());
    }

    @Test
    void noArgConstructor_allFieldsNull() {
        WiseBalanceStatement statement = new WiseBalanceStatement();
        assertNull(statement.getAccountHolder());
        assertNull(statement.getIssuer());
        assertNull(statement.getBankDetails());
        assertNull(statement.getTransactions());
        assertNull(statement.getEndOfStatementBalance());
        assertNull(statement.getQuery());
    }

    @Test
    void equality_basedOnFields() {
        WiseStatementQuery query = WiseStatementQuery.builder().currency("GBP").build();
        WiseBalanceStatement a = WiseBalanceStatement.builder().query(query).build();
        WiseBalanceStatement b = WiseBalanceStatement.builder().query(query).build();
        WiseBalanceStatement c = WiseBalanceStatement.builder().build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    void serialisation_excludesNullFields() throws Exception {
        WiseBalanceStatement statement = WiseBalanceStatement.builder().build();
        String json = mapper.writeValueAsString(statement);
        assertEquals("{}", json);
    }

    @Test
    void serialisation_usesJsonPropertyNames() throws Exception {
        WiseStatementQuery query = WiseStatementQuery.builder().currency("GBP").build();
        WiseBalanceStatement statement = WiseBalanceStatement.builder().query(query).build();
        String json = mapper.writeValueAsString(statement);
        assertTrue(json.contains("\"query\""));
    }

    @Test
    void deserialisation_mapsJsonPropertyNames() throws Exception {
        String json = "{\"query\":{\"currency\":\"USD\"},\"transactions\":[]}";
        WiseBalanceStatement statement = mapper.readValue(json, WiseBalanceStatement.class);

        assertNotNull(statement.getQuery());
        assertEquals("USD", statement.getQuery().getCurrency());
        assertNotNull(statement.getTransactions());
    }

}
