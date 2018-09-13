package nimbl3.surveyapp.service.repository

import nimbl3.surveyapp.service.SurveyApiService

object RepositoryProvider {
    fun provideRepository(): Repository {
        return Repository(SurveyApiService.create())
    }
}