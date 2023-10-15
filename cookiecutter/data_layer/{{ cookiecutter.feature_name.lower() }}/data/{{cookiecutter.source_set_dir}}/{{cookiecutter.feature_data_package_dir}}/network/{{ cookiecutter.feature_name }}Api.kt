package {{ cookiecutter.feature_data_package }}.network

import retrofit2.http.GET
import retrofit2.http.Url

internal interface {{ cookiecutter.feature_name }}Api {
  @GET
  suspend fun exampleRequest(@Url url: String)
}
