package web.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;


@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="role")
    private String role;

    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }


    @Override
    public String getAuthority() {
        return role;
    }
}
