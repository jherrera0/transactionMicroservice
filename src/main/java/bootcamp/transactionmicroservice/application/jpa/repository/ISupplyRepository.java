package bootcamp.transactionmicroservice.application.jpa.repository;

import bootcamp.transactionmicroservice.application.jpa.entity.SupplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ISupplyRepository extends JpaRepository<SupplyEntity, Long> {
        SupplyEntity findByProductIdAndQuantityAndSupplierIdAndSupplierAndDateAndStatus(
                Long productId, Long quantity, Long supplierId, String supplier, LocalDateTime date, String status);

        @Modifying
    @Query("UPDATE SupplyEntity s SET s.productId = :productId, s.quantity = :quantity, s.supplierId = :supplierId, "+"s.supplier = :supplier, s.date = :date, s.status = :status WHERE s.id = :id")
    void updateBySupply(@Param("id") Long id,
                       @Param("productId") Long productId,
                       @Param("quantity") Long quantity,
                       @Param("supplierId") Long supplierId,
                       @Param("supplier") String supplier,
                       @Param("date") LocalDateTime date,
                       @Param("status") String status);
}