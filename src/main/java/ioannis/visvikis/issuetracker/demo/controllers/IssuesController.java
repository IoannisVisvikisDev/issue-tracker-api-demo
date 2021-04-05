package ioannis.visvikis.issuetracker.demo.controllers;


import ioannis.visvikis.issuetracker.demo.Commons;
import ioannis.visvikis.issuetracker.demo.entities.Issue;
import ioannis.visvikis.issuetracker.demo.models.requests.DeleteIssueRequest;
import ioannis.visvikis.issuetracker.demo.models.requests.RaiseIssueRequest;
import ioannis.visvikis.issuetracker.demo.models.requests.UpdateIssueRequest;
import ioannis.visvikis.issuetracker.demo.services.FetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssuesController {


    @Autowired
    private FetchService fetchService;

    @GetMapping
    public List<Issue> getAllIssues(){
        return fetchService.getAllIssues();
    }


    @GetMapping("/{issueId}")
    public Issue getIssue(@PathVariable String issueId) {
        return fetchService.getIssueById(Long.parseLong(issueId));
    }


    @PostMapping
    public Issue raiseIssue(@Valid @RequestBody RaiseIssueRequest raiseIssueRequest, HttpServletRequest servletRequest){
        String callingUserUsername = servletRequest.getAttribute(Commons.USERNAME_KEY.toString()).toString();
        return fetchService.addIssue(raiseIssueRequest, callingUserUsername);
    }


    @PutMapping
    public Issue updateIssue(@Valid @RequestBody UpdateIssueRequest updateIssueRequest, HttpServletRequest servletRequest){
        String callingUserUsername = servletRequest.getAttribute(Commons.USERNAME_KEY.toString()).toString();
        return fetchService.updateIssue(updateIssueRequest, callingUserUsername);
    }


    @DeleteMapping
    public void deleteIssue(@Valid @RequestBody DeleteIssueRequest deleteIssueRequest){
        fetchService.deleteIssue(deleteIssueRequest.getIssueId());
    }

}
