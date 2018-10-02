package uow.itpm.teamblue.module.plagiarismapi;

import uow.itpm.teamblue.model.PlagiarismApiUser;
import uow.itpm.teamblue.model.PlagiarismCheck;

public interface PlagiarismCheckerApi {
    public PlagiarismApiUser loginToApi();
    public PlagiarismCheck checkByText(PlagiarismCheck plagiarismCheck);
    public PlagiarismCheck status(PlagiarismCheck plagiarismCheck);
    public PlagiarismCheck result(PlagiarismCheck plagiarismCheck);
    public PlagiarismCheck generateReadOnlyKey(PlagiarismCheck plagiarismCheck);
}
