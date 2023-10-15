package {{ cookiecutter.feature_data_package }}.di

import {{ cookiecutter.feature_data_package }}.{{ cookiecutter.feature_name }}RepositoryImpl
import {{ cookiecutter.feature_data_package }}.di.provider.{{ cookiecutter.feature_name }}ApiProvider
import {{ cookiecutter.feature_data_package }}.network.{{ cookiecutter.feature_name }}Api
import {{ cookiecutter.feature_domain_package }}.{{ cookiecutter.feature_name }}Repository
import toothpick.config.Module

class {{ cookiecutter.feature_name }}DataModule : Module() {
  init {
    bind({{ cookiecutter.feature_name }}Repository::class.java)
      .to({{ cookiecutter.feature_name }}RepositoryImpl::class.java)
      .singletonInScope()

    bind({{ cookiecutter.feature_name }}Api::class.java)
      .toProvider({{ cookiecutter.feature_name }}ApiProvider::class.java)
      .providesSingletonInScope()
  }
}
