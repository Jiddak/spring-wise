# Coding Standards

## Imports

Three groups, each separated by a blank line:

```java
import com.example.project.model.domain.*;  // project base-package (wildcards OK for cohesive sub-packages)
import com.example.project.core.Config;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
```

## Domain Model Classes

External API response models.

```java
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyEntity {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("type")
    private MyEntityType type;

    @JsonProperty("amount")
    private BigDecimal amount;

}
```

Rules:
- Each Lombok/Jackson annotation on its own line
- `@JsonProperty("name")` on the line directly above the field
- Blank line after `{`, blank line between every field, blank line before `}`
- `@JsonInclude` on models mapping to external API responses; omit on internal DTOs

## Enums

```java
public enum MyEntityType {

    @JsonProperty("ACTIVE")
    ACTIVE,

    @JsonProperty("INACTIVE")
    INACTIVE,

}
```

Rules:
- `@JsonProperty` on its own line directly above each constant
- Blank line between each constant
- Blank line after `{` and before `}`

## Request Classes

Internal DTOs — no Jackson annotations.

```java
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEntityRequest {

    private long entityId;

    @Builder.Default
    private String idempotenceUuid = UUID.randomUUID().toString();

    private MyEntityCreate entity;

}
```

Rules:
- Primitive `long` for required IDs; boxed `Long` for nullable IDs
- `@Builder.Default` for fields with auto-generated defaults (e.g. UUID)

## Response Classes

Internal DTOs — no Jackson annotations.

```java
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEntityResponse {

    private MyEntity entity;

}
```

## HTTP Client Class

```java
public CreateEntityResponse createEntity(CreateEntityRequest request) {

    MyEntity result = restClient.post()
        .uri("v1/resources/{id}", Map.of("id", request.getEntityId()))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .header("X-idempotence-uuid", request.getIdempotenceUuid())
        .body(request.getEntity())
        .retrieve()
        .toEntity(MyEntity.class)
        .getBody();

    return CreateEntityResponse.builder().entity(result).build();

}
```

Rules:
- Blank line after `{`, blank line before `return`, blank line before `}`
- Each fluent `RestClient` call on its own line, indented 4 spaces from the variable assignment
- POST/PUT chain order: `.uri()` → `.contentType()` → `.accept()` → `.header()` → `.body()` → `.retrieve()` → `.toEntity()` → `.getBody()`

GET with path variables only:
```java
.uri("v1/resources/{id}", Map.of("id", request.getId()))
```

GET with query parameters:
```java
.uri(b -> b.path("v1/resources/{id}")
    .queryParam("filter", request.getFilter())
    .build(request.getId()))
```

Optional query parameters:
```java
.queryParamIfPresent("type", Optional.ofNullable(request.getType()))
```

List responses:
```java
.toEntity(new ParameterizedTypeReference<List<MyEntity>>() {})
```

## Types

| Use case | Type |
|---|---|
| Required ID (request) | `long` |
| Nullable ID (response) | `Long` |
| Monetary amount / rate | `BigDecimal` |
| Nullable boolean | `Boolean` |
| Timestamp | `String` (ISO 8601) |

## Naming

| Category | Pattern | Example |
|---|---|---|
| Domain model | `{Prefix}{Entity}` | `ApiBalance` |
| Request DTO | `{Action}{Entity}Request` | `CreateBalanceRequest` |
| Response DTO | `{Action}{Entity}Response` | `CreateBalanceResponse` |
| Enum (type) | `{Prefix}{Entity}Type` | `ApiBalanceType` |
| Enum (state) | `{Prefix}{Entity}State` | `ApiInvestmentState` |
| Package (models) | `model.{domain}` | `model.balance` |
| Package (client) | `client` | `client` |
