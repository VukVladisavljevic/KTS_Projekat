package com.kits.project.services.implementations;

import com.kits.project.model.AccountAuthority;
import com.kits.project.repositories.AccountAuthorityRepository;
import com.kits.project.services.interfaces.AccountAuthorityServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountAuthorityServiceImplementation implements AccountAuthorityServiceInterface {
    @Autowired
    private AccountAuthorityRepository accountAuthorityRepository;

    @Override
    @Transactional(readOnly = false)
    public AccountAuthority save(AccountAuthority accountAuthority) {
        return this.accountAuthorityRepository.save(accountAuthority);
    }

    @Override
    @Transactional
    public void remove(Long id){
        this.accountAuthorityRepository.deleteById(id);
    }

    @Override
    public int AuthorityByAccId(Long id) {
        return this.accountAuthorityRepository.AuthorityByAccId(id);
    }
}
