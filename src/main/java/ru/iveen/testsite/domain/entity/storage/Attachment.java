package ru.iveen.testsite.domain.entity.storage;

import ru.iveen.testsite.domain.entity.product.Product;
import ru.iveen.testsite.domain.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * @author Polyakov Anton
 * @created 10.05.2022 5:10
 * @project testSite
 */

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "attachments")
public class Attachment {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private EType type;

    private String extension;

    private Long userId;

    @OneToMany(mappedBy = "avatar", cascade = CascadeType.PERSIST) // связь для каскадного удаления attachment
    private List<User> user;

    @OneToMany(mappedBy = "photo", cascade = CascadeType.PERSIST) // связь для каскадного удаления attachment
    private List<Product> product;

    private String path;
    @PreRemove
    private void preRemove() {
        user.forEach( child -> child.setAvatar(null));
        product.forEach(child -> child.setPhoto(null));
    }

    public Attachment(String extension, EType type, Long userId) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.extension = extension;
        this.userId = userId;
    }

    public String getFilename() {
        return this.id + "." + this.extension;
    }
}
