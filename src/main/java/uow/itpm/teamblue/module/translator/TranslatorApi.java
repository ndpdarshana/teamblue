package uow.itpm.teamblue.module.translator;

import uow.itpm.teamblue.model.Translate;
import uow.itpm.teamblue.model.TranslatorResponse;

public interface TranslatorApi {
    public TranslatorResponse getLanguage(Translate translate);
    public TranslatorResponse getTranslated(Translate translate);
}
