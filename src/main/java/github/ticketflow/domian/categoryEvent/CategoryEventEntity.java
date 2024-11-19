package github.ticketflow.domian.categoryEvent;

import github.ticketflow.domian.category.CategoryEntity;
import github.ticketflow.domian.event.EventEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CategoryEvent")
@Builder
public class CategoryEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_event_id")
    private Long categoryEventId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private EventEntity eventEntity;

    public CategoryEventEntity(CategoryEntity categoryEntity, EventEntity eventEntity) {
        this.categoryEntity = categoryEntity;
        this.eventEntity = eventEntity;
    }
}
