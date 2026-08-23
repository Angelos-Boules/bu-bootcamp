import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach; 
 
public class ContactTest { 
 
    private Contact contact;
    
    @BeforeEach
    void setUp() {
        contact = new Contact("Ada Lovelace", "+1 617 555 0101");
    }

    @Test 
    void constructor_setsNameCorrectly() { 
        assertEquals("Ada Lovelace", contact.getName()); 
    } 
 
    @Test
    void constructor_setsPhoneCorrectly() { 
        assertEquals("+1 617 555 0101", contact.getPhone()); 
    } 
 
    @Test
    void getName_returnsExactString_notTransformed() { 
        assertEquals("Ada Lovelace", contact.getName());
    } 
 
    @Test
    void toString_containsName() { 
        assertTrue(contact.toString().contains("Ada Lovelace"));
    } 
 
    @Test
    void toString_containsPhone() {
        assertTrue(contact.toString().contains("+1 617 555 0101"));
    }
}