package az.edu.turing.usermanagmentsystem.dao.repository;

import az.edu.turing.usermanagmentsystem.dao.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, UUID> {
    List<ProfileEntity> findByUserId(UUID userId);
}
