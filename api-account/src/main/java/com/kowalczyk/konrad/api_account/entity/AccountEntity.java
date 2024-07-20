package com.kowalczyk.konrad.api_account.entity;

import com.kowalczyk.konrad.common.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = AccountEntity.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountEntity {

    public static final String TABLE_NAME = "api_account";

    @Id
    @Column(name = "pesel")
    private String pesel;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "balance")
    private double balance;

    @Column(name = "currency")
    private String currency;

    @PrePersist
    protected void onCreate() {
        if (this.role == null) {
            this.role = Role.USER;
        }
    }


}
