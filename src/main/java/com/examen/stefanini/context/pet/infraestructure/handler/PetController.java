package com.examen.stefanini.context.pet.infraestructure.handler;

import com.examen.stefanini.context.pet.application.CreatePet;
import com.examen.stefanini.context.pet.application.GetPet;
import com.examen.stefanini.context.pet.domain.exceptions.GeneralException;
import com.examen.stefanini.context.pet.infraestructure.handler.request.CreatePetRequest;
import com.examen.stefanini.context.pet.infraestructure.handler.response.CreatePetResponse;
import com.examen.stefanini.context.pet.infraestructure.handler.response.PetResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pet")
public class PetController {

    private final GetPet getPet;
    private final CreatePet createPet;

    public PetController(GetPet getPet, CreatePet createPet) {
        this.getPet = getPet;
        this.createPet = createPet;
    }

    @GetMapping("/{petId}")
    public PetResponse getPetById(@PathVariable Long petId) throws GeneralException {
        return getPet.getPetById(petId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatePetResponse createPet(@RequestBody CreatePetRequest request) throws GeneralException {
        return createPet.create(request);
    }
}
