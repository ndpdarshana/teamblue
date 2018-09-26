package uow.itpm.teamblue.model.repo;

import org.springframework.data.repository.CrudRepository;
import uow.itpm.teamblue.model.Document;

public interface DocumentRepository extends CrudRepository<Document, Integer> {}
