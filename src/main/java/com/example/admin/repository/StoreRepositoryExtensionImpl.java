package com.example.admin.repository;

import com.example.admin.dto.StoreResponseDto;
import com.example.admin.entity.QStore;
import com.example.admin.entity.QUser;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.example.admin.entity.QStore.*;
import static com.example.admin.entity.QUser.*;

public class StoreRepositoryExtensionImpl extends QuerydslRepositorySupport implements StoreRepositoryExtension{

    public StoreRepositoryExtensionImpl() {
        super(StoreRepositoryExtensionImpl.class);
    }

    @Override
    public List<StoreResponseDto> findAllByUser(Long userId) {
        //dto filed match
        List<StoreResponseDto> result = from(store)
                .join(store.user, user)
                .where(user.id.eq(userId))
                .select(Projections.bean(StoreResponseDto.class,
                                store.id,
                                store.name,
                                store.createdAt,
                                store.updatedAt,
                                user.id.as("userId")
                        )
                ).fetch();
        return result;
    }
}
