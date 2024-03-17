package org.n11_bootcamp.user_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.n11_bootcamp.user_service.enums.EnumGender;
import org.n11_bootcamp.user_service.enums.EnumStatus;
import org.n11_bootcamp.user_service.general.BaseEntity;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "_USER")
public class User extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserSeq")
    @SequenceGenerator(name = "UserSeq", sequenceName = "USER_SEQ")
    @Id
    private Long id;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "SURNAME", length = 100, nullable = false)
    private String surname;

    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;

    @Column(name = "EMAIL", length = 100, nullable = false)
    private String email;

    @Column(name = "LATITUDE", nullable = false)
    private Double latitude;

    @Column(name = "LONGITUDE", nullable = false)
    private Double longitude;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", length = 30)
    private EnumGender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", length = 30, nullable = false)
    private EnumStatus status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, targetEntity = UserReview.class)
    private List<UserReview> userReviews;

}
