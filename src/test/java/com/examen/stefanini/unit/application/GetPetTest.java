package com.examen.stefanini.unit.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.examen.stefanini.context.pet.application.GetPet;
import com.examen.stefanini.context.pet.domain.entities.Pet;
import com.examen.stefanini.context.pet.domain.exceptions.GeneralException;
import com.examen.stefanini.context.pet.domain.repositories.PetRepository;
import com.examen.stefanini.context.pet.infraestructure.handler.response.PetResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class GetPetTest {

    private final PetRepository petRepository = Mockito.mock(PetRepository.class);
    private final GetPet getPet = new GetPet(petRepository);

    @Test
    void shouldReturnPetResponse() throws GeneralException {
        when(petRepository.findById(20L)).thenReturn(new Pet(20L, "Max", "available"));

        PetResponse response = getPet.getPetById(20L);

        assertEquals(20L, response.id());
        assertEquals("Max", response.name());
        assertEquals("available", response.status());
    }
}
