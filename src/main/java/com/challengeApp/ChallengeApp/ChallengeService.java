package com.challengeApp.ChallengeApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {
//    private final List<Challenge> challenges = new ArrayList<>();
    private Long nextId = 1L;

    @Autowired
    private ChallengeRepository challengeRepository;


    public ChallengeService(){

    }

    public List<Challenge> getAllChallenges(){
        return challengeRepository.findAll();
    }

    public boolean addChallenge( Challenge challenge){   /// What is request body - whenever we sent a request to post mapping the post mapping contain some information is known as @RequestBody
        if(challenge != null){
            challenge.setId(nextId++);
            challengeRepository.save(challenge);
            return true;
        }else {
            return false;
        }

    }
    public Challenge getChallenge(String month) {
        Optional<Challenge> challenge=challengeRepository.findByMonthIgnoreCase(month);
        return challenge.orElse(null);
    }

    public boolean updatedChallenge(Long id, Challenge updatedChallenge) {
//        for(Challenge challenge:challenges){
//            if(challenge.getId().equals(id)){
//                challenge.setMonth(updatedChallenge.getMonth());
//                challenge.setDescription(updatedChallenge.getDescription());
//                return true ;
//            }
//        }
//        return false;

        Optional<Challenge> challenge = challengeRepository.findById(id);  //first we find id
        if(challenge.isPresent()){                                         //then we check id is present
            Challenge challengeToUpdate =challenge.get();
            challengeToUpdate.setMonth(updatedChallenge.getMonth());
            challengeToUpdate.setDescription(updatedChallenge.getDescription());
            challengeRepository.save(challengeToUpdate);
            return true;
        }
        return false;

    }

    public boolean deleteChallenge(Long id) {
//        return challenges.removeIf(challenge -> challenge.getId().equals(id));
        Optional<Challenge> challenge = challengeRepository.findById(id);
        if(challenge.isPresent()){
            challengeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
