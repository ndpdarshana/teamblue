package uow.itpm.teamblue.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uow.itpm.teamblue.model.PlagiarismApiUser;
import uow.itpm.teamblue.model.PlagiarismCheck;
import uow.itpm.teamblue.module.plagiarismapi.PlagiarismCheckerApi;

@Service
public class PlagiarismCheckerService {
    @Autowired
    private PlagiarismCheckerApi plagiarismCheckerApi;

    public void loginToApi(){
        PlagiarismApiUser plagiarismApiUser = plagiarismCheckerApi.loginToApi();
        System.out.println(plagiarismApiUser.getMessage());
        System.out.println(plagiarismApiUser.getAccessToken());
    }

    public PlagiarismCheck checkText(PlagiarismCheck plagiarismCheck){
        return plagiarismCheckerApi.checkByText(plagiarismCheck);
    }

    public PlagiarismCheck checkStatus(PlagiarismCheck plagiarismCheck){
        return plagiarismCheckerApi.status(plagiarismCheck);
    }

    public PlagiarismCheck result(PlagiarismCheck plagiarismCheck){
        if(plagiarismCheck.getPlagiarismApiStatus() != null
                && plagiarismCheck.getPlagiarismApiStatus().getProgressPercents() <  100){
            plagiarismCheck = checkStatus(plagiarismCheck);
            plagiarismCheck = result(plagiarismCheck);
        }
        return plagiarismCheckerApi.result(plagiarismCheck);
    }

    public PlagiarismCheck getKey(PlagiarismCheck plagiarismCheck){
        return plagiarismCheckerApi.generateReadOnlyKey(plagiarismCheck);
    }
}
