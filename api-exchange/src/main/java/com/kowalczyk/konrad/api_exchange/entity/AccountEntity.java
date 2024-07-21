package com.kowalczyk.konrad.api_exchange.entity;

import com.kowalczyk.konrad.common.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table(AccountEntity.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountEntity {

    public static final String TABLE_NAME = "api_account";

    @Id
    @Column("pesel")
    private String pesel;

    @Column("role")
    private Role role;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("balance")
    private double balance;

    @Column("currency")
    private String currency;


}
