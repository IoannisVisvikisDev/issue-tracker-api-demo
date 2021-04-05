package ioannis.visvikis.issuetracker.demo.services;

import ioannis.visvikis.issuetracker.demo.entities.Issue;
import ioannis.visvikis.issuetracker.demo.entities.User;
import ioannis.visvikis.issuetracker.demo.models.requests.RaiseIssueRequest;
import ioannis.visvikis.issuetracker.demo.models.requests.UpdateIssueRequest;
import ioannis.visvikis.issuetracker.demo.repositories.IssuesRepository;
import ioannis.visvikis.issuetracker.demo.repositories.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
public class FetchService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private IssuesRepository issuesRepository;



    public User getUserById(Long userId){
        return usersRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
    }

    public User getUserByUsername(String userName){
        Optional<User> userFoundOptional = Optional.empty();
        for (User user : usersRepository.findAll()) {
            if(!user.getUsername().equals(userName)) continue;
            userFoundOptional = Optional.of(user);
            break;
        }
        return userFoundOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
    }


    public List<User> getAllUsers(){
        List<User> allUsers = new LinkedList<>();
        usersRepository.findAll().forEach(user -> {
            allUsers.add(user);
        });
        return allUsers;
    }

    public void addUser(User newUser){
        usersRepository.save(newUser);
    }


    public Issue getIssueById(Long issueId){
        return issuesRepository.findById(issueId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue Not Found"));
    }

    public List<Issue> getAllIssues(){
        List<Issue> allIssues = new LinkedList<>();
        issuesRepository.findAll().forEach(issue -> {
            allIssues.add(issue);
        });
        return allIssues;
    }

    public Issue addIssue(RaiseIssueRequest raiseIssueRequest, String raisingUserUsername){
        User openingUser = getUserByUsername(raisingUserUsername);
        User assignee = getUserByUsername(raiseIssueRequest.getAssigneeUsername());
        Issue issueToStore = new Issue();
        issueToStore.setTitle(raiseIssueRequest.getTitle());
        issueToStore.setDescription(raiseIssueRequest.getDescription());
        issueToStore.setOpeningUser(openingUser);
        issueToStore.setAssignedUser(assignee);
        issueToStore.setEditorUser(openingUser);
        issueToStore.setDateCreated(new Timestamp(System.currentTimeMillis()));
        issueToStore.setDateLastEdited(new Timestamp(System.currentTimeMillis()));
        openingUser.getCreatedIssuesList().add(issueToStore);
        assignee.getAssignedIssuesList().add(issueToStore);
        return issuesRepository.save(issueToStore);
    }

    public Issue updateIssue(UpdateIssueRequest updateIssueRequest, String callingUserUsername){
        User updatingUser = getUserByUsername(callingUserUsername);
        Issue issueToUpdate = getIssueById(updateIssueRequest.getIssueId());
        String assigneeUsername = updateIssueRequest.getAssigneeUsername();
        if(assigneeUsername != null) {
            User newAssignee = getUserByUsername(updateIssueRequest.getAssigneeUsername());
            issueToUpdate.setAssignedUser(newAssignee);
            newAssignee.getAssignedIssuesList().add(issueToUpdate);
        }
        String newTitle = updateIssueRequest.getTitle();
        if(newTitle != null) {
            issueToUpdate.setTitle(newTitle);
        }
        String newDiscription = updateIssueRequest.getDescription();
        if(newDiscription != null){
            issueToUpdate.setDescription(newDiscription);
        }
        issueToUpdate.setEditorUser(updatingUser);
        issueToUpdate.setDateLastEdited(new Timestamp(System.currentTimeMillis()));
        return issuesRepository.save(issueToUpdate);
    }

    public void deleteIssue(Long issueId){
        Issue toDelete = getIssueById(issueId);
        User creator = toDelete.getOpeningUser();
        User assignee = toDelete.getAssignedUser();
        creator.getCreatedIssuesList().remove(toDelete);
        assignee.getAssignedIssuesList().remove(toDelete);
        issuesRepository.delete(toDelete);
    }


    //hash passwords for initial users provided in data.sql
    @PostConstruct
    private void hashExistingPasswords(){
        usersRepository.findAll().forEach(userEntry -> {
            userEntry.setPassword(BCrypt.hashpw(userEntry.getPassword(), BCrypt.gensalt(10)));
            usersRepository.save(userEntry);
        });
    }

}
