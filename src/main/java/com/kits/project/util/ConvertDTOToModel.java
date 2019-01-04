package com.kits.project.util;

import com.kits.project.DTOs.AccountCreateDTO;
import com.kits.project.model.User;
import org.modelmapper.ModelMapper;

public class ConvertDTOToModel {
    public ConvertDTOToModel(){}

    static ModelMapper mapper = new ModelMapper();

    public static User convertAccountCreateDTOToUser(AccountCreateDTO accountCreateDTO)
    {
        return mapper.map(accountCreateDTO, User.class);
    }

}
