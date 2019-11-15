package tech.torbay.userservice;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tech.torbay.userservice.service.VendorService;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@ExtendWith(SpringExtension.class)
@SpringBootTest
public class VendorServiceTest {
	
	@Autowired
    VendorService vendorService;

    @Test
    @DisplayName("Check Vendor Email already exist or not")
    void testEmailVerificationService() throws Exception{
    	
//    	Client client = clientService.findByEmail("test@gmail.com"); 
        assertEquals(1,vendorService.findVendorUserByEmail("user@vendor.com").getUserId().intValue() );
        
    }
    
}