package com.addressbookapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.addressbookapp.model.AddressBook;
import com.addressbookapp.model.Contact;
import com.addressbookapp.service.AddressBookService;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookService service;

    @GetMapping("/home")
    public String greet() {
        return "Welcome To Address Book";
    }
    
    //UC-5 Create AddressBook
    @PostMapping("/createBook")
    public AddressBook createAddressBook(@RequestBody AddressBook addressBook){
        return service.createAddressBook(addressBook);
    }

    //UC-1 Add Contact
    @PostMapping("/{bookId}/addContact")
    public Contact addContact(@PathVariable Integer bookId,
                              @RequestBody Contact contact){

        return service.addContact(bookId, contact);
    }
    
    // Get Contacts according to the addressbook type
    @GetMapping("/{bookId}/contacts")
    public List<Contact> getContactsByAddressBook(@PathVariable Integer bookId){
        return service.getContactsByAddressBook(bookId);
    }

    //UC-2 Get All Contacts
    @GetMapping("/all")
    public List<Contact> getContacts(){
        return service.getContacts();
    }

    //UC-3 Edit Contact
    @PutMapping("/edit/{id}")
    public Contact editContact(@PathVariable Integer id, @RequestBody Contact contact){
        return service.editContact(id, contact);
    }

    // UC-4 Delete Contact
    @DeleteMapping("/delete/{id}")
    public void deleteContact(@PathVariable Integer id){
        service.deleteContact(id);
    }

    //UC-6 Add Multiple Contacts
    @PostMapping("/{bookId}/addMultiple")
    public List<Contact> addMultipleContacts(@PathVariable Integer bookId,@RequestBody List<Contact> contacts){
        return service.addMultipleContacts(contacts);
    }

}