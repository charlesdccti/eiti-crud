package br.com.befullstack.eiticrud.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.Date;

/**
 * Entidade de Usuários
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private boolean isEnabled;

    @Temporal(TemporalType.DATE)
    private Date registerDate;

    private String name;

    private String surname;

    @Email
    private String email;

    private String phone;
}
