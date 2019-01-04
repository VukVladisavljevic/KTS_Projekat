package com.kits.project.services.interfaces;

import com.kits.project.model.AccountAuthority;

public interface AccountAuthorityServiceInterface {
    AccountAuthority save(AccountAuthority accountAuthority);

    void remove(Long id);

    int AuthorityByAccId(Long id);
}
