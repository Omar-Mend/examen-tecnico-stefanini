package com.examen.stefanini.context.pet.infraestructure.handler.response;

import java.time.OffsetDateTime;

public record CreatePetResponse(String transactionId, OffsetDateTime dateCreated, String status, String name) {
}
