package uow.itpm.teamblue.module.plagiarismapi;

import uow.itpm.teamblue.model.PlagiarismApiResult;
import uow.itpm.teamblue.model.PlagiarismCheck;

public interface PlagiarismCheckerListner {
    public PlagiarismApiResult plagiarismJobResponseHandler(PlagiarismCheck plagiarismCheck);
}
