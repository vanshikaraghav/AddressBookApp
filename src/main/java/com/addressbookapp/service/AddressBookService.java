package com.addressbookapp.service;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import com.addressbookapp.exception.ContactNotFoundException;
import com.addressbookapp.model.AddressBook;
import com.addressbookapp.model.Contact;
import com.addressbookapp.repository.AddressBookRepository;
import com.addressbookapp.repository.ContactRepository;
@Service
public class AddressBookService {

	@Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private ContactRepository contactRepository;

    // UC6 Create AddressBook
    public AddressBook createAddressBook(AddressBook addressBook){
        return addressBookRepository.save(addressBook);
    }

    // UC2 Add Contact
    public Contact addContact(Integer bookId, Contact contact){

        AddressBook addressBook = addressBookRepository.findById(bookId)
                .orElseThrow(() -> new ContactNotFoundException("AddressBook not found"));
        
        contact.setAddressBook(addressBook);

        return contactRepository.save(contact);
    }
    
        public List<Contact> getContactsByAddressBook(Integer bookId){

            return contactRepository.findByAddressBookId(bookId);

        }

        // Get All Contacts
        public List<Contact> getContacts(){
            return contactRepository.findAll();
        }

        // UC3 Edit Contact
        public Contact editContact(Integer id, Contact updatedContact){

            Optional<Contact> optionalContact = contactRepository.findById(id);

            if(optionalContact.isEmpty()){
                throw new ContactNotFoundException("Contact not found with id: "+id);
            }

            Contact contact = optionalContact.get();

            contact.setFirstName(updatedContact.getFirstName());
            contact.setLastName(updatedContact.getLastName());
            contact.setAddress(updatedContact.getAddress());
            contact.setCity(updatedContact.getCity());
            contact.setState(updatedContact.getState());
            contact.setZip(updatedContact.getZip());
            contact.setPhone(updatedContact.getPhone());
            contact.setEmail(updatedContact.getEmail());

            return contactRepository.save(contact);
        }

        // UC4 Delete Contact
        public void deleteContact(Integer id){

            if(!contactRepository.existsById(id)){
                throw new ContactNotFoundException("Contact not found with id: "+id);
            }

            contactRepository.deleteById(id);
        }

        // UC5 Add Multiple Contacts
        public List<Contact> addMultipleContacts(List<Contact> contacts){
            return contactRepository.saveAll(contacts);
        }
}