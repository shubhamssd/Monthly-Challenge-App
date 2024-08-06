package com.challengeApp.ChallengeApp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//Monthly Challenge Application


import java.util.List;

@RestController
@RequestMapping("/challenges")
@CrossOrigin(origins="http://localhost:3000")
public class ChallengeController {

    private ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping
   public ResponseEntity<List<Challenge>> getAllChallenges(){

       return new ResponseEntity<>(challengeService.getAllChallenges(), HttpStatus.OK);
   }

   @PostMapping
    public ResponseEntity<String> addChallenge(@RequestBody Challenge challenge){   /// What is request body - whenever we sent a requst to post maping the post nmaping contain some information is known as @RequestBody
       boolean isChallengeAdded=challengeService.addChallenge(challenge);
       if(isChallengeAdded)
           return new ResponseEntity<>( "Challenges Added Successfully",HttpStatus.OK);
       else
           return new ResponseEntity<>("Challenges Not Added",HttpStatus.NOT_FOUND);
   }

   @GetMapping("/{month}")
    public ResponseEntity<Challenge> getChallenge(@PathVariable String month){
        Challenge challenge = challengeService.getChallenge(month);
        if(challenge != null)
            return new ResponseEntity<>(challenge, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

   }

   @PutMapping("/{id}")
    public ResponseEntity<String> updateChallenge(@PathVariable Long id, @RequestBody Challenge updatedChallenge){
        boolean isChallengeUpdated = challengeService.updatedChallenge(id,updatedChallenge);
       if(isChallengeUpdated)
           return new ResponseEntity<>( "Challenge Updated Successfully",HttpStatus.OK);
       else
           return new ResponseEntity<>("Challenge Not Updated ",HttpStatus.NOT_FOUND);
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChallenge(@PathVariable Long id){
        boolean isChallengeDeleted = challengeService.deleteChallenge(id);
        if(isChallengeDeleted){
            return new ResponseEntity<>("Challenge Deleted Successfully",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Challenge Not Deleted ", HttpStatus.NOT_FOUND);
        }
   }



}
