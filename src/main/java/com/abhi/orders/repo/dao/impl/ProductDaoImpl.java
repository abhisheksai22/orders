package com.abhi.orders.repo.dao.impl;

import com.abhi.orders.entity.Product;
import com.abhi.orders.model.ProductPatchRequestBodyDto;
import com.abhi.orders.repo.dao.ProductDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public int patchUpdateProduct(Long productId, ProductPatchRequestBodyDto dto) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Product> update = cb.createCriteriaUpdate(Product.class);
        Root<Product> root = update.from(Product.class);
        boolean hasUpdate = false;

        if (dto.getDescription() != null) {
            update.set(root.get("description"), dto.getDescription());
            hasUpdate = true;
        }

        if (dto.getPrice() != null) {
            update.set(root.get("price"), dto.getPrice());
            hasUpdate = true;
        }

        if (dto.getInventoryId() != null) {
            update.set(root.get("inventoryId"), dto.getInventoryId());
            hasUpdate = true;
        }

        if (!hasUpdate) {
            return 0;
        }

        update.where(cb.equal(root.get("id"), productId));

        return entityManager.createQuery(update).executeUpdate();
    }
}
