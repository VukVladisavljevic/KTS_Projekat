package com.kits.project.services.interfaces;

import com.kits.project.model.Authority;

public interface AuthorityServiceInterface {
    Authority findByName(String name);
}
