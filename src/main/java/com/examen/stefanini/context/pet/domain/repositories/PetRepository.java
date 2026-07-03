package com.examen.stefanini.context.pet.domain.repositories;

import com.examen.stefanini.context.pet.domain.entities.Pet;
import com.examen.stefanini.context.pet.domain.exceptions.GeneralException;

public interface PetRepository {

    Pet findById(Long petId) throws GeneralException;

    Pet save(Long petId, String name, String status) throws GeneralException;
}
