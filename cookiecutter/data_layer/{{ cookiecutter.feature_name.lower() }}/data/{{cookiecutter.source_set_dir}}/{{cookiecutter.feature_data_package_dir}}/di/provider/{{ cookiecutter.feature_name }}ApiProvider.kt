package {{ cookiecutter.feature_data_package }}.di.provider

import retrofit2.Retrofit
import ru.kode.mileonair.core.data.network.AUTHORIZED_RETROFIT
import {{ cookiecutter.feature_data_package }}.network.{{ cookiecutter.feature_name }}Api
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Provider

internal class {{ cookiecutter.feature_name }}ApiProvider @Inject constructor(
  @Named(AUTHORIZED_RETROFIT)
  private val retrofit: Retrofit,
) : Provider<{{ cookiecutter.feature_name }}Api> {
  override fun get(): {{ cookiecutter.feature_name }}Api {
    return retrofit.create({{ cookiecutter.feature_name }}Api::class.java)
  }
}
