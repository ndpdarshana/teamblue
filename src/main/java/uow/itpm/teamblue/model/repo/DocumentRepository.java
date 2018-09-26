package uow.itpm.teamblue.model.repo;

import org.springframework.data.repository.CrudRepository;
import uow.itpm.teamblue.model.Document;

import java.util.List;

public interface DocumentRepository extends CrudRepository<Document, Integer> {
    public List<Document> findByUserId(Integer userId);
}
