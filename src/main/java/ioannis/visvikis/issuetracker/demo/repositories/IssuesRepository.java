package ioannis.visvikis.issuetracker.demo.repositories;

import ioannis.visvikis.issuetracker.demo.entities.Issue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IssuesRepository extends CrudRepository<Issue, Long> {


}
