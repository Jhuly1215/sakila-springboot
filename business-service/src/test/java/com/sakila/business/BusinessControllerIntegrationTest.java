package com.sakila.business;

import com.sakila.business.entity.Store;
import com.sakila.business.repository.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureGraphQlTester
@ActiveProfiles("test")
public class BusinessControllerIntegrationTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @Autowired
    private StoreRepository storeRepository;

    @BeforeEach
    void setUp() {
        storeRepository.deleteAll();
        Store store = new Store();
        store.setManagerStaffId(1);
        store.setAddressId(1);
        storeRepository.save(store);
    }

    @Test
    void testAllStoresQuery() {
        String query = "{ allStores { id managerStaffId } }";
        graphQlTester.document(query)
                .execute()
                .errors()
                .verify()
                .path("allStores[0].managerStaffId")
                .entity(Integer.class)
                .isEqualTo(1);
    }
}
