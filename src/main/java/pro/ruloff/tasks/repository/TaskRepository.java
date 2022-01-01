package pro.ruloff.tasks.repository;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.ruloff.tasks.domain.Task;

@Transactional
@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

  @Override
  List<Task> findAll();

  @Override
  Optional<Task> findById(Long id);

  @Override
  void deleteById(Long id);

  @Override
  long count();
}
