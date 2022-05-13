package ru.iveen.testsite.domain.entity.product;

import ru.iveen.testsite.domain.entity.storage.Attachment;
import ru.iveen.testsite.domain.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Polyakov Anton
 * @created 11.05.2022 1:25
 * @project testSite
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String title;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "favorites",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> userFavorites;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "photo")
    private Attachment photo;
}
