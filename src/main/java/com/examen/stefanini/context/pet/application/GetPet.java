package com.examen.stefanini.context.pet.application;

import com.examen.stefanini.context.pet.domain.exceptions.GeneralException;
import com.examen.stefanini.context.pet.domain.repositories.PetRepository;
import com.examen.stefanini.context.pet.infraestructure.handler.response.PetResponse;
import org.springframework.stereotype.Service;

@Service
public class GetPet {

    private final PetRepository petRepository;

    public GetPet(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public PetResponse getPetById(Long petId) throws GeneralException {
        var pet = petRepository.findById(petId);
        System.out.println("Pet obtained from external API: " + pet);

        return new PetResponse(pet.id(), pet.name(), pet.status());
    }


}
