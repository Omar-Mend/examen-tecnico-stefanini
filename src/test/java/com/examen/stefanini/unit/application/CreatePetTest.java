package com.examen.stefanini.unit.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.examen.stefanini.context.pet.application.CreatePet;
import com.examen.stefanini.context.pet.domain.entities.Pet;
import com.examen.stefanini.context.pet.domain.exceptions.GeneralException;
import com.examen.stefanini.context.pet.domain.repositories.PetRepository;
import com.examen.stefanini.context.pet.infraestructure.handler.request.CreatePetRequest;
import com.examen.stefanini.context.pet.infraestructure.handler.response.CreatePetResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CreatePetTest {

    private final PetRepository petRepository = Mockito.mock(PetRepository.class);
    private final CreatePet createPet = new CreatePet(petRepository);

    @Test
    void shouldCreatePetResponse() throws GeneralException {
        CreatePetRequest request = new CreatePetRequest(20L, "available", "Luna");
        when(petRepository.save(20L, "Luna", "available"))
                .thenReturn(new Pet(20L, "Luna", "available"));

        CreatePetResponse response = createPet.create(request);

        assertNotNull(response.transactionId());
        assertNotNull(response.dateCreated());
        assertEquals("available", response.status());
        assertEquals("Luna", response.name());
    }
}
