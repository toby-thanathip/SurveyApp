package nimbl3.surveyapp.service

object RepositoryProvider {
    fun provideRepository(): Repository {
        return Repository(SurveyApiService.create())
    }
}