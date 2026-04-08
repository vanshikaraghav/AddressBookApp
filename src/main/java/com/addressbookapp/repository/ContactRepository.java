package com.addressbookapp.repository;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.addressbookapp.model.Contact;

public interface ContactRepository extends JpaRepository<Contact,Integer>{

    List<Contact> findByAddressBookId(Integer bookId);
    
    boolean existsByFirstNameAndLastNameAndAddressBookId(String firstName, String lastName, Integer addressBookId);

}