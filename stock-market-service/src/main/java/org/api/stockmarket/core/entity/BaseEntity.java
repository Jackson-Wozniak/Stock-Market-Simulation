package org.api.stockmarket.core.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.core.exception.base.BaseException;
import org.springframework.http.HttpStatus;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public abstract static class NotFoundException extends BaseException {

        protected NotFoundException(String message, String controllerName){
            super(HttpStatus.NOT_FOUND, message, controllerName);
        }
    }
}
