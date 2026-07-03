package com.examen.stefanini.context.pet.application;

import com.examen.stefanini.context.pet.domain.entities.Pet;
import com.examen.stefanini.context.pet.domain.exceptions.GeneralException;
import com.examen.stefanini.context.pet.domain.repositories.PetRepository;
import com.examen.stefanini.context.pet.infraestructure.handler.request.CreatePetRequest;
import com.examen.stefanini.context.pet.infraestructure.handler.response.CreatePetResponse;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class CreatePet {
    private final PetRepository petRepository;

    public CreatePet(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public CreatePetResponse create(CreatePetRequest request) throws GeneralException {
        Pet pet = petRepository.save(new Pet(request.id(), request.name(), request.status()));
        System.out.println("Pet created in external API: " + pet);
        return new CreatePetResponse(
                UUID.randomUUID().toString(),
                OffsetDateTime.now(),
                pet.status(),
                pet.name()
        );
    }
}
