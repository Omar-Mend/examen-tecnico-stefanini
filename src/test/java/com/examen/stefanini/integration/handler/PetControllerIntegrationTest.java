package com.examen.stefanini.integration.handler;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.examen.stefanini.context.pet.domain.entities.Pet;
import com.examen.stefanini.context.pet.domain.exceptions.GeneralException;
import com.examen.stefanini.context.pet.domain.repositories.PetRepository;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PetControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StubPetRepository petRepository;

    @BeforeEach
    void setUp() {
        petRepository.clear();
        petRepository.savePet(new Pet(10L, "Max", "available"));
    }

    @Test
    void shouldGetPetById() throws Exception {
        mockMvc.perform(get("/api/pet/{petId}", 10L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.name").value("Max"))
                .andExpect(jsonPath("$.status").value("available"));
    }

    @Test
    void whenPetDoesNotExist() throws Exception {
        mockMvc.perform(get("/api/pet/{petId}", 99L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Pet not found"));
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        @Primary
        StubPetRepository stubPetRepository() {
            return new StubPetRepository();
        }
    }

    static class StubPetRepository implements PetRepository {

        private final Map<Long, Pet> pets = new HashMap<>();

        @Override
        public Pet findById(Long petId) throws GeneralException {
            Pet pet = pets.get(petId);
            if (pet == null) {
                throw new GeneralException(404, "Pet not found");
            }

            return pet;
        }

        @Override
        public Pet save(Long petId, String name, String status) {
            Pet pet = new Pet(petId, name, status);
            pets.put(petId, pet);
            return pet;
        }

        void savePet(Pet pet) {
            pets.put(pet.id(), pet);
        }

        void clear() {
            pets.clear();
        }
    }
}
