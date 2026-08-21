import java.util.*; 
 
public class ContactManager { 
 
    public static void main(String[] args) { 
 
        HashMap<String, Contact> contacts = new HashMap<>(); 
 
        // Step 4: add contacts here 
        contacts.put("Neil Armstrong", new Contact("Neil Armstrong", "+1 007 020 1969"));
        contacts.put("Wernher von Braun", new Contact("Wernher von Braun", "+1 019 121 9770"));
        contacts.put("Gene Kranz", new Contact("Gene Kranz", "+1 008 017 1933"));
        contacts.put("Alan Shepard", new Contact("Alan Shepard", "+1 005 005 1961"));
        contacts.put("John Glenn", new Contact("John Glenn", "+1 002 020 1962"));

        // Step 5: look up a contact 
        Contact retrieve = contacts.get("Wernher von Braun"); // intentionall mispelled to test not found
        if (retrieve == null) System.out.println("Contact not found");
        else System.out.println(retrieve);

        retrieve = contacts.get("Fake Person");
        if (retrieve == null) System.out.println("Contact not found");
        else System.out.println(retrieve);

        // Step 6: print sorted list 
        ArrayList<Contact> sorted = new ArrayList<>(contacts.values());
        sorted.sort((a, b) -> a.getName().compareTo(b.getName()));

        System.out.println("=== All Contacts ===");
        for (Contact contact : sorted) {
            System.out.println(contact);
        }
    } 
}