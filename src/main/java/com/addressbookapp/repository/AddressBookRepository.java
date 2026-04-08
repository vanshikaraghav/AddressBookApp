package com.addressbookapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.addressbookapp.model.AddressBook;

public interface AddressBookRepository extends JpaRepository<AddressBook, Integer> {

}