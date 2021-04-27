package runner.repository;

import runner.entity.Conclusion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConclusionRepository extends JpaRepository<Conclusion, Integer> {

}