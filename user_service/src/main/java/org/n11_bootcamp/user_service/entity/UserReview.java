package org.n11_bootcamp.user_service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.n11_bootcamp.user_service.enums.EnumRate;
import org.n11_bootcamp.user_service.general.BaseEntity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USER_REVIEW")
public class UserReview extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserReview")
    @SequenceGenerator(name = "UserReview", sequenceName = "USER_REVIEW_ID_SEQ")
    @Id
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_USER_REVIEW_USER"), nullable = false)
    private User user;

    @Column(name = "RESTAURANT_ID")
    private String restaurantId;

    @Column(name = "ORDER_ID")
    private Long orderId;

    @Enumerated(EnumType.STRING)
    @Column(name = "RATE", length = 30, nullable = false)
    private EnumRate rate;

    @Column(name = "COMMENT")
    private String comment;
}
