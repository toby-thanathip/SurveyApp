package nimbl3.surveyapp.db.repository

import nimbl3.surveyapp.service.SurveyApiService

object RepositoryProvider {
    fun provideRepository(): Repository {
        return Repository(SurveyApiService.create())
    }
}