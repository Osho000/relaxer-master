package com.example.relaxer.mapper;

import com.example.relaxer.DTO.AccountDTO;
import com.example.relaxer.DTO.UserDTO;
import com.example.relaxer.entity.Account;
import com.example.relaxer.entity.Hobby;
import com.example.relaxer.entity.Passport;
import com.example.relaxer.entity.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {
    public static User toEntity(UserDTO dto, List<Hobby> hobbies){
        User user = new User();
        user.setName(dto.name());
        user.setAge(dto.age());

        Passport passport = new Passport();
        passport.setNumber(dto.passport().getNumber());
        user.setPassport(passport);

        if (dto.accounts() != null){
            List<Account> accountList = dto.accounts().stream().map(acc -> {
                Account a = new Account();
                a.setTitle(acc.title());
                a.setUser(user);
                return a;
            }).collect(Collectors.toList());
            user.setAccounts(accountList);
        }

        user.setHobbies(Set.copyOf(hobbies));
        return user;
    }

    public static UserDTO toDTO(User user){
        List<AccountDTO> accountDTOS = user.getAccounts().stream()
                .map(a -> new AccountDTO(a.getTitle()))
                .toList();

        Set<String> hobbyNames = user.getHobbies().stream()
                .map(Hobby::getType)
                .collect(Collectors.toSet());

        return new UserDTO(
                user.getName(),
                user.getAge(),
                user.getPassport(),
                accountDTOS,
                hobbyNames
        );
    }
}