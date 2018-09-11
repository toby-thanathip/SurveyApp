package nimbl3.surveyapp.service

import nimbl3.surveyapp.model.Repository

object RepositoryProvider {
    fun provideRepository(): Repository {
        return Repository(SurveyApiService.create())
    }
}