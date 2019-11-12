package tech.torbay.userservice.unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tech.torbay.userservice.service.ClientService;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ClientServiceTest {
	
	@Autowired
    ClientService clientService;

    @Test
    @DisplayName("Check Client Email already exist or not")
    void testEmailVerificationService() throws Exception{
    	
//    	Client client = clientService.findByEmail("test@gmail.com"); 
        assertEquals(6,clientService.findByEmail("test@gmail.com").getClientId() );
    }
    
}