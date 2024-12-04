package ma.zyn.app.zynerator.security.bean;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;


@Entity
@Table(name = "role_app_user_app")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleUser   extends BaseEntity {

    private Long id;
    private Role role ;
    private User user ;


    public RoleUser(){
    super();
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY,generator="role_app_user_app_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    public Role getRole(){
        return this.role;
    }
    public void setRole(Role role){
        this.role = role;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    public User getUser(){
        return this.user;
    }
    public void setUser(User user){
        this.user = user;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleUser roleUser = (RoleUser) o;
        return id != null && id.equals(roleUser.id);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}

