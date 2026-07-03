package com.examen.stefanini.context.pet.infraestructure.repositories;

import com.examen.stefanini.context.pet.domain.entities.Pet;
import com.examen.stefanini.context.pet.domain.exceptions.GeneralException;
import com.examen.stefanini.context.pet.domain.repositories.PetRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@Repository
public class PetStorePetRepository implements PetRepository {

    private final RestClient petStoreRestClient;

    public PetStorePetRepository(RestClient petStoreRestClient) {
        this.petStoreRestClient = petStoreRestClient;
    }

    @Override
    public Pet findById(Long petId) throws GeneralException {

        try {
            var response = petStoreRestClient.get()
                    .uri("/pet/{petId}", petId)
                    .retrieve()
                    .body(PetStorePetResponse.class);

            return toDomain(response);

        } catch (RestClientResponseException e) {
            throw new GeneralException(e.getStatusCode().value(), e);

        } catch (Exception e) {
            throw new GeneralException(500, e);
        }
    }

    @Override
    public Pet save(Long petId, String name, String status) throws GeneralException {
        PetStorePetRequest request = new PetStorePetRequest(petId, name, status);

        try {
            var response = petStoreRestClient.post()
                    .uri("/pet")
                    .body(request)
                    .retrieve()
                    .body(PetStorePetResponse.class);

            return toDomain(response);

        } catch (RestClientResponseException e) {
            throw new GeneralException(e.getStatusCode().value(), e);

        } catch (Exception e) {
            throw new GeneralException(500, e);
        }


    }

    private Pet toDomain(PetStorePetResponse response) {
        if (response == null) {
            throw new IllegalStateException("Empty response from Petstore API");
        }
        return new Pet(response.id(), response.name(), response.status());
    }

    private record PetStorePetRequest(Long id, String name, String status) { }

    private record PetStorePetResponse(Long id, String name, String status) { }
}
